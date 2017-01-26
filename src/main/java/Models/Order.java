package Models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergiu on 19-Jan-17.
 */
@NamedQueries({
		@NamedQuery(query = "SELECT O FROM Order O WHERE state=:state",name = "GetAllConfirmedOrders")
})
@Entity
@Table(name="orders")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column()
	private String state;

	@Formula("(SELECT SUM(OP.Quantity) FROM ordersproducts OP WHERE OP.OrderId=id)")
	private int prodCount;
	@Formula("(SELECT U.Username FROM users U INNER JOIN orders O ON O.OwnerId=U.Id WHERE O.Id=id)")
	private String username;
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="OwnerId")
	private User owner;*/
	@Column
	@Temporal(value = TemporalType.DATE)
	private Date date;
	@Column
	private Double latitude;
	@Column
	private Double longitude;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getProdCount() {
		return prodCount;
	}

	public void setProdCount(int prodCount) {
		this.prodCount = prodCount;
	}

	/*@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ordersproducts",joinColumns = @JoinColumn(name="OrderId", referencedColumnName="Id"),
			inverseJoinColumns=@JoinColumn(name="ProductId", referencedColumnName="Id"))
	private List<Product> products;*/

	/*@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="DriverId")
	private User driver;*/

	public Order(){
		state="Confirmed";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public List<Product> getProducts() {
		return products;
	}*/

	/*public void setProducts(List<Product> products) {
		this.products = products;
	}*/

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/*public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}*/
}
