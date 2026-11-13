# Student Management System

A Student Management REST API built with Java 17, Spring Boot, Spring Data JPA and MySQL.

## Features

- Add a student
- Retrieve all students
- Retrieve a student by ID
- Update student details
- Delete a student
- Find students by course
- Request validation and centralized exception handling
- Appropriate HTTP status codes

## Requirements

- JDK 17 or later
- Maven 3.6+
- MySQL 8+
- Postman (for API testing)

## 1. Create the database

Run this in MySQL Workbench or the MySQL terminal:

```sql
CREATE DATABASE student_management;
```

## 2. Configure the database

Open `src/main/resources/application.properties`.

Set your MySQL username and password, or set environment variables:

```bash
export DB_URL="jdbc:mysql://localhost:3306/student_management"
export DB_USERNAME="root"
export DB_PASSWORD="your_mysql_password"
```

Do not commit real database passwords to a public repository.

## 3. Run the application

From the project root:

```bash
./mvnw spring-boot:run
```

If the Maven wrapper is not available on your system, use:

```bash
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.

## API endpoints

| Method | Endpoint | Description | Success status |
|---|---|---|---|
| POST | `/api/students` | Add a student | 201 Created |
| GET | `/api/students` | Get all students | 200 OK |
| GET | `/api/students/{id}` | Get student by ID | 200 OK |
| PUT | `/api/students/{id}` | Update student | 200 OK |
| DELETE | `/api/students/{id}` | Delete student | 204 No Content |
| GET | `/api/students/course/{course}` | Get students by course | 200 OK |

### Example request body

Use this JSON for POST `/api/students` and PUT `/api/students/{id}`:

```json
{
  "name": "Aarav Sharma",
  "email": "aarav@example.com",
  "course": "Computer Science",
  "age": 21
}
```

## Postman testing

Import `postman/Student-Management-System.postman_collection.json` into Postman. Start the Spring Boot application, then run the requests in the collection.

The collection includes all six required endpoints. For GET by ID, PUT and DELETE, create a student first and use the returned ID (the collection uses `1` by default).

## HTTP errors

- `400 Bad Request` – validation failed
- `404 Not Found` – student ID does not exist
- `409 Conflict` – duplicate email
- `500 Internal Server Error` – unexpected server error

## GitHub submission

Create a new public repository named `Student-Management-System`, then run these commands from this project folder:

```bash
git init
git add .
git commit -m "Build Student Management REST API"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/Student-Management-System.git
git push -u origin main
```

Replace `YOUR-USERNAME` with your GitHub username.

## Screenshots for assignment submission

After running the API, take screenshots in Postman showing successful responses for all six endpoints. Save them in the `screenshots/` folder before your final GitHub push. Do not present placeholder screenshots as actual test evidence.
