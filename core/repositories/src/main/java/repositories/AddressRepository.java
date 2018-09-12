package repositories;
import models.address.Address;
import models.address.dto.AddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address,Long> { 

}
