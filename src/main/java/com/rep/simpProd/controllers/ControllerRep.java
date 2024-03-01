package com.rep.simpProd.controllers;

import com.rep.simpProd.entity.Product;
import com.rep.simpProd.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControllerRep {

    private final ProductService productService;

    @Autowired
    public ControllerRep(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productsList";
    }

    @GetMapping("/prod/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).orElse(null);
        model.addAttribute("product", product);
        return "prodDet";
    }

    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "creationProd";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).orElse(null);
        model.addAttribute("product", product);
        return "creationProd";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("delByArt")
    public String deletByArticul(@RequestParam String nArticul){
        productService.deleteByArticul(nArticul);
        return "redirect:/products";
    }

}
