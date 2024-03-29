package com.rep.simpProd.entity;

import com.rep.simpProd.enums.TypePartenaire;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Contrepartie")
public class Contrepartie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String siren;
    @Enumerated(EnumType.STRING)
    private TypePartenaire typePartenaire;

    @OneToMany(mappedBy = "contrepartie")
    private List<Contract> contracts;

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
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

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public TypePartenaire getTypePartenaire() {
        return typePartenaire;
    }

    public void setTypePartenaire(TypePartenaire typePartenaire) {
        this.typePartenaire = typePartenaire;
    }
}
