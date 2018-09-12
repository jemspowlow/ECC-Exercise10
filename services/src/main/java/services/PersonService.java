package services;
import models.person.*;
import models.person.interfaces.*;
import models.person.dto.PersonDTO;
import models.address.Address;
import models.address.dto.AddressDTO;
import repositories.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.context.annotation.ComponentScan;
import org.modelmapper.ModelMapper;

@Service
public class PersonService {
	
	private final PersonRepository pr;
	private final ContactRepository cr;
	private final RolesRepository rr;
	private final ModelMapper modelMapper = new ModelMapper();	
	PersonService(PersonRepository pr, ContactRepository cr, RolesRepository rr) {
		this.pr = pr;
	 	this.cr = cr;
	 	this.rr = rr;
	 }
	
	public List<PersonDTO> findAll() {
		List<Person> persons = pr.findAll();
	 	List<PersonDTO> personsDTO = persons.stream()
	 		  .map(person -> modelMapper.map(person,PersonDTO.class))
	 		  .collect(Collectors.toList());
	 	return personsDTO;
	 }
	
	public Person save(Person newPerson) { 
		return pr.save(newPerson);
	}
	
	public PersonDTO findOne(Long id) { 
		Person person = pr.findById(id)
				 .orElseThrow(() -> new EntityNotFoundException(id));
		return modelMapper.map(person,PersonDTO.class);
	}
	
	public Person updatePerson(Person newPerson, Long id) { 
		
		return pr.findById(id)
			.map(person -> {
				person.setName(newPerson.getName());
				person.setBirthDay(newPerson.getBirthDay());
				person.setGender(newPerson.getGender());
				person.setGwa(newPerson.getGwa());
				person.setEmployed(newPerson.isEmployed());
				person.setSchool(newPerson.getSchool());
				person.setAddress(newPerson.getAddress());
				person.setDateHired(newPerson.getDateHired());
				person.setRoles(newPerson.getRoles());
				return pr.save(person);
			})
			.orElseGet(() -> {
				newPerson.setId(id);
				return pr.save(newPerson);
			});
	}
	
	public void deleteById(Long id) { 
		pr.deleteById(id);
	}
	
	public Page<ContactInfo> findPersonContacts(Long id, Pageable pageable) {
		return cr.findContactsByPersonId(id, pageable);
	 }
	
	public ContactInfo findPersonContact(Long id, Long contactId) { 
		return cr.findOneContactByPersonId(id, contactId);
	}
	
	public NameOnly findPersonName(Long id) { 
		return pr.findPersonName(id);
	}
	public AddressDTO findPersonAddress(Long id) { 
		
		Address address = pr.findPersonAddress(id);
		AddressDTO addressdto = modelMapper.map(address,AddressDTO.class);
		return addressdto;
	}
	public BirthDayOnly findPersonBirthDay(Long id){
		return pr.findPersonBirthDay(id);
	 }
	public EmployedOnly findPersonEmployed(Long id) {
		return pr.findPersonEmployed(id);
	 } 
	public DateHiredOnly findPersonDateHired(Long id) { 
		return pr.findPersonDateHired(id);
	}
	public GenderOnly findPersonGender(Long id) { 
		return pr.findPersonGender(id);
	}
	public SchoolOnly findPersonSchool(Long id) { 
		return pr.findPersonSchool(id);
	}
	public GwaOnly findPersonGwa(Long id) {
		return pr.findPersonGwa(id);
	 }
	public List<RolesInfo> findPersonRoles(Long id) { 
		return rr.findAllByPersonsId(id);
	}
	public RolesInfo findPersonRole(Long personsId, Long roleId) {
		return rr.findOneByPersonsIdAndId(personsId,roleId);
	 }
	//TODO: ADD options to update only specific fields
 }