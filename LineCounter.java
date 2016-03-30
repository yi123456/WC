import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class LineCounter implements Counter {
    @Override
    public Collection<Count> getCounts() {
        Collection<Count> counts = new ArrayList<>();
        counts.add(new Count("line", lineCount));
        return counts;
    }

    @Override
    public void count(BufferedReader reader) throws IOException {
        int c;
        while((c = reader.read()) != -1) {
            if('\n' == c) {
                ++lineCount;
            }
        }
    }

    private int lineCount = 1;
}
