package exceptions;

public class APIAutomationFrameworkException extends RuntimeException{

    public APIAutomationFrameworkException(String message) {
        super(message);
    }

    public APIAutomationFrameworkException(String message,Throwable throwable) {
        super(message, throwable);
    }
}
