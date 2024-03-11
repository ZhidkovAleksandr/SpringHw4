package com.rep.simpProd.helpers;

import com.rep.simpProd.entity.Contrepartie;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactInfo {

    private long id;
    @NotBlank(message="Telephone est requis")
    @Size(max=10)
    private String tel;
    @NotBlank(message="le code du pays est requis")
    @Size(max=4)
    private String countryCode;
    @NotBlank(message="le code postal est requis")
    @Size(max=6)
    private String postCode;
    @NotBlank(message="l'adresse est requise")
    @Size(max=200)
    private String fullAddress;
    @Email(message ="e-mail non valide")
    private String email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contrepartie getIdContr() {
        return idContr;
    }

    public void setIdContr(Contrepartie idContr) {
        this.idContr = idContr;
    }
}
