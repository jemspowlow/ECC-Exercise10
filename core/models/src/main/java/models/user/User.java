package models.user;
import javax.persistence.*;

@Entity
@Table(name="appuser")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private long id;
 
    @Column(name = "username")
    private String username;
 	@Column(name =" password")
 	private String password;
 	
 	public User(){ }
 
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
