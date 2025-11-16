# TMPS Labs - Techniques & Methods of Projecting Software

**Course:** Techniques & Methods of Projecting Software (TMPS)  
**Student:** Daniela Cebotari  
**Group:** FAF-231  
**University:** Technical University of Moldova

---

## About This Repository

This repository contains laboratory work for the TMPS course, focusing on software design patterns and principles. The projects demonstrate practical implementation of SOLID principles and various design patterns through a progression of increasingly complex systems.

### Repository Structure

```
tmps-labs/
├── lab-0/          # SOLID Principles - Task Management System (Python)
├── lab-1/          # Creational Design Patterns - Superhero Management (Java)
├── lab-2/          # Structural Design Patterns - Superhero Operations (Java)
└── README.md       # This file
```

---

## Lab Overview

| Lab | Topic | Language | Patterns/Principles |
|-----|-------|------|-|
| **Lab 0** | SOLID Principles | Python | SRP, OCP, LSP |
| **Lab 1** | Creational Patterns | Java | Factory, Builder, Prototype, Singleton |
| **Lab 2** | Structural Patterns | Java | Decorator, Composite, Facade |


---

## Design Patterns Summary

### Creational Patterns (Lab-1)
Focus on **object creation mechanisms**

| Pattern | Purpose | Lab-1 Usage |
|---------|---------|-------------|
| **Factory Method** | Type-specific creation | Different hero types (Homelander, Starlight, A-Train, Maeve) |
| **Builder** | Step-by-step construction | Complex heroes with 10+ attributes |
| **Prototype** | Clone templates | Quick hero creation from templates |
| **Singleton** | Single instance | VoughtInternational, TheSevenManager, PrototypeRegistry |

### Structural Patterns (Lab-2)
Focus on **object composition and relationships**

| Pattern | Purpose | Lab-2 Usage |
|---------|---------|-------------|
| **Decorator** | Dynamic enhancement | Temporary power-ups (training, Compound V) |
| **Composite** | Part-whole hierarchies | Individual heroes and teams with uniform interface |
| **Facade** | Simplified interface | Mission execution hiding subsystem complexity |

---

## References

### Books
- "Design Patterns: Elements of Reusable Object-Oriented Software" - Gang of Four (GoF)
- "Head First Design Patterns" - Eric Freeman & Elisabeth Robson
- "Clean Code" - Robert C. Martin
- "Effective Java" - Joshua Bloch

### Online Resources
- [Refactoring.Guru - Design Patterns](https://refactoring.guru/design-patterns)
- [SourceMaking - Design Patterns](https://sourcemaking.com/design_patterns)

### Domain Inspiration
- "The Boys" TV Series (Amazon Prime) - Superhero universe concepts

---

## License

This project is part of academic coursework for educational purposes.

---

## Author

**Daniela Cebotari**  
Group FAF-231  
Technical University of Moldova

---

**Last Updated:** November 2025
