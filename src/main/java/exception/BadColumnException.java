package exception;

public class BadColumnException extends RuntimeException {
    @Override
    public String getMessage() {
        return "BadColumnException";
    }
}
