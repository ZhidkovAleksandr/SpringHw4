package com.rep.simpProd.interfaces;

import com.rep.simpProd.entity.DockReceipt;
import com.rep.simpProd.entity.PaymentOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DockReceiptRepository extends JpaRepository<DockReceipt, Long> {

    @Query("SELECT p FROM DockReceipt p WHERE p.idPayment = ?1")
    DockReceipt findByIdPayment(PaymentOrders idPayment);

}
