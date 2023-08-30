package GroceryStore.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GroceryStore.project.Model.Category;
import GroceryStore.project.Model.CategoryNotFoundException;
import GroceryStore.project.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryservice;
    @GetMapping
    public List<Category> getcategory(){
    	List<Category> categorys=categoryservice.getcategory();
		return categorys;	
    }
    @PostMapping
    public HttpStatus createCategory(@RequestBody Category category,Authentication auth) throws CategoryNotFoundException{
    	categoryservice.createCategory(category);
    	return HttpStatus.CREATED;
    }
    @PutMapping("/{id}")
    public HttpStatus updateProduct (@PathVariable int id,@RequestBody Category category,Authentication auth) throws CategoryNotFoundException {
    	categoryservice.updateCategory(id,category);
    	return HttpStatus.OK;
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable int id,Authentication auth ) throws CategoryNotFoundException {
			categoryservice.deleteCategory(id);
    			return HttpStatus.OK;
    }
}
