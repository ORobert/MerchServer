package Models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergiu on 19-Jan-17.
 */
@Entity
@NamedQueries({
		@NamedQuery(query = "Select u from User u where u.username=:username and u.password=:pass", name = "getLoginUser")
})
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@OneToMany(mappedBy = "owner")
	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
