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
		@NamedQuery(query = "SELECT O FROM Order O WHERE O.state=:state",name = "GetAllConfirmedOrders"),
		@NamedQuery(query = "SELECT O FROM Order O WHERE O.state=:state AND O.driverId=:drId",name = "GetOrdersByDriver"),
		@NamedQuery(query = "SELECT O FROM Order O WHERE O.ownerId=:id",name = "GetOrdersByOwner"),
		@NamedQuery(query = "SELECT O.id FROM Order O WHERE O.ownerId=:ownerId",name = "GetAllByOwnerId")
})
@Entity
@Table(name="orders")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String state;

	@Column
	private Integer ownerId;

	@Formula("(SELECT U.Username FROM users U INNER JOIN orders O ON O.OwnerId=U.Id WHERE O.Id=id)")
	private String username;

	@Column
	private Double latitude;
	@Column
	private Double longitude;

	@Column
	@Temporal(value = TemporalType.DATE)
	private Date date;

	@Formula("(SELECT SUM(OP.Quantity) FROM ordersproducts OP WHERE OP.OrderId=id)")
	private int prodCount;

	@Column
	private Integer driverId;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name="OwnerId")
	@Column
	private String address;
	@Formula("(SELECT U.Username FROM users U INNER JOIN orders O ON O.DriverId=U.Id WHERE O.Id=id)")
	private String driver;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
