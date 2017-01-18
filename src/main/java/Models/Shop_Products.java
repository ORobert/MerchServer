package Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * Created by orobe on 27/12/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(query = "select p from Shop_Products p",name = "get all shop products"),
        @NamedQuery(query = "select p from Shop_Products p where p.name = :name",name = "get by name shop product"),
        @NamedQuery(query = "select p from Shop_Products p where p.price = :price",name = "get by price shop product"),
        @NamedQuery(query = "select p from Shop_Products p where p.id = :id",name = "get by id shop product"),
})

public class Shop_Products extends Products  implements Serializable {
    @Column
    private double price;

    public Shop_Products() {

    }

    public Shop_Products(String name, double price) {
        super(name);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
