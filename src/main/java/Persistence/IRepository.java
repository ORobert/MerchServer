package Persistence;

import Models.Order;
import Models.Product;
import Models.User;

import java.util.List;

/**
 * Created by orobe on 29/12/2016.
 */
public interface IRepository {
    List<Product> getAllProducts();
    List<Order> getAllConfirmedOrders();
    List<Product> getByName(String name);
    Product getById(int id);
    void orderProducts(Integer ownerId,List<Product> products);
    void takeOrders(List<Order> orders);
    void deliverOrder(Order order);
    User login(String username, String password);
    List<Order> getOrdersByDriver(User driver);
    void updateLocation(List<Order> orders, double longitude, double latitude);
    List<Product> getProductsByOrder(Order order);
}
