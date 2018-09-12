package services;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(long id) {
        super("Could not find Entity with " + id);
    }
}
