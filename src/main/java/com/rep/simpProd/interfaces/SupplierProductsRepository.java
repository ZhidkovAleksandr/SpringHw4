package com.rep.simpProd.interfaces;

import com.rep.simpProd.entity.SupplierProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierProductsRepository extends JpaRepository<SupplierProducts, Long> {
}
