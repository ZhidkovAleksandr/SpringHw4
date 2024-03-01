package com.rep.simpProd.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Supplier_Products")
public class SupplierProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;

    @Column(name = "in_order")
    private boolean inOrder;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private SupplierOrder supplierOrder;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isInOrder() {
        return inOrder;
    }

    public void setInOrder(boolean inOrder) {
        this.inOrder = inOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SupplierOrder getSupplierOrder() {
        return supplierOrder;
    }

    public void setSupplierOrder(SupplierOrder supplierOrder) {
        this.supplierOrder = supplierOrder;
    }
}
