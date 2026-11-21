# StayNest API

StayNest is a REST API for a rental property platform in Azerbaijan. Users can manage rental properties through a simple backend system.

## Project Information

- Project: StayNest
- API Version: v1
- Status: Assignment 2 — Mock REST API

## Team Members

- Novruz Mirishli — 000019638
- Shamsi Nagiev — 000018974

## Technologies

- Java 21
- Spring Boot
- Maven
- Jakarta Bean Validation
- In-memory mock repository

## Features

- Create, view, update, and delete rental properties
- Mock Bearer token authentication
- Role-based authorization
- Owner-based access control
- Input validation
- Consistent JSON responses
- HTML greeting page
- Structured error handling

## How to Run

### Requirements

- Java 21
- Internet connection for initial Maven dependencies

### Start the Application

**macOS / Linux**

```bash
./mvnw spring-boot:run
```

**Windows**

```powershell
.\mvnw.cmd spring-boot:run
```

The API runs at:

http://localhost:8080

## Authentication

The API uses mock Bearer token authentication.

| Token | User | Role |
|---|---|---|
| user-a-token | user-a | USER |
| user-b-token | user-b | USER |
| admin-token | admin | ADMIN |

Example request header:

Authorization: Bearer user-a-token

Regular users can access and manage only their own properties. Administrators can access and manage all properties.

**Note:** The application uses an in-memory mock repository. Property data resets when the application restarts.

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/v1/properties | List properties |
| GET | /api/v1/properties/{id} | Get property |
| POST | /api/v1/properties | Create property |
| PUT | /api/v1/properties/{id} | Update property |
| DELETE | /api/v1/properties/{id} | Delete property |
| GET | /api/v1/greeting | HTML greeting page |

## Testing

**macOS / Linux**

```bash
./mvnw clean test
```

**Windows**

```powershell
.\mvnw.cmd clean test
```

## API Documentation

See [API_DOCUMENTATION.md](API_DOCUMENTATION.md) for request formats, validation rules, response examples, and HTTP status codes.

## Postman Collection

Import `StayNest.postman_collection.json` into Postman to test the API endpoints.

## Contributors

See [CONTRIBUTORS.md](CONTRIBUTORS.md) for team contributions.