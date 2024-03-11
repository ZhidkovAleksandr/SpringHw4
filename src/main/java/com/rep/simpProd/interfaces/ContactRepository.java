package com.rep.simpProd.interfaces;

import com.rep.simpProd.entity.Contact;
import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.entity.DockReceipt;
import com.rep.simpProd.entity.PaymentOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT cont FROM Contact cont WHERE cont.idContr = ?1")
    Contact findByIdContr(Contrepartie idContr);

}
