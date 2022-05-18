# Mercado crédito

Stack tecnologico:
- Java 11
- Spring boot
- Base de datos H2
- Maven

Stack arquitectonico.
- Arquitectura Hexagonal
- DDD(Domain Driven Design)

##Ejecución
Si se sube al Spring tools suite se puede usar el maven instalado

##Ejecución sin Spring tools suite

Se debe tener maven instalado y ubicarse dentro de la carpeta raíz del proyecto y ejecutar:
- mvn install(para generar el jar)
- docker-compose up -d --build
- docker-compose logs -f 

En la carpeta resources/postman se encuentra un set de pruebas para el consumo de los endpoints generados
