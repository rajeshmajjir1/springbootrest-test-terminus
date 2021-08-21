package com.terminus.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.terminus.test.entity.Product;
import com.terminus.test.exception.ProductNotFoundException;
import com.terminus.test.repository.ProductRepository;

/** 
 *  Service class to manage Product CURD Operations
 *  
 * @author Rajesh Majji
 * @version 1.0
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }
    
    @Cacheable(value = "products",key = "#id")
    public Product listProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
    
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }
    
    @CachePut(value = "products",key = "#id")
    public Product updateProduct(Long id, Product product) {

    	productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setId(id);
        return productRepository.save(product);
    }
    
    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long id) {

    	productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

    	productRepository.deleteById(id);
    }
    
}