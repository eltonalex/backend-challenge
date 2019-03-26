package com.invillia.acme.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn
	private Item item;

	@Id
	@ManyToOne
	@JoinColumn
	private Order order;

	private LocalDate orderDate;

	public ItemOrder(Order order, LocalDate orderDate) {
		this.order = order;
		this.orderDate = orderDate;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ItemOrder))
			return false;
		ItemOrder that = (ItemOrder) o;
		return Objects.equals(item.getDescription(), that.item.getDescription())
				&& Objects.equals(order.getAddress(), that.order.getAddress())
				&& Objects.equals(orderDate, that.orderDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(item.getDescription(), order.getAddress(), orderDate);
	}
}
