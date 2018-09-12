package repositories;
import models.person.*;
import models.person.interfaces.*;
//import app.models.person.dto.*;
import models.address.Address;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
@Repository

public interface PersonRepository extends JpaRepository<Person,Long> { 	
	
	@Query(value="SELECT p FROM Person p WHERE p.id = :id ")
	NameOnly findPersonName(@Param("id")Long id);
	
	@Query(value="SELECT p.address FROM Person p WHERE p.id = :id ")
	Address findPersonAddress(@Param("id")Long id);
	
	@Query(value="SELECT p FROM Person p WHERE p.id = :id ")
	BirthDayOnly findPersonBirthDay(@Param("id")Long id);
	
	@Query(value="SELECT p FROM Person p WHERE p.id = :id ")
	EmployedOnly findPersonEmployed(@Param("id")Long id);
	
	@Query(value="SELECT p FROM Person p WHERE p.id = :id ")
	DateHiredOnly findPersonDateHired(@Param("id")Long id);
	
	@Query(value="SELECT p FROM Person p WHERE p.id = :id ")
	GenderOnly findPersonGender(@Param("id")Long id);
	
	@Query(value="SELECT p FROM Person p WHERE p.id = :id ")
	SchoolOnly findPersonSchool(@Param("id")Long id);
	
	@Query(value="SELECT p FROM Person p WHERE p.id = :id ")
	GwaOnly findPersonGwa(@Param("id")Long id);
	
 }
