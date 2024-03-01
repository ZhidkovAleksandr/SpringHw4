package com.rep.simpProd.services;

import com.rep.simpProd.entity.ProductsStock;
import com.rep.simpProd.interfaces.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStockService {
    private final ProductStockRepository productStockRepository;

    @Autowired
    public ProductStockService(ProductStockRepository productStockRepository){
        this.productStockRepository = productStockRepository;
    }

    public List<ProductsStock> getAllProductsOnStocks(){
        return productStockRepository.findAll();
    }

}
