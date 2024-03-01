package com.rep.simpProd.entity;

import com.rep.simpProd.enums.StorageTypes;
import jakarta.persistence.*;

@Entity
@Table(name = "Storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private StorageTypes storageType;
    private String note;
    private boolean notUse;

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

    public StorageTypes getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageTypes storageType) {
        this.storageType = storageType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isNotUse() {
        return notUse;
    }

    public void setNotUse(boolean notUse) {
        this.notUse = notUse;
    }
}
