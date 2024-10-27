package unify.basket.exceptions;

public class EntityExistsException extends RuntimeException {
    public EntityExistsException(String message) {
        super(message);
    }
}
