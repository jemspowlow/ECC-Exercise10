package controllers;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.CommandLineRunner;
@SpringBootApplication(scanBasePackages={"controllers","services"})
@EnableJpaRepositories(basePackages={"repositories"})
@EntityScan(basePackages={"models.person","models.roles","models.contact","models.address","models.person.dto","models.contact.dto","models.roles.dto","models.address.dto",
"models.person.interfaces","models.user","models.user.dto"})

public class Application extends SpringBootServletInitializer implements CommandLineRunner { 
	@Autowired
    DataSource dataSource;
	
	public static void main (String[] args) { 
	
		SpringApplication.run(Application.class,args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        System.out.println("DataSource = " + dataSource);
    }
 }
