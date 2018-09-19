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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
@RestController
class RolesController {

	private final RolesService service;
	Logger logger = LoggerFactory.getLogger(RolesController.class);

	RolesController(RolesService service) {
		this.service = service;
		logger.info("RolesController instantiated.");	
	}

 	@GetMapping("/roles")
	List<RolesDTO> all() {
		logger.info("[GET] /roles");
		return service.findAll();
	}

	@PostMapping("/roles")
	RolesDTO newRoles(@RequestBody RolesDTO newRoles) {
		logger.info("[POST] /roles");
		return service.save(newRoles);
	}

	// Single item
	@GetMapping("/roles/{id}")
	RolesDTO one(@PathVariable Long id) {
		logger.info("[GET] /roles/"+id);
		return service.findOne(id);
	}
	/*
	@PutMapping("/roless/{id}")
	Roles replaceRoles(@RequestBody Roles newRoles, @PathVariable Long id) {

	
	}
	*/
	
	@DeleteMapping("/roles/{id}")
	void deleteRoles(@PathVariable Long id) {
		logger.info("[DELETE] /roles/"+id);
		service.deleteById(id);
	}
}
