package models.roles.dto;
import models.person.dto.PersonDTO;
import javax.persistence.*;
import javax.persistence.ForeignKey;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class RolesDTO {

	private long id;	
	private String label;
	public RolesDTO() { }
	
	public long getId() { 
		return id;
	 }
	
	public String getLabel() {
		return label;
	 }
	
	public void setId (long id) {
		this.id = id;
	 }
	
	public void setLabel(String label) {
		this.label = label;
	 }

 }
