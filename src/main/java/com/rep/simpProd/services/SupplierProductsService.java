package com.rep.simpProd.services;

import com.rep.simpProd.entity.SupplierOrder;
import com.rep.simpProd.entity.SupplierProducts;
import com.rep.simpProd.interfaces.SupplierProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierProductsService {
    private final SupplierProductsRepository supplierProductsRepository;


    @Autowired
    public SupplierProductsService(SupplierProductsRepository supplierProductsRepository){
        this.supplierProductsRepository = supplierProductsRepository;
    }

    public SupplierProducts createSupplierProducts(SupplierProducts supplierProducts){
        return supplierProductsRepository.save(supplierProducts);
    }

}
