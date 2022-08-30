# Create project

``` 
https://start.spring.io/
```

# Build the project

``` 
mvn clean install
```

# Run application
Before running the application, check that port 8080(default port used by tomcat server) is free. If not, then change the port in application.properties file such as:
server.port=8081


``` 
mvn spring-boot:run
```

# Testing the application

## Save Data:

curl --location --request POST 'localhost:8081/save/employee?name=emp1' \
--header 'Authorization: Basic YXNoaXNoOnBhc3N3b3Jk' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=FFCF15F4A60A49D8CAAF497DABC99818' \
--data-raw '{
"empId":"123",
"empName":"emp1",
"salary":"150000",
"department":"Azure"
}'

## Get data:
curl --location --request GET 'localhost:8081/get/employee?name=emp1' \
--header 'Authorization: Basic YXNoaXNoOnBhc3N3b3Jk' \
--header 'Cookie: JSESSIONID=4818D96BA1235311BB0C0B9D8BECE5B3'

## Update data:

curl --location --request PUT 'localhost:8081/update/employee?salary=220000&name=emp1' \
--header 'Authorization: Basic YXNoaXNoOnBhc3N3b3Jk' \
--header 'Cookie: JSESSIONID=FBA61859697CA90109370DD9A448C233'

## Remove Data:

curl --location --request DELETE 'localhost:8081/delete/employee?name=emp1' \
--header 'Authorization: Basic YXNoaXNoOnBhc3N3b3Jk' \
--header 'Cookie: JSESSIONID=FBA61859697CA90109370DD9A448C233'
