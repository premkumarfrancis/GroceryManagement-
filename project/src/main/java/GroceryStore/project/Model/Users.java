package GroceryStore.project.Model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column
	private String name;
	@Column
	private int password;
	@Column
	private  String role;
	@Column
	private String mailId;
	public Users(int id, String name, int password, String role, String mailId) {
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
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
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

}
