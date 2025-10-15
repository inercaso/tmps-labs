from typing import List, Optional
from .task import Task, TaskPriority, TaskStatus
from .task_storage import TaskStorage
from .task_filter import TaskFilter

class TaskManager:
    def __init__(self, storage: TaskStorage):
        self._storage = storage
        self._filters: List[TaskFilter] = []

    def add_filter(self, task_filter: TaskFilter) -> None:
        self._filters.append(task_filter)

    def create_task(self, title: str, priority: TaskPriority = TaskPriority.MEDIUM, description: str = "") -> Task:
        task = Task(title, priority, description)
        self._storage.add(task)
        return task

    def get_task(self, task_id: int) -> Optional[Task]:
        return self._storage.get(task_id)

    def get_all_tasks(self) -> List[Task]:
        tasks = self._storage.get_all()
        for task_filter in self._filters:
            tasks = task_filter.apply(tasks)
        return tasks

    def update_task_status(self, task_id: int, status: TaskStatus) -> None:
        task = self.get_task(task_id)
        if task:
            task.update_status(status)

    def update_task_priority(self, task_id: int, priority: TaskPriority) -> None:
        task = self.get_task(task_id)
        if task:
            task.update_priority(priority)

    def remove_task(self, task_id: int) -> None:
        self._storage.remove(task_id)