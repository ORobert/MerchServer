package Models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by orobe on 27/12/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(query = "select p from Stock_Products p",name = "get all stock products"),
        @NamedQuery(query = "select p from Shop_Products p where p.name = :name",name = "get by name stock product"),
        @NamedQuery(query = "select p from Shop_Products p where p.id = :id",name = "get by id stock product"),
})
public class Stock_Products extends Products implements Serializable {
    @Column
    private int quantity;

    public  Stock_Products(){}

    public Stock_Products(String name, int quantity) {
        super(name);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
