package deviceAssignment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import deviceAssignment.exceptions.UnauthorizedException;
import deviceAssignment.users.User;
import deviceAssignment.users.UsersService;
import deviceAssignment.userspayloads.UserLoginPayload;
import deviceAssignment.userspayloads.UserRequestPayload;

@RestController
@RequestMapping("/auth")
public class JwtAuthController {
	
	@Autowired
	UsersService usersService;

	@Autowired
	JwtTools jwtTools;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody UserRequestPayload body) {
		User created = usersService.create(body);

		return created;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLoginPayload body) {

		User user = usersService.findByEmail(body.getEmail());

		if (body.getPassword().equals(user.getPassword())) {

			String token = jwtTools.createToken(user);
			return new ResponseEntity<>(token, HttpStatus.OK);

		} else {

			throw new UnauthorizedException("Credenziali non valide!");
		}
	}

}
