import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

public interface Counter {
    void count(BufferedReader reader) throws IOException;
    Collection<Count> getCounts();
}
