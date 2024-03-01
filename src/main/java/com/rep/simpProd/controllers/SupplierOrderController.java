package com.rep.simpProd.controllers;

import com.rep.simpProd.entity.*;
import com.rep.simpProd.services.ContractService;
import com.rep.simpProd.services.ProductService;
import com.rep.simpProd.services.SupplierOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class SupplierOrderController {

    private final ContractService contractService;
    private final ProductService productService;
    private final SupplierOrderService supplierOrderService;

    @Autowired
    public SupplierOrderController(ContractService contractService, ProductService productService, SupplierOrderService supplierOrderService){
        this.contractService = contractService;
        this.productService = productService;
        this.supplierOrderService = supplierOrderService;
    }

    @GetMapping("/suporders/{id}")
    public String getSupOrderById(@PathVariable Long id, Model model){
        SupplierOrder supplierOrder = supplierOrderService.getOrderById(id).orElse(null);
        model.addAttribute("supplierOrder", supplierOrder);
        return "supOrder/detailsSupOrder";
    }

    @GetMapping("/suporders/delete/{id}")
    public String deleteContract(@PathVariable Long id) {
        supplierOrderService.deleteSupplierOrder(id);
        return "redirect:/supOrders";
    }

    @GetMapping("/supOrders")
    public String getAllSupOrders(Model model) {
        List<SupplierOrder> sOrders = supplierOrderService.getAllSupplerOrders();
        model.addAttribute("supOrders", sOrders);
        return "supOrder/supOrdersList";
    }

    @GetMapping("/newSupOrder")
    public String createNewContract(Model model){
        List<Product> products = productService.getAllProducts();
        List<SupplierProducts> supplierProducts = prepareProductsForOrder(products);
        List<Contract> contracts = contractService.getAllContracts();
        SupplierOrder supplierOrder = new SupplierOrder();
        supplierOrder.setSumOrder(0);
        supplierOrder.setDateCreation(new Date());
        supplierOrder.setSupplierProducts(supplierProducts);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("supplierOrder", supplierOrder);
        attributes.put("contracts", contracts);
        model.addAllAttributes(attributes);
        return "supOrder/orderCreation";
    }

    @GetMapping("/newVersOrd")
    public String createNewSupplierOrder(Model model){
        List<Product> products = productService.getAllProducts();
        List<SupplierProducts> supplierProducts = prepareProductsForOrder(products);
        List<Contract> contracts = contractService.getAllContracts();
        SupplierOrder supplierOrder = new SupplierOrder();
        supplierOrder.setSumOrder(0);
        supplierOrder.setDateCreation(new Date());
        supplierOrder.setSupplierProducts(supplierProducts);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("supplierOrder", supplierOrder);
        attributes.put("contracts", contracts);
        //attributes.put("supplierProducts", supplierProducts);
        model.addAllAttributes(attributes);
        return "supOrder/orderCreationN";
    }


    //public String prepareSupOrder(@Valid @ModelAttribute("supplierOrder") SupplierOrder supplierOrder, @ModelAttribute("supplierProducts") List<SupplierProducts> supplierProducts, BindingResult bindingResult, Model model){
    //public String prepareSupOrder(@Valid @ModelAttribute SupplierOrder supplierOrder, BindingResult bindingResult, Model model){
    @PostMapping("/prepareSupOrder")
    public String prepareSupOrder(@Valid @ModelAttribute SupplierOrder supplierOrder,
                                  BindingResult bindingResult,
                                  Model model) {
        boolean conditionToSave = false;
        takeMarkedProducts(supplierOrder);
        supplierOrder.setSumOrder(countSumForOrder(supplierOrder.getSupplierProducts()));
        if(supplierOrder.getSumOrder()>supplierOrder.getContract().getSum()){
            bindingResult.rejectValue("sumOrder", "error.code", "Le montant de la commande n'excède pas le montant du contrat");
            conditionToSave = true;
        }
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("supplierOrder", supplierOrder);
        attributes.put("conditionToSave", conditionToSave);
        attributes.put("contracts", supplierOrder.getContract());
        //attributes.put("supplierProducts", supplierProducts);
        model.addAllAttributes(attributes);
        return "supOrder/confSupOrder";
    }

    private void takeMarkedProducts(SupplierOrder supplierOrder){
        List<SupplierProducts> markedProducts = new ArrayList<>();
        List<SupplierProducts> allProducts = supplierOrder.getSupplierProducts();
        Collections.sort(allProducts, Comparator.comparing(SupplierProducts::isInOrder));
        for (SupplierProducts s:allProducts) {
            if(s.isInOrder()){
                markedProducts.add(s);
            }

        }
        supplierOrder.setSupplierProducts(markedProducts);
    }

//( @ModelAttribute YourFormObject formObject, BindingResult bindingResult)
    @PostMapping("/prepareOrder")
    public String prepareOrder(@Valid @ModelAttribute SupplierOrder supplierOrder, BindingResult bindingResult, Model model) {
//        String viewReturn = "";
//        if ("contractAction".equals(action)) {
//            supplierOrder.setSumOrder(supplierOrder.getContract().getSum());
//            model.addAttribute(supplierOrder);
//            viewReturn = "supOrder/orderCreation";
//        } else if ("saveOrder".equals(action)) {
//            supplierOrderService.createSupplierOrder(supplierOrder);
//            viewReturn =  "redirect:/contreparties";
//        }
//        return viewReturn;

//        if (formObject.getSupplierOrder().getContract().getSum() <= 0) {
//            // Добавляем ошибку в объект BindingResult
//            bindingResult.rejectValue("supplierOrder.contract.sum", "error.code", "Значение должно быть положительным числом");
//        }

        boolean conditionToSave = false;
        setOrderForProducts(supplierOrder);
        supplierOrder.setSumOrder(countSumForOrder(supplierOrder.getSupplierProducts()));
        if(supplierOrder.getSumOrder()>supplierOrder.getContract().getSum()){
            bindingResult.rejectValue("sumOrder", "error.code", "Le montant de la commande n'excède pas le montant du contrat");
            conditionToSave = true;
        }
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("supplierOrder", supplierOrder);
        attributes.put("conditionToSave", conditionToSave);
        attributes.put("contracts", supplierOrder.getContract());
        model.addAllAttributes(attributes);
        //supplierOrderService.createSupplierOrder(supplierOrder);

            return "supOrder/confirmOrder";

    }

    private double countSumForOrder(List<SupplierProducts> supplierProductsList){
        double sumForOrder = 0;
        for (SupplierProducts s:supplierProductsList) {
            sumForOrder+=s.getProduct().getPrice()*s.getQuantity();
        }
        return sumForOrder;
    }

    @PostMapping("/saveSupOrder")
    public String submitOrder(@ModelAttribute("supplierOrder") SupplierOrder supplierOrder) {
//        String viewReturn = "";
//        if ("contractAction".equals(action)) {
//            supplierOrder.setSumOrder(supplierOrder.getContract().getSum());
//            model.addAttribute(supplierOrder);
//            viewReturn = "supOrder/orderCreation";
//        } else if ("saveOrder".equals(action)) {
//            supplierOrderService.createSupplierOrder(supplierOrder);
//            viewReturn =  "redirect:/contreparties";
//        }
//        return viewReturn;
        //supplierOrder.setSupplierProducts(supplierProducts);
        addOrderToProductsTable(supplierOrder);
        supplierOrderService.createSupplierOrder(supplierOrder);
        return "redirect:/supOrders";

    }

    private void setOrderForProducts(SupplierOrder supplierOrder){
        List<SupplierProducts> supplierProducts = supplierOrder.getSupplierProducts();
        List<Long> ids = supplierProducts.stream()
                .map(supplierProductsList -> supplierProductsList.getProduct().getId())
                .collect(Collectors.toList());
        List<Product> products = productService.findByIdIn(ids);
        List<SupplierProducts> generalSpList = new ArrayList<>();
        for (Product p:products) {
            for (SupplierProducts sp:supplierProducts) {
                if (p.getId().equals(sp.getProduct().getId())){
                    SupplierProducts nSp = new SupplierProducts();
                    nSp.setSupplierOrder(supplierOrder);
                    nSp.setProduct(p);
                    nSp.setInOrder(true);
                    nSp.setQuantity(sp.getQuantity());
                    generalSpList.add(nSp);
                    break;
                }
            }
        }
        supplierOrder.setSupplierProducts(generalSpList);

    }

    private void addOrderToProductsTable(SupplierOrder supplierOrder){
        for (SupplierProducts sp:supplierOrder.getSupplierProducts()) {
            //sp.setInOrder(true);
            sp.setSupplierOrder(supplierOrder);
        }
    }

//    @PostMapping
//    public String changeInfOrd(@ModelAttribute SupplierOrder supplierOrder, Model model){
//        supplierOrder.setSumOrder(supplierOrder.getContract().getSum());
//        model.addAttribute(supplierOrder);
//        return "supOrder/orderCreation";
//    }

    private List<SupplierProducts> prepareProductsForOrder(List<Product> products){

        List<SupplierProducts> supplierProducts = new ArrayList<>();
        for (Product p:products) {
            SupplierProducts sp = new SupplierProducts();
            sp.setInOrder(false);
            sp.setQuantity(0);
            sp.setProduct(p);
            supplierProducts.add(sp);
        }
        return supplierProducts;

    }

}
