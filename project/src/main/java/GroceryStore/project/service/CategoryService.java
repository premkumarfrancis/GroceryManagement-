package GroceryStore.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GroceryStore.project.Model.Category;
import GroceryStore.project.Model.CategoryNotFoundException;
import GroceryStore.project.Model.Product;
import GroceryStore.project.reposistory.CategoryReposistory;
@Service
public class CategoryService {
	@Autowired
	CategoryReposistory categoryRepository;
	
	public List<Category> getcategory() {
		List<Category> category=categoryRepository.findAll();
		return category;
	}

	/*public void createCategory(Category category) throws CategoryNotFoundException {
		Category newCategory=categoryRepository.findByName(category.getName());
			if(newCategory!=null) {
				category.setId(newCategory.getId());
				category.setName(newCategory.getName());
				categoryRepository.save(category);
			}
			else {
				categoryRepository.save(category);
			}
	}*/
	public void createCategory(Category category) throws CategoryNotFoundException {
	    Category existingCategory = categoryRepository.findByName(category.getName());
	    
	    if (existingCategory != null) {
	        throw new CategoryNotFoundException("Category with the given name already exists");
	    }
	    
	    categoryRepository.save(category);
	}


	public void updateCategory(int id, Category category) throws CategoryNotFoundException {
			Optional<Category>categories=categoryRepository.findById(id);
			if(categories.isPresent()) {
				Category exUser=categories.get();
				exUser.setName(category.getName());
				categoryRepository.save(exUser);
			}
			else {
				throw new CategoryNotFoundException("User Not Found");
			}
		
	}

	public void deleteCategory(int id) throws CategoryNotFoundException {
		Optional<Category> category=categoryRepository.findById(id);
		if(category.isPresent()) {
			categoryRepository.deleteById(id);
		}
		else {
			throw new CategoryNotFoundException("User Not Found");
		}
	}
	/*public void deleteCategory(int categoryId) {
	    Optional<Category> category = categoryRepository.findById(categoryId);
	    if (category.isPresent()) {
	        List<Product> products = category.get().getProducts();
	        for (Product product : products) {
	            product.setCategory(null); // Remove category reference
	            productRepository.save(product);
	        }
	        categoryRepository.deleteById(categoryId);
	    }
	}*/


}
