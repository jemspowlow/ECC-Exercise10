package models.roles;
import models.person.Person;
import javax.persistence.*;
import javax.persistence.ForeignKey;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity

@Table(name="roles")
public class Roles {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	private String label;
	
		@ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH, CascadeType.DETACH},
				 targetEntity = Person.class)
		@JoinTable(
		name = "person_roles",
		joinColumns = { @JoinColumn(name = "roles_id",nullable = false, updatable = false) },
		inverseJoinColumns = { @JoinColumn(name = "person_id",nullable=false,updatable=false)}
		)
		@JsonBackReference
	private Set<Person> persons;
	
	public Roles() { }
	
	public long getId() { 
		return id;
	 }
	
	public String getLabel() {
		return label;
	 }
	
	public Set<Person> getPersons() {
		return persons;	
	 }

	public void setId (long id) {
		this.id = id;
	 }
	
	public void setLabel(String label) {
		this.label = label;
	 }
	 
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	 }
	
	@Override 
	public boolean equals(Object obj) { 
		
		if(!(obj instanceof Roles)) { 
			return false;
		}
		
		Roles role = (Roles) obj;
		return (role.getId() == this.id && role.getLabel().equals(this.label));
	}
	
	@Override
	public int hashCode () {
		return Objects.hash(id,label);
	 } 
 }
