package com.rep.simpProd.controllers;

import com.rep.simpProd.entity.Contract;
import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.entity.Storage;
import com.rep.simpProd.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class StorageController {
    private final StorageService storagesService;

    @Autowired
    public StorageController(StorageService storagesService){
        this.storagesService = storagesService;
    }


    @GetMapping("/allStorages")
    public String getAllStorages(Model model){
        List<Storage> storageList = storagesService.getAllStorages();
        model.addAttribute("storages", storageList);
        return "storages/storagesList";
    }

    @GetMapping("/store/{id}")
    public String getStorageById(@PathVariable Long id, Model model) {
        Optional<Storage> storageOp = storagesService.getStorageById(id);
        Storage storage = storageOp.get();
        model.addAttribute("storage", storage);
        return "storages/storageDet";
    }

    @GetMapping("/store/delete/{id}")
    public String deleteContract(@PathVariable Long id) {
        storagesService.deleteStorage(id);
        return "redirect:/allStorages";
    }

    @GetMapping("/createNewStorage")
    public String createNewContract(Model model){
        Storage storage = new Storage();
        model.addAttribute("storage", storage);
        return "storages/creationStorage";
    }

    @PostMapping("/saveStorage")
    public String submitStorage(@ModelAttribute Storage storage) {
        storagesService.createStorage(storage);
        return "redirect:/allStorages";
    }

    @GetMapping("/store/edit/{id}")
    public String editStorage(@PathVariable Long id, Model model) {
        Storage storage = storagesService.getStorageById(id).orElse(null);
        model.addAttribute("storage", storage);
        return "storages/editingStorage";
    }

    @PostMapping("/updateStorage")
    public String updateStorage(@ModelAttribute Storage storage) {
        storagesService.updateStorage(storage.getId(), storage);
        return "redirect:/allStorages";
    }

}
