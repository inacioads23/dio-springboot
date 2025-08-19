package dio.dio.spring.security.jwt.controller;

import dio.dio.spring.security.jwt.model.User;
import dio.dio.spring.security.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    	
    private final UserService service;
    
	public UserController (UserService service) {
		this.service = service;
	}
	
    @PostMapping
    public void postUser(@RequestBody User user){
        service.createUser(user);
    }
}
