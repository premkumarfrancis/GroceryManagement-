package GroceryStore.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GroceryStore.project.Model.CategoryNotFoundException;
import GroceryStore.project.Model.Product;
import GroceryStore.project.Model.Users;
import GroceryStore.project.Model.UsersNotFoundException;
import GroceryStore.project.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UsersService usersService;
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Users> getMapping(Authentication auth) {
		
		List<Users> users = usersService.getusers();
		return users;
	}
	@GetMapping("/{id}")
	public Users getUserById(@PathVariable int id,Authentication auth) throws UsersNotFoundException {
		
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
}
