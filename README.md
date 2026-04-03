# Java Banking Application

A secure desktop banking application built with Java Swing, demonstrating object-oriented programming, event-driven architecture, and cryptographic security practices.

![Application Demo](docs/UpdatedBankingAppV2_0.gif)

## Features

- **Secure Authentication**: SHA-256 password hashing for credential protection
- **User Registration**: Account creation with comprehensive input validation
- **Deposit & Withdrawal**: Real-time transaction processing with balance updates
- **Transaction History**: Timestamped audit trail of all account activity
- **Data Persistence**: File-based storage for user accounts and transaction records
- **Session Management**: Secure logout and account switching capabilities
- **Export Functionality**: Generate formatted bank statements

## Technologies Used

- **Language**: Java SE
- **GUI Framework**: Java Swing
- **Security**: SHA-256 Cryptographic Hashing
- **Data Storage**: File I/O (text-based)
- **Architecture**: Event-Driven, MVC-inspired design

## Installation & Setup

### Prerequisites
- Java JDK 8 or higher
- Java Runtime Environment (JRE)

### Running from Source

1. Clone the repository:
```bash
git clone https://github.com/Orest-Z/Banking-App-Using-Java-Swing.git
cd Banking-App-Using-Java-Swing
```

2. Compile the source code:
```bash
cd src
javac *.java
```

3. Run the application:
```bash
java LoginScreen
```

### Running from JAR (Recommended)

1. Download the latest release from the [Releases](https://github.com/Orest-Z/Banking-App-Using-Java-Swing/releases) page
2. Run the application:
```bash
java -jar BankingApp.jar
```

## Usage

### Creating an Account
1. Launch the application
2. Click "Create New Account"
3. Enter a username (3-20 alphanumeric characters)
4. Set a password (minimum 6 characters)
5. Confirm your password

### Banking Operations
1. Login with your credentials
2. Enter transaction amounts in the input field
3. Click "Deposit" to add funds or "Withdraw" to remove funds
4. View your transaction history in real-time
5. Click "Save" to persist your data
6. Use "Download History" to export a bank statement

## Project Structure

```
Banking-App-Using-Java-Swing/
├── src/
│   ├── LoginScreen.java          # Authentication interface
│   ├── RegistrationScreen.java   # Account creation
│   └── BankingApp.java           # Main banking operations
├── docs/
│   └── UpdatedBankingAppV2_0.gif # Application demo
└── README.md
```

## Key Learning Outcomes

This project demonstrates:
- **Object-Oriented Programming**: Encapsulation, separation of concerns, single responsibility principle
- **Security Best Practices**: Password hashing, input validation, sanitization
- **GUI Development**: Event listeners, component hierarchy, user feedback mechanisms
- **Data Management**: File I/O operations, data persistence, format compatibility
- **Error Handling**: Exception management, user-friendly error messages, defensive programming

## Security Features

- Passwords are never stored in plain text
- SHA-256 cryptographic hash function for password security
- Username validation prevents file system vulnerabilities
- Input sanitization for all user-provided data
- Transaction amount validation to ensure data integrity

## Future Enhancements

- [ ] Migration to JavaFX for modern UI
- [ ] Database integration (SQLite/MySQL)
- [ ] Enhanced password requirements (complexity rules)
- [ ] Multi-account support (Savings, Checking)
- [ ] Interest calculation functionality
- [ ] PDF export for bank statements
- [ ] Transaction search and filtering

## Screenshots

### Login Screen
The application features a professional authentication interface with secure password handling.

### Banking Dashboard
Real-time balance updates and comprehensive transaction history tracking.

## Technical Documentation

For detailed technical documentation, architecture diagrams, and implementation details, see the [full documentation](docs/).

## System Requirements

- **Operating System**: Windows, macOS, or Linux
- **Java Version**: JDK/JRE 8 or higher
- **Memory**: Minimum 512 MB RAM
- **Storage**: 50 MB available disk space

## Contributing

This is a portfolio/learning project, but suggestions and feedback are welcome. Feel free to open an issue or submit a pull request.

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

**Orest Z**
- GitHub: [@Orest-Z](https://github.com/Orest-Z)
- Repository: [Banking-App-Using-Java-Swing](https://github.com/Orest-Z/Banking-App-Using-Java-Swing)

---

**Note**: This is an educational project designed to demonstrate software development skills. It is not intended for production use in real banking environments.
