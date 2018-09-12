package models.person.dto;
import models.roles.dto.RolesDTO;
import models.address.dto.AddressDTO;
import models.person.Gender;
import java.util.Set;
import java.util.Date;
public class PersonDTO{
	
	private long id;
	private NameDTO name;
	private Date birthDay;
	private Date dateHired;
	private double gwa;
	private Gender gender;	
	private boolean employed;
	private String school;
	private AddressDTO address;
	
		
	public PersonDTO() {
	
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

	public AddressDTO getAddress() {
		return address;
	 }	
	
	public void setAddress(AddressDTO address) {
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
	public NameDTO getName() { 
		return name;
	 } 
	public void setName(NameDTO name) {
		this.name = name;
	 }

	
	public static class NameDTO {

		private String firstName;
		private String lastName;
		private String middleName;
		
		public NameDTO() {}
		
		public NameDTO (String firstName, String lastName, String middleName) {
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
