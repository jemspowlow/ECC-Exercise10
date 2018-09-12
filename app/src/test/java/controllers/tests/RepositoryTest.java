package controllers.tests;
import repositories.*;
import controllers.Application;
import services.*;
import models.person.Person;
import models.person.dto.PersonDTO;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class RepositoryTest { 

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private PersonRepository pr;
	
	@Test
	public void personFindById() {
    // given
    Person person = new Person();
    person.setId(1);
    em.saveOrUpdate(person);
   	
 
    // when
    Person found = pr.findById(person.getId())
 					 .orElseThrow(() -> new EntityNotFoundException(person.getId()));
    // then
    assertThat(found.getId())
      .isEqualTo(person.getId());
	}
}
