# 🚆 TrainGo — Train Reservation System

A full-stack Train Reservation System built with **Spring Boot** (Java backend) and **HTML/CSS/JS** (frontend).

## Tech Stack
- **Backend:** Java 17, Spring Boot 3.2, Spring Data JPA, H2 In-Memory DB
- **Frontend:** HTML5, CSS3, Vanilla JavaScript
- **Build Tool:** Maven

## Features
- 🔍 Search trains by source and destination
- 🎫 Book tickets with passenger details and seat class selection
- ❌ Cancel tickets by PNR
- 📋 Check PNR status in real-time
- 📊 View all bookings with status
- 🪑 Live seat availability tracking
- 💰 Dynamic fare calculation by seat class (SL / 3AC / 2AC / 1AC)

## REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/trains` | Get all trains |
| GET | `/api/trains/search?source=X&destination=Y` | Search trains by route |
| GET | `/api/trains/{id}` | Get train by ID |
| POST | `/api/bookings/book` | Book a ticket |
| GET | `/api/bookings/status/{pnr}` | Get PNR status |
| PUT | `/api/bookings/cancel/{pnr}` | Cancel a ticket |
| GET | `/api/bookings` | Get all bookings |

## How to Run

### Prerequisites
- Java 17+
- Maven 3.6+

### Steps
```bash
# 1. Clone / download the project
cd train-reservation

# 2. Build the project
mvn clean install

# 3. Run the application
mvn spring-boot:run
```

### Access
- **Frontend:** http://localhost:8080
- **H2 Database Console:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:traindb`
  - Username: `sa` | Password: *(empty)*

## Project Structure
```
train-reservation/
├── src/main/java/com/trainreservation/
│   ├── TrainReservationApplication.java   ← Entry point
│   ├── model/
│   │   ├── Train.java                     ← Train entity
│   │   └── Booking.java                   ← Booking entity
│   ├── repository/
│   │   ├── TrainRepository.java
│   │   └── BookingRepository.java
│   ├── service/
│   │   ├── TrainService.java              ← Train logic + data seeding
│   │   └── BookingService.java            ← Booking/cancel logic
│   └── controller/
│       ├── TrainController.java           ← REST API for trains
│       └── BookingController.java         ← REST API for bookings
├── src/main/resources/
│   ├── static/index.html                  ← Frontend UI
│   └── application.properties
└── pom.xml
```

## Resume Description
> *"Developed a full-stack Train Reservation System using Java Spring Boot REST API with H2 in-memory database and a vanilla JS frontend. Implemented features including train search, ticket booking with dynamic fare calculation by seat class, PNR status tracking, and ticket cancellation with live seat availability updates."*
