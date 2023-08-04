package deviceAssignment.users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import deviceAssignment.exceptions.NotFoundException;
import deviceAssignment.userspayloads.UserRequestPayload;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    
    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return usersService.find(page, size, sortBy);
    }
    
    @GetMapping("/{userId}")
    public User getUser(@PathVariable UUID userId) throws Exception {
    	return usersService.findById(userId);
    }
 
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserRequestPayload body) {
        return usersService.create(body);
    }
     
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable UUID userId, @RequestBody UserRequestPayload body) throws Exception {
        return usersService.findByIdAndUpdate(userId, body);
    }
    
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
        usersService.findByIdAndDelete(userId);
    }

}
