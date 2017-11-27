package exception;

public class NotAllowedDataTypeException extends RuntimeException {
    @Override
    public String getMessage() {
        return "NotAllowedDataTypeException";
    }
}
