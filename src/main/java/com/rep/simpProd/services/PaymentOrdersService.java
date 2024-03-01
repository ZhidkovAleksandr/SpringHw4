package com.rep.simpProd.services;

import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.entity.PaymentOrders;
import com.rep.simpProd.interfaces.PaymentOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentOrdersService {

    private final PaymentOrdersRepository paymentOrdersRepository;

    @Autowired
    public PaymentOrdersService(PaymentOrdersRepository paymentOrdersRepository){
        this.paymentOrdersRepository = paymentOrdersRepository;
    }

    public PaymentOrders createPayment(PaymentOrders paymentOrder){
        return paymentOrdersRepository.save(paymentOrder);
    }

    public List<PaymentOrders> getAllPaymentOrders() {
        return paymentOrdersRepository.findAll();
    }

    public Optional<PaymentOrders> getPaymentOrderById(Long id) {
        return paymentOrdersRepository.findById(id);
    }

    public void deletePaymentOrder(Long id) {
        paymentOrdersRepository.deleteById(id);
    }

    public PaymentOrders updatePaymentOrder(Long id, PaymentOrders updatedPayOrder) {
        Optional<PaymentOrders> existingPayOrderOp = paymentOrdersRepository.findById(id);

        if (existingPayOrderOp.isPresent()) {
            PaymentOrders existingPayOrder = existingPayOrderOp.get();
            existingPayOrder.setPayed(updatedPayOrder.isPayed());
            return paymentOrdersRepository.save(existingPayOrder);
        } else {

            return null;
        }
    }

    public List<PaymentOrders> findAllPayedOrder(){
        return paymentOrdersRepository.findByPayedTrue();
    }

}
