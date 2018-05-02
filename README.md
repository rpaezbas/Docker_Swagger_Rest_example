This is a beginner aplication with the basics of a java rest service, deployed
like microservices in Docker and documented with Swagger.

Intruccions to run:

Step 1: build the docker images from ./App and ./AppSwaggerDoc
	They have to be built with the same name that the docker-compose.yml
knows for these images.
	If you dont change the docker-compose.yml the commands to be run are:
	From ./App -> docker build --tag=dockerdb .
	From ./AppSwaggerDoc -> docker build --tag=glassfish_swagger .

Step 2: run the container
	From the root folder of the project run -> docker-compose up
	Now you should have:
		 payara running in port 8080 (Rest Service)
(http://localhost:8080/Login/api)
		 payara running in port 8081 (Swagger doc)
(http://localhost:8081/Swag_3/)
		postgresql database in port 5321
		activeMq server 

The configuration of the payara server, is the domain.xml file of ./App,this
file is copied to the payara server in order to configure it.The postgresql driver is
also copied from the folder ./App to the folder /glassfish/domain/domains/lib
