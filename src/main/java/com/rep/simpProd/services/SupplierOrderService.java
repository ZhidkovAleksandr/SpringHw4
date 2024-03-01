package com.rep.simpProd.services;

import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.entity.PaymentOrders;
import com.rep.simpProd.entity.Product;
import com.rep.simpProd.entity.SupplierOrder;
import com.rep.simpProd.interfaces.SupplierOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierOrderService {
    private final SupplierOrderRepository supplierOrderRepository;

    @Autowired
    public SupplierOrderService(SupplierOrderRepository supplierOrderRepository){
        this.supplierOrderRepository = supplierOrderRepository;
    }

    public SupplierOrder createSupplierOrder(SupplierOrder supplierOrder){
        return supplierOrderRepository.save(supplierOrder);
    }

    public List<SupplierOrder> getAllSupplerOrders() {
        return supplierOrderRepository.findAll();
    }

    public Optional<SupplierOrder> getOrderById(Long id) {
        return supplierOrderRepository.findById(id);
    }

    public void deleteSupplierOrder(Long id) {
        supplierOrderRepository.deleteById(id);
    }

    public void updateSupOrder(Long id, SupplierOrder updatedOrder) {
        Optional<SupplierOrder> existingSupOrder = supplierOrderRepository.findById(id);

        if (existingSupOrder.isPresent()) {
            SupplierOrder existingOrder = existingSupOrder.get();
            existingOrder.setPayed(true);
            supplierOrderRepository.save(existingOrder);
        } else {


        }
    }

}

