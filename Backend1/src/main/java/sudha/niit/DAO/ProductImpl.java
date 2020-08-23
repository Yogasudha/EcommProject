package sudha.niit.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sudha.niit.Model.Product;

@Repository("productDAO")
@Transactional
public class ProductImpl implements ProductDAO 
{

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addProduct(Product product) 
	{
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(product);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public boolean deleteProduct(Product product) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(product);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public boolean updateProduct(Product product) 
	{
		try
		{
			sessionFactory.getCurrentSession().update(product);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}


	public List<Product> listProducts() 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Product");
		List<Product> productList=query.list();
		session.close();
		return productList;
	}

	public Product getProduct(int productId) 
	{
		Session session=sessionFactory.openSession();
		Product product=session.get(Product.class,productId);
		session.close();
		return product;
	}


	public List<Product> listProductsCategoryWise(int categoryId) 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Product where categoryId=:catid");
		query.setParameter("catid", categoryId);
		List<Product> productList=query.list();
		session.close();
		return productList;
	}

}
