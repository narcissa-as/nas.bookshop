# 📚 Online Bookshop – Spring Boot / Java Backend Project

## Project Overview

This project is a **server-side rendered online bookshop application** built with **Java and the Spring ecosystem**, focusing on **clean backend architecture, domain modeling, and maintainable design** rather than UI complexity.

The application manages core bookshop concepts such as **Books, Authors, Publishers, and Categories**, and demonstrates how a traditional web-based system can be implemented using **Spring Boot, Spring MVC, and Spring Data JPA** following enterprise-grade practices.

This project was developed incrementally with **meaningful, traceable commits**, reflecting real development workflow rather than monolithic delivery.

---

## Key Features

- CRUD operations for core domain entities (Book, Author, Publisher, Category)
- Search functionality for books
- Server-side rendered views using **Thymeleaf**
- Relational domain modeling:
  - One-to-many
  - Many-to-many
- Pagination and transactional data access
- Custom exception handling using `@ControllerAdvice`
- Dedicated error pages (400 / 404)
- Database bootstrap using `data.sql`
- Layered architecture with clear separation of concerns
- Unit and service-level tests using **JUnit**, **Mockito**, and **BDD-style testing**

---

## Architecture & Design

The project follows a **layered architecture** commonly used in enterprise Spring applications:

### Web Layer
- Spring MVC Controllers
- Thymeleaf view templates
- Web configuration and application bootstrap

### Service Layer
- Business logic encapsulated behind service interfaces
- Transaction boundaries defined at the service level

### Data Layer
- Domain entities
- Spring Data JPA repositories
- Command objects and converters for web input handling
- Custom exception definitions

This separation ensures **maintainability**, **testability**, and **clear responsibility boundaries**.

---

## Technology Stack

- **Language:** Java
- **Frameworks:** Spring Boot, Spring MVC, Spring Data JPA, Hibernate
- **Databases:**
  - H2 (in-memory, default for local development)
  - MySQL (configurable via `application.properties`)
- **Testing:** JUnit, Mockito, BDD-style tests
- **Build Tool:** Maven
- **Frontend (supporting):** Thymeleaf, HTML, CSS
- **Version Control:** Git (incremental, task-based commits)

---

## Running the Application Locally

1. Clone the repository
2. Run the application using your IDE or via Maven:
