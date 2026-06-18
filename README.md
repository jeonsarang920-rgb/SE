# Student Record Management System

## Student Class

The Student class stores student information such as Student ID, Name, Department, and GPA. It also contains constructors, getters, setters.

## Student Manager Class

The Student Manager class manages all student records using an ArrayList. It allows users to add, search, update, delete, and display students.

## File Storage

The system stores student records using:

* Text File (`students.txt`) with `Scanner` and `PrintWriter`
* Binary File (`students.dat`) with `DataInputStream` and `DataOutputStream`
* Object Serialization (`students.ser`) with `ObjectInputStream` and `ObjectOutputStream`

## Report

The system generates a report showing:

* Total Students
* Highest GPA
* Lowest GPA
* Average GPA

## File Management

The `File` class is used to create files and folders automatically and display file information such as name, path, size, and last modified date.

## Backup

Buffered streams are used to create a backup copy of the student records.

## Exception Handling
Try-catch blocks are used to handle errors and prevent the program from crashing

Try-catch blocks are used to handle errors and prevent the program from crashing.
