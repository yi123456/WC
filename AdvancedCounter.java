import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class AdvancedCounter implements Counter {
    @Override
    public void count(BufferedReader reader) throws IOException {
        int c;
        int visibleCharCount = 0;
        int slashCount = 0;
        while((c = reader.read()) != -1) {
            char ch = (char)c;
            if('\n' == c) {
                if(visibleCharCount < 2) {
                    ++emptyLineCount;
                }
                else if(slashCount > 1) {
                    ++lineOfCommentCount;
                }
                else {
                    ++lineOfCodeCount;
                }
                visibleCharCount = 0;
                slashCount = 0;
            }
            else {
                if(Character.isWhitespace(c)) {
                    if(slashCount < 2){
                        slashCount = 0;
                    }
                }
                else {
                    ++visibleCharCount;
                    if ('/' == c) {
                        ++slashCount;
                    }
                    else if(slashCount < 2){
                        slashCount = 0;
                    }
                }
            }
        }
        if(visibleCharCount < 2) {
            ++emptyLineCount;
        }
        else if(slashCount > 1) {
            ++lineOfCommentCount;
        }
        else {
            ++lineOfCodeCount;
        }
    }

    @Override
    public Collection<Count> getCounts() {
        Collection<Count> counts = new ArrayList<>();
        counts.add(new Count("line of code", lineOfCodeCount));
        counts.add(new Count("empty line", emptyLineCount));
        counts.add(new Count("line of comment", lineOfCommentCount));
        return counts;
    }

    private int emptyLineCount = 0;
    private int lineOfCommentCount = 0;
    private int lineOfCodeCount = 0;
}
