package models.contact;
import models.person.Person;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name="contact")
public class Contact {
		@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
		@Column(name = "id")	
		private long id;
		
		@Enumerated(EnumType.ORDINAL)
		private ContactType type;
		
		private String details;
		
		private int idx;
		
		@ManyToOne
		@JoinColumn (name = "person_id")
		private Person person;
		
		public Contact(){}
			
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
		public int getIdx(){
			return idx;
		 }
		public void setIdx(int idx) {
			this.idx = idx;
		 }
		
		
		public Person getPerson() {
			return person;
		 }
		public void setPerson(Person person) {
			this.person = person;
		 }
	 }
