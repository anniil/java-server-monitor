Hi There!!

# 🚀 Java Server Monitor

A lightweight **Java + Spring Boot backend** system for remote server monitoring.  
Built to showcase **real-time system metrics, secure authentication, role-based access control, and production-style backend architecture**.

This project focuses on **backend observability and system design**, not UI dashboards or CRUD workflows.

---

## 🔥 Project Overview

- Monitor live server health and system metrics
- Expose metrics and logs via secure REST APIs
- Authenticate users using JWT
- Enforce role-based authorization (Admin / Viewer)
- Designed with scalability, latency, and reliability in mind

---

## ✨ Features

- 📊 Real-time CPU, memory, disk, and uptime metrics
- 📜 Server log monitoring with recent log windowing
- 🔐 JWT-based authentication (cookie-based)
- 👥 Role-based access control
  - ADMIN → full access
  - VIEWER → read-only access
- 🌐 RESTful API architecture
- ⚡ Clean separation of concerns

---

## 🛠 Tech Stack

- Java
- React.Js
- Spring Boot
- Spring Security
- Maven
- Web Sockets
- JWT (JSON Web Tokens)
- Linux OS system metrics

---

## 📂 Folder Structure
java-server-monitor/
├── src/main/java/
│ ├── controller/ # REST API controllers
│ ├── service/ # Business logic
│ ├── security/ # JWT, filters, role checks
│ ├── config/ # Application & security configs
│ └── monitor/ # System metrics collectors
│
├── src/main/resources/
│ └── application.yml # Application configuration
│
├── pom.xml # Maven dependencies
└── README.md

🧠 Design Decisions
Backend-first observability (no UI dependency)

Stateless JWT authentication for scalability

Log instability handled using recent-log windowing

Clear separation between controller, service, and security layers

Built to resemble real-world production backend systems

📈 Future Enhancements
WebSocket-based real-time log streaming

Prometheus & Grafana integration

Alerting and threshold-based notifications

Dockerized deployment

Multi-server monitoring support

🎯 Why This Project?
This project demonstrates:

Backend system design principles

Secure authentication & authorization

Real-time data handling

Production-grade Java & Spring Boot practices

Well-suited for Backend Engineer / SDE / Platform roles.

🤝 Contributing

Contributions are welcome.
For major changes, please open an issue before submitting a pull request.

