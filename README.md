# 🎬 MovieFlix Booking System – Backend (Source Code)

This repository contains the **backend source code (`src` folder only)** of **MovieFlix**,  
a full-featured **Movie Ticket Booking Application** built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **MySQL**.

The project follows an **industry-standard layered architecture** with clean separation of concerns.

---

## 🧱 Project Architecture

src/main/java/com/kartik/MovieFlix/Booking  
│  
├── Controller        # REST APIs (Auth, Admin, Movies, Shows, Theater, Booking)  
├── DTO               # Request & Response DTOs  
├── Entity            # JPA Entities  
├── Jwt               # JWT Service & Authentication Filter  
├── Repository        # JPA Repositories  
├── Security          # Spring Security Configuration  
├── Service           # Business Logic Layer  
└── MovieFlixBookingApplication.java  

---

## 🔐 Security & Authentication

- JWT-based authentication
- Stateless session management
- BCrypt password encryption
- Role-based authorization
  - ROLE_USER
  - ROLE_ADMIN
- Method-level security using `@PreAuthorize`

---

## 👤 Roles & Access Control

### 👤 USER
- Register & Login
- View movies, theaters & shows
- Create bookings
- View & cancel own bookings

### 👑 ADMIN
- Register admin users (ADMIN-only)
- Add / update / delete movies
- Add / update / delete theaters
- Create & manage shows
- Confirm / cancel bookings
- Access protected admin APIs

---

## 📌 API Modules

### 🔑 Authentication APIs
POST /api/auth/registernormaluser  
POST /api/auth/login  

### 👑 Admin APIs (JWT + ADMIN role required)
POST /api/admin/registeradminuser  

### 🎬 Movie APIs
POST   /api/movies/addmovie (ADMIN)  
GET    /api/movies/getallmovies  
GET    /api/movies/getmoviebygenre  
GET    /api/movies/getmoviebylang  
GET    /api/movies/getmoviebytitle  
PUT    /api/movies/updatemovie/{id} (ADMIN)  
DELETE /api/movies/deletemovie/{id} (ADMIN)  

### 🏢 Theater APIs
POST   /api/theater/addtheater (ADMIN)  
GET    /api/theater/gettheaterbylocation  
PUT    /api/theater/updatetheater/{id} (ADMIN)  
DELETE /api/theater/deletetheater/{id} (ADMIN)  

### 🎭 Show APIs
POST   /api/show/createshow (ADMIN)  
GET    /api/show/getallshows  
GET    /api/show/getshowbymovie/{id}  
GET    /api/show/getshowbytheater/{id}  
PUT    /api/show/updateshow/{id} (ADMIN)  
DELETE /api/show/deleteshow/{id}  

### 🎟 Booking APIs
POST /api/booking/createbooking  
GET  /api/booking/getuserbookings/{id}  
GET  /api/booking/getshowbookings/{id}  
PUT  /api/booking/{id}/confirm  
PUT  /api/booking/{id}/cancel  
GET  /api/booking/getbookingbystatus/{status}  

---

## 🗄 Database Design

- MySQL Database
- JPA / Hibernate ORM
- Tables
  - users
  - user_roles
  - movies
  - theaters
  - shows
  - bookings

---

## 🛠 Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- Hibernate / JPA
- MySQL
- Maven
- REST APIs

---

## 📌 Highlights

- Clean layered architecture
- Secure JWT implementation
- Role-based access control
- Industry-style REST APIs
- Ready for frontend integration
- Resume & placement ready project

---

## 👨‍💻 Author

**Kartik Jindal**  
Backend Developer | Java | Spring Boot | Security | JWT
