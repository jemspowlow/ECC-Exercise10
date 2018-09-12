package services;
import models.contact.Contact;
import repositories.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.ComponentScan;
@Service
public class ContactService {

	private final ContactRepository cr;
	
	ContactService(ContactRepository cr) {
		this.cr = cr;
	 }
	
	public List<Contact> findAll() {
		return cr.findAll();
	 }
	

	
	public Contact findOne(Long id) { 
		return cr.findById(id)
				 .orElseThrow(() -> new EntityNotFoundException(id));
	}

 }
