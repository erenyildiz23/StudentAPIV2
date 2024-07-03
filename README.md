# StudentAPI with React.js Client

This project is a full-stack web application for my Internship that combines a Spring Boot backend with a React.js frontend. The application allows for managing student information, including creating, reading, updating, and deleting student records. 

## Table of Contents
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- **Backend:**
  - Java
  - Spring Boot
  - Spring Data JPA
  - PostgreSQL

- **Frontend:**
  - React.js
  - Axios (for HTTP requests)

- **Others:**
  - Lombok
  - Maven

## Prerequisites

Make sure you have the following installed on your system:

- Java 17 or higher
- Node.js and npm
- PostgreSQL
- Maven

## Installation

### Backend

1. **Clone the repository:**
    ```sh
    git clone https://github.com/erenyildiz23/StudentAPI.git
    cd StudentAPI/studentapi
    ```

2. **Set up PostgreSQL:**
    Create a database named `studentdb` and a user with the necessary permissions. Update the `application.properties` file with your database credentials.

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/studentdb
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the project:**
    ```sh
    ./mvnw clean install
    ```

### Frontend

1. **Navigate to the frontend directory:**
    ```sh
    cd ../student-api-client
    ```

2. **Install dependencies:**
    ```sh
    npm install
    ```

## Running the Application

### Backend

To run the Spring Boot application, navigate to the `studentapi` directory and use the following command:

```sh
./mvnw spring-boot:run
 ```
### Frontend

To start the React application, navigate to the `student-api-client` directory and use the following command:

```sh
npm start
 ```
## API Endpoints

The following endpoints are available in the Student API:

- **GET /api/students:** Retrieve a list of all students.
- **GET /api/students/{id}:** Retrieve a student by ID.
- **POST /api/students:** Create a new student.
- **PUT /api/students/{id}:** Update a student by ID.
- **DELETE /api/students/{id}:** Delete a student by ID.
- **PATCH /api/students/{id}:** Update specific fields of a student by ID.


## Project Structure

```plaintext
StudentAPI/
│
├── studentapi/                 # Spring Boot backend
│   ├── src/main/java/com/student/studentapi/
│   │   ├── controller/         # REST controllers
│   │   ├── dto/                # Data Transfer Objects
│   │   ├── entity/             # JPA entities
│   │   ├── exception/          # Exception handling
│   │   ├── repo/               # Repositories
│   │   ├── security/           # Security configuration
│   │   ├── service/            # Services
│   │   └── StudentapiApplication.java  # Main Spring Boot application
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml                 # Maven configuration
│
└── student-api-client/         # React.js frontend
    ├── public/
    └── src/
        ├── components/         # React components
        ├── App.js              # Main React component
        └── index.js            # React entry point
    ├── package.json            # npm configuration
    └── .gitignore
 ```
## Running the Application with Docker

### Docker Configuration

- **Dockerfile for Backend (`studentapi/Dockerfile`):**
    ```dockerfile
    FROM maven:3.8.4-openjdk-17-slim AS backend-build
    WORKDIR /app
    COPY studentapi/pom.xml .
    COPY studentapi/src ./src
    RUN mvn clean package -DskipTests

    FROM openjdk:17-jdk-alpine
    WORKDIR /app
    COPY --from=backend-build /app/target/*.jar ./app.jar
    ENTRYPOINT ["java", "-jar", "/app/app.jar"]
    ```

- **Dockerfile for Frontend (`student-api-client/Dockerfile`):**
    ```dockerfile
    FROM node:18 AS builder
    WORKDIR /app
    COPY student-api-client/package*.json ./
    RUN npm install
    COPY student-api-client .
    RUN npm run build

    FROM nginx:alpine
    COPY --from=builder /app/build /usr/share/nginx/html
    EXPOSE 80
    CMD ["nginx", "-g", "daemon off;"]
    ```

- **Docker Compose Configuration (`docker-compose.yml`):**
    ```yaml
    version: '3.8'

    services:
      backend:
        build:
          context: .
          dockerfile: studentapi/Dockerfile
        ports:
          - "8080:8080"
        environment:
          SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/studentdb
          SPRING_DATASOURCE_USERNAME: username
          SPRING_DATASOURCE_PASSWORD: password
        depends_on:
          - db

      frontend:
        build:
          context: .
          dockerfile: student-api-client/Dockerfile
        ports:
          - "3000:80"
        depends_on:
          - backend

      db:
        image: postgres:13
        environment:
          POSTGRES_DB: studentdb
          POSTGRES_USER: username
          POSTGRES_PASSWORD: password
        ports:
          - "5432:5432"
    ```

### Running the Application

1. **Ensure Docker and Docker Compose are installed on your system.**

2. **Navigate to the project root directory:**
    ```sh
    cd /path/to/StudentAPI
    ```

3. **Build and run the Docker containers:**
    ```sh
    docker-compose up --build
    ```

4. **The backend will be available at `http://localhost:8080` and the frontend at `http://localhost:3000`.**

## Contributing

If you would like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature/YourFeature`).
6. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

