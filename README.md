# Address Book API

The Address API is a Spring Boot application that provides RESTful endpoints for creating new AddressBooks ,adding friends to an AddressBook of an user and their associated transactions.
It supports operations like create AddressBook, add a friend to an address book, get the list of friends sorted by name, and retrieve common friends in 2 address books of an user  
These APIs can be integrated with appropriate front end frameworks to complete the user experience. The application also includes basic validation mechanisms.

---

## Features

- CRUD operations for AddressBook
- Validation of api parameters for adding a new friend
- In-memory H2 database for development and testing

---

## Requirements

- Java 17+
- Maven 3.8+
- Spring Boot 3.1+
- Postman or any REST client for testing (optional)

---

## Build Application
- mvn clean install
---
## Run application
- mvn spring-boot:run
---
## Access the H2 Console
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (leave blank)
---
## API Endpoints

#### 1\. Create a New AddressBook

-   **URL**: `POST /api/v1/addressbook/create/{userId}`
-   **Headers**:
    -   `Content-Type: application/json`
  -   **Request Body**:\
      `{
          "addressBookName" : "testAddressBook1",
           "userName": "test1"
       }`

* * * * *

#### 2\. Add Friend to the AddressBook 

-   **URL**: `GET /api/v1/addressbook/create/{userId}/{addressBookId}/friends`

-   **Request Body**:\
    `{
        "addressBookName" : "testAddressBook1",
         "userName": "test1"
     }`

* * * * *

#### 3\. Get Friends of a User sorted by name.

-   **URL**: `GET /api/v1/addressbook/get/{userId}/{addressBookId}/friends`


* * * * *

#### 4\. Get Unique Friends of a User in 2 different Address Books.

-   **URL**: `GET get/{userId}/{addressBookId1}/friends/{addressBookId2}`

* * * * *

Running Tests
-------------

### Unit and Integration Tests

To run the test suite, execute:

`mvn test`


* * * * *
Possible Enhancements
-------------------

-   Add Swagger/OpenAPI documentation
-   Implement JWT for authorization
-   Migrate to a persistent database (e.g., PostgreSQL)
-   Add Docker support for containerized deployment
-   Enhance the API to get the unique friend details from more than 2 Address Books. 


