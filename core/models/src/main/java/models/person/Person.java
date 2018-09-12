package models.person;
import models.address.Address;
import models.contact.Contact;
import models.roles.Roles;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name="person")
public class Person{
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;
	
	@Embedded
	private Name name;
	
	@Column(name = "birth_day")
	private Date birthDay;
	
	@Column(name = "date_hired")
	private Date dateHired;
	
	private double gwa;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private boolean employed;
	
	private String school;
	
	@ManyToOne( cascade = CascadeType.ALL)
	private Address address;
	
	@ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH, CascadeType.DETACH},
				 targetEntity = Roles.class)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JsonManagedReference
	private Set<Roles> roles;
		
	public Person() {
	}
	
	public long getId() {
		return id;
	 }
	
	public void setId(long id) {
		this.id = id;
	 }

	public Gender getGender() {
		return gender;
	 }
	
	public void setGender(Gender gender) {
		this.gender = gender;
	 }
	 
	public double getGwa() {
		return gwa;
	 }
	
	public void setGwa(double gwa) {
		this.gwa = gwa;
	 }
	
	public boolean isEmployed() {
		return employed;
	 }
	
	public void setEmployed(boolean employed) {
		this.employed = employed;
	 }
	
	public String getSchool() {
		return school;
	 }
	
	public void setSchool(String school) {
		this.school = school;
	 } 

	public Address getAddress() {
		return address;
	 }	
	
	public void setAddress(Address address) {
		this.address = address;
	 }
	
	public Date getBirthDay() {
		return birthDay;
	 }
	 	
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	 }
	 public Date getDateHired() {
		return dateHired;
	 }
	 	
	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	 }
	public Name getName() { 
		return name;
	 } 
	public void setName(Name name) {
		this.name = name;
	 }
	public Set<Roles> getRoles() {
		return roles;
	 }
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	 }
	@Embeddable
	public static class Name {
		
		@Column(name = "first_name")
		private String firstName;
		@Column(name = "last_name")
		private String lastName;
		@Column(name = "middle_name")
		private String middleName;
		
		public Name() {}
		
		public Name (String firstName, String lastName, String middleName) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.middleName = middleName;
		 }
	
		public String getFirstName() {
			return firstName;
		 }
	
		public String getLastName() {
			return lastName;
		 }
	
		public String getMiddleName() {
			return middleName;
		 } 
	
	 	public void setFirstName(String firstName) {
	 		this.firstName = firstName;
	 	 }
	 	 
	 	public void setLastName(String lastName) {
	 		this.lastName = lastName;
	 	 }
	 	
	 	public void setMiddleName(String middleName) {
	 		this.middleName = middleName;
	 	 }
	 }

}
