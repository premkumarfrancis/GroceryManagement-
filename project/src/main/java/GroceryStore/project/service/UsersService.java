package GroceryStore.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GroceryStore.project.Model.Category;
import GroceryStore.project.Model.CategoryNotFoundException;
import GroceryStore.project.Model.Product;
import GroceryStore.project.Model.Users;
import GroceryStore.project.Model.UsersNotFoundException;
import GroceryStore.project.reposistory.UsersRepository;
@Service
public class UsersService {
@Autowired
 UsersRepository usersRepository;
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

	public List<Users> getusers() {
		List<Users> user=usersRepository.findAll();
		return user;
	}
	
	public Users getUserById(int id) throws UsersNotFoundException {
		Optional<Users> user=usersRepository.findById(id);
		if(user.isPresent()) {
			Users preUser=user.get();
			return preUser;
		}
		else {
			throw new UsersNotFoundException("User Not Found");
		}
	}
}
