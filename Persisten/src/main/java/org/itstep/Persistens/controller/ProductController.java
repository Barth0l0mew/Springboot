package org.itstep.Persistens.controller;


import org.itstep.Persistens.model.Product;
import org.itstep.Persistens.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/product")
    public List<Product> getAllProduct (){
        return productService.findAll();
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        return
                product.map
                        (ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
