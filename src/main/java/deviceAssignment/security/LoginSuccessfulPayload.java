package deviceAssignment.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginSuccessfulPayload {
	String accessToken;
}
