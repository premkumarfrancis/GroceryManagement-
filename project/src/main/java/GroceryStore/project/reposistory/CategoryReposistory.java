package GroceryStore.project.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

import GroceryStore.project.Model.Category;

public interface CategoryReposistory extends JpaRepository<Category,Integer>{

	Category findByName(String name);

}
