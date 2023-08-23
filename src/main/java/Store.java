public class Store {
    private static Store store = new Store();
    Task[] tasks = new Task[100];
    int taskCount = 0;

    private Store() {
    }

    public static Store getInstance() {
        return store;
    }

    public void addTask(Task task) throws DukeException {
        if (taskCount == 100) {
            throw new DukeException("task list is full");
        }
        tasks[taskCount] = task;
        taskCount++;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public Task getTask(int index) throws DukeException{
        if (index > taskCount || index < 1) {
            throw new DukeException("index out of bounds when calling getTask from store");
        }
        return tasks[index - 1];
    }

    public void deleteTask(int index) throws DukeException{
        if (index > taskCount || index < 1) {
            throw new InvalidInputException("index out of bounds");
        }
        for (int i = index - 1; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        taskCount--;
    }

    public void mark(int index) throws DukeException {
        if (index > taskCount || index < 1) {
           throw new InvalidInputException("index out of bounds");
        }
        tasks[index-1].mark();
       
    }

    public void unmark(int index) throws DukeException {
        if (index > taskCount || index < 1) {
            throw new InvalidInputException("index out of bounds");
        }

        tasks[index-1].unmark(); 
    }

    public void updateDescription(int index, String description) throws DukeException{
        if (index > taskCount || index < 1) {
            throw new InvalidInputException("index out of bounds");
        }
        tasks[index-1].setDescription(description);  
    }  

    public int getTaskCount() {
        return taskCount;
    }

    public boolean hasTaskAtIndex(int index) {
        return index <= taskCount && index > 0;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskCount; i++) {
            result += (i + 1) + ". " + tasks[i] + "\n";
        }
        return result;
    }
}

