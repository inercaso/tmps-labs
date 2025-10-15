from task_management.task_manager import TaskManager
from task_management.task_storage import InMemoryTaskStorage
from task_management.ui_menu import TaskMenu

def main() -> None:
    storage = InMemoryTaskStorage()
    manager = TaskManager(storage)
    menu = TaskMenu(manager)
    
    options = {
        1: ("add task", menu.add_tasks),
        2: ("view tasks", menu.view_tasks),
        3: ("update task status", menu.update_task_status),
        4: ("filter tasks", menu.filter_tasks),
    }
    
    while True:
        print("\n=== task management system ===")
        for key, (name, _) in options.items():
            print(f"{key}. {name}")
        print("5. exit")
        
        try:
            choice = int(input("\nenter your choice (1-5): "))
            if choice == 5:
                print("\ngoodbye!")
                break
                
            if choice in options:
                options[choice][1]()
            else:
                print("invalid choice. please try again.")
        except ValueError:
            print("please enter a number.")

if __name__ == "__main__":
    main()