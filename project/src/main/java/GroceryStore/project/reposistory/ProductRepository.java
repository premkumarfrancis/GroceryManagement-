package GroceryStore.project.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GroceryStore.project.Model.Product;

 @Repository
  public interface ProductRepository extends JpaRepository<Product,Integer> {

	Product findByName(String name);

	Product getProductByName(String productName);

}
 
