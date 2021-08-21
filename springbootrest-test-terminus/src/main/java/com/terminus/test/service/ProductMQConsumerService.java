package com.terminus.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terminus.test.dto.ProductStatus;
import com.terminus.test.entity.Product;
import com.terminus.test.repository.ProductRepository;

/** 
 *  Service class to consume MQ messages 
 *  
 * @author Rajesh Majji
 * @version 1.0
 */
@Service
public class ProductMQConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ProductMQConsumerService.class);

	@Autowired
    ProductRepository productRepository;
	
    @RabbitListener(queues = "${mq.queue.name}")
    public void consumeMessageFromQueue(ProductStatus productStatus) {
        Product product = productStatus.getProduct();
        productRepository.save(product);
        logger.info("Message recieved from queue and Saved to DB :  {}",productStatus);
    }
}