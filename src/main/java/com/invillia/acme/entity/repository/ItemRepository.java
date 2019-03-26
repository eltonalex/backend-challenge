package com.invillia.acme.entity.repository;

import org.springframework.data.repository.CrudRepository;

import com.invillia.acme.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{

}
