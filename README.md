
# StayNest API

StayNest is a REST API for a rental property platform in Azerbaijan.
Users can manage rental properties through a simple backend system.

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

- Create rental properties
- View properties
- Update properties
- Delete properties
- Mock Bearer token authentication
- Owner-based access control
- Input validation
- Consistent JSON responses
- HTML greeting page

## How to Run

### Requirements

- Java 21
- Internet connection for initial Maven dependencies

### Start the Application

Open PowerShell in the project directory:

```powershell
.\mvnw.cmd spring-boot:run
```

The API runs at:

http://localhost:8080

## Authentication

Use one of these mock tokens:

- user-a-token
- user-b-token
- admin-token

Example header:

Authorization: Bearer user-a-token

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/v1/properties | List properties |
| GET | /api/v1/properties/{id} | Get property |
| POST | /api/v1/properties | Create property |
| PUT | /api/v1/properties/{id} | Update property |
| DELETE | /api/v1/properties/{id} | Delete property |
| GET | /api/v1/greeting | HTML greeting page |

## Testing

Run tests with:

```powershell
.\mvnw.cmd test
```

## API Documentation

See [API_DOCUMENTATION.md](API_DOCUMENTATION.md) for request formats,
validation rules, response examples, and HTTP status codes.

## Contributors

See [CONTRIBUTORS.md](CONTRIBUTORS.md) for team contributions.
