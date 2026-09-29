# Historias de Usuario - Prácticas Backend (Java Spring Boot + PostgreSQL)

Este documento contiene un backlog inicial con historias de usuario para el desarrollo y configuración base de una aplicación enterprise orientadas al desarrollo de una API REST con **Java Spring Boot**, persistencia con **Spring Data JPA** y base de datos relacional **PostgreSQL**.

---

## HU-001: Configuración inicial del proyecto Spring Boot
**Tipo:** Tarea / Historia Técnica   
**Prioridad:** Alta  
**Estimación:** [ ] Story Points  

### Descripción
Como desarrollador backend, quiero inicializar el proyecto base con Spring Boot y la conexión a PostgreSQL para tener el entorno listo para construir las funcionalidades.

### Criterios de Aceptación
- [x] El proyecto está creado utilizando Spring Initializr con Java 17 o superior.
- [x] Se incluyen las dependencias: *Spring Web*, *Spring Data JPA*, *PostgreSQL Driver* y *Lombok*.
- [x] El archivo `application.properties` o `application.yml` está configurado correctamente con las credenciales de conexión a una base de datos PostgreSQL local o en contenedor.
- [x] La aplicación arranca exitosamente sin errores de configuración.

---

## HU-002: Creación de la entidad de Dominio (Catálogo de Productos)
**Tipo:** Historia de Usuario  
**Prioridad:** Alta  
**Estimación:** [ ]  Story Points  

### Descripción
Como Product Owner, quiero que se cree la entidad de dominio y su respectiva tabla en PostgreSQL para el catálogo de "Productos", de modo que podamos almacenar la información principal.

### Criterios de Aceptación
- [x] Se crea la clase entidad `Product` anotada con `@Entity` y `@Table(name = "products")`.
- [x] La entidad cuenta con los siguientes atributos mapeados:
  - `id` (Long, Clave Primaria, Auto-incremental)
  - `name` (String, Obligatorio, Máximo 100 caracteres)
  - `description` (String, Opcional)
  - `price` (BigDecimal, Obligatorio, Mayor a 0)
  - `stock` (Integer, Obligatorio, Mayor o igual a 0)
- [x] Al iniciar la aplicación con la propiedad `spring.jpa.hibernate.ddl-auto=update` (o mediante script SQL), la tabla se genera correctamente en PostgreSQL.

---

## HU-003: Repositorio de Datos para Productos
**Tipo:** Historia de Usuario  
**Prioridad:** Alta  
**Estimación:** [ ] Story Points  

### Descripción
Como desarrollador backend, quiero implementar la capa de persistencia (Repository) para la entidad `Ticket` para poder realizar operaciones de base de datos de forma sencilla.

### Criterios de Aceptación
- [x] Se crea la interfaz `TicketRepository` que extiende de `JpaRepository<Ticket, Long>`.
- [x] Se verifica que Spring Data JPA provea automáticamente los métodos básicos de CRUD (`save`, `findById`, `findAll`, `deleteById`).
- [x] Se añade un método derivado para buscar Tickets por nombre (ej. `findByNameContainingIgnoreCase`).
- [x] Se realiza una prueba unitaria o de integración básica para comprobar la conexión y persistencia con PostgreSQL.

---

## HU-004: Implementación de la Capa de Servicio (Lógica de Negocio)
**Tipo:** Historia de Usuario  
**Prioridad:** Media  
**Estimación:** [ ] Story Points  

### Descripción
Como desarrollador backend, quiero crear la clase de servicio `ProductService` para encapsular la lógica de negocio antes de exponerla a través de la API REST.

### Criterios de Aceptación
- [x] Se crea la clase `ProductServiceImpl` anotada con `@Service`.
- [x] Se inyecta `ProductRepository` mediante inyección por constructor.
- [x] Se implementan los métodos de negocio: listar todos, buscar por ID, guardar, actualizar y eliminar.

---

## HU-005: Exponer Endpoints REST para Productos (CRUD Completo)
**Tipo:** Historia de Usuario  
**Prioridad:** Alta  
**Estimación:** [ ] Story Points  

### Descripción
Como cliente de la API, quiero consumir los endpoints REST para la entidad `Product` para poder gestionar el catálogo desde aplicaciones externas o frontend.

