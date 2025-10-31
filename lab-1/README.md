# Vought International - Superhero Management System

**Course:** TMPS (Design Patterns and Programming Techniques)  
**Lab:** Laboratory Work #1 - Creational Design Patterns  
**Author:** Daniela Cebotari 
**Domain:** Superhero Management System (Based on "The Boys" universe)

---

## Objectives

1. Study and understand **Creational Design Patterns**
2. Choose a domain and define its main classes/models/entities
3. Implement **at least 3 creational design patterns** in a sample project

---

## Domain Description

This project implements a **Superhero Management System** for **Vought International**, the fictional corporation from "The Boys" that creates and manages superheroes using Compound V.

The system demonstrates how Vought:
- Creates complex superheroes with multiple attributes (Builder Pattern)
- Produces different types of superheroes (Factory Method Pattern)
- Clones successful superhero templates (Prototype Pattern)
- Manages single instances of critical systems (Singleton Pattern)

---

## Implemented Design Patterns

### 1. **Builder Pattern** 
**Purpose:** Construct complex superhero objects step-by-step

**Implementation:**
- `SuperheroBuilder` (interface)
- `DetailedSuperheroBuilder` (concrete implementation)

**Why Builder?**
- Superheroes have 10+ attributes (name, stats, powers, costume, etc.)
- Many optional configurations
- Fluent interface for readable code
- Validates object construction

**Example:**
```java
SuperheroBuilder builder = new DetailedSuperheroBuilder();
Superhero customHero = builder
    .setName("Captain Liberty")
    .setHeroType("Custom Hero (Builder Pattern)")
    .setStats(90, 85, 88, 75, 80, 70)
    .addPower("Flight", "High-speed aerial movement", 85)
    .addPower("Energy Shield", "Protective force field", 80)
    .addPower("Leadership", "Inspires team members", 90)
    .setCostume("Stars and stripes tactical suit")
    .setBackstory("Former military officer enhanced with Compound V")
    .setWeakness("Overconfidence in leadership abilities")
    .setTeam("The Seven")
    .setBrandValue(4)
    .setPublicRating(85)
    .build();
```

**Files:**
- `domain/factory/builders/SuperheroBuilder.java`
- `domain/factory/builders/DetailedSuperheroBuilder.java`

---

### 2. **Factory Method Pattern**
**Purpose:** Create different types of superheroes with type-specific configurations

**Implementation:**
- `SuperheroFactory` (abstract factory)
- `HomelanderTypeFactory` (alpha leader type)
- `StarlightTypeFactory` (energy projector type)
- `ATrainTypeFactory` (speedster type)
- `MaeveTypeFactory` (warrior type)

**Why Factory Method?**
- Each superhero type has different default stats
- Type-specific powers and weaknesses
- Encapsulates creation logic
- Easy to add new hero types

**Example:**
```java
SuperheroFactory homelanderFactory = new HomelanderTypeFactory();
Superhero homelander = homelanderFactory.createSuperhero("Homelander");
System.out.println("Created: " + homelander.getName() + " (Power Level: " + homelander.getStats().getPowerLevel() + ")");
// Returns a fully configured alpha-leader type hero
```

**Files:**
- `domain/factory/factories/SuperheroFactory.java`
- `domain/factory/factories/HomelanderTypeFactory.java`
- `domain/factory/factories/StarlightTypeFactory.java`
- `domain/factory/factories/ATrainTypeFactory.java`
- `domain/factory/factories/MaeveTypeFactory.java`

---

### 3. **Prototype Pattern**
**Purpose:** Clone pre-configured superhero templates

**Implementation:**
- `Superhero.clone()` (implements Cloneable)
- `PrototypeRegistry` (stores and manages templates)

**Why Prototype?**
- Quick creation from proven templates
- Create variations of successful heroes
- Deep copy for complex objects
- Template library management

