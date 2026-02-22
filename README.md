# ShoppingApp – Spring Boot E-Commerce Backend

## 📌 Overview
ShoppingApp is a production-style e-commerce backend built using Spring Boot and Spring Security.  
The project implements complete user authentication, guest cart handling, order processing, and Razorpay payment integration with secure verification and refunds.

This backend is designed to follow real-world backend architecture and business logic used in modern e-commerce systems.

---

## 🚀 Features
- JWT-based Authentication (User & Admin)
- Guest Cart support using cookies
- Automatic Guest Cart → User Cart merge on login
- Product Management (Admin CRUD operations)
- Cart Management (Add, remove, update quantity, clear cart)
- Order Management (Place order after payment verification, cancel order, refund)
- Razorpay Payment Integration (Create order, verify payment, refund)
- Transaction management using `@Transactional`
- Secure REST APIs with Spring Security
- MySQL integration using Spring Data JPA (Hibernate)

---

## 🧱 Modules
- **Auth Module** – Signup, Login, Logout, Reset Password
- **Cart Module** – Guest Cart, User Cart, Merge on Login
- **Order Module** – Place Order, Cancel Order, View Orders
- **Payment Module** – Razorpay Create Order, Verify Payment, Refund
- **Admin Module** – Manage Products, Orders, Users, Sales

---

## 🛠 Tech Stack
- Java
- Spring Boot
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- MySQL
- Razorpay API
- Maven

---

