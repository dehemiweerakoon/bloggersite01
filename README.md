## Spring Boot Blogger Site

# Overview

This is a blogging platform built using Spring Boot that allows users to register, log in, create, edit, update, and delete blog posts. User authentication and authorization are handled using Spring Security.

# Features

1. User Registration

2. User Login & Authentication (Spring Security & JWT)

3. Create, Read, Update, and Delete (CRUD) operations for blog posts

4. Role-based Access Control (Admin/User)

5. RESTful API endpoints

# Technologies Used

* Backend: Spring Boot, Spring Security, Spring Data JPA, Hibernate, MySQL

* Authentication: JWT (JSON Web Token)

* Database: MySQL

* Build Tool: Maven

# Installation & Setup

**Prerequisites**

1. Java 17+

2. MySQL Database

3. Maven

**Steps to Run**

1. Clone the repository
   ```
   git clone https://github.com/dehemiweerakoon/bloggersite01
   cd springboot-blogger
   ```
2. Configure MySQL Database

   Update *application.properties* in *src/main/resources/*:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
3. Build and Run the Application
   ```
   mvn clean install
   mvn spring-boot:run
   ```
![image](https://github.com/user-attachments/assets/d7c37f0e-362c-4f3f-b639-70c5266d3d47)


# Security

  -Passwords are encrypted using BCrypt.
  
  -JWT is used for secure authentication and authorization.





