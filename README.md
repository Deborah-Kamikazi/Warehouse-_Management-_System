# Warehouse Management System (WMS) – Backend

A **Spring Boot–based Warehouse Management System (WMS)** designed to model real-world logistics workflows such as warehouse setup, location management, carton handling, inventory receiving, and stock movements.

This project focuses heavily on **business rules**, **transactions**, **optimistic locking**, **concurrency safety**, and **testable service design**, rather than simply implementing CRUD endpoints.

> ⚠️ This is a learning-driven project. Progress is incremental, and understanding **why** things work is more important than completing all tasks.

---

## 🚀 Project Goals

- Model core warehouse logistics domains
- Apply **optimistic locking** to prevent lost updates
- Use **transactions** to guarantee data consistency
- Handle **concurrent updates safely**
- Write **unit and service-level tests** for critical business logic
- Build a backend that reflects **real enterprise system design**

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL / H2 (for testing)
- JUnit 5 & Mockito
- Postman (API testing)
- Maven

---

## 📦 Domain Overview

The system models the following concepts:

- **Warehouse** – Physical storage site
- **Location** – Structured storage coordinates inside a warehouse
- **CartonHeader** – Template describing carton contents
- **SSCC** – Unique identifier for a physical carton
- **Item** – Actual stock stored at a location
- **Stock Movements** – Receiving, moving, adjusting stock
- **Stock History** – Audit trail of inventory changes

---

## ✅ Current Status

| Task | Description | Status |
|-----|------------|--------|
| Task 1 | Warehouse Domain & API | ✅ Completed |
| Task 2 | Location Domain & API | ✅ Completed |
| Task 3 | Carton Header Domain & API | ✅ Completed |
| Task 4–10 | Inventory, Movements, History, Security | ⏳ Pending |

> Endpoints are currently tested using **Postman**.

---

## 🧩 Implemented Features

### ✅ Task 1 – Warehouse Domain & API

**Entity:** `Warehouse`

**Key Features**
- Unique `warehouseNumber`
- Optimistic locking via `@Version`
- Automatic `createdTimestamp` & `updatedTimestamp`
- Safe concurrent updates
- Clear error handling for duplicates
- Unit tests for repository and service layers

**Endpoints**

```http
POST /api/warehouses
PUT  /api/warehouses/{id}

``` 

## ✅ Task 2 – Location Domain & API

Warehouses are divided into structured storage locations so goods can be found efficiently.

Locations are identified by physical coordinates but referenced operationally by a single **generated location code**.

Because locations may be updated concurrently, **optimistic locking** is applied.

### Entity: `Location`

| Field | Description |
|-----|------------|
| id | Primary key |
| row | Physical row |
| section | Physical section |
| shelf | Physical shelf |
| locationCode | Generated code (row-section-shelf) |
| warehouseId | Reference to owning warehouse |
| version | Optimistic locking |
| createdTimestamp | Creation time |
| updatedTimestamp | Last update time |

---

### Business Rules

- `locationCode` is generated automatically from `row-section-shelf` (example: `1-2-4`)
- `(warehouseId, locationCode)` must be unique
- The same `locationCode` may exist in different warehouses
- A location can only be created for an existing warehouse

---

### API Examples

#### Create Location

```http
POST /api/warehouses/{warehouseId}/locations

```
## ✅ Task 3 – Carton Header Domain & API

A carton header represents a template for many identical cartons with the same contents.

In logistics, this avoids repeating product information for every physical box.

Because carton headers may be updated by multiple users, **optimistic locking** is used to prevent accidental overwrites.

### Entity: `CartonHeader`

| Field | Description |
|-----|------------|
| id | Primary key |
| barcode | Unique carton barcode |
| description | Carton contents description |
| version | Optimistic locking |
| createdTimestamp | Creation time |
| updatedTimestamp | Last update time |

---

### API Examples

#### Create Carton Header

```http
POST /api/carton-headers




