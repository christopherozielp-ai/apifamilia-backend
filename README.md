# 🏠 APIFAMILIA - API REST Spring Boot + MySQL

API REST para gestión de miembros de una familia, lista para conectar con tu app Angular.

---

## 📋 Requisitos

- Java 17+
- Maven 3.8+
- MySQL 8+

---

## ⚙️ Configuración

### 1. Crear la base de datos
Ejecuta el script SQL:
```bash
mysql -u root -p < src/main/resources/schema.sql
```

### 2. Configurar credenciales
Edita `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/apifamilia
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD_AQUI
```

### 3. Ejecutar el proyecto
```bash
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080/api`

---

## 📡 Endpoints

| Método | URL | Descripción |
|--------|-----|-------------|
| GET | `/api/miembros` | Obtener todos los miembros |
| GET | `/api/miembros/{id}` | Obtener un miembro por ID |
| POST | `/api/miembros` | Crear un nuevo miembro |
| PUT | `/api/miembros/{id}` | Actualizar un miembro |
| DELETE | `/api/miembros/{id}` | Eliminar un miembro |
| GET | `/api/miembros/relacion/{relacion}` | Buscar por relación familiar |
| GET | `/api/miembros/buscar?q=texto` | Buscar por nombre/apellido/email |

---

## 📦 Ejemplo de JSON

### Crear / Actualizar miembro (POST / PUT)
```json
{
  "nombre": "Ana",
  "apellido": "López",
  "email": "ana.lopez@email.com",
  "telefono": "+52 81 1234 5678",
  "relacion": "Hija",
  "fechaNacimiento": "2000-06-15",
  "direccion": "Calle Roble 789",
  "ciudad": "Monterrey",
  "pais": "México"
}
```

### Respuesta exitosa
```json
{
  "id": 1,
  "nombre": "Ana",
  "apellido": "López",
  "email": "ana.lopez@email.com",
  "telefono": "+52 81 1234 5678",
  "relacion": "Hija",
  "fechaNacimiento": "2000-06-15",
  "direccion": "Calle Roble 789",
  "ciudad": "Monterrey",
  "pais": "México"
}
```

---

## 🔗 Conectar con Angular

En tu servicio Angular, usa esta URL base:
```typescript
private apiUrl = 'http://localhost:8080/api/miembros';
```

El controlador tiene `@CrossOrigin(origins = "*")` habilitado para evitar problemas de CORS.

---

## 📁 Estructura del proyecto

```
src/main/java/com/familia/apifamilia/
├── ApifamiliaApplication.java     ← Clase principal
├── controller/
│   └── MiembroController.java     ← Endpoints REST
├── service/
│   └── MiembroService.java        ← Lógica de negocio
├── repository/
│   └── MiembroRepository.java     ← Acceso a BD
├── model/
│   └── Miembro.java               ← Entidad JPA
├── dto/
│   └── MiembroDTO.java            ← Objeto de transferencia
└── exception/
    ├── MiembroNotFoundException.java
    └── GlobalExceptionHandler.java
```
