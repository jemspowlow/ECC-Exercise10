package controllers;

import java.util.List;
import models.contact.Contact;
import models.contact.dto.ContactDTO;
import repositories.*;
import services.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	List<ContactDTO> all() {
		return service.findAll();
	}

	// Single item
	@GetMapping("/contacts/{id}")
	ContactDTO one(@PathVariable Long id) {

		return service.findOne(id);
	}
	
	@PostMapping("/contacts")
	ContactDTO newContact(@RequestBody ContactDTO newContact) {
		return service.save(newContact);
	}
	
	@DeleteMapping("/contacts/{id}")
	void deleteContact(@PathVariable Long id) {
		service.deleteById(id);
	}
	
	@PatchMapping("/contacts/{id}/details") 
	ContactDTO editContactDetails(@RequestBody ContactDTO contactdto, @PathVariable Long id){
		return service.editContactDetails(contactdto,id);
	}
	
	@PatchMapping("/contacts/{id}/type") 
	ContactDTO editContactType(@RequestBody ContactDTO contactdto, @PathVariable Long id){
		return service.editContactType(contactdto,id);
	}	
}
