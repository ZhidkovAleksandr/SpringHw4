package com.rep.simpProd.interfaces;

import com.rep.simpProd.entity.Contract;
import com.rep.simpProd.entity.Contrepartie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
