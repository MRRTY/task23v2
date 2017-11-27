package exception;

public class NoSuchColumnException extends RuntimeException {
    @Override
    public String getMessage() {
        return "NoSuchColumnException";
    }
}
