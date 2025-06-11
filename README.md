# Requerimientos 
**Como único requerimiento se debe crear una base de datos llamada compras y cambiar las credenciales en el archivo properties.yml de rp_ms-books-payments**

# Se carga 5 proyectos
eureka -> servidor de registro

gateway-filter-> proxy inverso, uso de post y traduce a post, get, put, patch, delete

books-catalogue -> crud de libros (H2)

books-payments -> gestión de compra de libros y actualización del stock de los libros, **se debe crear una base de datos llamada compras en mysql y modificar las credenciales de acceso en el properties **

gateway ->proxy inverso (opcional)


# Direcciones de Eureka, swagger, gateway
http://localhost:8761/     EUREKA
http://localhost:8762/actuator/gateway/routes
http://localhost:8000/swagger-ui/index.html      SWAGGER libros
http://localhost:8020/swagger-ui/index.html	     SWAGGER compra

# Papel.postman_collection.json
se agrega archivo Unir Relatos Papel.postman_collection.json para importar en postman y realizar las pruebas.

# Comando para crear replicas de los microservicios
java -Dserver.port=8001 -jar rp-ms-books-payments-0.0.1-SNAPSHOT.jar
java -Dserver.port=8021 -jar rp-ms-books-catalogue-0.0.1-SNAPSHOT.jar
