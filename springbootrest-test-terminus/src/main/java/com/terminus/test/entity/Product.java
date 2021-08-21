package com.terminus.test.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** 
 *  Product Entity Class
 *  
 * @author Rajesh Majji
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "quantity")
    private int quantity;
	
	@Column(name = "price")
    private double price;
}
