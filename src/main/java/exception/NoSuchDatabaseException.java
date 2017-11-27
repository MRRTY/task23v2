package exception;

public class NoSuchDatabaseException extends RuntimeException {
    @Override
    public String getMessage() {
        return "NoSuchDatabaseException";
    }
}
