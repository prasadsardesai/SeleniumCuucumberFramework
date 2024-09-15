# Selenium Automation Framework with Cucumber BDD

## Overview

This project is a Selenium Automation Framework built using Cucumber BDD for automated testing of web applications. 
The framework is designed to be scalable, maintainable, and easy to use, emphasizing Behavior-Driven Development (BDD) principles.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Folder Structure](#folder-structure)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Reporting](#reporting)
- [Contributing](#contributing)
- [License](#license)

## Features

- Supports BDD using Cucumber
- Page Object Model (POM) design pattern
- Configurable test execution
- Cross-browser testing support
- Detailed reporting with Extent Reports
- Integration with Junit

## Prerequisites

Ensure you have the following installed:

- Java JDK (version 11 or above)
- Maven (version 3.6 or above)
- Git
- A compatible web browser (Chrome, Firefox,Safari, Ferefox etc.)


## Installation

1. **Clone the repository:**

   ```bash
   git clone git@github.com:PrasadSardesai/SeleniumCuucumberFramework.git

Install dependencies:

mvn clean install

## Folder Structure
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── 
│   │   │           ├── pages
│   │   │           ├── utils
│   │   │           └── steps
│   │   └── resources
│   │       ├── features
│   │       └── config
│   └── test
│       └── java
│           └── com
│                   └── runners
└── pom.xml

## Usage
Define feature files:

Write your BDD scenarios in the src/main/resources/features directory using the Gherkin syntax.

Implement step definitions:

Implement the steps in the src/main/java/com/steps directory.

Configure your testing framework:

Update the pom.xml to include dependencies for Cucumber, Selenium, and any reporting tools you wish to use.


## Running Tests
To run the tests, execute the following command:

mvn test

You can also run specific scenarios or tags using Cucumber options in the test runner classes located in src/test/java/com/runners.

## Reporting
Reports can be generated using Extent Reports. Configure your reporting in the respective configuration 
files to get detailed insights into your test execution.

### Customization Notes
- **Project URL**: Make sure to replace the GitHub URL in the clone section with your actual repository URL.
- **Folder Structure**: Update the package names, if your project uses different names.
- **Reporting section**: If you use a different reporting tool, update that section accordingly.
- **License**: Ensure you have a `LICENSE` file if you're mentioning one in the README.
