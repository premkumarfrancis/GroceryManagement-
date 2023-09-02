package GroceryStore.project.reposistory;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GroceryStore.project.Model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUserId(int userId);

	List<Order> findByUser_IdAndOrderDateBetween(int userId, LocalDate startDate, LocalDate endDate);

}
