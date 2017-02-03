package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by orobe on 27/12/2016.
 */
@NamedQueries({
        @NamedQuery(query = "SELECT P FROM Product P",name = "GetAllProducts")
        //@NamedQuery(query = "select p from Shop_Product p where p.name = :name",name = "get by name shop product"),
        //@NamedQuery(query = "select p from Shop_Product p where p.price = :price",name = "get by price shop product"),
        //@NamedQuery(query = "select p from Shop_Product p where p.id = :id",name = "get by id shop product"),
})
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int quantity;
	@Column
	private double price;

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(name = "ordersproducts",
//            joinColumns = {@JoinColumn(name = "ProductId",referencedColumnName = "id")},
//            inverseJoinColumns = @JoinColumn(name = "OrderId",referencedColumnName = "id")
//    )
//	private List<Order> orders;

    public Product(){}


//    public List<Order> getOrderList() {
//        return orders;
//    }
//
//    public void setOrderList(List<Order> orders) {
//        this.orders = orders;
//    }

    public Product(final String name){
        this.name=name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        String stock="";
        if(this.quantity > 0){
            stock="In Stock";
        }
        else {
            stock="Stock 0";
        }
        return name + "------" + price + "   " + stock;
    }
}
