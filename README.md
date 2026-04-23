# AIMS — An Internet Media Store

> A desktop application for browsing and purchasing media products (Books, CDs, DVDs) built with Java and JavaFX.

---

## Screenshots

| Home Page | Database Design |
|:---------:|:---------------:|
| <img src="./assets/images/home_aims.png" alt="Home Page" width="420"> | <img src="./assets/images/aims_db_design.png" alt="Database Design" width="420"> |

---

## Features

- Browse a catalog of media products: **Books**, **CDs**, and **DVDs**
- Add items to a shopping cart and manage quantities
- Place orders with delivery information
- Process payments via an interbank payment subsystem (credit card)
- View order invoices and transaction history
- Persistent data storage with **SQLite**

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 11+ |
| UI Framework | JavaFX 15 |
| Database | SQLite (`sqlite-jdbc`) |
| Testing | JUnit 5 |
| IDE | Eclipse |

---

## Project Structure

```
AIMS-Student/
├── src/
│   ├── App.java                  # Application entry point
│   ├── common/exception/         # Custom exception classes
│   ├── controller/               # Business logic controllers
│   ├── entity/                   # Domain models (Cart, Media, Order, Payment, …)
│   ├── subsystem/                # Interbank payment subsystem
│   ├── utils/                    # Utility classes & configuration
│   └── views/                    # JavaFX FXML layouts & screen handlers
├── test/                         # Unit tests
├── lib/
│   ├── linux/javafx-sdk-15/      # JavaFX SDK for Linux
│   └── win/javafx-sdk-15/        # JavaFX SDK for Windows
└── assets/
    ├── db/                       # SQLite schema & MySQL Workbench file
    └── images/                   # Product and UI images
```

---

## Getting Started

### Prerequisites

- **Java 11** or higher
- **Eclipse IDE** (recommended)
- **JavaFX 15** (bundled under `lib/`)

### 1. Clone the repository

```bash
git clone https://github.com/leminhnguyen/AIMS-Student.git
cd AIMS-Student
```

### 2. Open in Eclipse

Go to **Eclipse** → **File** → **Open Projects from File System…** and select the cloned root directory.

### 3. Configure dependencies

#### SQLite JDBC

**Project** → **Properties** → **Java Build Path** → **Classpath** → **Add JARs…**  
Select `sqlite-jdbc-3.7.2.jar` from the `lib/` directory.

#### JUnit 5

**Project** → **Properties** → **Java Build Path** → **Modulepath** → **Add Library…** → **JUnit** → **Next**.

#### JavaFX

1. Open **Eclipse** → **Window** → **Preferences** → **Java** → **Build Path** → **User Libraries** → **New**.
2. Name the library (e.g., `JavaFX15`) and add all JARs from the appropriate directory:
   - **Linux:** `lib/linux/javafx-sdk-15/lib/`
   - **Windows:** `lib/win/javafx-sdk-15/lib/`
3. Add the user library to the project classpath.

### 4. Add VM arguments

Open **Run** → **Run Configurations…** → **Java Application**, select your launch configuration, and add the following under **VM arguments**:

**Linux:**
```
--module-path lib/linux/javafx-sdk-15/lib --add-modules javafx.controls,javafx.fxml
```

**Windows:**
```
--module-path lib/win/javafx-sdk-15/lib --add-modules javafx.controls,javafx.fxml
```

### 5. Set up the database

Import the SQL schema into SQLite using the file at `assets/db/aims_sqlite.sql`.

---

## Running Tests

Tests are located in the `test/` directory and use **JUnit 5**. Run them from Eclipse via **Run As** → **JUnit Test**.

---

## Authors

| Name | Role | Batch |
|---|---|---|
| nguyenlm | Software Engineering Student | K61 |
| manhvd | Software Engineering Student | K61 |
| hieudm | ICT Student | K61 |

---

## License

This project is intended for educational purposes as part of the Software Engineering curriculum.
