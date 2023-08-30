import java.util.Scanner;
import command.List;
import command.Mark;
import command.Unmark;
import data.task.builder.DeadlineBuilder;
import data.task.builder.EventBuilder;
import data.task.builder.TodoBuilder;
import command.AddTask;
import command.DeleteCommand;
import exception.DukeException;
import ui.Invoker;

public class Duke {
    private Invoker invoker = new Invoker();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Doctor101");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Duke duke = new Duke();
        duke.invoker.setCommand("list", new List());
        duke.invoker.setCommand("mark", new Mark());
        duke.invoker.setCommand("unmark", new Unmark());
        duke.invoker.setCommand("todo", new AddTask(new TodoBuilder()));
        duke.invoker.setCommand("deadline", new AddTask(new DeadlineBuilder()));
        duke.invoker.setCommand("event", new AddTask(new EventBuilder()));
        duke.invoker.setCommand("delete", new DeleteCommand());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                scanner.close();
                return;
            }
            try {
                duke.invoker.execute(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
    }
}