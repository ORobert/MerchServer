package Persistence;

import Models.Order;
import Models.OrderState;
import Models.Product;
import Models.User;

import javax.persistence.*;
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

	public List<Order> getOrdersByDriver(User driver) {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("GetAllOrdersByDriver");
		query.setParameter("state", "ToBeDelivered");
		query.setParameter("drId", driver.getId());
		List<Order> resultList  = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return resultList;
	}

	public void updateLocation(List<Order> orders, double longitude, double latitude) {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction trans=entityManager.getTransaction();
		Query query=entityManager.createQuery("UPDATE Order O SET O.latitude=:lat,O.longitude=:long WHERE O.id=:id");
		for (Order order:orders) {
			query.setParameter("lat",latitude);
			query.setParameter("long",longitude);
			query.setParameter("id",order.getId());
			trans.begin();
			query.executeUpdate();
			trans.commit();
		}
		entityManager.close();
		entityManagerFactory.close();
	}

	public List<Order> getAllConfirmedOrders() {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("GetAllConfirmedOrders");
		query.setParameter("state", "Confirmed");
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
		EntityTransaction trans=entityManager.getTransaction();
		Query query=entityManager.createQuery("UPDATE Order O SET O.driverId=:drId,O.state=:state WHERE O.id=:id");
		for (Order order:orders) {
			query.setParameter("drId",order.getDriverId());
			query.setParameter("state",order.getState());
			query.setParameter("id",order.getId());
			trans.begin();
			query.executeUpdate();
			trans.commit();
		}
		entityManager.close();
		entityManagerFactory.close();
	}

	public void deliverOrder(Order order) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction trans = entityManager.getTransaction();
		Query query = entityManager.createQuery("UPDATE Order O SET O.driverId=:drId,O.state=:state WHERE O.id=:id");
		query.setParameter("drId", order.getDriverId());
		query.setParameter("state", "Delivered");
		query.setParameter("id", order.getId());
		trans.begin();
		query.executeUpdate();
		trans.commit();
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
