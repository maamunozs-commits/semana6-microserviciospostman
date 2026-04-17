# Microservicios - Cuidado Mascotas

Proyecto de dos microservicios REST desarrollados con Spring Boot 3 y Oracle XE, para la asignatura **FULLSTACK**.

---

## Microservicios

| Servicio | Puerto | Endpoints base |
|---|---|---|
| servicio-pedidos | 8081 | `/api/pedidos` |
| servicio-citas | 8082 | `/api/citas` |

---

## Tecnologías

- Java 17 / Spring Boot 3.3.5
- Spring Data JPA + Hibernate
- Oracle XE (XEPDB1)
- Bean Validation
- Maven multi-módulo

---

## Requisitos previos

- Java 17+
- Maven 3.8+
- Oracle XE corriendo en `localhost:1521`

---

## Configuración Oracle

Conectado como **SYSTEM** en SQL Developer, ejecutar:

```sql
ALTER PROFILE DEFAULT LIMIT PASSWORD_VERIFY_FUNCTION NULL;
DROP USER CUIDADOMASCOTAS CASCADE;
CREATE USER CUIDADOMASCOTAS IDENTIFIED BY mascotas;
GRANT CONNECT, RESOURCE, UNLIMITED TABLESPACE TO CUIDADOMASCOTAS;
ALTER USER CUIDADOMASCOTAS ACCOUNT UNLOCK;
```

Luego conectado como **CUIDADOMASCOTAS**, ejecutar el script `tablas_oracle.sql` incluido en la raíz del proyecto.

---

## Ejecutar los microservicios

Abrir dos terminales desde la raíz del proyecto:

**Terminal 1:**
```bash
cd servicio-pedidos
mvn spring-boot:run
```

**Terminal 2:**
```bash
cd servicio-citas
mvn spring-boot:run
```

---

## Endpoints disponibles

### Servicio Pedidos — `http://localhost:8081`

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/pedidos` | Obtener todos |
| GET | `/api/pedidos/{id}` | Obtener por ID |
| GET | `/api/pedidos/estado/{estado}` | Filtrar por estado |
| GET | `/api/pedidos/categoria/{categoria}` | Filtrar por categoría |
| POST | `/api/pedidos` | Crear pedido |
| PUT | `/api/pedidos/{id}` | Actualizar pedido |
| DELETE | `/api/pedidos/{id}` | Eliminar pedido |

### Servicio Citas — `http://localhost:8082`

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/citas` | Obtener todas |
| GET | `/api/citas/{id}` | Obtener por ID |
| GET | `/api/citas/estado/{estado}` | Filtrar por estado |
| GET | `/api/citas/veterinario/{nombre}` | Filtrar por veterinario |
| POST | `/api/citas` | Crear cita |
| PUT | `/api/citas/{id}` | Actualizar cita |
| DELETE | `/api/citas/{id}` | Eliminar cita |

---

## Ejemplos de uso

### Crear pedido
```http
POST http://localhost:8081/api/pedidos
Content-Type: application/json

{
  "nombreProducto": "Shampoo para Perros",
  "categoriaProducto": "Higiene",
  "cantidad": 2,
  "precioUnitario": 7990,
  "nombreCliente": "Valentina Reyes",
  "fechaPedido": "2026-04-16"
}
```

### Crear cita
```http
POST http://localhost:8082/api/citas
Content-Type: application/json

{
  "nombreMascota": "Max",
  "tipoMascota": "Perro",
  "nombreDueno": "Luis Mendez",
  "telefonoDueno": "+56911223344",
  "servicio": "Control de peso",
  "veterinario": "Dr. Ramirez",
  "fechaHora": "2026-04-20 09:00"
}
```

---

## Validaciones

Si se envían datos inválidos al POST, el servidor responde **HTTP 400** con el detalle de cada campo que falló.

---

## Autor

Matias Muñoz — FULLSTACK Semana 6