**Example:**
```java
PrototypeRegistry registry = PrototypeRegistry.getInstance();
Superhero clonedSpeedster = registry.getTemplate("speedster");
clonedSpeedster.setName("Velocity");
clonedSpeedster.setBackstory("Second-generation speedster, trained by A-Train");
clonedSpeedster.getStats().setSpeed(95);
System.out.println("Created clone: " + clonedSpeedster.getName());
```

**Files:**
- `domain/models/Superhero.java` (implements clone())
- `domain/factory/prototypes/PrototypeRegistry.java`

---

### 4. **Singleton Pattern** (Bonus)
**Purpose:** Ensure single instances of critical management systems

**Implementation:**
- `VoughtInternational` (main corporation)
- `TheSevenManager` (team management)
- `PrototypeRegistry` (template storage)

**Why Singleton?**
- One corporation instance per system
- Centralized superhero registry
- Single team manager
- Resource management

**Example:**
```java
VoughtInternational vought = VoughtInternational.getInstance();
TheSevenManager theSeven = TheSevenManager.getInstance();

VoughtInternational vought2 = VoughtInternational.getInstance();
TheSevenManager theSeven2 = TheSevenManager.getInstance();

System.out.println("VoughtInternational instance check: " + (vought == vought2));
System.out.println("TheSevenManager instance check: " + (theSeven == theSeven2));
// Always returns the same instance
```

**Files:**
- `domain/factory/singleton/VoughtInternational.java`
- `domain/factory/singleton/TheSevenManager.java`
- `domain/factory/prototypes/PrototypeRegistry.java`

---

## Project Structure

```
lab-1/
├── src/
│   ├── client/
│   │   └── Main.java                    # Application entry point
│   │
│   └── domain/
│       ├── models/
│       │   ├── Superhero.java           # Main superhero class
│       │   ├── SuperheroStats.java      # Stats class
│       │   └── Power.java               # Power/ability class
│       │
│       └── factory/
│           ├── builders/
│           │   ├── SuperheroBuilder.java
│           │   └── DetailedSuperheroBuilder.java
│           │
│           ├── factories/
│           │   ├── SuperheroFactory.java
│           │   ├── HomelanderTypeFactory.java
│           │   ├── StarlightTypeFactory.java
│           │   ├── ATrainTypeFactory.java
│           │   └── MaeveTypeFactory.java
│           │
│           ├── prototypes/
│           │   └── PrototypeRegistry.java
│           │
│           └── singleton/
│               ├── VoughtInternational.java
│               └── TheSevenManager.java
│
├── bin/                                  # Compiled classes (generated)
├── compile.ps1                           # Compilation script
├── run.ps1                               # Run script
├── clean.ps1                             # Clean script
├── README.md                             # This file
└── conditions.md                         # Lab requirements

```
---

## Output

1. **Builder Pattern Demo** - Creating a custom superhero step-by-step
2. **Factory Method Demo** - Creating different superhero types
3. **Prototype Pattern Demo** - Cloning templates to create new heroes
4. **Singleton Pattern Demo** - Verifying single instances
5. **Management Systems** - Vought statistics and The Seven roster
6. **Detailed Hero Info** - Complete information for selected heroes

