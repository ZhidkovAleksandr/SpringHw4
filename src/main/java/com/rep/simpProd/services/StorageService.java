package com.rep.simpProd.services;

import com.rep.simpProd.entity.Product;
import com.rep.simpProd.entity.Storage;
import com.rep.simpProd.interfaces.StoragesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageService {
    private final StoragesRepository storagesRepository;

    @Autowired
    public StorageService(StoragesRepository storagesRepository){
        this.storagesRepository = storagesRepository;
    }

    public List<Storage> getAllStorages() {
        return storagesRepository.findAll();
    }

    public Optional<Storage> getStorageById(Long id) {
        return storagesRepository.findById(id);
    }

    public Storage createStorage(Storage storage) {
        return storagesRepository.save(storage);
    }

    public Storage updateStorage(Long id, Storage updatedStorage) {
        Optional<Storage> existingStorageOptional = storagesRepository.findById(id);

        if (existingStorageOptional.isPresent()) {
            Storage existingStorage = existingStorageOptional.get();
            existingStorage.setName(updatedStorage.getName());
            existingStorage.setNotUse(updatedStorage.isNotUse());
            existingStorage.setStorageType(updatedStorage.getStorageType());
            existingStorage.setNote(updatedStorage.getNote());
            return storagesRepository.save(existingStorage);
        } else {

            return null;
        }
    }

    public void deleteStorage(Long id) {
        storagesRepository.deleteById(id);
    }

}
