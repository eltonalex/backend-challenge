package com.invillia.acme.entity.repository;

import org.springframework.data.repository.CrudRepository;

import com.invillia.acme.entity.Store;

public interface StoreRepository extends CrudRepository<Store, Long>{
	
	public Store findByName(String name);

}
