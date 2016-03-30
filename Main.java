import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        CommandLine commandLine = new CommandLine();
        try {
            commandLine.parse(args);
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        try {
            if(commandLine.isGraphicalEnabled()) {
                System.err.println("Incomplete");
                return;
            }
            else {
                Actor actor = new Actor(CounterFactory.createCounter(commandLine.getCounterName()));
                if(commandLine.isRecursive()) {
                    actor.count(new File(commandLine.getPath()), commandLine.getPattern());
                }
                else {
                    actor.count(new File(commandLine.getPath()));
                }
                for (Count count : actor.getCounts()) {
                    System.out.println(count.getType() + ":" + count.getCount());
                }
            }
        }
        catch(InvalidArgumentException e) {
            System.err.printf(e.getMessage());
            return;
        }
    }
}
