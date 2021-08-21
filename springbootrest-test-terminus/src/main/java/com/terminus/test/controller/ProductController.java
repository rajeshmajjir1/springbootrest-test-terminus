package com.terminus.test.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.terminus.test.entity.Product;
import com.terminus.test.service.ProductService;

/** 
 *  Product Controller class having all endpoits for CRUD operations
 *  
 * @author Rajesh Majji
 * @version 1.0
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;
    
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
  //The function receives a GET request, processes it and gives back a list of Products as a response.
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> listAllProducts() {
    	logger.info("listing all product details");
        return ResponseEntity.ok().body(productService.listAllProducts());
    }
    
  //The function receives a GET request with id parameter, processes it and gives back Product with id matched as a response.
    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    	
    	logger.info("getting the Product with id: {}",id);
        return ResponseEntity.ok().body(productService.listProduct(id));
    }
    
  //The function receives a POST request with product details, inserts the product in to DB and gives back inserted Product as a response.
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {

    	logger.info("adding new product: {}",product);
        Product createdProduct = productService.addNewProduct(product);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();

        return ResponseEntity.created(uri).body(createdProduct);
    }
    
  //The function receives a PUT request with product details, updates the product mapped with id in to DB and gives back updated Product as a response.
    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
    	logger.info("updating the product with id: {}",id);
        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }
    
  //The function receives a DELETE request with product id, deletes the product mapped with id from DB.
    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
    	logger.info("deleting the product with id: {}",id);
    	productService.deleteProduct(id);
    }
}