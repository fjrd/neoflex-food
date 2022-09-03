# Neoflex-food
[![Build](https://github.com/Fjrd/neoflex-food/actions/workflows/build.yml/badge.svg)](https://github.com/Fjrd/neoflex-food/actions/workflows/build.yml)
## About
Demo application developed as part of microservice architecture training at Neoflex company


## Installation
```bash
mvn clean install -DskipTests
docker-compose -f docker-compose.yml up --build
```

## Architecture preview
![Screenshot 2022-09-03 at 14 50 30](https://user-images.githubusercontent.com/18509753/188269151-a6d92e38-722c-470b-83e2-2f904e8cf017.png)


## Api documentation
Customers service (TODO) http://localhost:8092/swagger-ui.html   
Orders service http://localhost:8081/swagger-ui.html  
Payment service http://localhost:8087/swagger-ui.html  
Restaurant service http://localhost:8082/swagger-ui.html  
Delivery service http://localhost:8085/swagger-ui.html

## Authors
Reztsov Alexander, [reztsov.dev@gmail.com](reztsov.dev@gmail.com)\
Semen Ryzhkov []()\
Alexander Michaylenko []()

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.

