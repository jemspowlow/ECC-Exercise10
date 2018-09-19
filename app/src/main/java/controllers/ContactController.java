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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
//READ ONLY
@RestController
class ContactController {
	private final ContactService service;
	Logger logger = LoggerFactory.getLogger(ContactController.class);

	ContactController(ContactService service) {
		this.service = service;
		logger.info("ContactController instantiated.");	
	}

	// Aggregate root
	@GetMapping("/contacts")
	List<ContactDTO> all() {
		logger.info("[GET] /contacts");
		return service.findAll();
	}

	// Single item
	@GetMapping("/contacts/{id}")
	ContactDTO one(@PathVariable Long id) {
		logger.info("[GET] /contacts/"+id);
		return service.findOne(id);
	}
	
	@PostMapping("/contacts")
	ContactDTO newContact(@RequestBody ContactDTO newContact) {
		logger.info("[POST] /contacts");
		return service.save(newContact);
	}
	
	@DeleteMapping("/contacts/{id}")
	void deleteContact(@PathVariable Long id) {
		logger.info("[DELETE] /contacts/"+id);
		service.deleteById(id);
	}
	
	@PatchMapping("/contacts/{id}/details") 
	ContactDTO editContactDetails(@RequestBody ContactDTO contactdto, @PathVariable Long id){
		logger.info("[PATCH] /contacts/"+id+"/details");
		return service.editContactDetails(contactdto,id);
	}
	
	@PatchMapping("/contacts/{id}/type") 
	ContactDTO editContactType(@RequestBody ContactDTO contactdto, @PathVariable Long id){
		logger.info("[PATCH] /contacts/"+id+"/type");
		return service.editContactType(contactdto,id);
	}	
}
