package controllers;

import java.util.List;
import models.roles.Roles;
import models.roles.dto.RolesDTO;
import repositories.*;
import services.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RolesController {

	private final RolesService service;

	RolesController(RolesService service) {
		this.service = service;
	}

	// Aggregate root
	@GetMapping("/roles")
	List<RolesDTO> all() {
		return service.findAll();
	}

	@PostMapping("/roles")
	Roles newRoles(@RequestBody Roles newRoles) {
		return service.save(newRoles);
	}

	// Single item
	@GetMapping("/roles/{id}")
	RolesDTO one(@PathVariable Long id) {

		return service.findOne(id);
	}
	/*
	@PutMapping("/roless/{id}")
	Roles replaceRoles(@RequestBody Roles newRoles, @PathVariable Long id) {

	
	}
	*/
	
	@DeleteMapping("/roles/{id}")
	void deleteRoles(@PathVariable Long id) {
		service.deleteById(id);
	}
}
