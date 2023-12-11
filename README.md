# Football League System with Spring Boot
This Spring Boot project is designed to manage football teams, simulate matches, and display the league standings with detailed information about each team. The system uses algorithms to generate match results and provides APIs documented with OpenAPI.

## API Documentation
API documentation can be found at http://localhost:8080/swagger-ui.html. This documentation is automatically generated with OpenAPI.

## Database
The project uses the H2 database by default. The database configuration can be found in the application.properties file.
```bash
spring.datasource.url=jdbc:h2:./demo
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=user
spring.datasource.password=123456
spring.h2.console.enabled=true
spring.h2.console.path=/h2

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```
