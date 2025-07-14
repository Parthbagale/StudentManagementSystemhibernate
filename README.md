# StudentManagementSystemhibernate
Console-based Student Management using Hibernate 7.0.5, JPA, PostgreSQL

Group Members

Parth Pramod Bagale

Sanchit Sanjay Kale

Kartik Shankar Gade

# Student Management System 🧑‍🎓

![Java](https://img.shields.io/badge/Java-17-blue) 
![Maven](https://img.shields.io/badge/Maven-3.8.6-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![JDBC](https://img.shields.io/badge/JDBC-4.2-green)
![Hibernate](https://img.shields.io/badge/Hibernate-7.0.5.Final-brightgreen)
![Platform](https://img.shields.io/badge/Platform-Console--Based-informational)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

A console-based Java application to manage student records using:

- ✅ Hibernate 7.0.5
- ✅ JPA (Jakarta Persistence API)
- ✅ PostgreSQL database
- ✅ Multithreading
- ✅ File I/O
- ✅ Menu-driven interface
- ✅ Maven-based structure

- ## 🛠️ Technologies Used

- Java 17
- Hibernate ORM 7.0.5
- Jakarta Persistence (JPA)
- PostgreSQL
- Maven (for dependency management)
- File I/O
- Multithreading


## 🛠️ Prerequisites

- Java 17+
- PostgreSQL installed and configured
- Eclipse IDE with Maven support
- Hibernate 7.0.5 dependencies

## 🚀 Features

- Add / View / Update / Delete students
- Auto-save thread for periodic backups
- Export and import data using file I/O
- Neatly formatted SQL logs

## 💾 Database Setup

Create the database:
```sql
CREATE DATABASE studentdb;
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    course VARCHAR(50),
    grade VARCHAR(10)
);
```

🧩 Project Structure
```
StudentManagementSystem/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com.javaproject.app/
│       │       ├── StudentManagement.java
│       │       ├── entity/
│       │       │   └── Student.java
│       │       ├── util/
│       │       │   └── HibernateUtil.java
│
├── resources/
│   └── hibernate.cfg.xml
│
├── pom.xml
└── README.md
```


🖼️ Screenshots


<img width="900" height="694" alt="Screenshot 2025-07-15 021544" src="https://github.com/user-attachments/assets/8cfcf292-f8c6-4440-86a7-1362d9206e00" />

<img width="1125" height="663" alt="Screenshot 2025-07-15 021617" src="https://github.com/user-attachments/assets/95797ecb-8b0f-44aa-a32c-e0076f9a4d6e" />

<img width="818" height="795" alt="Screenshot 2025-07-15 021645" src="https://github.com/user-attachments/assets/75144d2f-18b5-4258-ad48-eb25aa1e6be2" />


<img width="578" height="165" alt="Screenshot 2025-07-15 021712" src="https://github.com/user-attachments/assets/92a83839-f866-47a6-8a1c-b78d512ff396" />

---

## 👨‍💻 Developer

Made with ❤️ by **Parth Pramod Bagale**

- 📍 Location: Solapur, Maharashtra, India
  
- 🛠️ Passionate about Java, Hibernate, and backend development
- 📚 Currently learning: Spring Boot, APIs, and full-stack development



