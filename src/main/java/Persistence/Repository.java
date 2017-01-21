package Persistence;

import Models.Order;
import Models.Product;
import Models.User;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Sergiu on 19-Jan-17.
 */
public class Repository implements IRepository {
	public List<Product> getAllProducts() {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("GetAllProducts");
        List<Product> resultList  = query.getResultList();
        entityManager.close();
        entityManagerFactory.close();
        return resultList;
	}

	public List<Order> getAllOrders() {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("GetAllOrders");
		List<Order> resultList  = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return resultList;
	}

	public void orderProducts(Order order) {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.persist(order);
		entityManager.close();
		entityManagerFactory.close();
	}

	public void takeOrders(List<Order> orders) {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//Query query=entityManager.createQuery("UPDATE Order ")
		entityManager.close();
		entityManagerFactory.close();
	}

	public void deliverOrder(Order order) {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//Query query=entityManager.createQuery("UPDATE Order ")
		entityManager.close();
		entityManagerFactory.close();
	}

	public User login(String username, String password) {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("getLoginUser");
		query.setParameter("username",username);
		query.setParameter("pass",password);
		List<User> list= query.getResultList();
		if(list.size()==1){
			return list.get(0);
		}else
			return null;

	}

	public List<Product> getByName(String name) {
		return null;
	}

	public Product getById(int id) {
		return null;
	}
}
