package product.mangagement.productm.exceptions;

public class EntityAlreadyExistException extends RuntimeException {
    
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
