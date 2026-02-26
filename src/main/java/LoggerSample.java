import utils.Log;

public class LoggerSample {

    public static void main(String[] args) {
        LoggerSample loggerSample = new LoggerSample();
        System.out.println("Demonstrating logging with Log4J:");
        loggerSample.demoLogging();
        
    }

    public void demoLogging() {
        Log.info("This is an info message");
        Log.warn("This is a warning message");
        Log.error("This is an error message");
        Log.debug("This is a debug message");
    }
}
