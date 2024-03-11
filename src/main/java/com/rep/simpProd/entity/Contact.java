package com.rep.simpProd.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tel;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "id_contr")
    private Contrepartie idContr;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Contrepartie getIdContr() {
        return idContr;
    }

    public void setIdContr(Contrepartie idContr) {
        this.idContr = idContr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
