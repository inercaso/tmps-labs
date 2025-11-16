# Laboratory Work #2 - Structural Design Patterns

**Course:** Techniques & Methods of Projecting Software (TMPS)  
**Author:** Daniela Cebotari  
**Group:** FAF-231  
**Topic:** Structural Design Patterns  
**Domain:** Vought International - Superhero Operations System

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

This laboratory work extends the Vought International Superhero Management System developed in Lab-1. While the first lab focused on **creating** superheroes using creational design patterns (Factory, Builder, Prototype, Singleton), Lab-2 focuses on **organizing and operating** with these heroes using structural design patterns.

The system simulates Vought International's operations from "The Boys" universe, where superheroes need to be:
- **Enhanced** with temporary power-ups (Compound V, training programs)
- **Organized** into teams with synergy bonuses
- **Deployed** on missions through complex operational workflows

### Why Structural Patterns?

After creating heroes (Lab-1), we face new challenges:

**Problem 1:** Heroes need temporary enhancements without permanently modifying their base attributes
- **Solution:** Decorator Pattern - dynamically wrap heroes with enhancement layers

**Problem 2:** Missions can be assigned to individual heroes OR teams, requiring different handling logic
- **Solution:** Composite Pattern - treat individuals and teams uniformly through common interface

**Problem 3:** Mission execution involves coordinating multiple subsystems (coordination, resource allocation, reporting)
- **Solution:** Facade Pattern - provide simple unified interface hiding subsystem complexity

---

## Objectives

As specified in the laboratory requirements:

1. **Study and understand Structural Design Patterns** - Learn how patterns organize objects into larger structures
2. **Extend the previous laboratory work** - Build upon Lab-1's creational patterns with new operational features
3. **Implement at least 3 structural design patterns** - Decorator, Composite, and Facade patterns
4. **Create unified client interface** - Single `Main.java` client that uses all patterns transparently
5. **Organize code by responsibilities** - Clear package structure separating concerns
6. **Document implementation** - Comprehensive report with theory, code examples, and results

---

## Theoretical Background

### What are Structural Design Patterns?

**Definition:** Structural design patterns are concerned with how classes and objects are composed to form larger structures. They use composition and inheritance to create flexible and efficient relationships between entities.

**Key Principle:** Structural patterns focus on *composition* rather than inheritance, which provides greater flexibility and promotes loose coupling.

### Categories of Structural Patterns

The Gang of Four identified 7 structural patterns:

| Pattern | Purpose | When to Use |
|---------|---------|-------------|
| **Adapter** | Convert interface of a class into another interface clients expect | Integrating incompatible interfaces |
| **Bridge** | Decouple abstraction from implementation | Multiple orthogonal dimensions of variation |
| **Composite** | Compose objects into tree structures | Part-whole hierarchies with uniform treatment |
| **Decorator** | Attach additional responsibilities to objects dynamically | Flexible alternative to subclassing |
| **Facade** | Provide unified interface to a set of interfaces in subsystem | Simplifying complex subsystems |
| **Flyweight** | Use sharing to support large numbers of fine-grained objects | Memory optimization for many similar objects |
| **Proxy** | Provide surrogate or placeholder for another object | Control access, lazy loading, or logging |

This lab implements **Decorator**, **Composite**, and **Facade** patterns.

---

### Pattern 1: Decorator Pattern

#### Theory

**Intent:** Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.

**Key Concepts:**
- **Component Interface:** Defines operations that can be decorated
- **Concrete Component:** The object being decorated
- **Decorator:** Abstract class that wraps a component and implements the same interface
- **Concrete Decorators:** Add specific responsibilities

**Structure:**
```
Component (interface)
    ├─ ConcreteComponent
    └─ Decorator (wraps Component)
         ├─ ConcreteDecoratorA
         └─ ConcreteDecoratorB
```

