package sudha.niit.DAO;

import sudha.niit.Model.OrderDetail;

public interface OrderDetailDAO 
{
	public boolean payment(OrderDetail orderDetail);
	public boolean updateCartItemStatus(String username,int orderId);
}
