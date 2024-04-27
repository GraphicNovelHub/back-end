package pe.programadoredeslavadoras.ficuniverse.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String resourceName, String attributeKey, String attributeValue, String action) {
        super(String.format("%s with %s %s not is %s ", resourceName, attributeKey, attributeValue, action));
        StackTraceElement[] mio = {};
        this.setStackTrace(mio);
    }
}
