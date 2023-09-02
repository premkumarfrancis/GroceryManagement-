package GroceryStore.project.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Users {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column
	private String name;
	@Column
	private String password;
	@Column
	private  String role;
	@Column
	private String mailId;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders = new ArrayList<>();

	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int id, String name, String password, String role, String mailId) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
		this.mailId = mailId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMail_id(String mailId) {
		this.mailId = mailId;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

}
