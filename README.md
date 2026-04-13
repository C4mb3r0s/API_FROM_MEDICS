# API FROM MEDICS

REST API for medical clinic management (Voll Med). Handles doctors, patients, and appointment scheduling with business validation rules, JWT authentication, and automated database migrations.

---

## Technologies

| Technology | Version |
|---|---|
| Java | 17 |
| Spring Boot | 3.3.0 |
| Spring Web | — |
| Spring Data JPA | — |
| Spring Security | — |
| Spring Validation | — |
| MySQL | — |
| Flyway | — |
| Auth0 java-jwt | 4.2.1 |
| SpringDoc OpenAPI / Swagger UI | 2.5.0 |
| Lombok | — |

---

## Project Structure

```
src/main/
├── java/med/voll/api/
│   ├── ApiApplication.java
│   ├── controller/
│   │   ├── AutenticacionController.java
│   │   ├── ConsultaController.java
│   │   ├── MedicoController.java
│   │   └── PacienteController.java
│   ├── domain/
│   │   ├── consulta/
│   │   │   ├── Consulta.java
│   │   │   ├── ConsultaRepository.java
│   │   │   ├── ReservaDeConsultas.java
│   │   │   └── validaciones/
│   │   │       ├── ValidadorConsultaAnticipacion.java
│   │   │       ├── ValidadorFueraDelHorarioConsultas.java
│   │   │       ├── ValidadorMedicoActivo.java
│   │   │       ├── ValidadorMedicoHorarioOcupado.java
│   │   │       ├── ValidadorPacienteActivo.java
│   │   │       └── ValidadorPacienteSinConsultaEnElMismoDia.java
│   │   ├── direccion/
│   │   │   └── Direccion.java
│   │   ├── medico/
│   │   │   ├── Medico.java
│   │   │   ├── MedicoRepository.java
│   │   │   └── Especialidad.java
│   │   ├── paciente/
│   │   │   ├── Paciente.java
│   │   │   └── PacienteRepository.java
│   │   └── usuarios/
│   │       ├── Usuario.java
│   │       └── UsuarioRepository.java
│   └── infra/
│       ├── errores/
│       │   └── TratadorDeErrores.java
│       └── security/
│           ├── SecurityConfigurations.java
│           ├── SecurityFilter.java
│           └── TokenService.java
└── resources/
    ├── application.properties
    └── db/migration/
        ├── V1__create-table-medicos.sql
        ├── V2__alter-table-medicos-add-telefono.sql
        ├── V3__alter-table-medicos-add-activo.sql
        ├── V4__create-table-usuarios.sql
        ├── V5__create-table-pacientes.sql
        └── V6__create-table-consultas.sql
```

---

## Features

- Full CRUD for doctors and patients with logical soft-delete
- Appointment booking with business rule validation (Strategy pattern):
  - Minimum 30-minute advance booking
  - Appointments only within clinic working hours
  - Doctor must be active and available
  - Patient cannot have more than one appointment per day
- Stateless JWT authentication via Spring Security
- Flyway-managed incremental SQL migrations
- Swagger UI documentation at `/swagger-ui.html`
- Global error handler for consistent API responses

---

## Getting Started

### Prerequisites

- Java 17
- MySQL running locally
- Maven

### Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed_api
spring.datasource.username=your_user
spring.datasource.password=your_password
api.security.secret=your_jwt_secret
```

### Run

```bash
./mvnw spring-boot:run
```

API will be available at `http://localhost:8080`  
Swagger UI at `http://localhost:8080/swagger-ui.html`

---

## Author

**C4mb3r0s** — [github.com/C4mb3r0s](https://github.com/C4mb3r0s)
