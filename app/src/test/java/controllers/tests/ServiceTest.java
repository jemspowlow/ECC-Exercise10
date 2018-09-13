package controllers.tests;
import repositories.*;
import controllers.Application;
import services.*;
import models.person.Person;
import models.person.dto.PersonDTO;
import java.util.Optional;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.context.SpringBootTest;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ServiceTest { 

	@Mock	
	PersonRepository pr;
	
	@InjectMocks
	PersonService ps;
	
	@Mock
	Person person;
	
	//Test for personrepository.save()
	@Test
	public void personRepositoryTest1() { 
		//Person person = mock(Person.class);	
		when(pr.save(any(Person.class))).thenReturn(any(Person.class));
		ps.save(person);
		verify(pr).save(person);	
	}
	
	@Test
	public void personRepositoryTest2() { 
		doNothing().when(pr).deleteById(anyLong());
		ps.deleteById(anyLong());
		verify(pr).deleteById(anyLong());
	}
}
