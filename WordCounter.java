import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordCounter implements Counter {
    @Override
    public void count(BufferedReader reader) throws IOException {
        int c;
        boolean inWord = false;
        while((c = reader.read()) != -1) {
            if(Character.isAlphabetic(c)) {
                if(!inWord) {
                    ++wordCount;
                    inWord = true;
                }
            }
            else {
                inWord = false;
            }
        }
    }

    @Override
    public List<Count> getCounts() {
        List<Count> counts = new ArrayList<>();
        counts.add(new Count("word", wordCount));
        return counts;
    }

    private int wordCount = 0;
}
