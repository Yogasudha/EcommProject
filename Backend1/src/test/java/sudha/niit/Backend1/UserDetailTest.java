package sudha.niit.Backend1;

import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDetailTest {
	static UserDetailDAO userDAO;
	@BeforeClass
	public static void executeFirst()
	{
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("process");
	context.refresh();
	userDAO=(UserDAO)context.getBean("userDAO");
	context.close();
	}
	
	@Ignore
	@Test
	public void registerUserTest()
	{
		User user=new User();
		user.setUserName("lidya18");
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
		User user= new User();
		user=userDAO.getUser("lidya18");
		user.setCustomerName("Lidya Dashwood");
		assertTrue("Update failed", userDAO.updateUser(user));
	}
}
