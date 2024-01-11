# Ignacio Bacigalupi Prueba Tecnica

# API Agencia de Turismo

Desarrollo de una API que permite la gestión de reservas de vuelos y hoteles para una agencia de turismo. Esta API facilita la gestión completa de hoteles y vuelos, desde la creación de los mismos hasta la generación de reservas correspondientes. Además, permite realizar filtros para verificar la disponibilidad de vuelos y hoteles por ubicacion y fechas específicas.

# Requisitos

Asegúrate de tener instalado Java en tu máquina. La aplicación utiliza **JPA** para interactuar con una base de datos, así que también necesitarás configurar una fuente de datos. El proyecto cuenta con un backup de la base de datos para ser utilizado.

### Autenticación

La API implementa un sistema de autenticación basado en **Spring Security**. Los endpoints que requieren autenticación están protegidos mediante este sistema. Los empleados de la agencia deben autenticarse para realizar operaciones de alta, baja y modificación en la base de datos.

Cómo autenticarse
Se deben utilizar las siguientes credenciales para autenticarse:

- **Usuario**: hackaboss
- **Contraseña**: 1234

### Configuración de la base de datos MySQL

Para conectar la API a la base de datos MySQL, utiliza las siguientes credenciales:

- **Host:** localhost
- **Puerto:** 3306
- **Usuario:** root
- **Contraseña:** 12345
- **Nombre de la base de datos:** agencia_turismo

# Tecnologías Utilizadas

- Java 17: Lenguaje de programación principal.
- Spring Boot 3.2.1: Framework para desarrollo de aplicaciones en Java.
- Spring Data JPA + Hibernate: Para la interacción con la capa de persistencia.
- Spring Security : Para la implementación de autenticación.
- MySQL: Base de datos utilizada para almacenamiento de datos.

# Funcionalidades Principales:

- **Gestión de Hoteles**: Permite la creación, actualización y eliminación de registros de hoteles, así como la búsqueda de hoteles por ID y obtener todos los hoteles registrados.

- **Gestión de Vuelos**: Ofrece operaciones para la creación, modificación y eliminación de registros de vuelos. También facilita la búsqueda de vuelos por ID y obtener todos los vuelos registrados.

- **Gestión de habitaciones**: Permite la creación de habitaciones para ser asignadas a los hoteles correspondientes.

- **Gestion reservas de Hoteles y Vuelos**: Permite realizar la creacion y borrado de reservas tanto de hoteles como de vuelos, especificando detalles como fechas, cantidad de personas, tipo de habitación o asiento, y devuelve el costo total de la reserva.

- **Filtrado de Disponibilidad**: Permite verificar la disponibilidad de hoteles y vuelos en un rango de fechas específico y según el destino seleccionado.

## Documentación de la API

La documentación de la API se ha realizado utilizando Swagger, lo que permite una fácil comprensión de los endpoints disponibles y cómo interactuar con ellos. Puedes acceder a la documentación y probar los endpoints utilizando el siguiente enlace:

http://localhost:8080/doc/swagger-ui.html

## Colección de Postman

Para facilitar la comprensión y prueba de los endpoints de la API, se proporciona una colección de Postman en formato JSON. Esta colección contiene ejemplos predefinidos de solicitudes para ayudarte a comenzar a trabajar con la API.

# Supuestos:

- **Asignación de habitaciones**: Las habitaciones deben asignarse a un hotel existente en el momento de su creación. Esto implica que para crear una habitación, se requiere que el hotel correspondiente ya esté registrado en el sistema.

- **Dependencias para las reservas**: Para realizar una reserva, es necesario que tanto los vuelos como los hoteles estén creados previamente en el sistema. La reserva se vincula con estos elementos existentes.

- **Creación de usuarios**: En el caso de la reserva de hotel, se asume que la creación de usuarios se realiza implícitamente en el proceso de reserva, el sistema no permite usuarios repetidos al realizar la reserva esto lo corrobora mediante el correo del usuario.
