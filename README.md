# Car Rental System

## Project Overview
This project is a lightweight, pure Java implementation of a Car Rental System designed to manage vehicle reservations. It follows a service-oriented architecture to handle core business logic, including inventory management, reservation validation, and collision detection (preventing double-bookings).

The system is built using **Java 21** and **Maven**, utilizing **JUnit 5** for a comprehensive test suite.

## Key Features
* **Inventory Management:** Supports multiple car types (Sedan, SUV, Van).
* **Reservation Logic:** Validates requests against current inventory and existing schedules.
* **Conflict Detection:** specific algorithms to detect date overlaps for individual vehicles.
* **Extensibility:** Designed with clear interfaces to allow for future integration with databases or external APIs.

## Technical Stack
* **Language:** Java 21
* **Build Tool:** Maven 3.x
* **Testing:** JUnit 5 (Jupiter)
