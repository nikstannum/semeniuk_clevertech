# Test task from the company Clever Technology
## A simple application for receiving a sales receipt
##### Author: Nikita Semeniuk

## Description

The idea is to provide the application with a set of parameters (product ID and, if available, discount card ID) passed as command line arguments or as a string in a file, and the application will generate a sales receipt as a string and output the result to a file or standard output stream.

The form of passing parameters as command line arguments when starting the application:

java App 3-1 2-5 5-1 card-1

This means that the receipt must contain one product with id=3, five products with id=2, one product with id=5, and a discount for a discount card with id=1 must also be applied.

When transferring initial data in a file (available in the resources folder), the parameters should have the same form as when transferring data via the command line.

Goods and discount cards indicating the discount amount for a particular card are stored both in a relational database and as a collection in the application itself (similar to an in-memory database).

Stack used:
Java 17, Java JDBC API, Gradle 7.5 build tool, PostgreSQL DB, Lombok plugin, log4j2

## Startup instructions:

1) run the application in the TOMCAT servlet container (this application was developed using the version 9.0.62)
2) open browser (Google Browser should be preferred)
3) open developer tools
4) enter a request in the address bar in the format http://localhost:portNumber/check?params (for example: http://localhost:8080/check?itemId=1&itemId=1&itemId=2&card=1)
