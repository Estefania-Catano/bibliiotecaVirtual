# 📚 Virtual Library Management System

A comprehensive Java-based library management system that implements Object-Oriented Programming principles and the **Model-View-Controller (MVC)** architectural pattern for efficient book inventory and user management.

## 🏗️ System Architecture

The project follows **MVC** pattern for clear separation of concerns:

- **Model**: Domain entities and business logic (Use Cases)
- **View**: Console-based user interface
- **Controller**: Orchestrates the flow between model and view

## 📁 Project Structure

```
src/
├── App.java                           # Application entry point
├── User/                              # User management module
│   ├── controllers/
│   │   └── UserController.java        # User operations controller
│   ├── models/
│   │   └── User.java                  # User entity
│   └── useCases/
│       └── UserUseCase.java           # User business logic
└── Inventory/                         # Book inventory module
    ├── controllers/
    │   └── InventoryController.java    # Inventory operations controller
    ├── models/
    │   └── Inventory.java             # Book entity
    └── useCases/
        └── InventoryUseCase.java      # Inventory business logic
```

## 🎯 Key Features

### ✅ Core Functionality

- **User Management**: Create, authenticate, and manage user accounts
- **Book Inventory Management**: Add, update, delete, and view books
- **Book Lending System**: Track book borrowing status
- **Admin Authentication**: Secure admin access with limited login attempts
- **Category Organization**: Books organized by categories
- **Real-time Status Tracking**: Monitor book availability and borrowing status

### 📖 System Capabilities

#### 👤 User Management
- User registration and authentication
- Secure login with attempt limitations (max 3 tries)
- User account management

#### 📚 Book Inventory Management
- Add new books to the library
- Update existing book information
- Delete books from the system
- View all registered books with status
- Lend books to users
- Track borrowing status (Available/Borrowed)

## 🔐 Authentication System

The system includes a secure authentication mechanism:

- **Maximum 3 login attempts** before account lockout
- **Credential validation** for admin access
- **User session management**

## 🚀 Installation and Execution

### Prerequisites

- Java JDK 8 or higher
- Java IDE (IntelliJ IDEA, Eclipse, VS Code) or command-line compiler

### Setup Instructions

1. **Clone or download** the project
2. **Navigate** to the project directory:
   ```bash
   cd codificacion
   ```
3. **Compile** all Java files:
   ```bash
   javac -d out src/User/models/*.java src/User/useCases/*.java src/User/controllers/*.java src/Inventory/models/*.java src/Inventory/useCases/*.java src/Inventory/controllers/*.java src/App.java
   ```
4. **Run** the application:
   ```bash
   java -cp out App
   ```

### IDE Execution

1. Import the project into your preferred Java IDE
2. Ensure proper package structure is maintained
3. Run the `App.java` class as the main entry point

## 📋 Main Menu System

### Primary Menu Options

```
=== MAIN MENU ===
1. Create new user
2. Login as admin user
3. View registered users
4. Exit
```

### Admin System Menu

```
=== ADMIN SYSTEM MENU ===
1. View registered books
2. Add new book
3. Update book
4. Lend book
5. Delete book
6. Exit
```

## 🔧 Technical Implementation

### Core Classes

#### 📦 Model Layer (`models/`)

- **`User.java`**:
  - Represents user entities with authentication
  - Methods: `validCredential()`, getters, setters
  - Encapsulates user data and validation logic

- **`Inventory.java`**:
  - Represents book entities with status tracking
  - Properties: title, category, borrowing status
  - Methods: getters, setters, `toString()`

#### 🎮 Controller Layer (`controllers/`)

- **`UserController.java`**:
  - Manages user operations and authentication flow
  - Handles main system navigation
  - Coordinates between user model and view

- **`InventoryController.java`**:
  - Manages book inventory operations
  - Handles admin system functionality
  - Controls book lending and management processes

#### 🔧 Use Cases Layer (`useCases/`)

- **`UserUseCase.java`**:
  - Contains user business logic
  - Handles user CRUD operations
  - Manages authentication processes

- **`InventoryUseCase.java`**:
  - Contains inventory business logic
  - Handles book CRUD operations
  - Manages book status and availability

## 🎮 User Guide

### 1. System Startup
- Launch the application
- Select from main menu options
- Create users or login as admin

### 2. User Management
- **Create User**: Register new users in the system
- **View Users**: Display all registered users
- **Admin Login**: Access admin functionality with authentication

### 3. Book Management (Admin Only)
- **View Books**: Display all books with current status
- **Add Book**: Register new books with title and category
- **Update Book**: Modify existing book information
- **Lend Book**: Change book status to borrowed
- **Delete Book**: Remove books from the system

## ✨ Design Principles

### Object-Oriented Programming Features

- **Encapsulation**: Private attributes with public access methods
- **Abstraction**: Clear separation of concerns across layers
- **Modularity**: Specialized classes for specific functionality
- **Inheritance**: Structured class hierarchy
- **Polymorphism**: Interface-based implementations

### MVC Pattern Benefits

- **Maintainability**: Easy to modify individual layers without affecting others
- **Scalability**: Simple to add new features and functionality
- **Reusability**: Components can be reused across different contexts
- **Testability**: Each layer can be tested independently
- **Separation of Concerns**: Clear responsibility distribution

## 🛠️ Development Features

This project demonstrates:

- **Clean Architecture**: Well-structured code organization
- **MVC Implementation**: Proper separation of model, view, and controller
- **Java Best Practices**: Following Java coding standards and conventions
- **Error Handling**: Comprehensive exception management
- **User Experience**: Intuitive console-based interface
- **Data Management**: Efficient in-memory data operations

## 🔍 System Workflow

1. **Application Start**: Initialize controllers and display main menu
2. **User Registration**: Create new user accounts
3. **Admin Authentication**: Secure login for administrative functions
4. **Book Management**: Full CRUD operations on book inventory
5. **Status Tracking**: Real-time monitoring of book availability
6. **Session Management**: Proper user session handling

## 📊 Data Management

- **In-Memory Storage**: Efficient data management using Java Collections
- **Status Tracking**: Real-time book availability monitoring
- **User Management**: Secure user account handling
- **Error Handling**: Comprehensive exception management

---

*This project serves as a demonstration of Object-Oriented Programming principles, MVC architecture implementation, and Java development best practices in a practical library management context.*
