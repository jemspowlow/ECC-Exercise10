package controllers;

import java.util.List;
import models.user.User;
import models.user.dto.UserDTO;
import repositories.UserRepository;
import services.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
class UserController {

	private final UserService service;

	UserController(UserService service) {
		this.service = service;
	}
	@GetMapping("/users")
	List<UserDTO> findAll() { 
		return service.findAll();
	}
	@PostMapping(value="/register", consumes="application/json;charset=UTF-8")
	UserDTO registerUser (@RequestBody UserDTO userdto) { 
		return service.save(userdto);
	
	 }
	
}
