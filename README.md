# Laboratory Work Nr.0 - SOLID Principles

### Course: Techniques & Methods of Projecting Software
### Student: Daniela Cebotari
### Group: FAF-231

## 1. Purpose of Laboratory Work
The purpose of this laboratory work is to understand and implement SOLID principles in a practical software development context. By creating a task management system, we demonstrate how these principles can be applied to create maintainable, flexible, and robust software.

## 2. Lab Task
Create a program that implements at least 3 SOLID Principles:
- **S**ingle Responsibility Principle (SRP)
- **O**pen/Closed Principle (OCP)
- **L**iskov Substitution Principle (LSP)

The implementation should follow clean code practices and demonstrate object-oriented programming concepts.

## 3. Theoretical Considerations

### SOLID Principles Overview

#### Single Responsibility Principle (SRP)
- A class should have only one reason to change
- Each class should be responsible for a single part of the functionality
- The functionality should be entirely encapsulated by the class

#### Open/Closed Principle (OCP)
- Software entities should be open for extension but closed for modification
- You should be able to add new functionality without changing existing code
- Achieved through abstraction and polymorphism

#### Liskov Substitution Principle (LSP)
- Objects should be replaceable with instances of their subtypes without altering program correctness
- Inherited classes should complement, not replace, base class behavior
- Ensures that inheritance is used correctly

## 4. Implementation

### Project Structure
```
src/
├── main.py
└── task_management/
    ├── task.py            # Core task entity
    ├── task_filter.py     # Task filtering functionality
    ├── task_manager.py    # Task management operations
    ├── task_storage.py    # Storage abstraction
    └── ui_menu.py         # User interface handling
```

### SOLID Principles Implementation

#### 1. Single Responsibility Principle (SRP)
Each class has a single, well-defined responsibility:

```python
# task.py - Responsible only for task data and state
class Task:
    def __init__(self, title: str, priority: TaskPriority, description: str = ""):
        self._id = Task._next_id
        Task._next_id += 1
        self._title = title
        # ...

# task_storage.py - Responsible only for data persistence
class TaskStorage(ABC):
    @abstractmethod
    def add(self, task: Task) -> None:
        pass
    # ...

# ui_menu.py - Responsible only for user interaction
class TaskMenu:
    def __init__(self, manager: TaskManager):
        self._manager = manager
    # ...
```

#### 2. Open/Closed Principle (OCP)
The system is open for extension through abstract classes and interfaces:

```python
# task_filter.py - Can add new filters without modifying existing ones
class TaskFilter(ABC):
    @abstractmethod
    def apply(self, tasks: List[Task]) -> List[Task]:
        pass

class StatusFilter(TaskFilter):
    def apply(self, tasks: List[Task]) -> List[Task]:
        return [task for task in tasks if task.status == self._status]

class PriorityFilter(TaskFilter):
    def apply(self, tasks: List[Task]) -> List[Task]:
        return [task for task in tasks if task.priority == self._priority]
```

#### 3. Liskov Substitution Principle (LSP)
Storage implementations can be substituted without affecting the system:

```python
# task_storage.py
class TaskStorage(ABC):
    @abstractmethod
    def add(self, task: Task) -> None:
        pass
    # ...

class InMemoryTaskStorage(TaskStorage):
    def __init__(self):
        self._tasks: Dict[int, Task] = {}
    # ...

# Can easily add new storage types:
class FileTaskStorage(TaskStorage):
    def add(self, task: Task) -> None:
        # Implementation for file storage
        pass
```

### Clean Code Practices

1. **Meaningful Names**
   ```python
   class TaskManager:
       def create_task(self, title: str, priority: TaskPriority) -> Task:
   ```

2. **Type Hints**
   ```python
   def get_task(self, task_id: int) -> Optional[Task]:
   ```

3. **Consistent Formatting**
   ```python
   def update_task_status(self, task_id: int, status: TaskStatus) -> None:
       task = self.get_task(task_id)
       if task:
           task.update_status(status)
   ```

4. **Separation of Concerns**
   - UI logic is separated from business logic
   - Storage is abstracted from task management
   - Filtering is handled by dedicated classes

## 5. Conclusions

Through this laboratory work, I have learned:

1. **Practical Application of SOLID**: 
   - Implementing these principles leads to more maintainable and flexible code
   - Each principle serves a specific purpose in improving code quality

2. **Clean Code Benefits**:
   - Self-documenting code reduces the need for comments
   - Consistent naming and formatting improves readability
   - Type hints enhance code clarity and catch potential errors

3. **Design Pattern Usage**:
   - Strategy pattern for filters
   - Repository pattern for storage
   - Command pattern for menu actions

4. **Code Organization**:
   - Proper separation of concerns makes the code easier to understand and modify
   - Abstract classes and interfaces provide clear contracts for implementation
   - Modular design allows for easy extension of functionality

5. **Skills Gained**:
   - Better understanding of object-oriented design principles
   - Experience in writing maintainable and extensible code
   - Practice in creating well-structured Python applications

The implementation successfully demonstrates how SOLID principles can be applied in a real-world application, resulting in a flexible and maintainable codebase that can be easily extended with new features.