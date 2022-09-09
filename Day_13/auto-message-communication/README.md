# Build
```
mvn clean install
```
# Run
```
docker-compose -f docker-compose-rabbitmq.yml up -d 
rabbitmq-consumer> mvn spring-boot:run 
rabbitmq-producer> mvn spring-boot:run
```
# Test
```
curl --location --request GET 'http://localhost:8081/rabbitmq/producer?to=consumer&from=producer&content=Hello_consumer'
```
# Show data on browser::
```
http://localhost:15672/
```
user-name:guest
password:guest

# Stop Container
```
docker-compose -f docker-compose-rabbitmq.yml down
```