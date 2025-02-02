package duke.data.task;

import duke.exception.InvalidInputException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private By by;

    public void setBy(String by) throws InvalidInputException {
        this.by = new By(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
