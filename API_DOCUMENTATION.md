
# StayNest API Documentation

## 1. Overview

StayNest is a rental property management REST API.

Base URL:
http://localhost:8080/api/v1

API Version: v1

## 2. Authentication

The API uses mock Bearer token authentication.

Available tokens:
- `user-a-token` — user-a (USER role)
- `user-b-token` — user-b (USER role)
- `admin-token` — admin (ADMIN role)

Example request header:

Authorization: Bearer user-a-token

Regular users can access only their own properties.
Admins can access all properties.

## 3. Property Endpoints

### GET /properties

Returns the properties accessible to the authenticated user.

Authentication: Required

Success status: 200 OK

### GET /properties/{id}

Returns a property by its ID.

Authentication: Required

Success status: 200 OK

Possible errors:
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found

### POST /properties

Creates a new property.

Authentication: Required

Required JSON body:

{
  "title": "Modern Apartment",
  "description": "A comfortable apartment in Baku.",
  "pricePerNight": 100,
  "location": "Baku, Azerbaijan"
}

Success status: 201 Created

Possible errors:
- 400 Bad Request
- 401 Unauthorized

### PUT /properties/{id}

Updates an existing property.

Authentication: Required

Request body: Same fields as POST /properties.

Success status: 200 OK

Possible errors:
- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found

### DELETE /properties/{id}

Deletes a property.

Authentication: Required

Success status: 200 OK

Possible errors:
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found

## 4. Greeting Endpoint

### GET /greeting

Returns an HTML page containing project information,
team members, API endpoints, and project status.

Authentication: Not required

Success status: 200 OK

## 5. Response Format

Successful responses use this structure:

{
  "success": true,
  "message": "Property retrieved successfully",
  "data": {}
}

List responses additionally include metadata:

{
  "success": true,
  "message": "Properties retrieved successfully",
  "data": [],
  "meta": {
    "total": 0,
    "page": 1,
    "perPage": 0
  }
}

Error responses use this structure:

{
  "success": false,
  "error": {
    "code": "NOT_FOUND",
    "message": "Property not found"
  }
}

Validation errors may also include a details array
describing invalid fields.

## 6. Validation Rules

- title: Required, 3 to 100 characters.
- description: Required, 10 to 1000 characters.
- pricePerNight: Required, must be greater than 0.
- location: Required, cannot be blank.

## 7. HTTP Status Codes

- 200 OK: Successful request.
- 201 Created: Property created.
- 400 Bad Request: Invalid input.
- 401 Unauthorized: Missing or invalid token.
- 403 Forbidden: Access denied.
- 404 Not Found: Property does not exist.

## 8. Technology Stack

- Java 21
- Spring Boot
- Maven
- Jakarta Bean Validation
- In-memory mock property repository

## 9. Running the API

macOS / Linux:

./mvnw spring-boot:run

Windows PowerShell:

.\mvnw.cmd spring-boot:run

Greeting page:
http://localhost:8080/api/v1/greeting
