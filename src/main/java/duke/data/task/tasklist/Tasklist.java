package duke.data.task.tasklist;

import duke.data.task.Task;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

/**
 * Manager class for all the tasks of the user.
 */
public class Tasklist {
    private int taskCount = 0;
    private Task[] tasks = new Task[100];

    public Tasklist() {
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     * @throws DukeException If task list is full.
     */
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

    public Task getTask(int index) throws DukeException {
        if (index > taskCount || index < 1) {
            throw new DukeException("index out of bounds when calling getTask from store");
        }
        return tasks[index - 1];
    }

    /**
     * Deletes a task at the index from the task list.
     * @param index The index of the task to be deleted.
     * @throws DukeException If index is invalid.
     */
    public void deleteTask(int index) throws DukeException {
        if (index > taskCount || index < 1) {
            throw new InvalidInputException("index out of bounds");
        }
        for (int i = index - 1; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        taskCount--;
    }

    /**
     * Marks a task at the index as done.
     * @param index The index of the task to be marked as done.
     * @throws DukeException If index is invalid.
     */
    public void mark(int index) throws DukeException {
        if (index > taskCount || index < 1) {
            throw new InvalidInputException("index out of bounds");
        }
        tasks[index - 1].mark();
    }
    /**
     * Marks a task at the index as not done.
     * @param index The index of the task to be marked as done.
     * @throws DukeException If index is invalid.
     */
    public void unmark(int index) throws DukeException {
        if (index > taskCount || index < 1) {
            throw new InvalidInputException("index out of bounds");
        }

        tasks[index - 1].unmark();
    }
    public void setDescriptionAtIndex(int index, String description) throws DukeException {
        if (index > taskCount || index < 1) {
            throw new InvalidInputException("index out of bounds");
        }
        tasks[index - 1].setDescription(description);
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

    public String getUserInputStrs() {
        String result = "";
        for (int i = 0; i < taskCount; i++) {
            result += tasks[i].getUserInputString() + "\n";
        }
        return result;
    }

    /**
     * Returns a tasklist with tasks that contain the keyword in their description.
     * @param keyword The keyword to be searched for.
     * @return A tasklist with tasks that contain the keyword in their description.
     * @throws DukeException if the tasklist is full.
     */
    public Tasklist findTasksWithKeyword(String keyword) throws DukeException {
        Tasklist result = new Tasklist();

        for (int i = 0; i < taskCount; i++) {
            if (tasks[i].hasKeyword(keyword)) {
                result.addTask(tasks[i]);
            }
        }
        return result;
    }
}
