package Persistence;

import Models.Order;
import Models.OrderState;
import Models.Product;
import Models.User;

import javax.persistence.*;
import java.util.Date;
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

	public List<Product> getProductsByOrder(Order order) {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction trans=entityManager.getTransaction();
		Query query=entityManager.createNativeQuery("SELECT P.Id,P.Name,P.Price,OP.Quantity FROM products P INNER JOIN ordersproducts OP " +
				"ON P.Id=OP.ProductId WHERE OP.OrderId=:id",Product.class);
		query.setParameter("id",order.getId());
		return query.getResultList();
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

	public void orderProducts(Integer ownerId,List<Product> products) {

		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Date date = new Date();
		for (Product product : products){
			Order order = new Order();
			order.setOwnerId(ownerId);
			order.setState("ToBeDelivered");
			order.setAddress("Dorobanrilor Nr.51");
			order.setDate(date);
			entityManager.getTransaction().begin();
			entityManager.persist(order);
			entityManager.getTransaction().commit();
			
//			entityManager.flush();
			entityManager.getTransaction().begin();
			entityManager.createNativeQuery("INSERT into ordersproducts(OrderId,ProductId,Quantity) VALUES (?,?,?)").
					setParameter(1,order.getId()).
					setParameter(2,product.getId()).
					setParameter(3,product.getQuantity()).
					executeUpdate();
			entityManager.getTransaction().commit();
//			entityManager.flush();
		}
		entityManager.close();
		entityManagerFactory.close();

//		Order order = new Order();
//		order.setOwnerId(ownerId);
//		order.setState("ToBeDelivered");
//		order.setAddress("Dorobanrilor Nr.51");
//		order.setProducts(products);
//		entityManager.persist(order);

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
