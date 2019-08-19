package aem.component.security.oauth2server.exception;

public class FieldExistException extends Exception {

    public FieldExistException(String field, String value) {
        super(String.format("The %s [%s] already exists", field, value));
    }
}
