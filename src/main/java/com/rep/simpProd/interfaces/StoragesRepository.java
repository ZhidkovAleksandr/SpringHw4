package com.rep.simpProd.interfaces;

import com.rep.simpProd.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoragesRepository extends JpaRepository<Storage, Long> {
}
