package deviceAssignment.users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import deviceAssignment.exceptions.BadRequestException;
import deviceAssignment.exceptions.NotFoundException;
import deviceAssignment.userspayloads.UserRequestPayload;

@Service
public class UsersService {
	private final UserRepository usersRepo;

	@Autowired
	public UsersService(UserRepository usersRepo) {
		this.usersRepo = usersRepo;
	}

	public User create(UserRequestPayload body) {
		// check if email already in use
		usersRepo.findByEmail(body.getEmail()).ifPresent(user -> {
			throw new BadRequestException("This e-mail has been already used");
		});
		User newUser = new User(body.getName(), body.getSurname(), body.getEmail(), body.getPassword(), body.getUsername());
		return usersRepo.save(newUser);
	}

	public Page<User> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort)); 
		return usersRepo.findAll(pageable);
	}

	public User findById(UUID id) throws NotFoundException {
		return usersRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public User findByIdAndUpdate(UUID id, UserRequestPayload body) throws NotFoundException {
		User found = this.findById(id);
		found.setEmail(body.getEmail());
		found.setName(body.getName());
		found.setSurname(body.getSurname());

		return usersRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		usersRepo.delete(found);
	}

	public User findByEmail(String email) {
		return usersRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}
}
