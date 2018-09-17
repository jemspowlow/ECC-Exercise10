package models.user.dto;

public class UserDTO {
    private long id;
    private String username;
 	private String password;
 	
 	public UserDTO(){}
    //standard getters and setters
    public void setUsername(String username) { 
    	this.username = username;
     }
    
    public void setPassword(String password) { 
    	this.password = password;
     }
     
    public String getUsername() { 
    	return this.username;
     }
    
    public String getPassword() { 
    	return this.password;
     }
     
     public void setId(long id) { 
    	this.id = id;
     }
    
    public long getId() { 
    	return id;
     }
}
