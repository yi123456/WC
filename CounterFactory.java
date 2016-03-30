public class CounterFactory {
    public static Counter createCounter(String option) throws InvalidOptionException {
        Counter counter;
        switch(option){
            case "-c":
                counter = new CharCounter();
                break;
            case "-w":
                counter = new WordCounter();
                break;
            case "-l":
                counter = new LineCounter();
                break;
            case "-a":
                counter = new AdvancedCounter();
                break;
            default:
                throw new InvalidOptionException("Unknown option unexpceted.");
        }
        return counter;
    }
}
