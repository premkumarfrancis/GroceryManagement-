package GroceryStore.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GroceryStore.project.Model.Product;
import GroceryStore.project.Model.ProductNotFoundException;
import GroceryStore.project.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping
	public List<Product> getProduct() {
		List<Product> products = productService.getProduct();
		return products;
	}

	@PostMapping
	public HttpStatus createProduct(@RequestBody Product product,Authentication auth) throws ProductNotFoundException {
		productService.createProduct(product);
		return HttpStatus.CREATED;
	}

	@PutMapping("/{id}")
	public HttpStatus updateProduct(@PathVariable int id, @RequestBody Product product,Authentication auth) throws ProductNotFoundException {
		productService.updateProduct(id, product);
		return HttpStatus.OK;
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteProduct(@PathVariable int id,Authentication auth) throws ProductNotFoundException {
		productService.deleteProduct(id);
		return HttpStatus.OK;

	}

}
