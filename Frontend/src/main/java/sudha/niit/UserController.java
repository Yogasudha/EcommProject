package sudha.niit;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sudha.niit.DAO.ProductDAO;
import sudha.niit.Model.Product;

@Controller
public class UserController 
{

	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping("/login_success")
	public String loginSuccess(HttpSession session,Model m)
	{
		String page="";
		
		boolean loggedIn=false;
		
		//This object will contain the logged in user detail like username and role.
		SecurityContext sContext=SecurityContextHolder.getContext();
		Authentication authentication=sContext.getAuthentication();
		
		String username=authentication.getName();
		
		//Getting all the roles associated with the user
		Collection<GrantedAuthority> roles=(Collection<GrantedAuthority>)authentication.getAuthorities();
		
		for(GrantedAuthority role:roles)
		{
			session.setAttribute("role", role.getAuthority());
			
			if(role.getAuthority().equals("ROLE_ADMIN"))
			{
				loggedIn=true;
				page="AdminHome";
				session.setAttribute("loggedIn", loggedIn);
				session.setAttribute("username", username);
			}
			else
			{
				List<Product> productList=productDAO.listProducts();
				m.addAttribute("productList", productList);
				loggedIn=true;
				page="UserHome";
				session.setAttribute("loggedIn", loggedIn);
				session.setAttribute("username", username);
			}
		}
		return page;
	}
	
	@RequestMapping("/perform_logout")
	public String loggingout(HttpSession session)
	{
		session.invalidate();
		return "Login";
	}
	
}

