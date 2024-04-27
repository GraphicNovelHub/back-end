package pe.programadoredeslavadoras.ficuniverse.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FetchNotFoundException extends RuntimeException{
    public FetchNotFoundException(String resourceName, String attributeKey, String attributeValue) {
        super(String.format("%s with %s %s not found.", resourceName, attributeKey, attributeValue));
        StackTraceElement[] mio = {};
        this.setStackTrace(mio);
    }
}

