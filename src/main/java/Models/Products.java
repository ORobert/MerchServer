package Models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by orobe on 27/12/2016.
 */
@MappedSuperclass
public abstract class Products  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    public Products(){

    }

    public Products(final String name){
        this.name=name;
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
