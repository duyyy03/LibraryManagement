# Library Management System

## Overview
The Library Management System is developed to manage books, users, and borrowing/return activities for FPT University's library. The application stores data in binary files and automates various library management processes.

## Features

### Data Management
- **Books.dat**: Stores book details like ID, title, author, year, and availability.
- **Users.dat**: Stores user details like ID, name, date of birth, contact information, and status.
- **Loans.dat**: Stores borrowing and returning transactions.

### Core Functions
1. **Manage Books**:
   - Add, update, and delete books.
   - Display all active books.
2. **Manage Users**:
   - Add, update, and delete users.
   - Display all active users.
3. **Manage Loans**:
   - Borrow and return books.
   - Display all currently borrowed books.
4. **Reporting**:
   - Generate reports on borrowed and overdue books.
5. **Data Storage**:
   - Persist data to files (`Books.dat`, `Users.dat`, `Loans.dat`) and reload on startup.
6. **Exit**: Safely close the application, ensuring all data is stored.

## Object-Oriented Design
- **Data Structure**: Utilizes `HashMap<String, Object>` for storing data.
- **Class Design**: Implements classes for `Book`, `User`, and `Loan` with interfaces for management.

## Usage
The system provides a user-friendly menu for navigating between functions. It ensures data integrity and requires confirmation for critical operations like deletions.
