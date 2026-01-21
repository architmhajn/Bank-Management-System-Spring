# 🏦 Bank Management System (Spring Boot)

A **secure, backend-focused Bank Management System** built using **Spring Boot**, featuring **JWT-based authentication**, **role-based access control**, and **transaction tracking**. This project demonstrates real-world backend engineering practices such as layered architecture, security, database interaction, and REST API design.

---

## 🚀 Key Highlights

* 🔐 **JWT Authentication & Authorization** (Admin/User roles)
* 🧱 **Clean Layered Architecture** (Controller → Service → DAO)
* 💳 **Account Management** (Create, Block, Activate)
* 💰 **Transaction Support** (Deposit, Withdraw, History)
* 🗄️ **MySQL Database Integration**
* 🛡️ **Spring Security with Custom JWT Filter**
* 🧪 **API Tested using PowerShell Invoke-RestMethod**
* 📦 **Maven-based Spring Boot Project**

---

## 🧠 Why This Project Matters

This project is **not a CRUD demo**.

It focuses on:

* Real authentication flows (JWT)
* Secure API access
* Debugging real Spring Boot issues
* Writing production-style backend code

It reflects **industry-level backend development skills** expected from entry-level Java/Spring developers.

---

## 🏗️ Tech Stack

| Layer           | Technology                     |
| --------------- | ------------------------------ |
| Language        | Java 25                        |
| Framework       | Spring Boot 3.5.9              |
| Security        | Spring Security + JWT          |
| Database        | MySQL                          |
| Build Tool      | Maven                          |
| API Testing     | PowerShell (Invoke-RestMethod) |
| Version Control | Git & GitHub                   |

---

## 📁 Project Structure

```
src/main/java/com/bank/bankmanagement
│
├── controller
│   ├── AdminController.java
│   └── AccountController.java
│
├── service
│   └── AccountService.java
│
├── dao
│   ├── AccountDAO.java
│   └── TransactionDAO.java
│
├── model
│   ├── Account.java
│   └── Transaction.java
│
├── security
│   ├── JwtUtil.java
│   ├── JwtFilter.java
│   └── SecurityConfig.java
│
└── BankmanagementApplication.java
```

---

## 🔐 Security Flow (JWT)

1. Admin logs in using credentials
2. Server generates **JWT token**
3. Token is sent in `Authorization: Bearer <token>` header
4. `JwtFilter` validates token on every request
5. Access is granted based on **ROLE_ADMIN / ROLE_USER**

---

## 📌 Core API Endpoints

### 🔑 Authentication

```
POST /api/admin/auth/login
```

Returns JWT token on successful login.

---

### 🏦 Account Management

```
POST   /api/accounts/create
GET    /api/admin/accounts
PUT    /api/admin/block/{accountNo}
PUT    /api/admin/activate/{accountNo}
```

---

### 💸 Transactions

```
POST /api/accounts/deposit
POST /api/accounts/withdraw
GET  /api/accounts/transactions/{accountNo}
```

---

## 🧪 API Testing (Without Postman)

All APIs were tested using **PowerShell**:

```powershell
Invoke-RestMethod \
  -Uri http://localhost:8080/api/admin/accounts \
  -Method GET \
  -Headers @{ Authorization = "Bearer <JWT_TOKEN>" }
```

This proves hands-on understanding beyond GUI tools.

---

## 🗄️ Database Design

### Account Table

* account_no (PK)
* name
* pin (hashed)
* balance
* status (ACTIVE / BLOCKED)

### Transaction Table

* id (PK)
* account_no (FK)
* type (DEPOSIT / WITHDRAW)
* amount
* timestamp

---

## ⚙️ How to Run Locally

### 1️⃣ Clone Repository

```bash
git clone https://github.com/architmhajn/Bank-Management-System-Spring.git
```

### 2️⃣ Configure Database

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/bank_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### 3️⃣ Run Application

```bash
./mvnw clean spring-boot:run
```

Server starts at:

```
http://localhost:8080
```

---

## 📈 Future Enhancements

* 🔄 Refresh Tokens
* 📊 Transaction Analytics
* 👥 User Registration Flow
* 🌐 Frontend (React / Angular)
* 🧪 Unit & Integration Tests

---

## 👨‍💻 Author

**Archit Mahajan**
B.Tech – Artificial Intelligence


---

## ⭐ Final Note

This project reflects:

* Persistence
* Debugging ability
* Real backend understanding

If you’re reviewing this as a recruiter or mentor — **this is not a copied project**. It was built, broken, fixed, and stabilized step by step.

⭐ If you found this useful, consider starring the repository.
