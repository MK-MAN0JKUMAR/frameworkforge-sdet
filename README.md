# FrameworkForge – Enterprise Automation Framework

FrameworkForge is an **enterprise-grade automation framework** designed for scalable and maintainable browser automation using **Selenium, Java, TestNG, and Maven**.

The framework is built using **production-level SDET practices** including:

* Clean layered architecture
* Thread-safe driver management
* Parallel test execution
* Environment-based configuration
* CI/CD ready pipeline
* Logging, reporting, and screenshots
* Data-driven testing
* CI friendly execution

This project demonstrates how **real-world automation frameworks are engineered**, not just how tests are written.

---

# Tech Stack

| Technology       | Purpose                       |
| ---------------- | ----------------------------- |
| Java 21          | Core language                 |
| Selenium 4       | Browser automation            |
| TestNG           | Test orchestration            |
| Maven            | Build & dependency management |
| Log4j2           | Logging                       |
| Extent Reports   | Reporting                     |
| Apache POI       | Excel data driven testing     |
| Jenkins          | CI/CD execution               |
| Docker (planned) | Containerized automation      |

---

# Framework Architecture

The framework follows a **layered automation architecture** to keep responsibilities separated and maintainable.

```
Tests (TestNG)
      │
      ▼
Base Test Layer
      │
      ▼
Page Object Layer
      │
      ▼
Utility Layer
(WaitUtil, ScreenshotUtil, ExcelUtil)
      │
      ▼
Driver Management
(DriverFactory + DriverManager)
      │
      ▼
WebDriver Execution
(Selenium)
```

### Key design principles

* Separation of concerns
* Reusable utilities
* Centralized driver lifecycle
* Environment configuration isolation
* Thread-safe parallel execution

---

# Automation Execution Flow

```
TestNG Suite
    │
    ▼
BaseTestClass
    │
    ▼
DriverFactory
    │
    ▼
Browser Launch
    │
    ▼
Page Objects
    │
    ▼
Wait Utilities
    │
    ▼
Test Actions
    │
    ▼
Assertions
    │
    ▼
Reporting + Logs + Screenshots
```

---

# Project Structure

```
FrameworkForge
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── framework/
│   │               ├── config
│   │               │   └── ConfigReader.java
│   │               │
│   │               ├── driver
│   │               │   ├── DriverFactory.java
│   │               │   └── DriverManager.java
│   │               │
│   │               └── utils
│   │                   ├── DataGenerator.java
│   │                   ├── ExcelUtility.java
│   │                   └── WaitUtil.java
│   │
│   │
│   └── test/
│       └── java/
│           ├── framework/
│           │       │
│           │       ├── base
│           │       │   └── BaseTestClass.java
│           │       │
│           │       ├── dataproviders
│           │       │   └── DataProviders.java
│           │       │
│           │       ├── listeners
│           │       │   ├── ExtentReportListener.java
│           │       │   ├── RetryAnalyzer.java
│           │       │   └── RetryTransformer.java
│           │       │
│           │       ├── pages
│           │       │   ├── MyHomePage.java
│           │       │   ├── UserLoginPage.java
│           │       │   └── UserRegistrationPage.java
│           │       │
│           │       ├── report
│           │       │   └── ExtentFactory.java
│           │       │
│           │       ├── tests
│           │       │   ├── UserLoginTest.java
│           │       │   ├── UserLoginTest_DDT.java
│           │       │   └── UserRegistrationTest.java
│           │       │
│           │       └── utils
│           │           ├── ScreenshotUtil.java
│           │           └── TestDataManager.java
│           │
│           └── resources/
│                   │
│                   ├── config
│                   │   └── config.properties
│                   │
│                   ├── META-INF/
│                   │       └── services
│                   │            │
│                   │            ├── org.testng.IAnnotationTransformer
│                   │            └── org.testng.ITestNGListener
│                   │
│                   ├── suites
│                   │   ├── smoke.xml
│                   │   ├── sanity.xml
│                   │   ├── regression.xml
│                   │   └── master.xml
│                   │
│                   ├── testdata
│                   │   └── loginData.xlsx
│                   │
│                   └── log4j2.xml
│
├── test-results
│   ├── reports
│   │      └── ExtentReport_2026.03.04.16.30.24.html
│   │
│   ├── screenshots
│   │      └── userShouldRegisterSuccessfully_2026.02.01.17.35.18.png
│   │
│   └── logs
│          └── automation-2026-02-04.log
│
├── .github
│       └── workflows
│                 └── ui-tests.yml
│
├── Jenkinsfile
├── docker-compose.yml
├── Project Structure
├── pom.xml
└── README.md
```

---

# Parallel Execution Model

FrameworkForge uses **ThreadLocal WebDriver management** to allow safe parallel execution.

```
Test Thread 1  → Driver Instance 1
Test Thread 2  → Driver Instance 2
Test Thread 3  → Driver Instance 3
```

Benefits:

* No driver collision
* No shared state
* Parallel test execution
* Faster CI pipelines

---

# Stability Layer (Test Reliability)

To reduce flaky tests the framework includes a **stability layer**.

```
Test Action
     │
     ▼
WaitUtil
     │
     ▼
Explicit Wait
     │
     ▼
Clickable / Visible Check
     │
     ▼
Safe Selenium Interaction
```

This avoids:

* element not clickable
* stale element
* timing issues

---

# CI/CD Pipeline

FrameworkForge integrates with **Jenkins pipelines**.

```
Developer Push
       │
       ▼
GitHub Repository
       │
       ▼
Jenkins Pipeline Trigger
       │
       ▼
Build Stage
(mvn clean compile)
       │
       ▼
Test Execution
(smoke / sanity / regression)
       │
       ▼
Report Generation
       │
       ▼
Artifacts (Reports + Screenshots)
```

---

# Execution Modes

Framework supports **profile based execution**.

### Local Execution

```
mvn clean test -Plocal
```

---

### CI Execution

```
mvn clean test -Pci
```

---

### Regression Suite

```
mvn clean test -Pregression
```

---

### Disable Reports

```
mvn clean test -Pregression -Pno-report
```

---

# Test Suites

| Suite          | Purpose                        |
| -------------- | ------------------------------ |
| smoke.xml      | Quick validation               |
| sanity.xml     | CI pipeline verification       |
| regression.xml | Full functional coverage       |
| master.xml     | Full + cross browser execution |

---

# Reporting

Framework generates multiple artifacts.

| Artifact      | Description         |
| ------------- | ------------------- |
| Extent Report | HTML report         |
| Screenshots   | Captured on failure |
| Logs          | Execution logs      |
| CI artifacts  | Uploaded in Jenkins |

Reports location:

```
test-results/reports
```

---

# CI Friendly Design

FrameworkForge is designed for **CI environments**.

Capabilities:

* Headless browser support
* Environment configuration
* Parameterized execution
* Test grouping
* Artifact generation
* Jenkins pipeline integration

---

# Future Enhancements

Planned improvements:

* Docker based execution
* Selenium Grid integration
* Cross browser matrix execution
* Cloud execution (BrowserStack / LambdaTest)
* Test analytics dashboards

---

# Why This Framework

This framework demonstrates **real automation engineering practices**:

* layered architecture
* driver lifecycle control
* test reliability strategy
* CI/CD integration
* scalable project structure

It is designed as a **portfolio level SDET automation framework**, not tutorial code.

---

# Author

Manoj Kumar
SDET | Automation Engineer

Tech Stack
Java | Selenium | TestNG | Maven | Jenkins | CI/CD

