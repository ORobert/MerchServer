package Models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergiu on 19-Jan-17.
 */
@NamedQueries({
		@NamedQuery(query = "SELECT O FROM Order O",name = "GetAllOrders")
})
@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private OrderState state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="OwnerId")
	private User owner;

	@Column
	private double latitude;
	@Column
	private double longitude;

	@ManyToMany
	@JoinTable(name = "ordersproducts",joinColumns = @JoinColumn(name="OrderId", referencedColumnName="Id"),
			inverseJoinColumns=@JoinColumn(name="ProductId", referencedColumnName="Id"))
	private List<Product> products;

	@OneToOne
	@JoinColumn(name="DriverId")
	private User driver;

	public Order(){
		state=OrderState.Confirmed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
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

	public User getOwner() {
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
	}
}
