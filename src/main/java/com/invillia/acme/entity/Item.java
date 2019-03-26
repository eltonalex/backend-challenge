package com.invillia.acme.entity;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long idItem;
	
	@Column
	private String description;
	
	@Column
	private BigDecimal price;
	
	@Column
	private Integer quantity;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private Set<ItemOrder> itemOrders;
	
	public Item(String description, BigDecimal price, Integer quantity, ItemOrder... itemOrders) {
		super();
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		for(ItemOrder itemOrder : itemOrders) itemOrder.setItem(this);
        this.itemOrders = Stream.of(itemOrders).collect(Collectors.toSet());
	}
	
	public Long getId() {
		return idItem;
	}
	public void setId(Long idItem) {
		this.idItem = idItem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
