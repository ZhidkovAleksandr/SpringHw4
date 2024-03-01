package com.rep.simpProd.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Products_Stock")
public class ProductsStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_storage")
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "id_receipt")
    private DockReceipt dockReceipt;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public DockReceipt getDockReceipt() {
        return dockReceipt;
    }

    public void setDockReceipt(DockReceipt dockReceipt) {
        this.dockReceipt = dockReceipt;
    }
}
