package com.rep.simpProd.interfaces;

import com.rep.simpProd.entity.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Long> {
}
