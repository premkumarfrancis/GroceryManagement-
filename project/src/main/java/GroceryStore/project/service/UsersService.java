package GroceryStore.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GroceryStore.project.Model.Category;
import GroceryStore.project.Model.CategoryNotFoundException;
import GroceryStore.project.Model.Order;
import GroceryStore.project.Model.OutOfStockException;
import GroceryStore.project.Model.Product;
import GroceryStore.project.Model.ProductNotFoundException;
import GroceryStore.project.Model.Users;
import GroceryStore.project.Model.UsersNotFoundException;
import GroceryStore.project.reposistory.OrderRepository;
import GroceryStore.project.reposistory.ProductRepository;
import GroceryStore.project.reposistory.UsersRepository;

@Service
public class UsersService {
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;

	public void createUsers(Users users) throws UsersNotFoundException {
		Users user = usersRepository.findByName(users.getName());
		if (user == null) {
			usersRepository.save(users);
		} else {
			throw new UsersNotFoundException("User Already Exists");
		}
	}

	public void updateUsers(int id, Users users) throws UsersNotFoundException {
		Optional<Users> user = usersRepository.findById(id);
		if (user.isPresent()) {
			Users exUser = user.get();
			exUser.setMail_id(users.getMailId());
			exUser.setName(users.getName());
			exUser.setPassword(users.getPassword());
			exUser.setRole(users.getRole());
			usersRepository.save(exUser);
		} else {
			throw new UsersNotFoundException("User Not Found");
		}
	}

	public void deleteUsers(int id) throws UsersNotFoundException {
		Optional<Users> user = usersRepository.findById(id);
		if (user.isPresent()) {
			usersRepository.deleteById(id);
		} else {
			throw new UsersNotFoundException("User Not Found");
		}
	}

	public List<Users> getusers() {
		List<Users> user = usersRepository.findAll();
		return user;
	}

	public Users getUserById(int id) throws UsersNotFoundException {
		Optional<Users> user = usersRepository.findById(id);
		if (user.isPresent()) {
			Users preUser = user.get();
			return preUser;
		} else {
			throw new UsersNotFoundException("User Not Found");
		}
	}

	public List<Order> getUserOrders(int userId) {
		return orderRepository.findByUserId(userId);
	}
	

	    public void placeOrder(int userId, String productName, int quantity) throws UsersNotFoundException, ProductNotFoundException, OutOfStockException {
	        Users user = usersRepository.findById(userId)
	                .orElseThrow(() -> new UsersNotFoundException("User not found"));

	        Product product = productRepository.getProductByName(productName);

	        if (product == null) {
	            throw new ProductNotFoundException("Product not found");
	        }

	        if (product.getQuantity() < quantity) {
	            throw new OutOfStockException("Product quantity is insufficient");
	        }

	        // Create a new order
	        Order order = new Order();
	        order.setUser(user);
	        order.setProduct(product);
	        order.setQuantity(quantity);

	        // Reduce the quantity of the product
	        product.setQuantity(product.getQuantity() - quantity);

	        // Save the order and update the product
	        orderRepository.save(order);
	        productRepository.save(product); // You should implement a saveProduct method in your ProductService
	    }
	    public List<Order> getOrdersByDate(int userId, LocalDate startDate, LocalDate endDate) {
	        return orderRepository.findByUser_IdAndOrderDateBetween(userId, startDate, endDate);
	    }
	}

