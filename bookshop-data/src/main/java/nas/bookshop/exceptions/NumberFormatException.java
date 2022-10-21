package nas.bookshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//a response for all Not Found Exceptions on whole Application area
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NumberFormatException extends RuntimeException {

    public NumberFormatException() { super();}

    public NumberFormatException(String message) {
        super(message);
    }

}
