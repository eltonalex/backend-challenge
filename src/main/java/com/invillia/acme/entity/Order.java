package com.invillia.acme.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long idOrder;
	
	@Column
	private String address;
	
	@Column
	private LocalDate confirmationDate;
	
	@Column
	private String status;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<ItemOrder> itemOrders = new HashSet<>();
	
	public Order() {}

	public Order( String address, LocalDate confirmationDate, String status) {
		super();
		this.address = address;
		this.confirmationDate = confirmationDate;
		this.status = status;
	}

	public Long getId() {
		return idOrder;
	}

	public void setId(Long idOrder) {
		this.idOrder = idOrder;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(LocalDate confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
}
