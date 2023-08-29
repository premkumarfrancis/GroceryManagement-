package GroceryStore.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GroceryStore.project.Model.Category;
import GroceryStore.project.Model.CategoryNotFoundException;
import GroceryStore.project.reposistory.CategoryReposistory;
@Service
public class CategoryService {
	@Autowired
	CategoryReposistory categoryRepository;
	
	public List<Category> getcategory() {
		List<Category> category=categoryRepository.findAll();
		return category;
	}

	public void createCategory(Category category) throws CategoryNotFoundException {
			category=categoryRepository.findByName(category.getName());
			if(category!=null) {
				categoryRepository.save(category);
			}
			else {
				throw new CategoryNotFoundException("Already Exist");
			}
		
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

}
