package models.contact.dto;
import models.contact.ContactType;
import models.person.Person;
import java.util.Objects;

public class ContactDTO {
	
		private long id;		
		private ContactType type;
		private String details;
		
		
		public ContactDTO(){}			
		public long getId() {
			return id;
		 }
		
		public void setId(long id) {
			this.id = id;
		 }
		public ContactType getType() {
			return type;
		 }
			
		public void setType(ContactType type) {
			this.type = type;
		 }
		 
		public String getDetails() { 
			return details;
		 }
	
		public void setDetails (String details) {
			this.details = details;
		 } 
		 
		@Override 
		public boolean equals(Object obj) { 
		
			if(!(obj instanceof ContactDTO)) { 
				return false;
			}
		
			ContactDTO contactdto = (ContactDTO) obj;
			return (contactdto.getId() == this.id && contactdto.getDetails().equals(this.details));
		}
	
		@Override
		public int hashCode () {
			return Objects.hash(id,details);
		 }
	 }
