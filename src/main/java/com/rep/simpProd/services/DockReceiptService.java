package com.rep.simpProd.services;

import com.rep.simpProd.entity.DockReceipt;
import com.rep.simpProd.entity.PaymentOrders;
import com.rep.simpProd.entity.Storage;
import com.rep.simpProd.interfaces.DockReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DockReceiptService {
    private final DockReceiptRepository dockReceiptRepository;

    @Autowired
    public DockReceiptService(DockReceiptRepository dockReceiptRepository){
        this.dockReceiptRepository = dockReceiptRepository;
    }

    public DockReceipt createIncomeStorage(DockReceipt dockReceipt) {
        return dockReceiptRepository.save(dockReceipt);
    }

    public DockReceipt findByIdPayment(PaymentOrders idPayment){
        return dockReceiptRepository.findByIdPayment(idPayment);
    }

}