### Criterios de Aceptación
- [x] Se crea el controlador `ProductController` anotado con `@RestController` y mapeado a `/api/products`.
- [x] Se implementa el endpoint `GET /api/products` que retorna la lista de todos los productos (Código HTTP 200).
- [x] Se implementa el endpoint `GET /api/products/{id}` que retorna un producto por su ID o un 404 si no existe.
- [x] Se implementa el endpoint `POST /api/products` para crear un nuevo producto (Código HTTP 201).
- [x] Se implementa el endpoint `PUT /api/products/{id}` para actualizar un producto existente.
- [x] Se implementa el endpoint `DELETE /api/products/{id}` para eliminar un producto (Código HTTP 204).

---

## HU-006: Validación de Datos de Entrada (Bean Validation)
**Tipo:** Historia de Usuario  
**Prioridad:** Media  
**Estimación:** [ ] Story Points  

### Descripción
Como desarrollador backend, quiero agregar validaciones en las peticiones de entrada usando Bean Validation para evitar que datos incorrectos o vacíos lleguen a la base de datos.

### Criterios de Aceptación
- [x] Se incluye la dependencia `spring-boot-starter-validation`.
- [x] Se agregan anotaciones de validación en los DTOs o en la entidad `Product` (ej. `@NotBlank`, `@NotNull`, `@Positive`, `@Min`).
- [x] Se añade la anotación `@Valid` en los métodos correspondientes del `ProductController`.
- [x] Si una validación falla, la API responde con un código HTTP 400 (Bad Request) y un mensaje descriptivo de los errores.

---


## HU-001: Inicialización de Microservicio CRUD para Catálogo de Clientes (PostgreSQL)

**Tipo:** Historia Técnica / Configuración

**Prioridad:** Alta

**Estimación:** [ ] Story Points

### Descripción

Como arquitecto de software, quiero inicializar la estructura base de un microservicio Spring Boot para el catálogo de "Clientes" conectado a una base de datos PostgreSQL, para asegurar una arquitectura desacoplada.

### Criterios de Aceptación

- [x] El microservicio se crea como un proyecto Spring Boot independiente con Java 17+.
- [x] Se incluyen las dependencias de *Spring Web*, *Spring Data JPA* y *PostgreSQL Driver*.
- [x] El archivo `application.properties` configura correctamente el datasource de PostgreSQL y un puerto dedicado (ej. `8081`).
- [x] La aplicación arranca y conecta exitosamente con la base de datos PostgreSQL.

## HU-002: Implementación de Operación CREATE en Microservicio de Clientes

**Tipo:** Historia de Usuario

**Prioridad:** Alta

**Estimación:** [ ] Story Points

### Descripción

Como cliente de la API, quiero enviar una petición POST al microservicio para registrar un nuevo cliente en la base de datos PostgreSQL.

### Criterios de Aceptación

- [x] Se crea la entidad JPA `Client` con los campos: `id` (Long, PK), `fullName` (String), `email` (String) y `phone` (String).
- [x] Se implementa el repositorio `ClientRepository` y el controlador REST con el endpoint `POST /api/clients`.
- [x] Al enviar un JSON válido, el registro se almacena en PostgreSQL y se retorna el código HTTP 201 con el recurso creado.
- [x] Si faltan campos obligatorios, la API responde con un código de error adecuado.

## HU-003: Implementación de Operación READ (Listar y Buscar por ID) en Clientes

**Tipo:** Historia de Usuario

**Prioridad:** Alta

**Estimación:** [ ] Story Points

### Descripción

Como usuario del sistema, quiero consultar los registros del catálogo de clientes mediante endpoints GET para visualizar la información almacenada en PostgreSQL.

### Criterios de Aceptación

- [x] Se implementa el endpoint `GET /api/clients` que retorna la lista completa de clientes con código HTTP 200.
- [x] Se implementa el endpoint `GET /api/clients/{id}` que retorna un cliente específico por su identificador.
- [x] Si el ID consultado no existe en la base de datos, el microservicio retorna un código HTTP 404 (Not Found).

## HU-004: Implementación de Operación UPDATE en Microservicio de Clientes

**Tipo:** Historia de Usuario

**Prioridad:** Media

**Estimación:** [ ] Story Points

### Descripción

Como operador del sistema, quiero actualizar los datos de un cliente existente enviando una petición PUT para mantener la información al día en PostgreSQL.

### Criterios de Aceptación

- [x] Se implementa el endpoint `PUT /api/clients/{id}` en el controlador REST.
- [x] El servicio valida si el cliente existe antes de aplicar los cambios.
- [x] Si el registro existe, se actualizan sus atributos en la base de datos y se retorna HTTP 200 con el objeto modificado.
- [x] Si el cliente no existe, se retorna un código HTTP 404.

