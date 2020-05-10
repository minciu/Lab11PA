package java.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class InvalidNumberOfPlayersForGameExceptions extends RuntimeException {

	public InvalidNumberOfPlayersForGameExceptions(String message) {
        super(message);
    }
}
