from datetime import datetime
from enum import Enum, auto
from typing import Optional

class TaskPriority(Enum):
    LOW = auto()
    MEDIUM = auto()
    HIGH = auto()

class TaskStatus(Enum):
    TODO = auto()
    IN_PROGRESS = auto()
    COMPLETED = auto()

class Task:
    _next_id = 1

    def __init__(
        self,
        title: str,
        priority: TaskPriority = TaskPriority.MEDIUM,
        description: str = ""
    ):
        self._id = Task._next_id
        Task._next_id += 1
        self._title = title
        self._description = description
        self._priority = priority
        self._status = TaskStatus.TODO
        self._created_at = datetime.now()
        self._completed_at: Optional[datetime] = None

    @property
    def id(self) -> int:
        return self._id

    @property
    def title(self) -> str:
        return self._title

    @property
    def description(self) -> str:
        return self._description

    @property
    def priority(self) -> TaskPriority:
        return self._priority

    @property
    def status(self) -> TaskStatus:
        return self._status

    @property
    def created_at(self) -> datetime:
        return self._created_at

    @property
    def completed_at(self) -> Optional[datetime]:
        return self._completed_at

    def update_status(self, status: TaskStatus) -> None:
        self._status = status
        if status == TaskStatus.COMPLETED:
            self._completed_at = datetime.now()

    def update_priority(self, priority: TaskPriority) -> None:
        self._priority = priority

    def __str__(self) -> str:
        return f"Task: {self._title} ({self._status.name}) - Priority: {self._priority.name}"