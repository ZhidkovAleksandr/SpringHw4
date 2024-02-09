package com.rep.simpProd.services;

import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.interfaces.ContrepartieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ContrepartieService {
    private final ContrepartieRepository contrepartieRepository;

    @Autowired
    public ContrepartieService(ContrepartieRepository contrepartieRepository){
        this.contrepartieRepository = contrepartieRepository;
    }

    public Contrepartie createContrePartie(Contrepartie contrepartie){
        return contrepartieRepository.save(contrepartie);
    }

}
