package com.terminus.test.service;

import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.terminus.test.dto.ProductStatus;
import com.terminus.test.entity.Product;

/** 
 *  Service class to Produce MQ messages 
 *  
 * @author Rajesh Majji
 * @version 1.0
 */
@Service
public class ProductMQProducerService {

	
	@Value("${mq.exchange.name}") String exchangeName;
	@Value("${mq.routing.key}") String routingKey;
	
	@Autowired
    private RabbitTemplate template;
	
    public String createNewProduct(Product product) {
    	product.setId(new Random().nextLong());
        ProductStatus productStatus = new ProductStatus(product, "PROCESS", "order placed succesfully in MQ");
        template.convertAndSend(exchangeName, routingKey, productStatus);
        return "Success !!";
    }
}