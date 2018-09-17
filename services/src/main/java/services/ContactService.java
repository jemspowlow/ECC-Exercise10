package services;
import models.contact.Contact;
import models.contact.dto.ContactDTO;
import repositories.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.ComponentScan;
@Service
public class ContactService {

	private final ContactRepository cr;
	private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	private final MapperFacade modelMapper = mapperFactory.getMapperFacade();
	ContactService(ContactRepository cr) {
		this.cr = cr;
	 }
	
	public List<ContactDTO> findAll() {
		List<Contact> contactList =  cr.findAll();
	 	
	 	List<ContactDTO> dtoList = contactList.stream()
	 			   							  .map(c -> modelMapper.map(c,ContactDTO.class))
	 			   							  .collect(Collectors.toList());
	 	return dtoList;
	 }
	
	public ContactDTO findOne(Long id) { 
		Contact contact =  cr.findById(id)
				 			 .orElseThrow(() -> new EntityNotFoundException(id));
		
		return modelMapper.map(contact,ContactDTO.class);
	}
	public ContactDTO save(ContactDTO contactdto) { 
		Contact newContact = modelMapper.map(contactdto,Contact.class);
		cr.save(newContact);
		contactdto = modelMapper.map(newContact,ContactDTO.class);
		
		return contactdto;
	}
	public void deleteById(Long id) { 
		cr.deleteById(id);
	}
	
	public ContactDTO editContactDetails(ContactDTO contactdto, Long id) { 
		Contact contact =  cr.findById(id)
				 			 .orElseThrow(() -> new EntityNotFoundException(id));
		contact.setDetails(contactdto.getDetails());
		
		return modelMapper.map(cr.save(contact),ContactDTO.class);
	}
	
	public ContactDTO editContactType(ContactDTO contactdto, Long id) { 
		Contact contact =  cr.findById(id)
				 			 .orElseThrow(() -> new EntityNotFoundException(id));
		contact.setType(contactdto.getType());
		
		return modelMapper.map(cr.save(contact),ContactDTO.class);
	}
	
 }
