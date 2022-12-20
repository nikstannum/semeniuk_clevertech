# Test task from the company Clever Technology
## A simple application for receiving a sales receipt
##### Author: Nikita Semeniuk

## Description

The idea is to provide the application with a set of parameters (product ID and, if available, discount card ID) passed as command line arguments or as a string in a file, and the application will generate a receipt as a string and output the result to a file or standard output stream.

The form of passing parameters as command line arguments when starting the application:

Java application 3-1 2-5 5-1 card-1

This means that the receipt must contain one product with id=3, five products with id=2, one product with id=5, and a discount for a discount card with id=1 must also be applied.

When transferring raw data in a file (available in the resources folder), the parameters must be the same as when transferring data via the command line. In this case, as command line arguments, you need to pass the path to the file with the source data, for example, src/main/resources/data.txt

Goods and discount cards indicating the discount amount for a particular card are stored both in a relational database and as a collection (HashMap) in the application itself (similar to an in-memory database).

In order to use a database as a data store, you must specify the value true for the use.db key in the properties file (this value has already been set). In order to use the collection, you must specify false.

## Stack used:

Java 17, Java JDBC API, Gradle 7.5 build tool, PostgreSQL database, Lombok plugin, log4j2

## Startup instructions:

1) specify the necessary settings in the properties file (directory for the received file, use of the database, etc.)
2) when using an IDE: specify the command line arguments in the configuration and run the application's main() method (App.class); when using the command line interface, compile the application and run it by specifying the necessary arguments after the class file name (App)
3) depending on the arguments passed (just a set of arguments or a path to a file with source data), the result of the application will be output to the standard output stream or to a file, respectively
