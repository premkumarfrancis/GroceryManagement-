package GroceryStore.project.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan 
@Table(name="orders")
public class Order {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private Users user;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;
	    
	    @Column
	    private int quantity;
	    
	    @Column(name = "order_date") // Add this annotation for the date column
	    private LocalDate orderDate; // Add the date field


		public Order() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Order(int id, Users user, Product product, int quantity, LocalDate orderDate) {
			super();
			this.id = id;
			this.user = user;
			this.product = product;
			this.quantity = quantity;
			this.orderDate = orderDate;
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public Users getUser() {
			return user;
		}


		public void setUser(Users user) {
			this.user = user;
		}


		public Product getProduct() {
			return product;
		}


		public void setProduct(Product product) {
			this.product = product;
		}


		public int getQuantity() {
			return quantity;
		}


		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}


		public LocalDate getOrderDate() {
			return orderDate;
		}


		public void setOrderDate(LocalDate orderDate) {
			this.orderDate = orderDate;
		}

}