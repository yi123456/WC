import java.io.*;
import java.util.Collection;

public class Actor {
    public Actor(Counter counter) {
        this.counter = counter;
    }

    public void count(File file) throws InvalidArgumentException {
        try {
            if (file.exists()) {
                if (file.isDirectory()) {
                    throw new InvalidArgumentException("File expected");
                } else {
                    counter.count(new BufferedReader(new FileReader(file)));
                }
            } else {
                throw new FileNotFoundException();
            }
        }
        catch(IOException e) {
            throw new InvalidArgumentException("Fail to read file");
        }
    }

    public void count(File file, String pattern) throws InvalidArgumentException {
        try {
            if (file.exists()) {
                if (file.isDirectory()) {
                    count(file.listFiles(), pattern);
                } else {
                    throw new InvalidArgumentException("Directory Expected");
                }
            } else {
                throw new FileNotFoundException();
            }
        }
        catch (IOException e) {
            throw new InvalidArgumentException("Fail to read directory");
        }
    }

    private void count(File[] files, String pattern) throws IOException {
        for (File file : files) {
            if (file.isDirectory()) {
                count(file.listFiles(), pattern);
            } else {
                if(file.getName().matches(pattern)) {
                    counter.count(new BufferedReader(new FileReader(file)));
                }
            }
        }
    }

    public Collection<Count> getCounts() {
        return counter.getCounts();
    }

    private Counter counter;
}
