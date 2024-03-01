package com.rep.simpProd.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rep.simpProd.entity.Contract;
import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.entity.PaymentOrders;
import com.rep.simpProd.entity.SupplierOrder;
import com.rep.simpProd.helpers.PaymentOrderStructure;
import com.rep.simpProd.services.EmailService;
import com.rep.simpProd.services.PaymentOrdersService;
import com.rep.simpProd.services.SupplierOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class PaymentOrderController {
    private final PaymentOrdersService paymentOrdersService;
    private final SupplierOrderService supplierOrderService;
    private final EmailService emailService;

    @Autowired
    public PaymentOrderController(PaymentOrdersService paymentOrdersService, SupplierOrderService supplierOrderService, EmailService emailService){
        this.paymentOrdersService = paymentOrdersService;
        this.supplierOrderService = supplierOrderService;
        this.emailService = emailService;
    }

    @GetMapping("/paymentsOrders")
    public String getAllPayOrders(Model model) {
        List<PaymentOrders> paymentOrders = paymentOrdersService.getAllPaymentOrders();
        model.addAttribute("paymentOrders", paymentOrders);
        return "paymentsOrders/payOrdList";
    }

    @GetMapping("/newPaymentOrder")
    public String createNewContract(Model model){
        List<SupplierOrder> supplierOrders = supplierOrderService.getAllSupplerOrders();
        PaymentOrders paymentOrder = new PaymentOrders();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("paymentOrder", paymentOrder);
        attributes.put("supOrders", supplierOrders);
        model.addAllAttributes(attributes);
        return "paymentsOrders/creationPaymentOrder";
    }

    @PostMapping("/continueEditingPaymentOrd")
    public String preparePayOrder(@Valid @ModelAttribute("paymentOrder")  PaymentOrders paymentOrder, BindingResult bindingResult, Model model){
        boolean bicPayerIsCorret = checkBIC(paymentOrder.getBicPayer());
        boolean ibanPayerIsCorrect = checkIBAN(paymentOrder.getIbanPayer());
        boolean bicRecievierIsCorrect = checkBIC(paymentOrder.getBicReciever());
        boolean ibanRecivierIsCorrect = checkIBAN(paymentOrder.getIbanReciever());

//        boolean dataPayer = bicPayerIsCorret&&ibanPayerIsCorrect;
//        boolean dataRecivier = bicRecievierIsCorrect&&ibanRecivierIsCorrect;
//
//        boolean conditionToSave = true;
//
//        if(!(dataPayer&&dataRecivier)){
//            conditionToSave = false;
//        }

        if(!bicPayerIsCorret){
            bindingResult.rejectValue("bicPayer", "bicPayer.invalid", "BIC est incorrect");
        }
        if(!ibanPayerIsCorrect){
            bindingResult.rejectValue("ibanPayer", "ibanPayer.invalid", "IBAN est incorrect");
        }

        if(!bicRecievierIsCorrect){
            bindingResult.rejectValue("bicReciever", "bicReciever.invalid", "BIC est incorrect");
        }
        if(!ibanRecivierIsCorrect){
            bindingResult.rejectValue("ibanReciever", "ibanReciever.invalid", "IBAN est incorrect");
        }



        String purposePayment = "Paiement de l'ordre " + paymentOrder.getIdSupplier().getName();
        paymentOrder.setSum(paymentOrder.getIdSupplier().getSumOrder());
        paymentOrder.setPurpose(purposePayment);
        paymentOrder.setDatePayment(new Date());
        paymentOrder.setSum(paymentOrder.getIdSupplier().getSumOrder());
        paymentOrder.setRecipient(paymentOrder.getIdSupplier().getContract().getContrepartie().getName() + " " + paymentOrder.getIdSupplier().getContract().getContrepartie().getSiren());



        if (bindingResult.hasErrors()) {
            model.addAttribute("paymentOrder",paymentOrder);
            return "paymentsOrders/validationPaymentOrder";
        } else {
            if(paymentOrder.getDatePayment() == null){
               paymentOrder.setDatePayment(new Date());
            }
            model.addAttribute("paymentOrder",paymentOrder);
            return "paymentsOrders/maintPaiement";
        }

        //attributes.put("paymentOrder", paymentOrder);
        //attributes.put("nextStep", conditionToSave);
        //model.addAllAttributes(attributes);


    }

    @PostMapping("/savePaymentOrder")
    public String savePaymentOrder(@ModelAttribute PaymentOrders paymentOrder){
        paymentOrder.setDatePayment(new Date());
        paymentOrdersService.createPayment(paymentOrder);
        return "redirect:/paymentsOrders";
    }

    @GetMapping("/payOrd/edit/{id}")
    public String sendPaymentOrder(@PathVariable Long id, RedirectAttributes attributes) {

        Optional<PaymentOrders> paymentOrderOp = paymentOrdersService.getPaymentOrderById(id);
        if(paymentOrderOp.isPresent()){
            PaymentOrders paymentOrder = paymentOrderOp.get();
            paymentOrder.setPayed(true);
            PaymentOrderStructure paySer = new PaymentOrderStructure(paymentOrder);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String paymentMessage = gson.toJson(paySer);
            boolean send;
            try {
                emailService.sendMessageToOtherPartie("zhidkov.alex@yahoo.com",
                        paymentOrder.getName(), paymentMessage);
                send = true;
            }catch (Exception e){
                send = false;
            }
            if (send){
                paymentOrdersService.updatePaymentOrder(id, paymentOrder);
                supplierOrderService.updateSupOrder(paymentOrder.getIdSupplier().getId(), paymentOrder.getIdSupplier());
                return "redirect:/payOrd/{id}";
            }else{
                return "redirect:/paymentsOrders";
            }

        }else {
            return "redirect:/paymentsOrders";
        }
    }

    @GetMapping("/payOrd/{id}")
    public String getSupOrderById(@PathVariable Long id, Model model) {
        //SupplierOrder supplierOrder = supplierOrderService.getOrderById(id).orElse(null);
        PaymentOrders paymentOrder = paymentOrdersService.getPaymentOrderById(id).orElse(null);
        model.addAttribute("paymentOrder", paymentOrder);
        return "paymentsOrders/paymentDet";
    }


    private boolean checkIBAN(String iban) {

        Pattern p = Pattern.compile("^([A-Z]{2}[ \\-]?[0-9]{2})(?=(?:[ \\-]?[A-Z0-9]){9,30}$)((?:[ \\-]?[A-Z0-9]{3,5}){2,7})([ \\-]?[A-Z0-9]{1,3})?$");
        Matcher m = p.matcher(iban);
        return m.matches();

    }

    private boolean checkBIC(String bic) {


        Pattern p = Pattern.compile("[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}");
        Matcher m = p.matcher(bic);
        return m.matches();


    }


}
