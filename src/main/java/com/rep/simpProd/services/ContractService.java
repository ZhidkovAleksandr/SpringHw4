package com.rep.simpProd.services;

import com.rep.simpProd.entity.Contract;
import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.interfaces.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository){
        this.contractRepository = contractRepository;
    }

    public Contract createContract(Contract contract){
        return contractRepository.save(contract);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Optional<Contract> getContractById(Long id) {
        return contractRepository.findById(id);
    }

    public Contract updateContract (Long id, Contract updatedContract) {
        Optional<Contract> existingContractOptional = contractRepository.findById(id);

        if (existingContractOptional.isPresent()) {
            Contract existingContract = existingContractOptional.get();
            existingContract.setName(updatedContract.getName());
            existingContract.setIsDone(updatedContract.getIsDone());
            return contractRepository.save(existingContract);
        } else {

            return null;
        }
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }



}
