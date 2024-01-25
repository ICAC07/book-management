# Book Management


Sistema administrador de libros

### Entorno

- `Máquina con S.O. Windows 64 bits`
- `Java 21`
- `Maven 3.9.1`
- `Spring Boot 3.3.0-SNAPSHOT`
- `Lombok 1.18.30`
- `MapStruct 1.5.4.Final`
- `Springdoc 2.3.0`
- `DataBase like H2`
- `Junit 5`


### Servicio

API Rest que gestiona la administración de libros. Permite operaciones como:

- Consultar todos los libros en sistema o bien por Id.
- Agregar nuevos libros
- Eliminar algún libro


### Ejecutar

#### Compilación

```java
mvn clean install
```

#### Levantar el proyecto

1. Con comando de maven

```java
mvn spring-boot:run
```

2. Desde el JAR  

```java
java -jar ./target/book-management-0.0.1-SNAPSHOT.jar
```


IMPORTANTE:
	De aqui en adelante queda pendiente de actualizar la documentación del README.md y el archivo swagger (boo-management.yml).
	Debido a que este branch, maneja la seguridad mediante un JWT.
	
	
### Documentación del API (Pendiente, de actualizar)
### Collections para Postman (Pendiente, de actualizar)