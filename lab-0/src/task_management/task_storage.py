from abc import ABC, abstractmethod
from typing import Dict, List, Optional
from .task import Task

class TaskStorage(ABC):
    @abstractmethod
    def add(self, task: Task) -> None:
        pass

    @abstractmethod
    def get(self, task_id: int) -> Optional[Task]:
        pass

    @abstractmethod
    def get_all(self) -> List[Task]:
        pass

    @abstractmethod
    def remove(self, task_id: int) -> None:
        pass

class InMemoryTaskStorage(TaskStorage):
    def __init__(self):
        self._tasks: Dict[int, Task] = {}

    def add(self, task: Task) -> None:
        self._tasks[task.id] = task

    def get(self, task_id: int) -> Optional[Task]:
        return self._tasks.get(task_id)

    def get_all(self) -> List[Task]:
        return list(self._tasks.values())

    def remove(self, task_id: int) -> None:
        if task_id in self._tasks:
            del self._tasks[task_id]