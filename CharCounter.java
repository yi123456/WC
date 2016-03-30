import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CharCounter implements Counter {
    @Override
    public Collection<Count> getCounts() {
        Collection<Count> counts = new ArrayList<>();
        counts.add(new Count("char", charCount));
        return counts;
    }

    @Override
    public void count(BufferedReader reader) throws IOException {
        int c;
        while((c = reader.read()) != -1) {
            if(Character.isAlphabetic(c)) {
                ++charCount;
            }
        }
    }

    private int charCount = 0;
}
