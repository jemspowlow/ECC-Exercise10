package controllers;

import java.util.List;
import models.person.Person;
import models.person.interfaces.*;
import models.person.dto.PersonDTO;
import models.address.Address;
import models.address.dto.AddressDTO;
import models.contact.Contact;
import models.contact.dto.ContactDTO;
import models.roles.dto.RolesDTO;
import models.roles.Roles;
import repositories.*;
import services.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
@RestController
class PersonController {
	Logger logger = LoggerFactory.getLogger(PersonController.class);
	private final PersonService service;

	PersonController(PersonService service) {
		this.service = service;
		logger.info("PersonController instantiated.");	
	}

	// Aggregate root
	@GetMapping("/persons")
	List<PersonDTO> all() {
		logger.info("[GET] /persons");
		return service.findAll();
	 }
	
	@GetMapping("/persons/{id}/contacts")
	Page<ContactInfo> getPersonContacts(@PathVariable Long id, Pageable pageable) { 
		logger.info("[GET] /persons/"+id+"/contacts");
		return service.findPersonContacts(id,pageable);
	 }
	
	@GetMapping("/persons/{id}/contacts/{contactId}")
	ContactInfo getPersonContact(@PathVariable Long id, @PathVariable Long contactId) { 
		logger.info("[GET] /persons/"+id+"/contacts/"+contactId);
		return service.findPersonContact(id, contactId);
	 }
	
	@GetMapping("/persons/{id}/name")
	NameOnly getPersonName(@PathVariable Long id) { 
		logger.info("[GET] /persons/"+id+"/name");
		return service.findPersonName(id);
	 }
	@GetMapping("/persons/{id}/address")
	AddressDTO getPersonAddress(@PathVariable Long id) {
		logger.info("[GET] /persons/"+id+"/address");
		return service.findPersonAddress(id);
	 }
	@GetMapping("/persons/{id}/birthday")
	BirthDayOnly getPersonBirthDay(@PathVariable Long id) { 
		logger.info("[GET] /persons/"+id+"/birthday");
		return service.findPersonBirthDay(id);
	 }
	@GetMapping("/persons/{id}/datehired")
	DateHiredOnly getPersonDateHired(@PathVariable Long id) { 
		logger.info("[GET] /persons/"+id+"/datehired");
		return service.findPersonDateHired(id);
	 }
	@GetMapping("/persons/{id}/gender")
	GenderOnly getPersonGender(@PathVariable Long id) {
		logger.info("[GET] /persons/"+id+"/gender");
		return service.findPersonGender(id);
	 }
	@GetMapping("/persons/{id}/gwa")
	GwaOnly getPersonGwa(@PathVariable Long id) {
		logger.info("[GET] /persons/"+id+"/gwa"); 
		return service.findPersonGwa(id);
	 }
	@GetMapping("/persons/{id}/school")
	SchoolOnly getPersonSchool(@PathVariable Long id) {
		logger.info("[GET] /persons/"+id+"/school"); 
		return service.findPersonSchool(id);
	 }
	@GetMapping("/persons/{id}/employed")
	EmployedOnly getPersonEmployed(@PathVariable Long id) {
		logger.info("[GET] /persons/"+id+"/employed");
		return service.findPersonEmployed(id);
	 }
	
	@GetMapping("/persons/{id}/roles")
	List<RolesInfo> getPersonRoles(@PathVariable Long id) {
		logger.info("[GET] /persons/"+id+"/roles"); 
		return service.findPersonRoles(id);
	 }
	
	@GetMapping("/persons/{personId}/roles/{roleId}")
	RolesInfo getPersonRole(@PathVariable Long personId, @PathVariable Long roleId) { 
		logger.info("[GET] /persons/"+personId+"/roles/"+roleId);
		return service.findPersonRole(personId,roleId);
	 }
	
	@PostMapping(path="/persons", consumes="application/json;charset=UTF-8")
	PersonDTO addNewPerson(@RequestBody PersonDTO newPerson) {
		logger.info("[POST] /persons");
		return service.add(newPerson);
	 }
	
	@PostMapping(path="/persons/{personId}/contacts", consumes="application/json;charset=UTF-8")
	ContactDTO addNewPerson(@RequestBody ContactDTO newContact, @PathVariable Long personId) {
		logger.info("[POST] /persons/"+personId+"/contacts");
		return service.addContact(newContact, personId);
	 }
	
	@PostMapping(path="/persons/{personId}/roles", consumes="application/json;charset=UTF-8")
	PersonDTO addARole(@RequestBody RolesDTO role, @PathVariable Long personId) { 
		logger.info("[GET] /persons/"+personId+"/roles");
		return service.addRole(role,personId);
	}
	
	// Single item
	@GetMapping("/persons/{id}")
	PersonDTO one(@PathVariable Long id) {
		logger.info("[GET] /persons/"+id);
		return service.findOne(id);		
	 }
	
	@PutMapping("/persons/{id}") 
	PersonDTO updatePerson(@RequestBody Person newPerson, @PathVariable Long id) {
		logger.info("[PUT] /persons/"+id+""); 
		return service.updatePerson(newPerson, id);
	 }
	
	@DeleteMapping("/persons/{id}")
	void deletePerson(@PathVariable Long id) {
		logger.info("[DELETE] /persons/"+id);
		service.deleteById(id);
	 }
	//TODO: ADD MORE PATCHMAPPING
	/*
	@PatchMapping("/persons/{id}/name")
	
	@PatchMapping("/persons/{id}/address")

	@PatchMapping("/persons/{id}/birthday")

	@PatchMapping("/persons/{id}/datehired")

	@PatchMapping("/persons/{id}/gender")

	@PatchMapping("/persons/{id}/gwa")
	@PatchMapping("/persons/{id}/school")

	@PatchMapping("/persons/{id}/employed")
	*/
}
