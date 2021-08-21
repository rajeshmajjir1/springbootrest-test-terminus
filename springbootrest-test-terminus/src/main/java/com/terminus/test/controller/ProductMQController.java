package com.terminus.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.terminus.test.entity.Product;
import com.terminus.test.service.ProductMQProducerService;

/** 
 *  Product Message Queue Controller to add product to Message Queue
 *  
 * @author Rajesh Majji
 * @version 1.0
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/mq")
public class ProductMQController {
	
    private static final Logger logger = LoggerFactory.getLogger(ProductMQController.class);

    @Autowired
    ProductMQProducerService productMQProducerService;
    
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public String createNewProduct(@RequestBody Product product) {
    	
    	logger.info("adding product to Message Queue: {}",product);
        String status = productMQProducerService.createNewProduct(product);
        return status;
    }
    
}