package repositories;
import models.roles.*;
import models.person.interfaces.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.context.annotation.ComponentScan;
@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> { 

	//@Query(value="SELECT r FROM Roles r WHERE r.id = :id ")
	List<RolesInfo> findAllByPersonsId(Long id);
	
	//@Query(value="SELECT r FROM Roles r WHERE r.id = :roleId AND r.persons.id = :id")
	RolesInfo findOneByPersonsIdAndId(Long personId,Long roleId);

 }
