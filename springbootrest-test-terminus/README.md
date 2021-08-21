Spring boot REST API Test   Application is for Product Management CRUD Operations.
===========================

Tech stack : Java 1.8, Spring boot,Spring boot data JPA, Hibernate, MySQL, RabbitMQ, Redis cache.

Before running the application required RabbitMQ, Redis Cache configurations in local.

Application will run on default port 8080 with contect_path 'springboot-rest'. Swagger API documentation is available and can be accessed with below URL.

Swagger - http://localhost:8080/springboot-rest/swagger-ui.html

Application having Two controller classes:

ProductController  -- For Product CRUD Operations For accessing the end points.
 
 GET(Fetching the list of Products)            		http://localhost:8080/springboot-rest/api/products
 GET(Fetching the product matched with the id)		http://localhost:8080/springboot-rest/api/products/{id}
 POST(Adding the new product to DB)					http://localhost:8080/springboot-rest/api/products
 PUT(Updating the product matched with the id)		http://localhost:8080/springboot-rest/api/products/{id}
 DELETE(Deleting the product matched with the id)	http://localhost:8080/springboot-rest/api/products/{id}
 
ProductMQController -- For Adding Product to Message Queue. Later will be saved to DB by Consumer Service
	
 POST(Adding the new product to MQ)				http://localhost:8080/springboot-rest/api/mq/products
  
RABBITMQ Configuration Local
=============================

Download and install ERlang
Download and install RabbitMQ
Go to RabbitMQ Server install Directory C:\Program Files\RabbitMQ Server\rabbitmq_server-3.9.4\sbin
Run command rabbitmq-plugins enable rabbitmq_management
Open browser and enter http://localhost:15672/ to redirect to RabbitMQ Dash board
Also we can Open it with IP Address http://127.0.0.1:15672
Login page default user name and password is guest
After successfully login you should see RabbitMQ Home page  
 
REDIS Server In Local
==============================

 Download Redis Server and start the server in local
 
Angular JS Application
==============================

Angular JS front end application can be accessed Once after start with 'ng-serve'

http://localhost:4200/

 