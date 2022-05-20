# ***TopologyAPI***

- Master Micro Task 2 An API for Topology
- Task 01 for Master Micro Internship - A program that plots a function of x within a given interval.
- This project was generated with JavaFX.

## Authors:

> **Youssef Ali Bazina**.

## Table of Contents

- [Why Java?](#Why-Java?)
- [Design Patterns](#Design-Patterns)
- [UML Class Diagram](#UML-Class-Diagram)
- [Design Decisions](#Design-Decisions)
- [Features](#Features)

## Why Java?

- **Java** is a general-purpose programming language that is class-based, object-oriented, and designed to have as few
  implementation dependencies as possible.
  It is intended to let application developers write once, run anywhere (WORA), meaning that compiled Java code can run
  on all platforms that support Java without the need for recompilation.
- In addition to, it is a multi-paradigm language that has class, object, and method (OOP) principals and provides spring boot framework which I practised it.

## Design Patterns

|    **Design Patterns**     |
|:--------------------------:|
| Data Access Object Pattern |
|      Factory Pattern       |
|     Singleton Pattern      |

- The Data Access Object **DAO** pattern is a **structural design pattern** that allows us to **isolate** the
  application/business layer from the persistence layer (usually a relational database but could be any other
  persistence mechanism) using an abstract API.
- Factory Pattern is a **creational design pattern** that allows us to **create** objects without exposing the creation
  logic to the client which is used between *Component* and other implemented components.
- Singleton Pattern is a **creational design pattern** that allows us to **create** a single object that can be accessed
  by many objects.

## UML Class Diagram

![image](https://drive.google.com/uc?export=view&id=1BX6NsBvCjG4osCCAnpHAirWHWIeHilZ9)

## Design Decisions

- Use of **Data Access Object Pattern** to access the database.
- Use of **Factory Pattern** to create the objects.
- Use of **Singleton Pattern** to create the single instance of the database.
- Use JSON Annotations to create the JSON files.
- Test the application with JUnit using MockMVC.

## Features

- Read a topology from a given JSON file and store it in the memory.
- Write a given topology from the memory to a JSON file.
- Query about which topologies are currently in the memory.
- Delete a given topology from memory.
- Query about which devices are in a given topology.
- Query about which devices are connected to a given netlist node in a given topology.