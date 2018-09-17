package services;
import models.user.User;
import models.user.dto.UserDTO;
import repositories.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
@Service
public class UserService {

	private final UserRepository ur;
	private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	private final MapperFacade modelMapper = mapperFactory.getMapperFacade();
	private final BCrypt encrypt = new BCrypt();
	UserService(UserRepository ur) {
		this.ur = ur;
	 }
	
	public List<UserDTO> findAll() {
		List<User> users = ur.findAll();
	 	return users.stream()
	 		 .map(user -> modelMapper.map(user,UserDTO.class))
	 		 .collect(Collectors.toList());
	 		 
	 }
	
	public UserDTO save(UserDTO userdto) { 
		String password = userdto.getPassword();
		String hashed = encrypt.hashpw(password, encrypt.gensalt());
		
		userdto.setPassword(hashed);
		User newUser = modelMapper.map(userdto,User.class);
		
		ur.save(newUser);
		
		userdto = modelMapper.map(newUser,UserDTO.class);
		return userdto;
	}
	
	public UserDTO findOne(Long id) { 
		User user =  ur.findById(id)
					 	.orElseThrow(() -> new EntityNotFoundException(id));
		
		return modelMapper.map(user,UserDTO.class);
	}
	
	public void deleteById(Long id) { 
		ur.deleteById(id);
	}
	
	//TODO: ADD options to update only specific fields
 }
