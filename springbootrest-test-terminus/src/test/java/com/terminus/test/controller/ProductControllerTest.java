package com.terminus.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terminus.test.entity.Product;
import com.terminus.test.exception.ProductNotFoundException;
import com.terminus.test.service.ProductService;
import com.terminus.test.util.JsonUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

  //below test case for fetching list of products
    @Test
    public void listAllProducts_whenGetMethod()
            throws Exception {

        Product product = new Product();
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        List<Product> allProducts = Arrays.asList(product);

        given(productService
                .listAllProducts())
                .willReturn(allProducts);

        mockMvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(product.getName())));
    }
    
  //below test case for fetching product with mapping id
    @Test
    public void listProductById_whenGetMethod() throws Exception {

        Product product = new Product();
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        given(productService.listProduct(product.getId())).willReturn(product);

        mockMvc.perform(get("/api/products/" + Long.toString(product.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(product.getName())));
    }

    @Test
    public void details_should_throw_exception_when_product_doesnt_exist() throws Exception {
        Product product = new Product();
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        Mockito.doThrow(new ProductNotFoundException(product.getId())).when(productService).listProduct(product.getId());

        mockMvc.perform(get("/products/" + Long.toString(product.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
  //below test case for Creating new product
    @Test
    public void createProduct_whenPostMethod() throws Exception {

        Product product = new Product();
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        given(productService.addNewProduct(product)).willReturn(product);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(product.getName())));
    }
    
  //below test cases for updating the product
    @Test
    public void updateProduct_whenPutProduct() throws Exception {

        Product product = new Product();
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);
        
        given(productService.updateProduct(product.getId(), product)).willReturn(product);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/api/products/" + Long.toString(product.getId()))
                .content(mapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(product.getName())));
    }

    @Test
    public void update_should_throw_exception_when_product_doesnt_exist() throws Exception {
        Product product = new Product();
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        Mockito.doThrow(new ProductNotFoundException(product.getId())).when(productService).updateProduct(product.getId(), product);
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/products/" + Long.toString(product.getId()))
                .content(mapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
   
  //below test cases for deleting the product
    @Test
    public void removeProductById_whenDeleteMethod() throws Exception {
        Product product = new Product();
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);
        
        doNothing().when(productService).deleteProduct(product.getId());

        mockMvc.perform(delete("/api/products/" + Long.toString(product.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_throw_exception_when_product_doesnt_exist() throws Exception {
        Product product = new Product();
        product.setId(101);
        product.setName("iPhone");
        product.setQuantity(1);
        product.setPrice(2500);

        Mockito.doThrow(new ProductNotFoundException(product.getId())).when(productService).deleteProduct(product.getId());

        mockMvc.perform(delete("/products/" + Long.toString(product.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
