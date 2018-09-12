package services;
import models.roles.Roles;
import models.roles.dto.RolesDTO;
import repositories.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Service
public class RolesService {

	private final RolesRepository rr;
	private final ModelMapper modelMapper = new ModelMapper();
	
	RolesService(RolesRepository rr) {
		this.rr = rr;
	 }
	
	public List<RolesDTO> findAll() {
		List<Roles> roles = rr.findAll();
	 	return roles.stream()
	 		 .map(role -> modelMapper.map(role,RolesDTO.class))
	 		 .collect(Collectors.toList());
	 		 
	 }
	
	public Roles save(Roles newRoles) { 
		return rr.save(newRoles);
	}
	
	public RolesDTO findOne(Long id) { 
		Roles role =  rr.findById(id)
					 	.orElseThrow(() -> new EntityNotFoundException(id));
		
		return modelMapper.map(role,RolesDTO.class);
	}
	/*
	public Roles updateRoles(Roles newRoles, Long id) { 
		//fill
		
	}*/
	
	public void deleteById(Long id) { 
		rr.deleteById(id);
	}
	
	//TODO: ADD options to update only specific fields
 }
