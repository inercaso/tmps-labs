# Laboratory Work #3 - Behavioral Design Patterns

**Course:** Techniques & Methods of Projecting Software (TMPS)  
**Author:** Daniela Cebotari  
**Group:** FAF-231  
**Topic:** Behavioral Design Patterns  
**Domain:** Vintage Film Reel Archive (The Obsidian Vault)

---

## Table of Contents
1. [Introduction](#introduction)
2. [Objectives](#objectives)
3. [Theoretical Background](#theoretical-background)
4. [Implementation & Explanation](#implementation--explanation)
5. [Project Architecture](#project-architecture)
6. [Results & Testing](#results--testing)
7. [Conclusions](#conclusions)

---

## Introduction

### Context and Motivation

This laboratory work focuses on the **Vintage Film Reel Archive**, a system designed to manage, restore, and preserve a collection of classic films from the 1920s and 1930s. Unlike previous labs that focused on creating objects (Creational) or structuring them (Structural), this lab addresses the **interaction and communication** between objects using Behavioral Design Patterns.

The "Obsidian Vault" archive faces specific challenges:
- **Preservation:** Films decay over time and need various restoration techniques.
- **History:** Restoration is a delicate process; archivists need to track changes and revert mistakes.
- **Access:** The collection needs to be browsed in multiple ways (by decade, genre, condition) without exposing its internal storage structure.

### Why Behavioral Patterns?

To solve these challenges, we employ behavioral patterns which are concerned with algorithms and the assignment of responsibilities between objects:

**Problem 1:** Restoration involves complex algorithms (Chemical, Digital, Manual) that change based on the film's needs.
- **Solution:** Strategy Pattern - Encapsulate restoration algorithms and make them interchangeable.

**Problem 2:** We need to traverse the film collection based on different criteria (Year, Genre, Condition) without exposing the underlying list structure.
- **Solution:** Iterator Pattern - Provide a standard way to traverse a collection.

**Problem 3:** We must track the condition of a film over time and be able to undo restoration steps if they damage the film.
- **Solution:** Memento Pattern - Capture and restore an object's internal state without violating encapsulation.

---

## Objectives

As specified in the laboratory requirements:

1. **Study and understand Behavioral Design Patterns** - Learn how patterns handle communication and control flow between objects.
2. **Implement at least 3 behavioral design patterns** - Memento, Iterator, and Strategy.
3. **Create a functional application** - A CLI tool for managing the film archive.
4. **Organize code by responsibilities** - Clear package structure separating concerns.
5. **Document implementation** - Comprehensive report with theory, code examples, and results.

---

## Theoretical Background

### What are Behavioral Design Patterns?

**Definition:** Behavioral design patterns are concerned with algorithms and the assignment of responsibilities between objects. They describe not just patterns of objects or classes but also the patterns of communication between them.

**Key Principle:** These patterns characterize complex control flow that's difficult to follow at run-time. They shift your focus away from flow of control to let you concentrate just on the way objects are interconnected.

### Categories of Behavioral Patterns

Common behavioral patterns include:

| Pattern | Purpose | When to Use |
|---------|---------|-------------|
| **Chain of Responsibility** | Pass requests along a chain of handlers | Decoupling sender and receiver |
| **Command** | Encapsulate a request as an object | Queueing, logging, or undoing operations |
| **Iterator** | Traverse elements of a collection sequentially | Accessing elements without exposing underlying representation |
| **Mediator** | Define an object that encapsulates how a set of objects interact | Reducing chaotic dependencies between objects |
| **Memento** | Capture and restore an object's internal state | Implementing undo mechanisms |
| **Observer** | Notify multiple objects about state changes | Event handling systems |
| **State** | Alter behavior when internal state changes | Finite state machines |
| **Strategy** | Define a family of algorithms and make them interchangeable | Selecting algorithms at runtime |
| **Template Method** | Define skeleton of an algorithm in superclass | Letting subclasses override specific steps |
| **Visitor** | Separate algorithms from the objects on which they operate | Adding operations to complex object structures |

This lab implements **Memento**, **Iterator**, and **Strategy** patterns.

---

### Pattern 1: Memento Pattern

#### Theory

**Intent:** Without violating encapsulation, capture and externalize an object's internal state so that the object can be restored to this state later.

**Key Concepts:**
- **Originator:** The object whose state needs to be saved (`FilmReel`).
- **Memento:** The immutable object that stores the state (`FilmSnapshot`).
- **Caretaker:** The object that keeps track of the mementos (`RestorationHistory`).

**Benefits:**
- Preserves encapsulation boundaries.
- Simplifies the Originator by moving storage responsibility to the Caretaker.
- Provides an easy recovery mechanism.

---

### Pattern 2: Iterator Pattern

#### Theory

**Intent:** Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.

**Key Concepts:**
- **Iterator Interface:** Defines methods for accessing and traversing elements (`ArchiveIterator`).
- **Concrete Iterator:** Implements the iterator interface (`SequentialIterator`, `DecadeIterator`, etc.).
- **Aggregate:** The collection interface.
- **Concrete Aggregate:** The collection implementation (`FilmArchive`).

**Benefits:**
- Supports variations in the traversal of a collection.
- Simplifies the Aggregate interface.
- Multiple traversals can be pending on the same aggregate.

---

### Pattern 3: Strategy Pattern

#### Theory

**Intent:** Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

**Key Concepts:**
- **Strategy Interface:** Common interface for all supported algorithms (`RestorationStrategy`).
- **Concrete Strategies:** Implement the algorithm using the Strategy interface (`DigitalRemasterStrategy`, etc.).
- **Context:** Maintains a reference to a Strategy object (`FilmArchive` / `Main`).

**Benefits:**
- Defines a family of algorithms.
- Switches algorithms at runtime.
- Eliminates conditional statements.
- Separates implementation details from the code that uses them.

---

## Implementation & Explanation

### System Architecture Overview

The system is built around a core `FilmArchive` that manages `FilmReel` objects. It uses Strategies to modify films, Iterators to browse them, and Mementos to save their state.

### Project Structure

```
lab-3/
├── src/
│   ├── core/                 (Core Logic)
│   │   └── FilmArchive.java
│   ├── model/                (Domain Models)
│   │   ├── Condition.java
│   │   └── FilmReel.java
│   ├── memento/              (Memento Pattern)
│   │   ├── FilmSnapshot.java
│   │   └── RestorationHistory.java
│   ├── iterator/             (Iterator Pattern)
│   │   ├── ArchiveIterator.java
│   │   ├── SequentialIterator.java
│   │   ├── DecadeIterator.java
│   │   ├── GenreIterator.java
│   │   └── ConditionIterator.java
│   ├── strategy/             (Strategy Pattern)
│   │   ├── RestorationStrategy.java
│   │   ├── RestorationResult.java
│   │   ├── DigitalRemasterStrategy.java
│   │   ├── ChemicalTreatmentStrategy.java
│   │   └── FrameByFrameStrategy.java
│   └── Main.java             (CLI Entry Point)
```

---

## Pattern Implementations

### 1. Memento Pattern Implementation

**Location:** `src/memento/` package

#### The Memento (Snapshot)
**File:** `src/memento/FilmSnapshot.java`
```java
public class FilmSnapshot {
    private final Condition condition;
    private final String stage;
    private final Date timestamp;
    // ... immutable fields
}
```
The snapshot stores a copy of the film's condition at a specific point in time. It is immutable to ensure history integrity.

#### The Originator (Film Reel)
**File:** `src/model/FilmReel.java`
```java
public class FilmReel {
    private Condition condition;
    
    public FilmSnapshot createSnapshot(String stage) {
        // Creates a deep copy of current condition
        return new FilmSnapshot(this.condition, stage, "Archivist");
    }
    
    public void restoreSnapshot(FilmSnapshot snapshot) {
        this.condition = snapshot.getCondition(); // Restores state
    }
}
```

#### The Caretaker (History)
**File:** `src/memento/RestorationHistory.java`
```java
public class RestorationHistory {
    private Map<String, List<FilmSnapshot>> history = new HashMap<>();
    
    public void save(FilmReel film, String stage) {
        // Saves snapshot to list
    }
    
    public void undo(FilmReel film) {
        // Retrieves last snapshot and restores film
    }
}
```

---

### 2. Iterator Pattern Implementation

**Location:** `src/iterator/` package

#### Iterator Interface
**File:** `src/iterator/ArchiveIterator.java`
```java
public interface ArchiveIterator {
    boolean hasNext();
    FilmReel next();
    void reset();
}
```

#### Concrete Iterators
We implemented multiple ways to traverse the archive:

1.  **SequentialIterator:** Iterates through the list in insertion order.
2.  **DecadeIterator:** Filters films by a specific decade (e.g., 1920s).
3.  **GenreIterator:** Filters films by genre (e.g., Horror).
4.  **ConditionIterator:** Sorts films by damage level (useful for prioritizing restoration).

**Example: DecadeIterator**
```java
public class DecadeIterator implements ArchiveIterator {
    private List<FilmReel> films;
    private int currentPosition = 0;
    private int targetDecade;

    public DecadeIterator(List<FilmReel> films, int decade) {
        this.targetDecade = decade;
        // Filter logic...
    }
    
    @Override
    public boolean hasNext() {
        // Logic to find next film in decade
    }
}
```

---

### 3. Strategy Pattern Implementation

**Location:** `src/strategy/` package

#### Strategy Interface
**File:** `src/strategy/RestorationStrategy.java`
```java
public interface RestorationStrategy {
    RestorationResult restore(FilmReel film);
    String getDescription();
    double estimateCost();
}
```

#### Concrete Strategies

1.  **ChemicalTreatmentStrategy:** Improves physical state but risks color fading.
2.  **DigitalRemasterStrategy:** High cost, significant improvement to image/audio.
3.  **FrameByFrameStrategy:** Highest quality, extremely slow, manual restoration.

**Example: Digital Remaster**
```java
public class DigitalRemasterStrategy implements RestorationStrategy {
    @Override
    public RestorationResult restore(FilmReel film) {
        Condition current = film.getCondition();
        // Logic to boost Image and Audio quality significantly
        // Logic to slightly improve Color
        return new RestorationResult(true, "Digital Remaster Complete");
    }
}
```

---

## Project Architecture

### SOLID Principles Applied

1.  **Single Responsibility Principle (SRP):**
    - `Condition` class only holds state data.
    - `RestorationHistory` only manages snapshots.
    - Strategies only contain restoration logic.

2.  **Open/Closed Principle (OCP):**
    - New restoration methods can be added by creating new Strategy classes without changing `FilmReel`.
    - New traversal methods can be added by creating new Iterators without changing `FilmArchive`.

3.  **Liskov Substitution Principle (LSP):**
    - Any `RestorationStrategy` can be used wherever the interface is expected.
    - Any `ArchiveIterator` works with the client code in `Main.java`.

4.  **Interface Segregation Principle (ISP):**
    - `ArchiveIterator` is a simple, focused interface.

5.  **Dependency Inversion Principle (DIP):**
    - The `FilmArchive` and `Main` classes depend on the `RestorationStrategy` interface, not concrete classes like `ChemicalTreatmentStrategy`.

---

## Results & Testing

The application features an interactive CLI menu. Below are results from the automated demonstration.

### 1. Browsing (Iterator Pattern)

```text
+--------------------------------------------------------+
|        ITERATOR PATTERN - Browsing the Archive         |
+--------------------------------------------------------+

Browsing by Decade: 1920s
--------------------------------------------------
1. Nosferatu (1922) - F.W. Murnau
   Condition: ████████░░ 4/10 | Horror | Germany

2. The Cabinet of Dr. Caligari (1920) - Robert Wiene
   Condition: ███░░░░░░░ 3/10 | Horror | Germany
```

### 2. Restoration (Strategy Pattern)

```text
Applying Restoration Strategy: Digital Remaster
--------------------------------------------------
Target: Nosferatu (1922)
Strategy: AI-Enhanced Digital Remaster
Cost: $5000.00

> Analyzing footage...
> Removing artifacts...
> Enhancing audio tracks...
> Restoration Complete!

Result: Image Quality +4, Audio Quality +3
```

### 3. History Tracking (Memento Pattern)

```text
+--------------------------------------------------------+
|         MEMENTO PATTERN - Restoration History          |
+--------------------------------------------------------+

Restoration Timeline: Haxan
--------------------------------------------------
o Nov 2025 - Acquired
  Image: 2/10 | Audio: 2/10 | Color: 3/10

o Nov 2025 - Post-Chemical Treatment
  Image: 3/10 | Audio: 3/10 | Color: 4/10

> Nov 2025 - Post-Digital Remaster
  Image: 6/10 | Audio: 4/10 | Color: 7/10

--------------------------------------------------
Total Improvement: +150% over 5 stages
```

---

## Conclusions

### Achievement Summary

In this laboratory work, I successfully implemented a system for managing a vintage film archive using three behavioral design patterns:

1.  **Memento Pattern:** Successfully implemented a robust undo/history mechanism. This allows archivists to experiment with restoration techniques without fear of permanent damage to the digital record.
2.  **Iterator Pattern:** Decoupled the traversal algorithms from the collection object. We can now browse the archive by Decade, Genre, or Condition without modifying the core `FilmArchive` class.
3.  **Strategy Pattern:** Encapsulated restoration logic. This makes the system highly extensible; adding a new restoration technique (e.g., "AI Colorization") would simply require adding a new class.

### Lessons Learned

- **Behavioral vs. Structural:** While structural patterns (Lab 2) focus on how classes are composed, behavioral patterns focus on how they communicate. This requires thinking about the *flow* of the application.
- **Encapsulation Power:** The Memento pattern demonstrated how to save state without breaking encapsulation, a critical concept for secure software design.
- **Flexibility:** The Strategy pattern showed how composition can replace inheritance to change behavior at runtime.

### Requirements Fulfillment

✅ **Objective 1:** Studied Behavioral Design Patterns.
✅ **Objective 2:** Implemented Memento, Iterator, and Strategy.
✅ **Objective 3:** Created a functional CLI application.
✅ **Objective 4:** Organized code into `model`, `core`, `strategy`, `iterator`, `memento` packages.
✅ **Objective 5:** Documented the work in this report.

### Future Enhancements

- **Observer Pattern:** Notify archivists when a film's condition drops below a critical threshold.
- **Command Pattern:** Encapsulate restoration requests to queue them for batch processing.
- **State Pattern:** Model the lifecycle of a film reel (Acquired -> In Restoration -> Archived -> Loaned).

---

## References

1. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.
2. Freeman, E., & Robson, E. (2020). *Head First Design Patterns* (2nd ed.). O'Reilly Media.
3. Refactoring.Guru. (n.d.). *Behavioral Design Patterns*. Retrieved from https://refactoring.guru/design-patterns/behavioral
