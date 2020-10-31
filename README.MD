# Coding Challenge
## 1. Instructions to run the microservices 


Clone challenge code repository
`git clone https://github.com/saleco/adds-challenge`

#Discovery Server Up and Running
`cd discovery-server `

`mvn clean install`

`mvn spring-boot:run`

You can now access Discovery Server on http://localhost:8761

#Public Service Up and Running
##You will be running two instances of the public service (Load Balancer)

`cd public-server `

`mvn clean install`

`mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=8081'`

`mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=8082'`

#Subscription Service Up and Running
##You will be running two instances of the subscription service (Load Balancer)

`cd subscription-server `

`mvn clean install`

`mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=8083'`

`mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=8084'`

#Email Service Up and Running
##You will be running two instances of the email service (Load Balancer). 
###The email service was configured to be asynchronous in order to have a small response time.
####In order to work properly please do change application.properties with you gmail credentials
`cd email-server `

`mvn clean install`

`mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=8085`

`mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=8086'`
