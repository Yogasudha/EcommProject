package sudha.niit;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sudha.niit.DAO.CartDAO;
import sudha.niit.DAO.OrderDetailDAO;
import sudha.niit.DAO.UserDetailDAO;
import sudha.niit.Model.Cart;
import sudha.niit.Model.OrderDetail;

@Controller
public class PaymentController 
{
	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	UserDetailDAO userDAO;
	
	@Autowired
	OrderDetailDAO orderDAO;
	
	@RequestMapping(value="/payment")
	public String makePayment(Model m,HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		
		List<Cart> cartItemList=cartDAO.listCartItems(username);
		m.addAttribute("listCartItems", cartItemList);
		
		m.addAttribute("total_Amount", this.totalCartValue(cartItemList));
		
		m.addAttribute("address", userDAO.getUser(username).getAddress());
		
		return "Payment";
	}
	
	@RequestMapping(value="/receipt",method=RequestMethod.POST)
	public String generateReceipt(Model m, HttpSession session,@RequestParam("pmode")String pmode)
	{
		String username=(String)session.getAttribute("username");
		
		List<Cart> cartItemList=cartDAO.listCartItems(username);
		m.addAttribute("listCartItems", cartItemList);
		
		int total_Amount=this.totalCartValue(cartItemList);
		
		m.addAttribute("total_Amount",total_Amount);
		m.addAttribute("address", userDAO.getUser(username).getAddress());
		
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setTotalShoppingAmount(total_Amount);
		orderDetail.setUsername(username);
		orderDetail.setOrderDate(new java.util.Date());
		orderDetail.setPmode(pmode);
		
		if(orderDAO.payment(orderDetail))
		{
			orderDAO.updateCartItemStatus(username, orderDetail.getOrderId());
		}
		
		m.addAttribute("order", orderDetail);
		m.addAttribute("address", userDAO.getUser(username).getAddress());
		
		return "Receipt";
	}
	
	public int totalCartValue(List<Cart> cartItemList)
	{
		int totalCost=0;
		int i=0;
		
		while(i<cartItemList.size())
		{
			Cart cartItem=cartItemList.get(i);
			totalCost=totalCost+(cartItem.getPrice()*cartItem.getQuantity());
			i++;
		}
		
		return totalCost;
	}
	
}

