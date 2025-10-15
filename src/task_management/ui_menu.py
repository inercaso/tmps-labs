from typing import List
from .task_manager import TaskManager
from .task import TaskStatus, TaskPriority
from .task_filter import StatusFilter, PriorityFilter

class TaskMenu:
    def __init__(self, manager: TaskManager):
        self._manager = manager

    def _get_priority_choice(self) -> TaskPriority:
        priorities = {
            1: TaskPriority.LOW,
            2: TaskPriority.MEDIUM,
            3: TaskPriority.HIGH
        }
        
        print("\nselect priority:")
        for key, value in priorities.items():
            print(f"{key}. {value.name.capitalize()}")
        
        while True:
            try:
                choice = int(input("enter your choice (1-3): "))
                if choice in priorities:
                    return priorities[choice]
                print("invalid choice. please try again.")
            except ValueError:
                print("please enter a number.")

    def add_tasks(self) -> None:
        while True:
            title = input("\nenter task title: ").strip()
            if not title:
                print("title cannot be empty.")
                continue
                
            description = input("enter task description (optional): ").strip()
            priority = self._get_priority_choice()
            
            task = self._manager.create_task(title, priority, description)
            print(f"\ntask created: {task}")
            
            if input("\nadd another task? (y/n): ").lower() != 'y':
                break

    def view_tasks(self, tasks: List = None) -> None:
        tasks = tasks or self._manager.get_all_tasks()
        
        if not tasks:
            print("\nno tasks found.")
            return
        
        print("\ntasks:")
        for task in tasks:
            print(f"\nid: {task.id}")
            print(f"title: {task.title}")
            print(f"status: {task.status.name}")
            print(f"priority: {task.priority.name}")
            if task.description:
                print(f"description: {task.description}")
            print(f"created: {task.created_at.strftime('%Y-%m-%d %H:%M')}")
            if task.completed_at:
                print(f"completed: {task.completed_at.strftime('%Y-%m-%d %H:%M')}")

    def update_task_status(self) -> None:
        tasks = self._manager.get_all_tasks()
        if not tasks:
            print("\nno tasks found.")
            return

        print("\ncurrent tasks:")
        for task in tasks:
            print(f"id: {task.id}, {task.title} - {task.status.name}")

        statuses = {
            1: TaskStatus.TODO,
            2: TaskStatus.IN_PROGRESS,
            3: TaskStatus.COMPLETED
        }

        while True:
            try:
                task_id = int(input("\nenter task id to update: "))
                task = self._manager.get_task(task_id)
                if not task:
                    print("task not found.")
                    continue

                print("\nselect new status:")
                for key, value in statuses.items():
                    print(f"{key}. {value.name.capitalize()}")
                
                status_choice = int(input("enter your choice (1-3): "))
                if status_choice in statuses:
                    self._manager.update_task_status(task_id, statuses[status_choice])
                    print("task status updated successfully.")
                    break
                print("invalid choice.")
            except ValueError:
                print("please enter a valid number.")

    def filter_tasks(self) -> None:
        print("\nfilter options:")
        print("1. by status")
        print("2. by priority")
        print("3. clear filters")
        
        try:
            choice = int(input("enter your choice (1-3): "))
            self._manager._filters.clear()
            
            if choice == 1:
                statuses = {
                    1: TaskStatus.TODO,
                    2: TaskStatus.IN_PROGRESS,
                    3: TaskStatus.COMPLETED
                }
                print("\nselect status:")
                for key, value in statuses.items():
                    print(f"{key}. {value.name.capitalize()}")
                    
                status_choice = int(input("enter your choice (1-3): "))
                if status_choice in statuses:
                    self._manager.add_filter(StatusFilter(statuses[status_choice]))
                    self.view_tasks()
                else:
                    print("invalid choice.")
            elif choice == 2:
                priority = self._get_priority_choice()
                self._manager.add_filter(PriorityFilter(priority))
                self.view_tasks()
            elif choice == 3:
                print("\nfilters cleared.")
                self.view_tasks()
            else:
                print("invalid choice.")
        except ValueError:
            print("invalid input.")