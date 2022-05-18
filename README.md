# Mercado crédito

Stack tecnologico:
- Java 11
- Spring boot
- Base de datos H2
- Maven

Stack arquitectonico.
- Arquitectura Hexagonal
- DDD(Domain Driven Design)

## Ejecución
Si se sube al Spring tools suite se puede usar el maven instalado

## Ejecución sin Spring tools suite

Se debe tener maven instalado y ubicarse dentro de la carpeta raíz del proyecto y ejecutar:
- mvn install(para generar el jar)
- docker-compose up -d --build
- docker-compose logs -f

Si no se tiene maven instalado en la carpeta ejecutable de resources se encuentra el jar de la aplicación con los archivos de docker. 

En la carpeta resources/postman se encuentra un set de pruebas para el consumo de los endpoints generados

# Arquitectura
Se ha optado por el uso de arquiecturas limpias, en este caso se ha usado Arquitectura Hexagonal con DDD(Domain-Driven-Design)

Dicha arquitectura se basa en la siguiente estructuración:

- **Application**: En este paquete van todas las entradas de la aplicación por ejemplo podemos encontrar los controladores y algunas clases para el uso común.
- **Domain**: Este paquete guarda toda la especificación del dominio o sea reglas de negocio. Al manejar DDD aseguramos que el modelo de negocio con sus reglas no sea ensuciado por el framework tecnológico que se esta usando. El paquete dominio se divide en 3 partes fundamentales:
 1. *Modelo*: Encargado en tener todos los modelos usados por el dominio
 2. *Repository*: Contiene las interfaces de repositorio que servirán de contrato para las implementaciones.
 3. *Usecase*: Acá va toda la lógica de negocio(reglas de negocio, etc) y usa el repositorio si es necesario
 
- **Infrastructure**: Contiene los adaptadores que son todos aquellos puertos que se conectan con diferentes apis, bases de datos, colas, etc. En nuestro caso tiene la conexión a la base de datos h2 con sus respectivas entidades y la implementación de datos. Los adaptadores implementan los repositorios de negocio y dicha implementación es inyectada por medio de inyección de dependencia hacía los casos de uso del dominio. Cabe destacar que como se esta usando Springboot se usan las anotaciones y la creación de beans para mantenerlos en el contexto y así poder acceder a ellos, sin embargo como la idea es que el dominio no sea ensuciado por cosas del framework, entonces en este caso adaptamos el framework al dominio y creamos beans desde los archivos de configuración referenciando los casos de uso y los repositorios.

## Aclaraciones
1. Se crearon los Endpoints correspondientes con el documento y además se creo un nuevo Endpoint en el cual se pueden enviar lo datos de las reglas para ser modificados.
2. A la hora de registrar un pago como puede ser inferior o superior a la cuota de la deuda se tomo la decisión personal de que si el valor es inferior a la cuota se ejecuta una formula en la cual se resta el valor pagado a la cuota y al restante se le aplica el mismo interés; este valor se acumula cada vez que las cuotas sean mas bajas y no se haya pagado.
3. Las pruebas unitarias solo fueron enfocadas en el dominio debido al tiempo
4. Tecnicamente cada clase tiene su responsabilidad además de haber hecho segregción de interfaces para los repositorios y los DAO. Como patrones de diseño al ser una arquitectura hexagonal ya trae implicito el código limpio y la separación de responsabilidades, también se usaron los patrones que Springboot nos facilita como singleton, fachadas que en si son los adapters, DAO, inyección de dependencia.
