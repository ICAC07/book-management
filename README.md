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

### Generar token de acceso
El endpoint **/v1/api/book**, está protegido. Mediante la autorización de un token de acceso (JWT).

Deberás generar el token de acceso, consumiendo la operación **/v1/api/auth/accessToken**. Indicar un usuario y password, internamente se generará un usuario y te devolverá un token.

### Estado del API
Podrás consultarlo mediante el endpoint **/v1/api/book/health**. No está protegido.

### Documentación del API
[API Documentación](http://localhost:9090/swagger-ui/index.html)

### Collections para Postman
La collections se encuentra en el repositorio con el nombre del archivo **BookManagement_Auth_Token.postman_collection**
