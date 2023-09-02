package GroceryStore.project.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import GroceryStore.project.Model.CategoryNotFoundException;
import GroceryStore.project.Model.Order;
import GroceryStore.project.Model.OutOfStockException;
import GroceryStore.project.Model.Product;
import GroceryStore.project.Model.ProductNotFoundException;
import GroceryStore.project.Model.Users;
import GroceryStore.project.Model.UsersNotFoundException;
import GroceryStore.project.service.ProductService;
import GroceryStore.project.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UsersService usersService;
	@Autowired
	ProductService productService;
	@GetMapping
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Users> getMapping() {
		
		List<Users> users = usersService.getusers();
		return users;
	}
	@GetMapping("/{id}")
	public Users getUserById(@PathVariable int id) throws UsersNotFoundException {
		
		Users user=usersService.getUserById(id);
		return user;
	}
	

	@PostMapping
	public HttpStatus createUsers(@RequestBody Users users) throws UsersNotFoundException {
		usersService.createUsers(users);
		return HttpStatus.CREATED;
	}

	@PutMapping("/{id}")
	public HttpStatus updateUsers(@PathVariable int id, @RequestBody Users users) throws UsersNotFoundException {
		usersService.updateUsers(id, users);
		return HttpStatus.OK;
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteUsers(@PathVariable int id) throws UsersNotFoundException {
		usersService.deleteUsers(id);
		return HttpStatus.OK;
	}
	@PostMapping("/{id}/order")
	public ResponseEntity<String> placeOrder(@PathVariable int id, @RequestParam String productName, @RequestParam int quantity) {
	    try {
	        Users user = usersService.getUserById(id);

	        if (user != null) {
	            usersService.placeOrder(user.getId(), productName, quantity);

	            // Return an acknowledgment message
	            return ResponseEntity.ok("Order placed successfully");
	        } else {
	            return ResponseEntity.badRequest().body("User not found");
	        }
	    } catch (ProductNotFoundException e) {
	        return ResponseEntity.badRequest().body("Product not found");
	    } catch (OutOfStockException e) {
	        return ResponseEntity.badRequest().body("Product quantity is insufficient");
	    } catch (UsersNotFoundException e) {
	        return ResponseEntity.badRequest().body("User not found");
	    }
	}
	@GetMapping("/{id}/orders")
	public ResponseEntity<List<Order>> getOrdersByDate(
	        @PathVariable int id,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws UsersNotFoundException {
	    try {
	        Users user = usersService.getUserById(id);

	        if (user != null) {
	            List<Order> orders = usersService.getOrdersByDate(user.getId(), startDate, endDate);

	            // Return the filtered orders
	            return ResponseEntity.ok(orders);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (UsersNotFoundException e) {
	        return ResponseEntity.notFound().build();
	    }
	   
	}
	
}
