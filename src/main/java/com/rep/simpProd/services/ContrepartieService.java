package com.rep.simpProd.services;

import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.entity.Product;
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

    public List<Contrepartie> getAllContres() {
        return contrepartieRepository.findAll();
    }

    public Optional<Contrepartie> getContrById(Long id) {
        return contrepartieRepository.findById(id);
    }

    public Contrepartie updateContrepartie(Long id, Contrepartie updatedContr) {
        Optional<Contrepartie> existingContrOptional = contrepartieRepository.findById(id);

        if (existingContrOptional.isPresent()) {
            Contrepartie existingContr = existingContrOptional.get();
            existingContr.setName(updatedContr.getName());
            existingContr.setSiren(updatedContr.getSiren());
            existingContr.setTypePartenaire(updatedContr.getTypePartenaire());
            return contrepartieRepository.save(existingContr);
        } else {

            return null;
        }
    }

    public void deleteContr(Long id) {
        contrepartieRepository.deleteById(id);
    }

}
