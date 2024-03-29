package com.rep.simpProd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    private String articul;

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean equals(Object otherObject ){
        if (this== otherObject)return true;

        if (otherObject==null) return false;

        if(getClass() !=otherObject.getClass())
            return false;
        Product other = (Product) otherObject;

        return articul.equals(other.articul)
                && name.equals(other.name)
                && price == price;

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, articul, price);
    }
}
