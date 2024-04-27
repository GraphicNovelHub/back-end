package pe.programadoredeslavadoras.ficuniverse.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class FetchIdNotFoundException extends RuntimeException{
    public FetchIdNotFoundException(String resourceName, Integer resourceId) {
        super(String.format("%s with id %d not found.", resourceName, resourceId));
        StackTraceElement[] mio = {};
        this.setStackTrace(mio);
    }
}
