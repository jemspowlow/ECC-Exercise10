package controllers;
import javax.sql.DataSource;
import javax.persistence.*;
import java.util.Properties;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.core.env.Environment;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Configuration
@EnableTransactionManagement
class ApplicationConfig { 
	@Autowired
	DataSource dataSource;
	Logger logger = LoggerFactory.getLogger(RolesController.class);
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setGenerateDdl(true);
	 	
	 	LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
	 	entityManager.setDataSource(dataSource);
	 	entityManager.setPackagesToScan(new String[] {"models.person","models.roles","models.contact","models.address","models.person.dto","models.contact.dto","models.roles.dto","models.address.dto",
"models.person.interfaces","models.user","models.user.dto"});
	 	entityManager.setJpaVendorAdapter(vendorAdapter);
	 	//entityManager.setJpaProperties(additionalProperties());
	 	logger.info("Entity Manager Factory created!");
	 	
	 	return entityManager;
	 }
	 
	@Bean
	public PlatformTransactionManager transactionManager() { 
		JpaTransactionManager tm = new JpaTransactionManager(entityManagerFactory().getObject());
		logger.info("Transaction Manager created!");
		return tm;
	 }
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() { 
		
		return new PersistenceExceptionTranslationPostProcessor();
	 }

}
