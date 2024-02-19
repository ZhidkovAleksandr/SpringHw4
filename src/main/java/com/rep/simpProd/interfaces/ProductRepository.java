package com.rep.simpProd.interfaces;

import com.rep.simpProd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.articul = :nArticul")
    void deleteByArticul(String nArticul);

    List<Product> findByIdIn(List<Long> ids);
}
