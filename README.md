# BasicATM

A simple multi-threaded ATM simulation in Java, designed with a basic Domain-Driven Design (DDD) architecture. Supports concurrent user sessions and essential banking operations.

## Features

- User authentication (account number & PIN)
- Check account balance
- Withdraw and deposit funds
- Transfer money between accounts
- Thread-safe operations using locks
- In-memory data storage for accounts and transactions

## Technologies

- Java 17+
- Maven
- IntelliJ IDEA (recommended)
- No external database required

## Domain-Driven Design (DDD) Architecture

This project follows a basic DDD approach:

- **Domain Layer**: Contains core business logic, models, and exceptions (`domain/models`, `domain/service`, `domain/exceptions`).
- **Application Layer**: Handles user interaction and session management (`application/controller`, `application/ui`).
- **Infrastructure Layer**: Provides in-memory implementations for repositories and services (`infrastructure/InMemory`, `infrastructure/Service`).

### Prerequisites

- Java (JDK 17 or higher)
- Maven

### Build & Run

1. **Clone the repository:**

`git clone git@github.com:YounesBousfiha/basicATM.git cd basicATM`

2. **Build the project:**

`mvn clean package`

3. **Run the application:**

`mvn exec:java -Dexec.mainClass="org.example.Main"`

### Debugging

- Use IntelliJ IDEA's built-in debugger.
- Set breakpoints and run in debug mode.
- View active threads in the "Debugger" panel.

## Usage

- On startup, three concurrent ATM sessions are started.
- Follow console prompts to log in and perform operations.
- All input/output is synchronized to avoid console overlap.

## Project Structure

- `src/main/java/org/example/domain/models` - Data models (Account, Transaction)
- `src/main/java/org/example/domain/IRepository` - Repository interfaces
- `src/main/java/org/example/infrastructure/InMemory` - In-memory repository implementations
- `src/main/java/org/example/application/controller` - Controllers and session management
- `src/main/java/org/example/application/ui` - Console UI
- `src/main/java/org/example/infrastructure/Service` - Service layer

## Contributing

1. Fork the repository
2. Create a new branch (`git checkout -b feature-xyz`)
3. Commit your changes
4. Push to your branch
5. Open a pull request

## License

This project is licensed under the MIT License.

## Author

[Younes Bousfiha](https://github.com/YounesBousfiha)