package GroceryStore.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GroceryStore.project.Model.Cart;
import GroceryStore.project.Model.Category;
import GroceryStore.project.Model.CategoryNotFoundException;
import GroceryStore.project.Model.Product;
import GroceryStore.project.Model.Users;
import GroceryStore.project.Model.UsersNotFoundException;
import GroceryStore.project.reposistory.CartRepository;
import GroceryStore.project.reposistory.UsersRepository;
@Service
public class UsersService {
@Autowired
 UsersRepository usersRepository;
 @Autowired
 CartRepository cartRepository;
	public void createUsers(Users users) throws UsersNotFoundException {
		Users user=usersRepository.findByName(users.getName());
		if(user!=null) {
			usersRepository.save(users);
		}
		else {
			throw new UsersNotFoundException("Already Exist");
		}
	}
	

	public void updateUsers(int id, Users users) throws UsersNotFoundException {
		Optional<Users> user=usersRepository.findById(id);
		if(user.isPresent()) {
			Users exUser=user.get();
			exUser.setMail_id(users.getMailId());
			exUser.setName(users.getName());
			exUser.setPassword(users.getPassword());
			exUser.setRole(users.getRole());
			usersRepository.save(exUser);
		}
		else {
			throw new UsersNotFoundException("User Not Found");
		}
	}

	public void deleteUsers(int id) throws UsersNotFoundException {
		Optional<Users> user=usersRepository.findById(id);
		if(user.isPresent()) {
			usersRepository.deleteById(id);
		}
		else {
			throw new UsersNotFoundException("User Not Found");
		}
	}

	public List<Product> getCart(int id) throws CategoryNotFoundException {
		Optional<Users> user=usersRepository.findById(id);
		if(user.isPresent()) {
			Users users = user.get();
			Optional<Cart> cart=cartRepository.findById(users.getId());
			if(cart.isPresent()) {
				Cart cartCategory= cart.get();
				List<Product> products=cartCategory.getProducts();
				return products;
				}
			else {
				throw new CategoryNotFoundException("User's Cart Not Found");
			}
		}
		else {
			throw new CategoryNotFoundException("Invalid User"); 
		}
	}

	public List<Users> getusers() {
		List<Users> user=usersRepository.findAll();
		return user;
	}

	
}
