package com.rep.simpProd.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Payment_Orders")
public class PaymentOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double sum;
    @Column(name = "bic_payer")
    private String bicPayer;
    @Column(name = "iban_payer")
    private String ibanPayer;
    @Column(name = "bic_reciever")
    private String bicReciever;
    @Column(name = "iban_reciever")
    private String ibanReciever;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_payment")
    private Date datePayment;
    private String titulair;
    private String recipient;
    private String purpose;

    private boolean payed;
    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private SupplierOrder idSupplier;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
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

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
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

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public SupplierOrder getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(SupplierOrder idSupplier) {
        this.idSupplier = idSupplier;
    }
}
