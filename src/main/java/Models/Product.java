package Models;

import javax.persistence.*;
import java.io.Serializable;

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

    public Product(){}

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
        return "Name "+name;
    }
}
