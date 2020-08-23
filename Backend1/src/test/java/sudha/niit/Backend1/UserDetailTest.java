package sudha.niit.Backend1;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sudha.niit.DAO.UserDetailDAO;
import sudha.niit.Model.UserDetail;

public class UserDetailTest {
	static UserDetailDAO userDAO;
	@BeforeClass
	public static void executeFirst()
	{
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("sudha.niit");
	context.refresh();
	userDAO=(UserDetailDAO)context.getBean("userDAO");
	context.close();
	}
	
	@Ignore
	@Test
	public void registerUserTest()
	{
		UserDetail user=new UserDetail();
		user.setUsername("lidya18");
		user.setCustomerName("Lidya Dashwood");
		user.setAddress("Kolathur, Chennai");
		user.setEmailId("lidya18@gmail.com");
		user.setMobileNo("9898989898");
		user.setPassword("abcde");
		user.setRole("ROLE_ADMIN");
		user.setEnabled(true);
		assertTrue("Problem in registering", userDAO.registerUser(user));
	}
	
	@Test
	public void updateUserTest()
	{
		UserDetail user= new UserDetail();
		user=userDAO.getUser("lidya18");
		user.setCustomerName("Lidya Dashwood");
		assertTrue("Update failed", userDAO.updateUser(user));
	}
}
