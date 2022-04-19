package model;

public class LogicalEquivalenceException extends Exception{
    private String message;
    public LogicalEquivalenceException(String message) {
        super(message);
        this.message = message;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
