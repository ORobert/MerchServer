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
    void orderProducts(Order order);
    void takeOrders(List<Order> orders);
    void deliverOrder(Order order);
    User login(String username, String password);
}
