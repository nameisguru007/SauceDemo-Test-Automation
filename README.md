# SaucePro – E-Commerce Test Automation Framework

## Project Overview
SaucePro is an E-Commerce web application test automation project developed using Selenium WebDriver, Java, TestNG, Maven, and Page Object Model (POM).

The framework automates important end-to-end user workflows, including login, product selection, shopping cart validation, and checkout functionality.

## Technologies Used
- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Apache POI (Excel Data-Driven Testing)
- Extent Reports
- Git & GitHub

## Project Features
- Automated login functionality
- Positive and negative login validation
- Product listing and selection
- Add and remove products from cart
- Shopping cart validation
- Checkout process validation
- Excel-based data-driven testing
- TestNG annotations and assertions
- HTML test execution reports

## Test Coverage
Implemented 17 automated regression test cases covering:
1. Valid login
2. Invalid username
3. Invalid password
4. Empty username
5. Empty password
6. Empty login credentials
7. Product listing validation
8. Product selection
9. Add product to cart
10. Remove product from cart
11. Cart item validation
12. Continue shopping
13. Checkout information validation
14. Empty checkout field validation
15. Checkout overview validation
16. Order completion
17. Logout functionality

## Project Structure
```text
SaucePro
├── src
│   ├── main/java
│   │   ├── base
│   │   ├── pages
│   │   └── utils
│   └── test/java
│       ├── tests
│       └── dataproviders
├── src/test/resources
│   └── testdata
├── reports
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Execution Steps

1. Clone the repository.
2. Import the project into Eclipse as an existing Maven project.
3. Update Maven dependencies.
4. Configure the browser and test data if required.
5. Execute the TestNG suite using `testng.xml`.

## Execution Result
- Total Test Cases: 17
- Passed: 17
- Failed: 0

## Learning & Development
This project was developed as a practical QA Automation learning project, with AI tools such as ChatGPT used for debugging assistance and learning framework implementation.

## Author
Gurushantayya