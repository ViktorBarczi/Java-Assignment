# Simple Library Management System

A Spring Boot application that provides a RESTful API to manage books and their copies in a simple library system.

## Features

- Add, retrieve, update, and delete books
- Manage multiple copies of each book
- Check availability of book copies
- RESTful API with proper request validation and error handling
- Swagger (OpenAPI) documentation for API endpoints
- Unit tests for services and controllers

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (for development/testing)
- Swagger (springdoc-openapi)
- JUnit & Mockito (for testing)

## Getting Started

### Prerequisites

- Java 17 or later
- Maven

### Clone the repository

```bash
git clone https://github.com/your-username/library-management-system.git
cd library-management-system
```

### Run the application

```bash
./mvnw spring-boot:run
```

The app will start at: `http://localhost:8080`

### Swagger UI

Once the app is running, visit:

```
http://localhost:8080/swagger-ui.html
```

or

```
http://localhost:8080/swagger-ui/index.html
```

To explore and test the API.

## API Endpoints

| Method | Endpoint                        | Description                         |
|--------|----------------------------------|-------------------------------------|
| GET    | `/api/books`                    | Get all books                       |
| POST   | `/api/books`                    | Add a new book                      |
| GET    | `/api/books/{id}`              | Get a book by ID (including copies) |
| PUT    | `/api/books/{id}`              | Update a book by ID                 |
| DELETE | `/api/books/{id}`              | Delete a book by ID                 |
| GET    | `/api/books/{id}/copies`       | Get copies of a book                |
| POST   | `/api/books/{id}/copies`       | Add a new copy for a book           |
| PUT    | `/api/books/{id}/copies/{cid}` | Update availability of a copy       |

## Example JSON Payloads

### Add a Book

```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "publishedYear": 2008
}
```

### Update a Book

```json
{
  "title": "Clean Code (2nd Edition)"
  "publishedYear": 1999
}
```

### Update Book Copy Availability

```json
{
  "available": false
}
```

## Testing

To run tests:

```bash
./mvnw test
```

## H2 Database Console

Access the in-memory H2 database at:

```
http://localhost:8080/h2-console
```

**JDBC URL:** `jdbc:h2:mem:testdb`  
**User:** `sa`  
**Password:** `password`

## License

This project is open-source and available under the [MIT License](LICENSE).