**Benefits:**
- More flexible than static inheritance
- Avoids feature-laden classes high in hierarchy
- Decorators can be stacked/combined at runtime
- Follows Single Responsibility Principle (each decorator handles one enhancement)
- Follows Open/Closed Principle (new decorators don't modify existing code)

**Drawbacks:**
- Can result in many small objects
- Decorators aren't transparent to the component (identity changes)
- Can be difficult to debug nested decorators

**Real-World Examples:**
- Java I/O streams: `BufferedReader(FileReader(file))`
- GUI components: borders, scrollbars on windows
- Middleware in web frameworks

---

### Pattern 2: Composite Pattern

#### Theory

**Intent:** Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions uniformly.

**Key Concepts:**
- **Component:** Interface for objects in composition
- **Leaf:** Represents leaf objects (no children)
- **Composite:** Stores child components and implements child-related operations
- **Client:** Manipulates objects through the component interface

**Structure:**
```
Component (interface)
    ├─ Leaf (no children)
    └─ Composite (has children: List<Component>)
```

**Benefits:**
- Uniform treatment of individual and composite objects
- Makes client code simple (no type-checking)
- Easy to add new component types
- Recursive operations naturally handled

**Drawbacks:**
- Can make design overly general
- Difficult to restrict component types
- May need type-checking despite uniform interface

**Real-World Examples:**
- File systems: files and directories
- GUI component hierarchies: panels containing buttons, other panels
- Organization charts: employees and departments
- Scene graphs in graphics rendering

---

### Pattern 3: Facade Pattern

#### Theory

**Intent:** Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.

**Key Concepts:**
- **Facade:** Knows which subsystem classes are responsible for a request and delegates client requests
- **Subsystems:** Implement subsystem functionality, handle work assigned by facade, have no knowledge of facade

**Structure:**
```
Facade
    ├─→ Subsystem1
    ├─→ Subsystem2
    ├─→ Subsystem3
    └─→ Subsystem4
```

**Benefits:**
- Shields clients from subsystem components
- Promotes loose coupling between subsystems and clients
- Doesn't prevent direct subsystem access if needed
- Simplifies common tasks while allowing customization

**Drawbacks:**
- Can become a "god object" coupled to all subsystem classes
- May hide too much complexity, making debugging harder
- Needs updates when subsystems change

**Real-World Examples:**
- Home theater systems: one button turns on TV, sound system, DVD player
- Computer startup: BIOS facade hides hardware initialization complexity
- API gateways: single entry point for microservices
- Database connection pools: simple interface hiding complex resource management

---

### Pattern Selection Rationale

For this lab, I chose these three patterns because:

1. **Decorator** - Heroes need flexible, stackable enhancements without explosion of subclasses
2. **Composite** - Teams and individuals share operations, supporting hierarchical structures
3. **Facade** - Mission execution involves multiple subsystems that should be simple for client

**Why not other patterns?**
- **Adapter:** Not needed - Lab-1 interfaces already compatible
- **Bridge:** Single implementation hierarchy sufficient
- **Proxy:** Security not a requirement for this lab
- **Flyweight:** Few unique heroes, memory not a concern

---

## Implementation & Explanation

### System Architecture Overview

The system follows a layered architecture:

```
┌─────────────────────────────────────────────┐
│           CLIENT LAYER                       │
│           (Main.java)                        │
│  Single unified interface for all operations│
└───────────────┬─────────────────────────────┘
                │
    ┌───────────┼───────────┐
    │           │           │
┌───▼────┐ ┌───▼────┐ ┌───▼────┐
│DECORATOR│ │COMPOSITE│ │FACADE  │
│Pattern  │ │Pattern  │ │Pattern │
└───┬────┘ └───┬────┘ └───┬────┘
    │          │          │
    └──────────┴──────────┘
                │
┌───────────────▼─────────────────────────┐
│  CREATIONAL PATTERNS LAYER (Lab-1)      │
│  Factory | Builder | Prototype | Singleton│
└───────────────┬─────────────────────────┘
                │
┌───────────────▼─────────────────────────┐
│         DOMAIN MODELS                    │
│  Superhero | SuperheroStats | Power     │
└──────────────────────────────────────────┘
```

### Project Structure

```
lab-2/
├── src/
│   ├── client2/                  (1 file - unified client)
│   │   └── Main.java             # All UI logic in single file
│   ├── domain/
│   │   ├── enhancements/         (5 files - Decorator Pattern)
│   │   │   ├── ISuperheroComponent.java
│   │   │   ├── SuperheroWrapper.java
│   │   │   ├── EnhancementDecorator.java
│   │   │   ├── TrainingDecorator.java
│   │   │   └── CompoundVDecorator.java
│   │   ├── teams/                (3 files - Composite Pattern)
│   │   │   ├── HeroComponent.java
│   │   │   ├── IndividualHero.java
│   │   │   └── HeroTeam.java
│   │   ├── operations/           (3 files - Facade Pattern)
│   │   │   ├── MissionFacade.java
│   │   │   ├── MissionCoordinator.java
│   │   │   └── MissionReporter.java
│   │   └── models/               (1 file)
│   │       └── Mission.java
│   ├── utilities/                (2 enums)
│   │   ├── MissionType.java
│   │   └── MissionStatus.java
│   └── tests/                    (4 test files)
│       ├── DecoratorTest.java
│       ├── CompositeTest.java
│       ├── FacadeTest.java
│       └── IntegrationTest.java
└── bin/                          (compiled classes)

Total: 19 Java files
```

**Package Organization Rationale:**
- `client2/` - Single client as required, named to avoid conflict with Lab-1
- `domain/enhancements/` - Decorator pattern for hero enhancements
- `domain/teams/` - Composite pattern for team hierarchies
- `domain/operations/` - Facade pattern for mission operations
- `utilities/` - Shared enums and constants
- `tests/` - Comprehensive automated testing

---

## Pattern Implementations

### 1. Decorator Pattern Implementation

**Location:** `domain/enhancements/` package

#### Component Interface

**File:** `domain/enhancements/ISuperheroComponent.java`

```java
// decorator pattern: component interface
public interface ISuperheroComponent {
    String getName();
    SuperheroStats getStats();
    int calculatePower();
    Superhero getBaseHero();
}
```

**Purpose:** Defines operations that can be performed on both base heroes and enhanced heroes.

#### Concrete Component (Wrapper)

**File:** `domain/enhancements/SuperheroWrapper.java`

```java
// decorator pattern: concrete component - wraps lab-1 superhero
public class SuperheroWrapper implements ISuperheroComponent {
    private Superhero hero;
    
    public SuperheroWrapper(Superhero hero) {
        this.hero = hero;
    }
    
    @Override
    public SuperheroStats getStats() {
        return hero.getStats();
    }
    
    @Override
    public int calculatePower() {
        return hero.getStats().getPowerLevel();
    }
    
    // ... other methods
}
```

**Design Decision:** The wrapper adapts Lab-1's `Superhero` class to work with the decorator pattern without modifying Lab-1 code. This follows the Open/Closed Principle.

#### Abstract Decorator

**File:** `domain/enhancements/EnhancementDecorator.java`

```java
// decorator pattern: abstract decorator
public abstract class EnhancementDecorator implements ISuperheroComponent {
    protected ISuperheroComponent wrappedHero;
    
    public EnhancementDecorator(ISuperheroComponent hero) {
        this.wrappedHero = hero;
    }
    
    @Override
    public String getName() {
        return wrappedHero.getName();
    }
    
    @Override
    public Superhero getBaseHero() {
        return wrappedHero.getBaseHero();
    }
    
    // Stats and power calculations overridden by concrete decorators
}
```

**Purpose:** Provides default delegation behavior. Concrete decorators only override methods they enhance.

#### Concrete Decorator 1: Training

**File:** `domain/enhancements/TrainingDecorator.java`

```java
// decorator pattern: concrete decorator - training enhancement
public class TrainingDecorator extends EnhancementDecorator {
    private static final int TRAINING_BOOST = 15;
    
    public TrainingDecorator(ISuperheroComponent hero) {
        super(hero);
    }
    
    @Override
    public SuperheroStats getStats() {
        SuperheroStats baseStats = wrappedHero.getStats();
        return new SuperheroStats(
            baseStats.getStrength() + TRAINING_BOOST,
            baseStats.getSpeed() + TRAINING_BOOST,
            baseStats.getDurability() + TRAINING_BOOST,
            baseStats.getIntelligence() + TRAINING_BOOST,
            baseStats.getCharisma() + TRAINING_BOOST,
            baseStats.getStability() + TRAINING_BOOST
        );
    }
    
    @Override
    public int calculatePower() {
        return getStats().getPowerLevel();
    }
}
```

**Enhancement Logic:** Adds +15 to all six stats (STR, SPD, DUR, INT, CHA, STAB) representing rigorous training program results.

#### Concrete Decorator 2: Compound V

**File:** `domain/enhancements/CompoundVDecorator.java`

```java
// decorator pattern: concrete decorator - compound v enhancement
public class CompoundVDecorator extends EnhancementDecorator {
    private static final double COMPOUND_V_MULTIPLIER = 1.3;
    
    public CompoundVDecorator(ISuperheroComponent hero) {
        super(hero);
    }
    
    @Override
    public SuperheroStats getStats() {
        SuperheroStats baseStats = wrappedHero.getStats();
        return new SuperheroStats(
            (int)(baseStats.getStrength() * COMPOUND_V_MULTIPLIER),
            (int)(baseStats.getSpeed() * COMPOUND_V_MULTIPLIER),
            (int)(baseStats.getDurability() * COMPOUND_V_MULTIPLIER),
            baseStats.getIntelligence(),  // unchanged
            baseStats.getCharisma(),      // unchanged
            baseStats.getStability()      // unchanged
        );
    }
    
    @Override
    public int calculatePower() {
        return getStats().getPowerLevel();
    }
}
```

**Enhancement Logic:** Multiplies physical stats (STR, SPD, DUR) by 1.3x, representing Compound V's physical power boost while mental stats remain unchanged.

#### Stacking Decorators

**Usage Example from Main.java:**

```java
// Base hero
Superhero hero = factory.createSuperhero("Homelander");
ISuperheroComponent wrapped = new SuperheroWrapper(hero);

// Stack enhancements
ISuperheroComponent enhanced = new TrainingDecorator(wrapped);      // +15 to all
enhanced = new CompoundVDecorator(enhanced);                        // x1.3 physical

// Result: First +15, then x1.3 on physical stats
// STR: 95 → 110 → 143
// INT: 80 → 95 → 95 (not multiplied)
```

**Why This Works:** Each decorator wraps the previous one, forming a chain. When `getStats()` is called, it propagates through all decorators in reverse order (newest to oldest).

---

### 2. Composite Pattern Implementation

**Location:** `domain/teams/` package

#### Component Interface

**File:** `domain/teams/HeroComponent.java`

```java
// composite pattern: component interface
public interface HeroComponent {
    String getName();
    int getPowerLevel();
    void displayInfo();
    void displayHierarchy(int indent);
    boolean isAvailable();
}
```

**Purpose:** Defines uniform interface for both individual heroes and teams. All operations work identically regardless of whether component is a leaf or composite.

#### Leaf: Individual Hero

**File:** `domain/teams/IndividualHero.java`

```java
// composite pattern: leaf - represents individual hero
public class IndividualHero implements HeroComponent {
    private ISuperheroComponent hero;
    private boolean available;
    
    public IndividualHero(ISuperheroComponent hero) {
        this.hero = hero;
        this.available = true;
    }
    
    @Override
    public int getPowerLevel() {
        return hero.calculatePower();
    }
    
    @Override
    public void displayHierarchy(int indent) {
        StringBuilder spacing = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            spacing.append(" ");
        }
        System.out.println(spacing.toString() + "└─ " + getName() + 
                          " [Power: " + getPowerLevel() + "]");
    }
    
    // ... other methods
}
```

**Design Decision:** `IndividualHero` wraps an `ISuperheroComponent`, which can be either a base hero or enhanced hero (from Decorator pattern). This demonstrates pattern composition.

#### Composite: Team

**File:** `domain/teams/HeroTeam.java`

```java
// composite pattern: composite - represents team that can contain heroes or sub-teams
public class HeroTeam implements HeroComponent {
    private String teamName;
    private List<HeroComponent> members;
    private static final double SYNERGY_BONUS = 1.1;
    
    public HeroTeam(String teamName) {
        this.teamName = teamName;
        this.members = new ArrayList<>();
    }
    
    public void addMember(HeroComponent member) {
        members.add(member);
    }
    
    @Override
    public int getPowerLevel() {
        int totalPower = 0;
        for (HeroComponent member : members) {
            totalPower += member.getPowerLevel();  // Recursive call
        }
        return (int)(totalPower * SYNERGY_BONUS);  // +10% team synergy
    }
    
    @Override
    public void displayHierarchy(int indent) {
        StringBuilder spacing = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            spacing.append(" ");
        }
        System.out.println(spacing.toString() + teamName + 
                          " [Power: " + getPowerLevel() + "]");
        for (HeroComponent member : members) {
            member.displayHierarchy(indent + 2);  // Recursive display
        }
    }
    
    // ... other methods
}
```

**Key Features:**
1. **Recursive Power Calculation:** Calls `getPowerLevel()` on each member, whether individual or sub-team
2. **Synergy Bonus:** Teams get 10% power boost representing teamwork
3. **Nested Teams:** A team can contain other teams, creating tree structures
4. **Uniform Interface:** Client treats teams and individuals identically

#### Composite Usage Example

```java
// Create individual heroes
Superhero h1 = factory.createSuperhero("Homelander");
Superhero h2 = factory.createSuperhero("Starlight");
IndividualHero hero1 = new IndividualHero(new SuperheroWrapper(h1));
IndividualHero hero2 = new IndividualHero(new SuperheroWrapper(h2));

// Create main team
HeroTeam mainTeam = new HeroTeam("The Seven");
mainTeam.addMember(hero1);
mainTeam.addMember(hero2);

// Create sub-team
HeroTeam strikeTeam = new HeroTeam("Strike Force");
strikeTeam.addMember(hero3);

// Nest teams
mainTeam.addMember(strikeTeam);  // Team within team!

// Uniform operations
System.out.println("Power: " + mainTeam.getPowerLevel());  // Recursive calculation
mainTeam.displayHierarchy(0);                              // Hierarchical display
```

**Output:**
```
The Seven [Power: 550]
  └─ Homelander [Power: 200]
  └─ Starlight [Power: 150]
  Strike Force [Power: 165]
    └─ A-Train [Power: 150]
```

---

### 3. Facade Pattern Implementation

**Location:** `domain/operations/` package

#### Subsystem 1: Mission Coordinator

**File:** `domain/operations/MissionCoordinator.java`

```java
// facade pattern: subsystem 1 - manages mission creation and execution
public class MissionCoordinator {
    private List<Mission> missions;
    
    public Mission createMission(String name, MissionType type) {
        Mission mission = new Mission(name, type);
        missions.add(mission);
        return mission;
    }
    
    public void executeMission(Mission mission, HeroComponent team) {
        int teamPower = team.getPowerLevel();
        mission.execute(teamPower);  // Evaluates success based on power vs difficulty
    }
    
    public List<Mission> getSuccessfulMissions() {
        List<Mission> successful = new ArrayList<>();
        for (Mission m : missions) {
            if (m.isSuccess()) {
                successful.add(m);
            }
        }
        return successful;
    }
    
    // ... other methods
}
```

**Responsibility:** Manages mission lifecycle - creation, execution, tracking

#### Subsystem 2: Mission Reporter

**File:** `domain/operations/MissionReporter.java`

```java
// facade pattern: subsystem 2 - generates reports and statistics
public class MissionReporter {
    
    public void displayMissionReport(Mission mission) {
        System.out.println("\n========================================");
        System.out.println("     MISSION EXECUTION REPORT");
        System.out.println("========================================");
        System.out.println("Mission: " + mission.getName());
        System.out.println("Type: " + mission.getType());
        System.out.println("Difficulty: " + mission.getDifficulty());
        System.out.println("Team Power: " + mission.getTeamPower());
        System.out.println("Status: " + (mission.isSuccess() ? "[SUCCESS]" : "[FAILED]"));
        System.out.println("========================================");
    }
    
    public void displayStatistics(List<Mission> missions) {
        // Calculate success rates, group by type, etc.
        int total = missions.size();
        int successful = 0;
        for (Mission m : missions) {
            if (m.isSuccess()) successful++;
        }
        
        System.out.println("Total Missions: " + total);
        System.out.println("Success Rate: " + (successful * 100 / total) + "%");
        // ... more statistics
    }
}
```

**Responsibility:** Generates reports and analyzes mission statistics

#### Facade: Mission Facade

**File:** `domain/operations/MissionFacade.java`

```java
// facade pattern: main facade - provides unified interface for mission operations
public class MissionFacade {
    private MissionCoordinator coordinator;
    private MissionReporter reporter;
    
    public MissionFacade() {
        this.coordinator = new MissionCoordinator();
        this.reporter = new MissionReporter();
    }
    
    public void executeMission(String missionName, MissionType type, HeroComponent team) {
        // Coordinate multiple subsystems with single method call
        System.out.println("\n> Mission Coordinator: Creating " + type + " mission...");
        Mission mission = coordinator.createMission(missionName, type);
        
        System.out.println("> Mission Coordinator: Assigning " + team.getName() + "...");
        coordinator.executeMission(mission, team);
        
        System.out.println("> Facade: Coordinating subsystems...\n");
        reporter.displayMissionReport(mission);
    }
    
    public void displayStatistics() {
        reporter.displayStatistics(coordinator.getAllMissions());
    }
    
    public int getTotalMissions() {
        return coordinator.getAllMissions().size();
    }
    
    public int getSuccessfulMissions() {
        return coordinator.getSuccessfulMissions().size();
    }
}
```

**Key Benefits:**
1. **Simplified API:** Client calls one method instead of coordinating multiple subsystems
2. **Encapsulation:** Subsystem complexity hidden from client
3. **Loose Coupling:** Client depends only on facade, not individual subsystems
4. **Single Responsibility:** Each subsystem handles one aspect (coordination vs reporting)

#### Facade Usage Example

**Without Facade (complex):**
```java
// Client must know about and coordinate all subsystems
MissionCoordinator coordinator = new MissionCoordinator();
MissionReporter reporter = new MissionReporter();

Mission mission = coordinator.createMission("Rescue", MissionType.RESCUE);
coordinator.executeMission(mission, team);
reporter.displayMissionReport(mission);
reporter.updateStatistics(mission);
```

**With Facade (simple):**
```java
// Single simple call
MissionFacade facade = new MissionFacade();
facade.executeMission("Rescue hostages", MissionType.RESCUE, team);
```

---

### Pattern Integration: All Working Together

**Example from IntegrationTest.java:**

```java
// 1. Create heroes using Lab-1 Factory pattern
Superhero hero1 = createTestHero("Hero 1", 70, 70, 70);
Superhero hero2 = createTestHero("Hero 2", 80, 80, 80);

// 2. Enhance heroes using DECORATOR pattern
ISuperheroComponent enhanced1 = new CompoundVDecorator(
    new SuperheroWrapper(hero1)
);
ISuperheroComponent enhanced2 = new TrainingDecorator(
    new SuperheroWrapper(hero2)
);

// 3. Build team using COMPOSITE pattern
HeroTeam team = new HeroTeam("All Patterns Team");
team.addMember(new IndividualHero(enhanced1));  // Decorator → Composite
team.addMember(new IndividualHero(enhanced2));

// 4. Execute mission using FACADE pattern
MissionFacade facade = new MissionFacade();
facade.executeMission("Full Integration", MissionType.COMBAT, team);

// Result: All three patterns working seamlessly together!
// - Decorator enhanced the heroes
// - Composite calculated team power with synergy
// - Facade coordinated the mission execution
```

**Data Flow:**
```
Client → Facade.executeMission()
           ↓
       Composite.getPowerLevel()
           ↓
       IndividualHero.getPowerLevel()
           ↓
       Decorator.calculatePower()
           ↓
       CompoundV → Training → Base Hero
```

---

## Project Architecture

### Layered Architecture

The system follows a strict layered architecture:

```
┌─────────────────────────────────────────┐
│  PRESENTATION LAYER (client2)           │
│  - Main.java (unified client)           │
│  - Menu system, input handling          │
└────────────┬────────────────────────────┘
             │ uses
┌────────────▼────────────────────────────┐
│  PATTERN LAYER (domain/*)               │
│  - Decorator (enhancements/)            │
│  - Composite (teams/)                   │
│  - Facade (operations/)                 │
└────────────┬────────────────────────────┘
             │ uses
┌────────────▼────────────────────────────┐
│  CREATIONAL LAYER (Lab-1)               │
│  - Factory, Builder, Prototype          │
│  - Singleton                            │
└────────────┬────────────────────────────┘
             │ creates
┌────────────▼────────────────────────────┐
│  MODEL LAYER (domain/models)            │
│  - Superhero, SuperheroStats, Power     │
│  - Mission                              │
└─────────────────────────────────────────┘
```

### Package Dependencies

```
client2
  └─→ domain.operations (MissionFacade)
  └─→ domain.enhancements (Decorator classes)
  └─→ domain.teams (Composite classes)
  └─→ domain.factory.factories (Lab-1 factories)
  └─→ utilities (enums)

domain.operations
  └─→ domain.models (Mission, Superhero)
  └─→ domain.teams (HeroComponent)
  └─→ utilities (MissionType, MissionStatus)

domain.enhancements
  └─→ domain.models (Superhero, SuperheroStats, Power)

domain.teams
  └─→ domain.models (Superhero)
  └─→ domain.enhancements (ISuperheroComponent)

utilities
  └─→ (no dependencies - leaf package)
```

### Design Principles Applied

1. **Single Responsibility Principle (SRP)**
   - Each class has one clear purpose
   - `MissionCoordinator`: mission lifecycle
   - `MissionReporter`: reporting only
   - `TrainingDecorator`: training enhancement only

2. **Open/Closed Principle (OCP)**
   - New decorators can be added without modifying existing code
   - New mission types added via enum, not code changes
   - Teams can contain any `HeroComponent` without knowing concrete type

3. **Liskov Substitution Principle (LSP)**
   - Any `HeroComponent` (individual or team) can be used interchangeably
   - Any `ISuperheroComponent` (base or decorated) works identically

4. **Dependency Inversion Principle (DIP)**
   - High-level `MissionFacade` depends on abstractions (Mission, HeroComponent)
   - Not on low-level implementation details

5. **Interface Segregation Principle (ISP)**
   - Focused interfaces: `HeroComponent` only has 5 methods needed by clients
   - `ISuperheroComponent` focused on hero-specific operations

---

## Results & Testing

### Automated Test Suite

The system includes 18 comprehensive automated tests organized by pattern:

#### Decorator Pattern Tests (5 tests)

**File:** `tests/DecoratorTest.java`

```java
[PASS] Test: Basic enhancement (Training)
[PASS] Test: Stacked enhancements
[PASS] Test: Original object unchanged
[PASS] Test: Compound V enhancement
[PASS] Test: Power calculation
Result: 5/5 passed
```

**Test Coverage:**
- Single enhancement application and stat verification
- Multiple decorators stacked (Training + Compound V)
- Original hero object immutability
- Compound V specific logic (physical stats only)
- Power level calculation accuracy across decorators

#### Composite Pattern Tests (5 tests)

**File:** `tests/CompositeTest.java`

```java
[PASS] Test: Individual hero operations
[PASS] Test: Team creation and member addition
[PASS] Test: Power aggregation with synergy
[PASS] Test: Nested teams
[PASS] Test: Uniform interface
Result: 5/5 passed
```

**Test Coverage:**
- Individual hero wrapping and operations
- Team creation and member management
- Power calculation with 10% synergy bonus
- Teams containing other teams (recursive structure)
- Uniform interface behavior for individuals and teams

#### Facade Pattern Tests (5 tests)

**File:** `tests/FacadeTest.java`

```java
[PASS] Test: Mission execution
[PASS] Test: Successful mission
[PASS] Test: Failed mission
[PASS] Test: Statistics tracking
[PASS] Test: Multiple missions
Result: 5/5 passed
```

**Test Coverage:**
- Mission execution workflow
- Success condition (team power > difficulty)
- Failure condition (team power < difficulty)
- Statistics tracking across missions
- Multiple concurrent missions

#### Integration Tests (3 tests)

**File:** `tests/IntegrationTest.java`

```java
[PASS] Test: Enhanced hero in team (Decorator + Composite)
[PASS] Test: Team mission with facade (Composite + Facade)
[PASS] Test: All patterns working together
Result: 3/3 passed
```

**Test Coverage:**
- Decorator pattern output used in Composite pattern
- Composite pattern output used in Facade pattern
- All three patterns working together in realistic scenario

### Test Results Summary

```
========================================
[1/4] Decorator Pattern Tests
========================================
[PASS] Test: Basic enhancement (Training)
[PASS] Test: Stacked enhancements
[PASS] Test: Original object unchanged
[PASS] Test: Compound V enhancement
[PASS] Test: Power calculation
Result: 5/5 passed

========================================
[2/4] Composite Pattern Tests
========================================
[PASS] Test: Individual hero operations
[PASS] Test: Team creation and member addition
[PASS] Test: Power aggregation with synergy
[PASS] Test: Nested teams
[PASS] Test: Uniform interface
Result: 5/5 passed

========================================
[3/4] Facade Pattern Tests
========================================
[PASS] Test: Mission execution
[PASS] Test: Successful mission
[PASS] Test: Failed mission
[PASS] Test: Statistics tracking
[PASS] Test: Multiple missions
Result: 5/5 passed

========================================
[4/4] Integration Tests (All Patterns)
========================================
[PASS] Test: Enhanced hero in team (Decorator + Composite)
[PASS] Test: Team mission with facade (Composite + Facade)
[PASS] Test: All patterns working together
Result: 3/3 passed

========================================
    ALL TESTS PASSED: 18/18
========================================
```

### Sample Application Output

#### Mission Execution

```
> Mission Coordinator: Creating COMBAT mission...
> Mission Coordinator: Assigning The Seven...
> Facade: Coordinating subsystems...

========================================
     MISSION EXECUTION REPORT
========================================
Mission: Save Metropolis
Type: COMBAT
Difficulty: 80
Team Power: 334
Status: [SUCCESS]
Reason: Team power exceeds difficulty
========================================
```

#### Hero Enhancement

```
--- Homelander (Base Stats) ---
STR: 95, SPD: 90, DUR: 98, INT: 80, CHA: 85, STAB: 95
Total Power: 90
---------------------------

> Applying Training Enhancement...
> Training applied!
> Applying Compound V Enhancement...
> Compound V applied!

--- Homelander (Enhanced) ---
STR: 143, SPD: 136, DUR: 146, INT: 95, CHA: 100, STAB: 110
Total Power: 128 (+38 increase!)
---------------------------
```

#### Team Hierarchy

```
--- THE SEVEN ---
Total Power: 334 (with +10% synergy bonus)

Members:
  The Seven [Power: 334]
    └─ Homelander [Power: 128]
    └─ Starlight [Power: 85]
    └─ A-Train [Power: 71]
    Strike Force [Power: 110]
      └─ Queen Maeve [Power: 100]
-----------------------------
```

### Mission Statistics

```
========================================
       MISSION STATISTICS
========================================

--- Overall Stats ---
Total Missions: 15
Successful: 12 (80%)
Failed: 3 (20%)
---------------------

--- By Mission Type ---
RESCUE: 6 missions, 83% success
COMBAT: 5 missions, 60% success
PR_EVENT: 4 missions, 100% success
-----------------------
```

---

## Conclusions

### Achievement Summary

Through this laboratory work, I have successfully:

#### 1. Theoretical Understanding
- Studied 7 structural patterns, implemented 3 most relevant to domain
- Understood composition vs inheritance tradeoffs
- Learned when each pattern is appropriate and when to avoid
- Recognized pattern combinations for solving complex problems

#### 2. Practical Implementation
- **Decorator Pattern**: Implemented flexible hero enhancement system
  - 2 concrete decorators (Training, Compound V)
  - Stackable enhancements preserving original objects
  - Follows Open/Closed Principle
  
- **Composite Pattern**: Implemented hierarchical team structure
  - Uniform interface for individuals and teams
  - Recursive power calculation with synergy bonuses
  - Support for nested teams (teams within teams)
  
- **Facade Pattern**: Implemented simplified mission operations
  - Coordinates 2 subsystems (MissionCoordinator, MissionReporter)
  - Hides complexity from client
  - Single method call replaces multi-step workflows

#### 3. Software Engineering Principles
- **SOLID Principles**: Applied SRP, OCP, LSP, DIP, ISP throughout
- **DRY**: Reused Lab-1 code without modification via wrapper
- **Separation of Concerns**: Clear package structure by responsibility
- **Loose Coupling**: Patterns interact through interfaces
- **High Cohesion**: Related functionality grouped together

#### 4. Testing & Quality
- **18 automated tests**: 100% pass rate
- **Test coverage**: Each pattern tested individually and in integration
- **Realistic scenarios**: Tests mirror actual usage patterns
- **Maintainability**: Tests serve as documentation of expected behavior

#### 5. Real-World Applications
The patterns implemented have direct parallels in professional software:

| Pattern | Lab-2 Usage | Industry Usage |
|---------|-------------|----------------|
| **Decorator** | Hero enhancements | Middleware in web frameworks, Stream processing |
| **Composite** | Team hierarchies | File systems, UI component trees, organization charts |
| **Facade** | Mission operations | API gateways, Library interfaces, Service abstractions |

### Lessons Learned

#### Design Insights
1. **Composition > Inheritance**: Decorators provide more flexibility than subclasses
2. **Uniform Interfaces**: Composite pattern greatly simplifies client code
3. **Abstraction Levels**: Facade pattern proves value of hiding complexity
4. **Pattern Synergy**: Patterns work better together than in isolation

#### Implementation Challenges
1. **Java 8 Compatibility**: Required replacing modern language features
2. **Package Naming**: Lab-1 conflict required `client2` package name
3. **Wrapper Pattern**: Needed to adapt Lab-1 classes without modification
4. **Recursive Algorithms**: Composite pattern required careful recursion handling

#### Best Practices Learned
1. **Interface-First Design**: Define interfaces before implementations
2. **Test-Driven Development**: Tests guided implementation decisions
3. **Code Organization**: Clear package structure prevents coupling
4. **Documentation**: Pattern comments help identify design intent

### Requirements Fulfillment

Checking against `cond.md` requirements:

✅ **Objective 1**: Studied and understood Structural Design Patterns (theory section)  
✅ **Objective 2**: Extended Lab-1 with new operational functionalities  
✅ **Objective 3**: Implemented 3 structural patterns (Decorator, Composite, Facade)  
✅ **Task 1**: Patterns help perform system tasks (enhancements, teams, missions)  
✅ **Task 1**: Creational patterns buried in functionalities (used transparently)  
✅ **Task 1**: Single client (`client2/Main.java`)  
✅ **Task 2**: Files grouped by responsibility (clear package structure)  
✅ **Task 3**: Comprehensive documentation with theory, implementation, results  

### Future Enhancements

Potential extensions for learning or production use:

1. **Additional Patterns**:
   - **Proxy**: Secure access control for classified hero information
   - **Adapter**: Integrate external hero databases
   - **Bridge**: Separate hero abstraction from platform implementation

2. **Behavioral Patterns (Lab-3?)**:
   - **Strategy**: Different mission execution strategies
   - **Observer**: Real-time mission monitoring
   - **Command**: Undo/redo for team assignments
   - **State**: Hero states (active, injured, training, retired)

3. **Persistence Layer**:
   - Save/load heroes and missions to database
   - Mission history tracking
   - Hero statistics over time

4. **Advanced Features**:
   - Multiple enhancement removal (decorator unwrapping)
   - Team member role assignments (leader, support, etc.)
   - Complex mission prerequisites and dependencies
   - Hero fatigue system affecting availability

### Final Thoughts

This laboratory work demonstrated that structural design patterns are essential tools for organizing complex object relationships. By using composition over inheritance, these patterns provide flexibility, maintainability, and extensibility that pure object-oriented hierarchies cannot achieve.

The combination of patterns proved particularly powerful - decorators enhance heroes, composites organize them into teams, and facades simplify operations. Each pattern solves a specific problem, but together they create a cohesive, professional system architecture.

Most importantly, this lab reinforced that design patterns are not just academic concepts but practical solutions to recurring real-world problems. Understanding when and how to apply them is a crucial skill for any software engineer.

**The Vought International Superhero Operations System successfully demonstrates the power and elegance of structural design patterns in creating maintainable, extensible software architectures.**

---

## References

1. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.

2. Freeman, E., & Robson, E. (2020). *Head First Design Patterns* (2nd ed.). O'Reilly Media.

3. Bloch, J. (2018). *Effective Java* (3rd ed.). Addison-Wesley Professional.

4. Martin, R. C. (2017). *Clean Architecture: A Craftsman's Guide to Software Structure and Design*. Prentice Hall.

5. Shvets, A. (2019). *Dive Into Design Patterns*. Refactoring.Guru. https://refactoring.guru/design-patterns

6. Oracle. (2023). *Java Platform, Standard Edition Documentation*. https://docs.oracle.com/javase/8/docs/

---

