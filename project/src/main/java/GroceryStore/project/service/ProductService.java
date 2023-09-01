package GroceryStore.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GroceryStore.project.Model.Category;
import GroceryStore.project.Model.CategoryNotFoundException;
import GroceryStore.project.Model.Product;
import GroceryStore.project.Model.ProductNotFoundException;
import GroceryStore.project.Model.Users;
import GroceryStore.project.Model.UsersNotFoundException;
import GroceryStore.project.reposistory.CategoryReposistory;
import GroceryStore.project.reposistory.ProductRepository;
@Service
public class ProductService {
@Autowired
ProductRepository productRepository;
@Autowired
CategoryReposistory categoryReposistory;

	public List<Product> getProduct() {
		List<Product> product=productRepository.findAll();
		return product;
	}

		public void createProduct(Product product) throws CategoryNotFoundException, ProductNotFoundException {
        Category cat = categoryReposistory.findByName(product.getCategory().getName());

        if (cat == null) {
            throw new CategoryNotFoundException("Category not found");
        }

        Product existingProduct = productRepository.findByName(product.getName());

        if (existingProduct != null) {
            throw new ProductNotFoundException("Product already exists");
        } else {
            product.setCategory(cat);
            productRepository.save(product);
        }
    }



	/*public void updateProduct(int id, Product products) throws ProductNotFoundException  {
		Optional<Product> product=productRepository.findById(id);
		if(product.isPresent()) {
			Product exUser=product.get();
			if(products.getName().equals(exUser.getName()))
			exUser.setPrice(products.getPrice());
			exUser.setQuantity(products.getQuantity());
			productRepository.save(exUser);
		}
		else 
			throw new ProductNotFoundException("User Not Found");
		}*/
	public void updateProduct(int id, Product updatedProduct) throws ProductNotFoundException {
	    Optional<Product> productOptional = productRepository.findById(id);
	    
	    if (productOptional.isPresent()) {
	        Product existingProduct = productOptional.get();
	        
	        // Update only the fields that are provided by the user
	        if (updatedProduct.getName() != null) {
	            existingProduct.setName(updatedProduct.getName());
	        }
	        if (updatedProduct.getPrice() != 0) {
	            existingProduct.setPrice(updatedProduct.getPrice());
	        }
	        if (updatedProduct.getQuantity() != 0) {
	            existingProduct.setQuantity(updatedProduct.getQuantity());
	        }
	        
	        productRepository.save(existingProduct);
	    } else {
	        throw new ProductNotFoundException("Product Not Found");
	    }
	}

	
	public void deleteProduct(int id) throws ProductNotFoundException {
	    Optional<Product> product = productRepository.findById(id);
	    if (product.isPresent()) {
	        Product existingProduct = product.get();
	        existingProduct.setCategory(null); // Clear category association
	        productRepository.deleteById(id);
	    } else {
	        throw new ProductNotFoundException("Product Not Found");
	    }
	}

	

	public Product getProductById(int productId) {
	    Optional<Product> product = productRepository.findById(productId);
	    return product.orElse(null); // Return the found product or null if not found
	}
	
	public Product getProductByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}



}
