package controllers;

import java.util.List;
import models.contact.Contact;
import repositories.*;
import services.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//READ ONLY
@RestController
class ContactController {
	private final ContactService service;

	ContactController(ContactService service) {
		this.service = service;
	}

	// Aggregate root
	@GetMapping("/contacts")
	List<Contact> all() {
		return service.findAll();
	}

	// Single item
	@GetMapping("/contacts/{id}")
	Contact one(@PathVariable Long id) {

		return service.findOne(id);
	}
}
