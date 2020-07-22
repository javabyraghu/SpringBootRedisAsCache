package in.nareshit.raghu;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResouceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8722013793153647430L;

	public ResouceNotFoundException(String message) {
        super(message);
    }
}
