package exception;

public class NotAllowedDatabaseNameException extends RuntimeException {
    @Override
    public String getMessage() {
        return "NotAllowedDatabaseNameException";
    }
}
