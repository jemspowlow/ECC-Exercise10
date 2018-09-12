package models.address.dto;
import java.util.Objects;
import models.address.Address;
public class AddressDTO { 
	private long id;
	private String streetNumber;
	private String city;
	private String zipCode;
	public AddressDTO() { }
	
	public AddressDTO(long id, String streetNumber, String city, String zipCode) { 
		this.id = id;
		this.streetNumber = streetNumber;
		this.city  = city;
		this.zipCode = zipCode;
	}
	
	public long getId() { 
		return this.id;
	}
	
	public String getStreetNumber() { 
		return this.streetNumber;
	}
	
	public String getCity() { 
		return this.city;
	}

	public String getZipCode() {
		return this.zipCode;
	}
	
	public void setId(long id) {
			this.id=id;
	}

	public void setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
		 }
		 

	public void setCity(String city) {
			this.city = city;
		 }

	public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		 }
	
	@Override 
	public boolean equals(Object obj) { 
		
		if(!(obj instanceof Address)) { 
			return false;
		}
		
		AddressDTO addressdto = (AddressDTO) obj;
		return (addressdto.getId() == this.id && addressdto.getCity().equals(this.city) && addressdto.getZipCode().equals(this.zipCode));
	}
	
	@Override
	public int hashCode () {
		return Objects.hash(id,city,zipCode,streetNumber);
	 }  
}
