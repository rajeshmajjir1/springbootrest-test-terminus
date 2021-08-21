package com.terminus.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.terminus.test.entity.Product;
import com.terminus.test.exception.ProductNotFoundException;
import com.terminus.test.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;
    
    //below test case for fetching list of products
    @Test
    public void shouldReturnAllProducts() {
        List<Product> products = new ArrayList();
        products.add(new Product());

        given(productRepository.findAll()).willReturn(products);

        List<Product> expected = productService.listAllProducts();

        assertEquals(expected, products);
        verify(productRepository).findAll();
    }
    
  //below test cases for fetching product with id
    @Test
    public void whenGivenId_shouldReturnProduct_ifFound() {
        Product product = new Product();
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);
        
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        Product expected = productService.listProduct(product.getId());

        assertThat(expected).isSameAs(product);
        verify(productRepository).findById(product.getId());
    }

    @Test(expected = ProductNotFoundException.class)
    public void details_should_throw_exception_when_product_doesnt_exist() {
        Product product = new Product();
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        given(productRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        productService.listProduct(product.getId());
    }
   
    //below test case for creating new product

    @Test
    public void whenSaveProduct_shouldReturnProduct() {
        Product product = new Product();
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        when(productRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(product);

        Product created = productService.addNewProduct(product);

        assertThat(created.getName()).isSameAs(product.getName());
        verify(productRepository).save(product);
    }
    
    //below test cases for updating  product
    @Test
    public void whenGivenId_shouldUpdateProduct_ifFound() {
        Product product = new Product();
        
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        Product newProduct = new Product();
        product.setName("Samsung");

        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
        productService.updateProduct(product.getId(), newProduct);

        verify(productRepository).save(newProduct);
        verify(productRepository).findById(product.getId());
    }

    @Test(expected = RuntimeException.class)
    public void update_should_throw_exception_when_product_doesnt_exist() {
        Product product = new Product();
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        Product newProduct = new Product();
        newProduct.setId(102);
        product.setName("Samsung");

        given(productRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        productService.updateProduct(product.getId(), newProduct);
    }
    
  //below test cases for Deleting  product
    @Test
    public void whenGivenId_shouldDeleteProduct_ifFound(){
        Product product = new Product();
        product.setId(103);
        product.setName("OnePlus");
        product.setQuantity(1);
        product.setPrice(2000);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.deleteProduct(product.getId());
        verify(productRepository).deleteById(product.getId());
    }

    @Test(expected = RuntimeException.class)
    public void delete_should_throw_exception_when_product_doesnt_exist() {
        Product product = new Product();
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        given(productRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        productService.deleteProduct(product.getId());
    }
}