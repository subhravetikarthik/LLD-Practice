package design_patterns.Singleton.Excercise2;

import org.springframework.boot.logging.LogLevel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LoggerImpl implements Logger {
    private static LoggerImpl loggerInstance = null;
    private PrintWriter printWriter;  // PrintWriter for writing log messages to a file
    private String filePath; // Path of the log file
    private LoggerImpl() {

    }
    @Override
    public void log(LogLevel level, String message) {
        // Check if the PrintWriter is initialized
        if(printWriter == null) {
            throw new IllegalStateException("Logger is not initialized in setLogFile()");
        }
        // Construct log message with current date and time, log level, and message content
        String logMessage = "DateTime: " + LocalDateTime.now() + " LogLevel: " + level + " Message: " + message;
        // Write log message to the log file
        printWriter.println(logMessage);
    }

    @Override
    public void setLogFile(String filePath) {
        try {
            // Set the file path
            this.filePath = filePath;
            // Create a PrintWriter to write to the specified file, appending if file exists
            printWriter = new PrintWriter(new FileWriter(this.filePath, true));
        } catch(IOException e) {
            // Handle IOException
            e.printStackTrace();
            System.out.println("Invalid file path. " + e.getMessage());
        }
    }

    @Override
    public String getLogFile() {
        return filePath;
    }

    @Override
    public void flush() {
        if(printWriter != null) {
            printWriter.flush();
        }
    }

    @Override
    public void close() {
        if(printWriter != null) {
            // Close the PrintWriter
            printWriter.close();
            // Set PrintWriter to null
            printWriter = null;
        }
    }

    public static Logger getInstance() {
        if(loggerInstance == null) {
            synchronized (LoggerImpl.class) {
                if(loggerInstance == null) {
                    loggerInstance = new LoggerImpl();
                }
            }
        }
        return loggerInstance;
    }

    public static void resetInstance() {
        loggerInstance = null;
    }
}