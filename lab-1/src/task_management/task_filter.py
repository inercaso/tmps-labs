from abc import ABC, abstractmethod
from typing import List
from .task import Task, TaskStatus, TaskPriority

class TaskFilter(ABC):
    @abstractmethod
    def apply(self, tasks: List[Task]) -> List[Task]:
        pass

class StatusFilter(TaskFilter):
    def __init__(self, status: TaskStatus):
        self._status = status

    def apply(self, tasks: List[Task]) -> List[Task]:
        return [task for task in tasks if task.status == self._status]

class PriorityFilter(TaskFilter):
    def __init__(self, priority: TaskPriority):
        self._priority = priority

    def apply(self, tasks: List[Task]) -> List[Task]:
        return [task for task in tasks if task.priority == self._priority]