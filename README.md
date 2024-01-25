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

## Compilación

```java
mvn clean install
```

2. Levantar el proyecto

2.1 Con comando de maven

```java
mvn spring-boot:run
```

2.2 Desde el JAR  

```java
java -jar ./target/book-management-0.0.1-SNAPSHOT.jar
```

### Documentación del API
[API Documentación](http://localhost:9090/swagger-ui/index.html)

El endpoint **/v1/api/book**, está protegido, por lo que deberás usar las siguientes credenciales:

| Username | Password |
|:--------:|:--------:|
| admin    | secret   |

El endpoint **/v1/api/book/health** no está protegido. Indica el estado del servicio


### Collections para Postman
La collections se encuentra en el repositorio con el nombre del archivo **book-management-collections.json**
