package com.nk.config;


import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.nk")
@PropertySource("classpath:ds-hibernate-cfg.properties")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	
	@Autowired
	private Environment env;
	
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }
	
	@Bean(name = "dataSource")
	public DataSource geDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driverclassname"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory geSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.nk.entity");
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("db.hibernatedialect"));
		properties.put("hibernate.show_sql", env.getProperty("db.hibernateshow_sql"));
		properties.put("hibernate.cache.use_second_level_cache", env.getProperty("db.hibernateUseSecondLevelCache"));
		properties.put("hibernate.cache.region.factory_class", env.getProperty("db.hibernateRegionFactoryClass"));
		properties.put("hibernate.cache.provider_class", env.getProperty("db.hibernateProviderClass"));
		properties.put("hibernate.cache.use_query_cache", env.getProperty("db.hibernatUseQueryCache"));
		properties.put("net.sf.ehcache.configurationResourceName", env.getProperty("db.configurationResourceName"));
		sessionFactory.setHibernateProperties(properties);
		try {
			sessionFactory.afterPropertiesSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionFactory.getObject();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		hibernateTransactionManager.afterPropertiesSet();
		return hibernateTransactionManager;
	}
}
