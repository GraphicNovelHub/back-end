package pe.programadoredeslavadoras.ficuniverse.shared.exceptions;

import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;
import java.util.stream.Collectors;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class ResourceValidationException extends RuntimeException{
    public ResourceValidationException(String resourceName, String message) {
        super(resourceName + ": " + message);
    }

    public <T> ResourceValidationException(String resourceName, Set<ConstraintViolation<T>> violations) {
        super(violations.stream()
                .map(violation -> String.format("%s : %s", violation.getPropertyPath(), violation.getMessage()))
                .collect(Collectors.joining(", ") ) );
        StackTraceElement[] mio = {};
        this.setStackTrace(mio);
    }
}
