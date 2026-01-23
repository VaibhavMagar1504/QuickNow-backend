package com.example.QuickNow.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.QuickNow.Model.Order;
import com.example.QuickNow.Model.OrderResponse;

public interface OrderRepo extends JpaRepository<Order, Integer> {

	@Query("SELECT new com.example.QuickNow.Model.OrderResponse(" +
		       "o.id, u.name, p.name, p.price, o.status, o.createAt, p.imageUrl, o.address, o.paymentMethod, u.mobile) " +
		       "FROM Order o " +
		       "JOIN o.user u " +
		       "JOIN o.product p")
		List<OrderResponse> getAllOrder();	
	
	@Query("SELECT new com.example.QuickNow.Model.OrderResponse(" +
	           "o.id, u.name, p.name, p.price, o.status, o.createAt, p.imageUrl, o.address, o.paymentMethod,u.mobile) " +
	           "FROM Order o " +
	           "JOIN o.user u " +
	           "JOIN o.product p " +
	           "WHERE u.id = :userId")
	List<OrderResponse> orderHistory(@Param("userId") int userId);


}
