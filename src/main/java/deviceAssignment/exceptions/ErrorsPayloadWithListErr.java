package deviceAssignment.exceptions;

import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class ErrorsPayloadWithListErr extends ErrorsPayload {
    private List<String> errors;

    public ErrorsPayloadWithListErr(String message, Date timestamp, int internalCode, List<String> errors) {
        super(message, timestamp, internalCode);
        this.errors = errors;
    }

}