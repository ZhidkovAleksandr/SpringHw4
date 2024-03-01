package com.rep.simpProd.controllers;

import com.rep.simpProd.entity.*;
import com.rep.simpProd.services.DockReceiptService;
import com.rep.simpProd.services.PaymentOrdersService;
import com.rep.simpProd.services.ProductStockService;
import com.rep.simpProd.services.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class GoodsIncomingController {

    private final PaymentOrdersService paymentOrdersService;
    private final StorageService storageService;
    private final DockReceiptService dockReceiptService;
    private final ProductStockService productStockService;
    @Autowired
    public GoodsIncomingController(PaymentOrdersService paymentOrdersService,
                                   StorageService storageService,
                                   DockReceiptService dockReceiptService,
                                   ProductStockService productStockService){

        this.dockReceiptService = dockReceiptService;
        this.storageService = storageService;
        this.paymentOrdersService = paymentOrdersService;
        this.productStockService = productStockService;

    }

    @GetMapping("/incommingCreation")
    public String createNewIncomming(Model model){
        DockReceipt dockReceipt =  new DockReceipt();
        List<Storage> storages = storageService.getAllStorages();
        List<PaymentOrders> paymentOrders = paymentOrdersService.findAllPayedOrder();
        dockReceipt.setDateIncome(new Date());
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("dockReceipt", dockReceipt);
        attributes.put("idPayment", paymentOrders);
        attributes.put("storages", storages);
        model.addAllAttributes(attributes);
        return "incomProd/incomeProdFetap";
    }

    @PostMapping("/recSuiv")
    public String prepareIncommings(@ModelAttribute("dockReceipt") DockReceipt dockReceipt,
                                    @RequestParam("storageId") long storageId, Model model) {


        DockReceipt exDockRec = dockReceiptService.findByIdPayment(dockReceipt.getIdPayment());
        if(exDockRec!=null){
            String er = "La réception de marchandises par paiement " + dockReceipt.getIdPayment().getName() + " existe déjà";
            model.addAttribute("errorMessage", er);
            return "errTemps/errorPageInc";
        }

        Optional<Storage> storage = storageService.getStorageById(storageId);
        Storage existingStorage;
        if(storage.isPresent()){
            existingStorage = storage.get();
        }else{
            existingStorage = null;
        }

        List<SupplierProducts> supplierProducts = dockReceipt.getIdPayment().getIdSupplier().getSupplierProducts();
        
        List<ProductsStock> productsStocks = new ArrayList<>();

        for (SupplierProducts s:supplierProducts) {
            ProductsStock productsStock = new ProductsStock();
            productsStock.setProduct(s.getProduct());
            productsStock.setQuantity(s.getQuantity());
            productsStock.setStorage(existingStorage);
            productsStock.setDockReceipt(dockReceipt);
            productsStocks.add(productsStock);
        }

        dockReceipt.setProductsStocks(productsStocks);
        dockReceipt.setSum(dockReceipt.getIdPayment().getSum());
        dockReceipt.setDateIncome(new Date());
        model.addAttribute("dockReceipt", dockReceipt);
        
        return "incomProd/confIncome";
    }

    @PostMapping("/confSaveIncom")
    public String saveIncomming(@ModelAttribute("dockReceipt") DockReceipt dockReceipt){
        String st = "";
        for (ProductsStock p:dockReceipt.getProductsStocks()) {
            p.setDockReceipt(dockReceipt);
        }
        dockReceiptService.createIncomeStorage(dockReceipt);
        return "incomProd/mainIncomeProd";
    }


    @GetMapping("/incommingOperations")
    public String showMenuIncom(Model model){
        return "incomProd/mainIncomeProd";
    }

    @GetMapping("/showProductOnStock")
    public String getAllPrStock(Model model){
        List<ProductsStock> productsStockList = productStockService.getAllProductsOnStocks();
        model.addAttribute("onStocks", productsStockList);
        return "incomProd/prOnStock";
    }

}
