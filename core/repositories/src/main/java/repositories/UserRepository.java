package repositories;
import models.user.User;
import models.user.dto.UserDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.context.annotation.ComponentScan;

@Repository
public interface UserRepository extends JpaRepository<User,Long> { 

		
	
 }
