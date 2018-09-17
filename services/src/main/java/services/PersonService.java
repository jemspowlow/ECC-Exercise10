package services;
import models.person.*;
import models.person.interfaces.*;
import models.person.dto.PersonDTO;
import models.address.Address;
import models.address.dto.AddressDTO;
import models.contact.Contact;
import models.contact.dto.ContactDTO;
import models.roles.dto.RolesDTO;
import models.roles.Roles;
import repositories.*;

import java.util.Optional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.context.annotation.ComponentScan;
import org.modelmapper.ModelMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.BoundMapperFacade;
@Service
public class PersonService {
	
	private final PersonRepository pr;
	private final ContactRepository cr;
	private final RolesRepository rr;
	private final AddressRepository ar;
	private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	
	private BoundMapperFacade<Person, PersonDTO> personMapper =  mapperFactory.getMapperFacade(Person.class, PersonDTO.class);
	private BoundMapperFacade<Address, AddressDTO> addressMapper =  mapperFactory.getMapperFacade(Address.class, AddressDTO.class);
	private BoundMapperFacade<Contact, ContactDTO> contactMapper =  mapperFactory.getMapperFacade(Contact.class, ContactDTO.class);
	private BoundMapperFacade<Roles, RolesDTO> rolesMapper =  mapperFactory.getMapperFacade(Roles.class, RolesDTO.class);	
	private MapperFacade modelMapper = mapperFactory.getMapperFacade();	
	
	PersonService(PersonRepository pr, ContactRepository cr, RolesRepository rr, AddressRepository ar) {
		this.pr = pr;
	 	this.cr = cr;
	 	this.rr = rr;
	 	this.ar = ar;
	 	mapperFactory.classMap(Person.class, PersonDTO.class).byDefault();
	 	mapperFactory.classMap(Address.class, AddressDTO.class).byDefault();
	 	mapperFactory.classMap(Contact.class, ContactDTO.class).byDefault();
	 	mapperFactory.classMap(Roles.class, RolesDTO.class).byDefault();
	 }
	
	public List<PersonDTO> findAll() {
		List<Person> persons = pr.findAll();
	 	List<PersonDTO> personsDTO = persons.stream()
	 		  .map(person -> personMapper.map(person))
	 		  .collect(Collectors.toList());
	 	return personsDTO;
	 }
	
	public Person save(Person newPerson) { 
		return pr.save(newPerson);
	}
	
	public PersonDTO findOne(Long id) { 
		Person person = pr.findById(id)
				 .orElseThrow(() -> new EntityNotFoundException(id));
		return personMapper.map(person);
	}
	
	public PersonDTO updatePerson(Person newPerson, Long id) { 
		
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
				return personMapper.map(pr.save(person));
			})
			.orElseGet(() -> {
				newPerson.setId(id);
				return personMapper.map(pr.save(newPerson));
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
		AddressDTO addressdto = addressMapper.map(address);
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
	
	//Post new person
	public PersonDTO add(PersonDTO newPerson) {
		Address newAddress = addressMapper.mapReverse(newPerson.getAddress()); 
		ar.save(newAddress);
		Person personEntity = personMapper.mapReverse(newPerson);
		personEntity.setAddress(newAddress);	
		
		return personMapper.map(pr.save(personEntity));
	}
	
	public ContactDTO addContact(ContactDTO contactdto,Long personId) { 
		Contact newContact = contactMapper.mapReverse(contactdto);
		Person person = pr.findById(personId)
				 .orElseThrow(() -> new EntityNotFoundException(personId));
		newContact.setPerson(person);
		return contactMapper.map(cr.save(newContact));
	}
	
	public PersonDTO addRole(RolesDTO roledto, Long personId) { 
		Optional<Long> optId = Optional.ofNullable(roledto.getId());
		Roles selectedRole = null;
		Person selectedPerson = pr.findById(personId)
								  .orElseThrow(() -> new EntityNotFoundException(personId));
		
		if(optId.isPresent()) { 
			
			selectedRole = rr.findById(optId.get())
			  				 .orElseThrow(() -> new EntityNotFoundException(optId.get()));
			
			selectedPerson.getRoles().add(selectedRole);
		}
			return personMapper.map(pr.save(selectedPerson));
		
		
	}
	//TODO: ADD options to update only specific fields
 }
