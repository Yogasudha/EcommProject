package sudha.niit.DAO;

import sudha.niit.Model.UserDetail;

public interface UserDetailDAO 
{
	public boolean registerUser(UserDetail userDetail);
	public boolean updateUser(UserDetail userDetail);
	public UserDetail getUser(String username);
}