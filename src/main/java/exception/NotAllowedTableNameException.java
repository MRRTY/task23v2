package exception;

public class NotAllowedTableNameException extends RuntimeException {
    @Override
    public String getMessage() {
        return "NotAllowedTableNameException";
    }
}
