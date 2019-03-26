package com.invillia.acme.entity.repository;

import org.springframework.data.repository.CrudRepository;

import com.invillia.acme.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
	public Order findByAddress(String address);
	
}
