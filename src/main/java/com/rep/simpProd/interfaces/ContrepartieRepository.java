package com.rep.simpProd.interfaces;

import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContrepartieRepository extends JpaRepository<Contrepartie, Long> {
}
