# FrameworkForge – SDET Automation Framework

Enterprise-grade test automation framework designed for scalable UI automation with **Selenium, TestNG, Maven**, and **CI/CD-ready architecture**.

This framework is built with **senior SDET practices**:

* Clean architecture
* Environment-based execution
* CLI control
* CI-ready
* Reporting, logs, screenshots handled correctly
* Thread-safe execution(Parallel execution)
* Docker

---

## Tech Stack

* Java 21
* Selenium 4
* TestNG
* Maven
* Log4j2
* Extent Reports
* Apache POI (Excel DDT)
* Jenkins (CI)
* Docker (containerized runs – planned)

---

## Project Structure

```
FrameworkForge/
│
├── src/
│   ├── main/java/framework/
│   │   ├── config/          # Config loaders
│   │   ├── driver/          # DriverFactory, DriverManager
│   │   ├── utils/           # Excel, Screenshot, Helpers
│   │   └── constants/       # Enums, constants
│   │
│   └── test/
│       ├── java/framework/
│       │   ├── base/        # BaseTestClass
│       │   ├── pages/       # Page Objects
│       │   ├── tests/       # Test classes
│       │   ├── listeners/   # TestNG listeners
│       │   └── dataproviders/
│       │
│       └── resources/
│           ├── config/      # config.properties
│           ├── testdata/    # Excel, JSON
│           ├── suites/      # TestNG XMLs
│           └── log4j2.xml
│
├── test-results/            # Auto-generated (ignored in git)
│   ├── reports/
│   ├── screenshots/
│   └── logs/
│
├── Jenkinsfile
├── pom.xml
├── .gitignore
└── README.md
```

---

## Execution Modes (Profiles)

### Local run (developer)

```bash
mvn clean test -Plocal
```

### CI run (Jenkins / GitHub Actions)

```bash
mvn clean test -Pci
```

### Regression run

```bash
mvn clean test -Pregression
```

### Disable reports

```bash
mvn clean test -Pregression -Pno-report
```

---

## Test Suites

| Suite          | Purpose              |
| -------------- | -------------------- |
| smoke.xml      | Quick validation     |
| sanity.xml     | CI validation        |
| regression.xml | Full coverage        |
| master.xml     | Full + cross browser |

---

## Reporting

* Extent Report (HTML)
* Screenshots on failure
* Logs per execution
* CI-friendly base64 screenshots
* Local relative-path screenshots

Reports are generated under:

```
test-results/reports/
```

---

## CI/CD (Jenkins)

Framework is **pipeline-ready**.

Example:

```bash
mvn clean test -Pci
```

Jenkinsfile included (declarative pipeline).

---

## Docker (Planned)

Next phase:

* Dockerfile
* Selenium Grid
* Headless execution
* Parallel CI scaling

---

## Why this framework is different

* Thread-safe WebDriver
* Environment-based config
* Profile-driven execution
* No hardcoded paths
* CI-friendly design
* Senior-level structure (not tutorial code)

---

## Author

Manoj Kumar
SDET | Automation Engineer
Java | Selenium | TestNG | CI/CD

---
No shortcuts from here.
