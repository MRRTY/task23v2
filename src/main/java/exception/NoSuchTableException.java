package exception;

public class NoSuchTableException extends RuntimeException {
    @Override
    public String getMessage() {
        return "NoSuchTableException";
    }
}
