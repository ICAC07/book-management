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

### Documentación del API
[API Documentación](http://localhost:9090/swagger-ui/index.html)

El endpoint **/v1/api/book**, está protegido. Mediante la autorización básica en memoria, por lo que deberás usar las siguientes credenciales:

| Username | Password | Role  |
|:--------:|:--------:|:-----:|
| admin    | secret   | ADMIN |
| user     | secret   | USER  |

El usuario **user**, únicamente tiene acceso a las operaciones de consulta.
El usuario **admin**, tiene acceso a todas las operaciones.

El endpoint **/v1/api/book/health** no está protegido. Indica el estado del servicio.


### Collections para Postman
La collections se encuentra en el repositorio con el nombre del archivo **BookManagement_Auth_Basic.postman_collection**