## HU-005: Implementación de Operación DELETE en Microservicio de Clientes

**Tipo:** Historia de Usuario

**Prioridad:** Media

**Estimación:** [ ] Story Points

### Descripción

Como administrador del sistema, quiero eliminar un cliente del catálogo mediante una petición DELETE para limpiar registros obsoletos en PostgreSQL.

### Criterios de Aceptación

- [x] Se implementa el endpoint `DELETE /api/clients/{id}` en el microservicio.
- [x] El sistema verifica la existencia del registro antes de proceder a la eliminación.
- [x] Al completarse la baja de forma exitosa, se retorna un código HTTP 204 (No Content).
- [x] Si el recurso no se encuentra, se retorna un código HTTP 404.

## HU-006: Creación de Microservicio para Catálogo de Proveedores (MySQL)

**Tipo:** Historia Técnica / Configuración

**Prioridad:** Alta

**Estimación:** [ ] Story Points

### Descripción

Como desarrollador backend, quiero configurar un nuevo microservicio Spring Boot para el catálogo de "Proveedores" utilizando **MySQL** como motor de persistencia relacional.

### Criterios de Aceptación

- [x] Se crea un nuevo proyecto Spring Boot con las dependencias de *Spring Web*, *Spring Data JPA* y *MySQL Driver*.
- [x] Se configura el archivo `application.properties` con la URL, usuario, contraseña y dialecto de MySQL en un puerto independiente (ej. `8082`).
- [x] Se define la entidad `Supplier` mapeada a una tabla de MySQL con atributos básicos (`id`, `companyName`, `contactEmail`).
- [x] Se verifica la correcta conexión y creación de la tabla al arrancar la aplicación.

## HU-007: Implementación de CRUD Completo para Proveedores (MySQL)

**Tipo:** Historia de Usuario

**Prioridad:** Alta

**Estimación:** [ ] Story Points

### Descripción

Como operador de compras, quiero realizar operaciones CRUD completas sobre el catálogo de proveedores para gestionar la red de abastecimiento almacenada en MySQL.

### Criterios de Aceptación

- [x] Se implementa el endpoint `POST /api/suppliers` para la creación de proveedores.
- [x] Se implementan los endpoints `GET /api/suppliers` y `GET /api/suppliers/{id}` para la consulta de registros.
- [x] Se implementa el endpoint `PUT /api/suppliers/{id}` para la modificación de datos de un proveedor.
- [x] Se implementa el endpoint `DELETE /api/suppliers/{id}` para la baja del registro en MySQL.
- [x] Todas las operaciones responden con los códigos de estado HTTP correctos (200, 201, 204, 404).

## HU-008: Creación de Microservicio para Catálogo de Bitácoras (Postgres)

**Tipo:** Historia Técnica / Configuración

**Prioridad:** Alta

**Estimación:** [ ] Story Points

### Descripción

Como arquitecto de software, quiero inicializar un microservicio Spring Boot para un catálogo de documentos o registros simples utilizando **Postgres** como base de datos.

### Criterios de Aceptación

- [x] Se crea un proyecto Spring Boot con la dependencia de *Spring Data Postgres* y *Spring Web*.
- [x] Se configura la conexión a Postgres mediante `spring.data.Postgres.uri` en un puerto dedicado (ej. `8083`).
- [x] La aplicación arranca sin errores de conexión con el servidor Postgres.

## HU-009: Implementación de Operaciones CRUD en Microservicio con Postgres

**Tipo:** Historia de Usuario

**Prioridad:** Alta

**Estimación:** [ ] Story Points

### Descripción

Como auditor del sistema, quiero consumir las operaciones CRUD de la API REST para gestionar documentos de bitácora almacenados en Postgres.

### Criterios de Aceptación

- [x] Se crea la interfaz `LogRepository` extendiendo de `JpaRepository<LogEntry, String>`.
- [x] Se implementa el endpoint `POST /api/logs` para registrar un nuevo documento en Postgres (retorna HTTP 201).
- [x] Se implementan los endpoints `GET /api/logs` y `GET /api/logs/{id}` para consultar las entradas de bitácora.
- [x] Se implementa el endpoint `PUT /api/logs/{id}` para actualizar una entrada existente y `DELETE /api/logs/{id}` para eliminarla.
