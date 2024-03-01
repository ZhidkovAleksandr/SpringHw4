package com.rep.simpProd.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    private Boolean isDone;
    private Double sum;

    @ManyToOne
    private Contrepartie contrepartie;

    @OneToMany(mappedBy = "contract")
    private List<SupplierOrder> supplierOrders;

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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Contrepartie getContrepartie() {
        return contrepartie;
    }

    public List<SupplierOrder> getSupplierOrders() {
        return supplierOrders;
    }

    public void setSupplierOrders(List<SupplierOrder> supplierOrders) {
        this.supplierOrders = supplierOrders;
    }

    public void setContrepartie(Contrepartie contrepartie) {
        this.contrepartie = contrepartie;
    }
}
