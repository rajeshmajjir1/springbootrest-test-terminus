package com.terminus.test.dto;

/** 
 *  DTO to place and catch the Product object
 *  
 * @author Rajesh Majji
 * @version 1.0
 */
import com.terminus.test.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductStatus {

    private Product product;
    private String status;//progress,completed
    private String message;
}
