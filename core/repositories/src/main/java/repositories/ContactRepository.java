package repositories;
import models.contact.Contact;
import models.person.interfaces.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> { 
	//finds all contacts of a given person
	Page<ContactInfo> findContactsByPersonId(Long id, Pageable pageable);	
 	
 	//finds a contact of a given person 
 	@Query(value="SELECT c FROM Contact c WHERE c.id = :contactId AND c.person.id = :personId") 
 	ContactInfo findOneContactByPersonId(@Param("personId") Long id, @Param("contactId")Long contactId);
 }
