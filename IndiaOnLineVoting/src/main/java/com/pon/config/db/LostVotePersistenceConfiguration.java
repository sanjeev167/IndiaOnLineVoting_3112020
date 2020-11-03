/**
 * 
 */
package com.pon.config.db;


import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.support.util.AppConstants;

/**
 * @author Sanjeev
 *
 */

@Configuration
@EnableTransactionManagement
//Load to Environment
//(@see resources/datasource-cfg.properties).
@PropertySource({ "classpath:datasource-cfg.properties" })
@EnableJpaRepositories(basePackages = {AppConstants.PKG_REPO_MASTERS,AppConstants.PKG_REPO_PUB,AppConstants.PKG_REPO_VOTER,AppConstants.PKG_REPO_VOTING
										},
										entityManagerFactoryRef = "lostVoteEntityManager", 
										transactionManagerRef = "lostVoteTransactionManager")

public class LostVotePersistenceConfiguration {	
	
	@Autowired
    private Environment env;// Contains Properties Load by @PropertySources
    
    //PON data source configuration
    @Bean   
    @Primary
    public DataSource ponDataSource() {    	
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.pon"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.pon"));
        dataSource.setUsername(env.getProperty("spring.datasource.username.pon"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.pon"));
        return dataSource;
    }

    
 // LostVoteEntityManager bean 
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean lostVoteEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ponDataSource());
        // Scan Entities in Package:       
        em.setPackagesToScan(AppConstants.LOST_VOTE_PKG_ENTITIES_ARRAY );
        em.setPersistenceUnitName(AppConstants.JPA_UNIT_LOSTVOTE); // Important !! We have tell which persistence unit JPA will use

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        
        // JPA & Hibernate      
        final HashMap<String, Object> jpaProperties = new HashMap<String, Object>();
        
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        
         //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        
         //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
 
         // Solved Error: PostGres createClob() is not yet implemented.
        // PostGres Only:
        jpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults",  false);
 
        em.setJpaPropertyMap(jpaProperties);
        em.afterPropertiesSet();
        
        return em;
    }
    
    
 // LostVoteTransactionManager bean
    @Bean
    @Primary
    public PlatformTransactionManager lostVoteTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(lostVoteEntityManager().getObject());
        return transactionManager;
    } 
    
}//End of PersistenceLostVoteAutoConfiguration
