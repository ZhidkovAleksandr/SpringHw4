package com.rep.simpProd.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Supplier_Order")
public class SupplierOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "sum_order")
    private double sumOrder;

    private boolean payed;

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @OneToMany(mappedBy = "supplierOrder", cascade = CascadeType.ALL)
    private List<SupplierProducts> supplierProducts;

    @ManyToOne
    private Contract contract;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreation;

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<SupplierProducts> getSupplierProducts() {
        return supplierProducts;
    }

    public void setSupplierProducts(List<SupplierProducts> supplierProducts) {
        this.supplierProducts = supplierProducts;
    }

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

    public double getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(double sumOrder) {
        this.sumOrder = sumOrder;
    }
}
