# Automation Exercise – Selenium + Java Test Framework

Automated UI and API test framework for [automationexercise.com](https://automationexercise.com), built with Selenium WebDriver and Java. The project follows standard test-automation practices (TestNG for execution and assertions, Allure for reporting, REST Assured included for API-level checks) and is set up as a Maven project so it can be easily cloned and run locally.
The framework applies the Page object model with method chaining for better readability and to facilitate test maintenance, and uses builder and factory patterns for test data management in parameterized tests.
## Documentation

Both UI and API test scenarios covered by this framework are tracked [in a Google Sheets checklist here](https://docs.google.com/spreadsheets/d/1DntUIwpm_i0xmL2AZIFOm5m6XnnJZGUDJBm95-kOiv8/edit?gid=817815568#gid=817815568). I did not set a goal to automate all the possible scenarios in this framework, moreover a lot of validations on this website are not implemented so there was no necessity to test them. I left corresponding notes about these cases in the checklist.

## Project Structure

```
src/
└── test/
    └── java/
        └── io/github/matveyvolodin/
            ├── api/                     # API-level test code
            │   ├── client/              # REST Assured client for API interactions
            │   ├── config/              # base request/response spec setup (getBaseSpec)
            │   ├── model/               # API request/response models
            │   └── tests/               # API-level tests using REST Assured
            |       └── data             # Data providers for API tests
            ├── model/                   # User class and UserFactory for test data management
            ├── pages/                   # Page Object Model classes representing app pages
            │   └── components/          # reusable page components (e.g., header, footer)
            └── tests/                   # UI/functional test classes organized by page
                └── components/          # tests for components present on every page (e.g., header, footer)
```


## CI and Allure reports

This repository includes a GitHub Actions workflow (`.github/workflows`) that runs the test suite automatically on pull requests.
The workflow is configured to generate and publish the Allure report as an artifact after each run, so you can view the latest test results [directly from here](https://matveyvolodin.github.io/automationexercise-selenium-java-framework/).

## Tech Stack & Prerequisites

- **Java 17** or higher
- **Git**
- **IntelliJ IDEA** (or any other IDE that supports Maven and TestNG)
- **Google Chrome**
- **Maven** – build and dependency management (bundled with IntelliJ IDEA)
- **Selenium WebDriver 4.44** – browser automation with built-in driver management
- **TestNG 7.12** – test runner, assertions, suite/parallel execution
- **Allure 2.34** – test reporting
- **REST Assured 5.4** – API testing support
- **Lombok** – reduces boilerplate in helper/model classes
- **AspectJ Weaver** – required by Allure for step/attachment annotations

## Getting Started in IntelliJ IDEA

### 1. Clone the repository

```bash
git clone https://github.com/matveyvolodin/automationexercise-selenium-java-framework.git
```

### 2. Open the project

- Launch IntelliJ IDEA and choose **Open**.
- Select the cloned project folder (the one containing `pom.xml`).
- IntelliJ will detect it as a Maven project automatically.

### 3. Download dependencies

IntelliJ usually starts resolving dependencies on its own as soon as the project opens. If it doesn't, or if you add/change a dependency later:

- Open the **Maven** tool window (right-hand sidebar, or **View → Tool Windows → Maven**).
- Click the **Reload All Maven Projects** icon (the circular refresh arrows).

This downloads everything listed in `pom.xml` (Selenium, TestNG, Allure, REST Assured, Lombok, AspectJ) into your local repository.

### 4. Enable annotation processing (for Lombok)

Since the project uses Lombok, make sure annotation processing is turned on:

- **Settings/Preferences → Build, Execution, Deployment → Compiler → Annotation Processors**
- Check **Enable annotation processing**.

If you don't already have it, install the **Lombok plugin**: **Settings/Preferences → Plugins → Marketplace → search "Lombok" → Install**.

## Running the Tests

You can run tests either from IntelliJ directly or via Maven from the terminal.

> Test stability may be affected by the bot-protection on automationexercise.com, which can temporarily block automated requests from CI runner IPs.

### From IntelliJ

- Navigate to a test class (or your TestNG suite XML file, if the project includes one) under `src/test/java`.
- Right-click the class, method, or suite file and choose **Run**.

### From the terminal (Maven)

Run the full test suite:

```bash
mvn clean test
```

Run a single test class:

```bash
mvn clean test -Dtest=SignupPageTest
```

Maven is configured to attach the AspectJ weaver as a Java agent automatically (via the `maven-surefire-plugin` config in `pom.xml`), so Allure step/annotation reporting works out of the box — no extra setup needed.

## Viewing Allure Report

After running the tests, results are written to `target/allure-results`. To generate and open the HTML report:

```bash
mvn allure:serve
```

This builds the report and opens it in your default browser. If you'd rather generate a static report without serving it:

```bash
mvn allure:report
```

The output will be available under `target/site/allure-maven-plugin/index.html`.

