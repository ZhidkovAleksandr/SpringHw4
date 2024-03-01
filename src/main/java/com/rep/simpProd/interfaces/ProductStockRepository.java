package com.rep.simpProd.interfaces;

import com.rep.simpProd.entity.ProductsStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductsStock, Long> {
}
