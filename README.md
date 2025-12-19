# 📚 Library Management System (Full-Stack)

A **full-stack Library Management System** built using **Spring Boot, PostgreSQL, and Vanilla JavaScript**, designed to manage books, members, and issue/return operations with **real-world business rules** such as availability tracking and fine calculation.

This project demonstrates **clean architecture**, **RESTful API design**, and **frontend–backend integration** without relying on heavy frontend frameworks.

---

## 🚀 Features

### 📖 Book Management

* Add new books with ISBN validation
* View all books with real-time availability
* Track total and available copies

### 👥 Member Management

* Register library members
* Validate member details (name, phone, email)
* View all registered members

### 🔄 Issue & Return System

* Issue books to members
* Prevent issuing already-issued books
* Return books only to the correct member
* Automatically update book availability

### 💰 Fine Calculation

* **7-day borrowing period**
* ₹10 per day fine for overdue returns
* Fine calculated using **Java Time API**
* Fine details returned to frontend

### 🛡 Data Integrity & Validation

* Centralized exception handling
* Backend-enforced business rules
* Input validation using `@Valid`

---

## 🧰 Tech Stack

### Backend

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA**
* **PostgreSQL**
* **Hibernate**
* **RESTful APIs**

### Frontend

* **HTML5**
* **CSS3**
* **Vanilla JavaScript**
* **Fetch API**

### Tools & Practices

* Layered Architecture (Controller / Service / Repository)
* DTO-based request handling
* Global Exception Handling
* Transaction management
* Git version control

---

## 🏗 Project Architecture

```
src/main/java/com/lms
 ├── controller        # REST Controllers
 ├── service           # Business Logic
 ├── repository        # JPA Repositories
 ├── model             # Entities
 ├── dto               # Request DTOs
 ├── exception         # Custom Exceptions & Handler
 └── config            # Configurations

src/main/resources
 ├── static             # Frontend (HTML, CSS, JS)
 └── application.properties
```

---

## 🔗 API Endpoints (Sample)

### Books

```
GET    /api/books
POST   /api/books
```

### Members

```
GET    /api/members
POST   /api/members
```

### Issue / Return

```
POST   /api/issues/issue?bookId={id}&memberId={id}
POST   /api/issues/return?bookId={id}&memberId={id}
```

---

## 🖥 Frontend Pages

| Page            | Description          |
| --------------- | -------------------- |
| `/books.html`   | Manage books         |
| `/members.html` | Manage members       |
| `/issue.html`   | Issue & return books |

Frontend is served directly by Spring Boot using static resources — no separate frontend server required.

---

## ⚙️ Setup & Run Locally

### 1️⃣ Clone Repository

```bash
git clone [https://github.com/your-username/library-management-system.git](https://github.com/Subham401/library-management-system.git)
cd library-management-system
```

### 2️⃣ Create PostgreSQL Database

```sql
CREATE DATABASE lms_db;
```

### 3️⃣ Update Database Config

Edit `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lms_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### 4️⃣ Run Application

```bash
mvn spring-boot:run
```

### 5️⃣ Open in Browser

```
http://localhost:8080/books.html
```

---

Just tell me 👍
