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
@RestController
class PersonController {

	private final PersonService service;

	PersonController(PersonService service) {
		this.service = service;
	}

	// Aggregate root
	@GetMapping("/persons")
	List<PersonDTO> all() {
		return service.findAll();
	 }
	
	@GetMapping("/persons/{id}/contacts")
	Page<ContactInfo> getPersonContacts(@PathVariable Long id, Pageable pageable) { 
		return service.findPersonContacts(id,pageable);
	 }
	
	@GetMapping("/persons/{id}/contacts/{contactId}")
	ContactInfo getPersonContact(@PathVariable Long id, @PathVariable Long contactId) { 
		return service.findPersonContact(id, contactId);
	 }
	
	@GetMapping("/persons/{id}/name")
	NameOnly getPersonName(@PathVariable Long id) { 
		return service.findPersonName(id);
	 }
	@GetMapping("/persons/{id}/address")
	AddressDTO getPersonAddress(@PathVariable Long id) { 
		return service.findPersonAddress(id);
	 }
	@GetMapping("/persons/{id}/birthday")
	BirthDayOnly getPersonBirthDay(@PathVariable Long id) { 
		return service.findPersonBirthDay(id);
	 }
	@GetMapping("/persons/{id}/datehired")
	DateHiredOnly getPersonDateHired(@PathVariable Long id) { 
		return service.findPersonDateHired(id);
	 }
	@GetMapping("/persons/{id}/gender")
	GenderOnly getPersonGender(@PathVariable Long id) { 
		return service.findPersonGender(id);
	 }
	@GetMapping("/persons/{id}/gwa")
	GwaOnly getPersonGwa(@PathVariable Long id) { 
		return service.findPersonGwa(id);
	 }
	@GetMapping("/persons/{id}/school")
	SchoolOnly getPersonSchool(@PathVariable Long id) { 
		return service.findPersonSchool(id);
	 }
	@GetMapping("/persons/{id}/employed")
	EmployedOnly getPersonEmployed(@PathVariable Long id) {
		return service.findPersonEmployed(id);
	 }
	
	@GetMapping("/persons/{id}/roles")
	List<RolesInfo> getPersonRoles(@PathVariable Long id) { 
		return service.findPersonRoles(id);
	 }
	
	@GetMapping("/persons/{personId}/roles/{roleId}")
	RolesInfo getPersonRole(@PathVariable Long personId, @PathVariable Long roleId) { 
		return service.findPersonRole(personId,roleId);
	 }
	
	@PostMapping(path="/persons", consumes="application/json;charset=UTF-8")
	PersonDTO addNewPerson(@RequestBody PersonDTO newPerson) {
		return service.add(newPerson);
	 }
	
	@PostMapping(path="/persons/{personId}/contacts", consumes="application/json;charset=UTF-8")
	ContactDTO addNewPerson(@RequestBody ContactDTO newContact, @PathVariable Long personId) {
		return service.addContact(newContact, personId);
	 }
	
	@PostMapping(path="/persons/{personId}/roles", consumes="application/json;charset=UTF-8")
	PersonDTO addARole(@RequestBody RolesDTO role, @PathVariable Long personId) { 
		return service.addRole(role,personId);
	}
	
	// Single item
	@GetMapping("/persons/{id}")
	PersonDTO one(@PathVariable Long id) {
		return service.findOne(id);		
	 }
	
	@PutMapping("/persons/{id}") 
	PersonDTO updatePerson(@RequestBody Person newPerson, @PathVariable Long id) { 
		return service.updatePerson(newPerson, id);
	 }
	
	@DeleteMapping("/persons/{id}")
	void deletePerson(@PathVariable Long id) {
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
