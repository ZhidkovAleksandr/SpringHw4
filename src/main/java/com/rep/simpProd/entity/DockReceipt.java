package com.rep.simpProd.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Dock_Receipt")
public class DockReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double sum;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_income")
    private Date dateIncome;

    @OneToOne
    @JoinColumn(name = "id_payment")
    private PaymentOrders idPayment;

    @OneToMany(mappedBy = "dockReceipt", cascade = CascadeType.ALL)
    private List<ProductsStock> productsStocks;

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

    public Date getDateIncome() {
        return dateIncome;
    }

    public void setDateIncome(Date dateIncome) {
        this.dateIncome = dateIncome;
    }

    public PaymentOrders getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(PaymentOrders idPayment) {
        this.idPayment = idPayment;
    }

    public List<ProductsStock> getProductsStocks() {
        return productsStocks;
    }

    public void setProductsStocks(List<ProductsStock> productsStocks) {
        this.productsStocks = productsStocks;
    }
}
