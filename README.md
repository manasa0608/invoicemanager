# invoicemanager


This is a project to fetch invoices from customers, compute tax according to the country and code and stores it in the database. It also provides a list of all invoices after computation.


The project has 2 APIs.
POST: Send the invoice data having details of individual items included.
GET: Retrieve all invoices after tax computation.

Steps involved:
1. Get the invoice details and obtain the list of items
2. For each item compute the tax and make the entry into the invoice_item table.
3. Track the sum of all tax computed in a variable 
4. Add the tax computed to the amount and make entry into the table using JPA
5. JUnit test cases using Mockito.


This project contains:

Java as the programming language
Gradle as build tool
local H2 database 
Insomnia to make API calls

Dependencies:

SpringBoot
Lombok
JPA
H2
JUnit
