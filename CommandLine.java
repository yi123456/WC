import java.util.HashSet;
import java.util.Set;

public class CommandLine {
    private boolean counterSpecified = false;
    private boolean recursiveEnabled = false;
    private boolean graphicalEnabled = false;
    private boolean pathSpecified    = false;
    private String counterName;
    private String path;
    private String pattern;

    public void parse(String[] args) throws InvalidArgumentException {
        Set<String> argSet = new HashSet<>();
        for (int i = 0; i < args.length; ++i) {
            if (!argSet.add(args[i])) {
                throw new InvalidOptionException("Duplicate option");
            }
            if ('-' == args[i].charAt(0)) {
                switch (args[i]) {
                    case "-c":
                    case "-w":
                    case "-l":
                    case "-a":
                        if (counterSpecified) {
                            throw new InvalidOptionException("Conflicted option");
                        }
                        counterSpecified = true;
                        counterName = args[i];
                        break;
                    case "-s":
                        recursiveEnabled = true;
                        break;
                    case "-x":
                        graphicalEnabled = true;
                        break;
                    default:
                        throw new InvalidOptionException("Unknown option");
                }
            }
            else {
                switch(args.length - i) {
                    case 0:
                        if(recursiveEnabled) {
                            throw new InvalidArgumentException("pattern expected");
                        }
                        else {
                            throw new InvalidArgumentException("path and pattern expected");
                        }
                    case 1:
                        if(recursiveEnabled) {
                            path = ".";
                            pattern = args[i];
                        }
                        else {
                            path = args[i];
                        }
                        break;
                    case 2:
                        if(!recursiveEnabled) {
                            throw new InvalidArgumentException("pattern specified without option -s");
                        }
                        path = args[i];
                        ++i;
                        pattern = args[i];
                        break;
                    default:
                        throw new InvalidArgumentException();
                }
            }
        }
    }

    public boolean isRecursive() {
        return recursiveEnabled;
    }

    public boolean isCounterSpecified() {
        return counterSpecified;
    }

    public boolean isGraphicalEnabled() {
        return graphicalEnabled;
    }

    public boolean isPathSpecified() {
        return pathSpecified;
    }

    public String getPath() {
        return path;
    }

    public String getCounterName() {
        return counterName;
    }

    public String getPattern() {
        return pattern.replaceAll("\\?", ".").replaceAll("\\*", ".$0");
    }
}
