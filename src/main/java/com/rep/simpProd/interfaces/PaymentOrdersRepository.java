package com.rep.simpProd.interfaces;

import com.rep.simpProd.entity.PaymentOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentOrdersRepository extends JpaRepository<PaymentOrders, Long> {

    public List<PaymentOrders> findByPayedTrue();

}
