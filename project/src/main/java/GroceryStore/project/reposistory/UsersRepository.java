package GroceryStore.project.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GroceryStore.project.Model.Users;

@Repository
public interface UsersRepository extends JpaRepository< Users,Integer> {

	Users findByName(String name);
	    //select * from users where name=name
	
	
}