Sample output:
```
VOUGHT INTERNATIONAL - SUPERHERO MANAGEMENT SYSTEM
Creational Design Patterns Demonstration

=== DEMONSTRATION 1: BUILDER PATTERN ===
Creating a custom superhero step-by-step...

Created: Captain Liberty (Custom Hero (Builder Pattern))
Stats: STR: 90, SPD: 85, DUR: 88, INT: 75, CHA: 80, STAB: 70, Power Level: 84
Powers: 3 abilities
Registered superhero with Vought: Captain Liberty
Added Captain Liberty to The Seven

=== DEMONSTRATION 2: FACTORY METHOD PATTERN ===
Creating different superhero types with factories...

Created: Homelander (Power Level: 90)
Registered superhero with Vought: Homelander
Added Homelander to The Seven
Created: Starlight (Power Level: 66)
Registered superhero with Vought: Starlight
Added Starlight to The Seven
Created: A-Train (Power Level: 71)
Registered superhero with Vought: A-Train
Added A-Train to The Seven
Created: Queen Maeve (Power Level: 78)
Registered superhero with Vought: Queen Maeve
Added Queen Maeve to The Seven

=== DEMONSTRATION 3: PROTOTYPE PATTERN ===
Cloning superhero templates...

Available templates:
- seven_leader
- speedster
- warrior
- energy_projector

Cloning 'speedster' template...
Created clone: Velocity
Registered superhero with Vought: Velocity
Added Velocity to The Seven

Cloning 'warrior' template...
Created clone: Battle Maiden
Registered superhero with Vought: Battle Maiden
Added Battle Maiden to The Seven

=== DEMONSTRATION 4: SINGLETON PATTERN ===
Verifying single instances of management systems...

VoughtInternational instance check: true
TheSevenManager instance check: true
Singleton pattern verified!


=== FINAL STATISTICS ===
Total heroes created: 7
The Seven members: 7/7

=== THE SEVEN ROSTER ===
- Captain Liberty (Custom Hero (Builder Pattern))
- Homelander (Homelander-Type (Alpha Leader))
- Starlight (Starlight-Type (Energy Projector))
- A-Train (A-Train-Type (Speedster))
- Queen Maeve (Maeve-Type (Warrior))
- Velocity (Speedster)
- Battle Maiden (Warrior)

=== DEMONSTRATION COMPLETE ===
Patterns demonstrated:
1. Builder Pattern - Complex object construction
2. Factory Method Pattern - Type-specific creation
3. Prototype Pattern - Template cloning
4. Singleton Pattern - Single instances
```

---

## Design Pattern Benefits

| Pattern | Problem Solved | Benefit |
|---------|---------------|---------|
| **Builder** | Complex object with many attributes | Step-by-step construction, readable code, validation |
| **Factory Method** | Type-specific configurations | Encapsulation, consistency, extensibility |
| **Prototype** | Recreating similar objects | Performance, quick creation, template management |
| **Singleton** | Multiple instances of critical systems | Single source of truth, centralized management |

---

## Main Classes

### Domain Models
- **`Superhero`** - Main superhero entity with all attributes
- **`SuperheroStats`** - Statistics (strength, speed, durability, etc.)
- **`Power`** - Individual superhero powers/abilities

### Creational Patterns
- **`SuperheroBuilder`** - Builder pattern interface
- **`SuperheroFactory`** - Factory method base class
- **`PrototypeRegistry`** - Prototype pattern registry
- **`VoughtInternational`** - Singleton corporation
- **`TheSevenManager`** - Singleton team manager

---

## Pattern Demonstration

The `Main.java` client demonstrates:

1. **Builder Pattern** - Creating "Captain Liberty" with custom configuration
2. **Factory Method** - Creating Homelander, Starlight, A-Train, Queen Maeve
3. **Prototype Pattern** - Cloning "speedster" and "warrior" templates
4. **Singleton Pattern** - Verifying single instances of management systems

---

## Code Quality

- Clean separation of concerns (models, factories, builders, singletons)
- Follows SOLID principles
- Proper encapsulation
- Fluent interfaces for builders
- Deep cloning for prototypes
- Thread-safe singleton implementations
- Comprehensive toString() methods for debugging

---

## Extensibility

It is easily extensible:

- **Add new hero types:** Create new factory classes
- **Add new powers:** Extend Power class or create subclasses
- **Add new teams:** Create new manager singletons
- **Add new templates:** Register in PrototypeRegistry

---

## References

- "Design Patterns: Elements of Reusable Object-Oriented Software" by Gang of Four
- "Head First Design Patterns" by Freeman & Freeman
- "The Boys" TV Series (Amazon Prime) - Domain inspiration
