package GroceryStore.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GroceryStore.project.Model.Product;
import GroceryStore.project.Model.ProductNotFoundException;
import GroceryStore.project.Model.Users;
import GroceryStore.project.Model.UsersNotFoundException;
import GroceryStore.project.reposistory.ProductRepository;
@Service
public class ProductService {
@Autowired
ProductRepository productRepository;
	public List<Product> getProduct() {
		List<Product> product=productRepository.findAll();
		return product;
	}

	public void createProduct(Product products) throws ProductNotFoundException  {
		Product product=productRepository.findByName(products.getName());
		if(product!=null) {
			productRepository.save(products);
		}
		else {
			throw new ProductNotFoundException("Already Exist");
		}
		
	}

	public void updateProduct(int id, Product products) throws ProductNotFoundException  {
		Optional<Product> product=productRepository.findById(id);
		if(product.isPresent()) {
			Product exUser=product.get();
			exUser.setName(products.getName());
			exUser.setPrice(products.getPrice());
			exUser.setQuantity(products.getQuantity());
			productRepository.save(exUser);
		}
		else 
			throw new ProductNotFoundException("User Not Found");
		}
public void deleteProduct(int id) throws ProductNotFoundException  {
		Optional<Product> product=productRepository.findById(id);
		if(product.isPresent()) {
			productRepository.deleteById(id);
		}
		else {
			throw new ProductNotFoundException("User Not Found");
		}
		
	}

}
