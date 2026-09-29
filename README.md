# Catálogos Backend

Monorepo Maven con cuatro microservicios independientes en Java 17 y Spring Boot 3. Cada módulo puede ejecutarse por separado y utiliza su propia base de datos.

## Requisitos

- JDK 17 o superior
- Maven 3.9 o superior
- Docker Desktop con Docker Compose (para levantar PostgreSQL y MySQL localmente)

## Iniciar bases de datos

Desde la raíz del repositorio:

```powershell
docker compose up -d
```

El contenedor PostgreSQL crea `products_db`, `clients_db` y `logs_db`; MySQL crea `suppliers_db`. Las credenciales incluidas (`catalogos` / `catalogos_dev`) son únicamente valores locales de desarrollo. No reutilizarlas en producción.

## Compilar y probar

```powershell
mvn clean verify
```

## Ejecutar servicios

En terminales separadas, desde la raíz:

```powershell
mvn -pl products-service spring-boot:run
mvn -pl clients-service spring-boot:run
mvn -pl suppliers-service spring-boot:run
mvn -pl logs-service spring-boot:run
```

También se pueden empaquetar todos los módulos con `mvn clean package` y ejecutar el JAR de cada módulo. Puertos predeterminados: productos `8080`, clientes `8081`, proveedores `8082` y bitácoras `8083`.

Las conexiones aceptan variables de entorno `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` y `SERVER_PORT`; revisa el `application.properties` de cada servicio para los valores predeterminados locales.

## Endpoints

| Servicio | Base URL | Operaciones |
|---|---|---|
| Productos | `http://localhost:8080/api/products` | GET, GET `/{id}`, POST, PUT `/{id}`, DELETE `/{id}`, GET `/search?name=texto` |
| Clientes | `http://localhost:8081/api/clients` | GET, GET `/{id}`, POST, PUT `/{id}`, DELETE `/{id}` |
| Proveedores | `http://localhost:8082/api/suppliers` | GET, GET `/{id}`, POST, PUT `/{id}`, DELETE `/{id}` |
| Bitácoras | `http://localhost:8083/api/logs` | GET, GET `/{id}`, POST, PUT `/{id}`, DELETE `/{id}` |

POST devuelve `201 Created`, DELETE `204 No Content`, las búsquedas inexistentes `404 Not Found` y las entradas inválidas `400 Bad Request`.

### Ejemplos JSON

Producto:

```json
{"name":"Teclado","description":"Mecánico","price":49.99,"stock":20}
```

Cliente:

```json
{"fullName":"Ana Pérez","email":"ana@example.com","phone":"555-0100"}
```

Proveedor:

```json
{"companyName":"Distribuidora Central","contactEmail":"ventas@example.com"}
```

Bitácora:

```json
{"message":"Se registró la operación"}
```

## Decisiones del backlog

- En productos se implementan `ProductRepository` y `TicketRepository` para cubrir los criterios de aceptación del backlog.
- En bitácoras se usa un ID `String` UUID, campo `message` y marca temporal generada/actualizada por el servidor.
- Los repositorios y servicios de clientes, proveedores y bitácoras se incluyeron para completar las capas CRUD solicitadas.
- Hibernate usa `ddl-auto=update` para facilitar desarrollo local. Para producción, reemplazarlo por migraciones versionadas y credenciales seguras.
