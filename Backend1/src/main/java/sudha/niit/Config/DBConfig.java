package sudha.niit.Config;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import sudha.niit.Model.Cart;
import sudha.niit.Model.Category;
import sudha.niit.Model.Product;
import sudha.niit.Model.UserDetail;

@Configuration
@ComponentScan("sudha.niit")
@EnableTransactionManagement
public class DBConfig 
{
	@Bean(name="dataSource")
	public DataSource getH2DataSource()
	{
		DriverManagerDataSource  datasource=new DriverManagerDataSource();

		datasource.setDriverClassName("org.h2.Driver");
		datasource.setUrl("jdbc:h2:tcp://localhost/~/test");
		datasource.setUsername("sa");
		datasource.setPassword("");

		System.out.println("==Creating DataSource Bean==");
		return datasource;	
	}
	
	@Bean(name="SessionFactory")
    public SessionFactory getsessionFactory() 
	{
		Properties hibernateproperties=new Properties();
		hibernateproperties.put("hibernate.hbm2ddl.auto","update");
		hibernateproperties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		hibernateproperties.put("hibernate.show_sql", true);
		hibernateproperties.put("hibernate.format_sql", true);
		
		LocalSessionFactoryBuilder factory=new LocalSessionFactoryBuilder(this.getH2DataSource());
		factory.addProperties(hibernateproperties);
		factory.addAnnotatedClass(Category.class);
		factory.addAnnotatedClass(Product.class);
		factory.addAnnotatedClass(UserDetail.class);
		factory.addAnnotatedClass(Cart.class);
		
		SessionFactory sessionFactory=factory.buildSessionFactory();	
		System.out.println("Session is created");
        return sessionFactory;
	}
	
	@Bean(name="txManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("Creating the TransactionManager Bean");
		return new HibernateTransactionManager(sessionFactory);
	}
}


