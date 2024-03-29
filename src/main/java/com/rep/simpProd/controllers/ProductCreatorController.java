package com.rep.simpProd.controllers;

import com.rep.simpProd.entity.Product;
import com.rep.simpProd.helpers.ProductsForm;
import com.rep.simpProd.services.ExcelParser;
import com.rep.simpProd.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ProductCreatorController {

    private MultipartFile parseFile;

    @Autowired
    private ExcelParser excelParser;
    @Autowired
    private ProductService productService;

    @GetMapping("parseForm")
    public String showParsingForm(){

        return "parsingExc/productsParse";

    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        String upDir = "/Users/alexandrzhidkov/Documents/УчебаJava/Курс/Spring/Lesson4/simpProd/src/main/resources/static/img/uploads/";

        try {
            file.transferTo(new File(upDir + "book.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        model.addAttribute("fileName", file.getOriginalFilename());
        model.addAttribute("fileSize", file.getSize());
        model.addAttribute("fileType", file.getContentType());
        return "parsingExc/productsParse";
    }


    @PostMapping("/parseFile")
    public String editPost(Model model) {

        String upDir = "/Users/alexandrzhidkov/Documents/УчебаJava/Курс/Spring/Lesson4/simpProd/src/main/resources/static/img/uploads/";

        File file = new File(upDir + "book.xlsx");
        Map<String, Object> parsingRes = new HashMap<>();
        try {
            parsingRes = excelParser.readAndParseExcelFile(file);
            if(parsingRes.containsKey("hasError")){
               boolean hasError = (boolean) parsingRes.get("hasError");
               if(hasError){
                   model.addAttribute("errorMessage", (String)parsingRes.get("error"));
                   return "parsingExc/parsingError";
               }
               List<Product> products = (List<Product>) parsingRes.get("parsedList");
               if (products.size()==0){
                   model.addAttribute("errorMessage", "list est vide");
                   return "parsingExc/parsingError";
               }else{
                   ProductsForm pf = new ProductsForm();
                   pf.setProducts(products);
                   model.addAttribute("productsForm", pf);
                   return "parsingExc/parsedTable";
               }
            }
            return "";
        } catch (IOException e) {
            String erMessage = e.getMessage();
            model.addAttribute("errorMessage", erMessage);
            return "parsingExc/parsingError";
        }finally {
            file.delete();
        }

    }

    @PostMapping("/saveParsed")
    public String addProductsFromFile(@ModelAttribute("productsForm") ProductsForm productsForm){
        List<Product> productList = productsForm.getProducts();

        Product lastProduct = null;

        for (Product product:productList) {
           if(lastProduct!=null){
               if (product.equals(lastProduct)){
                   continue;
               }
           }

           productService.createProduct(product);
           lastProduct = product;
        }

        return "parsingExc/parseComplete";
    }

}
