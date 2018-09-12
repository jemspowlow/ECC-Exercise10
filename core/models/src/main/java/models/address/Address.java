package models.address;
import javax.persistence.*;
@Entity
public class Address {
		
		@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
		@Column(name = "id")
		private long id;
		@Column(name="street_number")
		private String streetNumber;
		private String city;
		@Column(name="zip_code")
		private String zipCode;
		
		public Address(){}
		public Address(String streetNumber, String city, String zipCode) { 
			this.streetNumber = streetNumber;
			this.city = city;
			this.zipCode = zipCode;
		}
		public long getId() {
			return id;
		 }
		 
		public void setId(long id) {
			this.id=id;
		 }
		 
		public String getStreetNumber() {
			return streetNumber;
		 }	
		 
		public void setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
		 }
		 
		public String getCity() { 
			return city;
		 }
		 
		public void setCity(String city) {
			this.city = city;
		 }
		 
		public String getZipCode() {
			return zipCode;
		 }
		 
		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		 }
	}	
