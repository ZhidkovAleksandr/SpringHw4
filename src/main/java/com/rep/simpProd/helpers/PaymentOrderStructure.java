package com.rep.simpProd.helpers;

import com.rep.simpProd.entity.PaymentOrders;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentOrderStructure {

    private String id;
    private String name;
    private String sum;
    private String bicPayer;
    private String ibanPayer;
    private String bicReciever;
    private String ibanReciever;

    private String datePayment;
    private String titulair;

    private String recipient;
    private String purpose;

    public PaymentOrderStructure(PaymentOrders paymentOrder){

        this.id = Long.toString(paymentOrder.getId());
        this.name = paymentOrder.getName();
        this.sum = Double.toString(paymentOrder.getSum());
        if(paymentOrder.getDatePayment() == null){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            this.datePayment = formatter.format(new Date());
        }else{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            this.datePayment = formatter.format(paymentOrder.getDatePayment());
        }

        this.titulair = paymentOrder.getTitulair();
        this.bicPayer = paymentOrder.getBicPayer();
        this.ibanPayer = paymentOrder.getIbanPayer();
        this.bicReciever = paymentOrder.getBicReciever();
        this.ibanReciever = paymentOrder.getIbanReciever();
        this.recipient = paymentOrder.getRecipient();
        this.purpose = paymentOrder.getPurpose();


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getBicPayer() {
        return bicPayer;
    }

    public void setBicPayer(String bicPayer) {
        this.bicPayer = bicPayer;
    }

    public String getIbanPayer() {
        return ibanPayer;
    }

    public void setIbanPayer(String ibanPayer) {
        this.ibanPayer = ibanPayer;
    }

    public String getBicReciever() {
        return bicReciever;
    }

    public void setBicReciever(String bicReciever) {
        this.bicReciever = bicReciever;
    }

    public String getIbanReciever() {
        return ibanReciever;
    }

    public void setIbanReciever(String ibanReciever) {
        this.ibanReciever = ibanReciever;
    }

    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }

    public String getTitulair() {
        return titulair;
    }

    public void setTitulair(String titulair) {
        this.titulair = titulair;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
