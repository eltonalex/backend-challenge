package com.invillia.acme;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invillia.acme.entity.Item;
import com.invillia.acme.entity.Order;
import com.invillia.acme.entity.repository.ItemRepository;
import com.invillia.acme.entity.repository.OrderRepository;

@Controller
@RequestMapping(path = "/order")
public class OrderItemController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	public OrderItemController(OrderRepository orderRepository, ItemRepository itemRepository) {
		this.orderRepository = orderRepository;
		this.itemRepository = itemRepository;
	}

	@GetMapping(path = "/list/all")
	@ResponseBody
	public Iterable<Order> listAll() {
		return orderRepository.findAll();
	}

	@GetMapping(path = "/list/one/{id}")
	@ResponseBody
	public Optional<Order> listOne(@PathVariable Long id) {
		return orderRepository.findById(id);
	}

	@GetMapping(path = "/list/address/{address}")
	@ResponseBody
	public Order listName(@PathVariable String address) {
		return orderRepository.findByAddress(address);
	}

	@PostMapping
	@ResponseBody
	public Order create(@RequestParam String address, @RequestParam LocalDate confirmationDate,
			@RequestParam String status, @RequestParam List<Item> itens) {
		Order order = new Order(address, confirmationDate, status);

		if (status != null && status.length() > 0 && address != null && address.length() > 0) {
			Order orderNew = orderRepository.save(order);
			for (Item item : itens) {
				itemRepository.save(item);
			}
			return orderNew;
		}

		return order;
	}

	@GetMapping(path = "/address/{address}")
	@ResponseBody
	public Order findByName(@PathVariable String address) {
		return orderRepository.findByAddress(address);
	}

	@PutMapping(path = "/update/id")
	@ResponseBody
	public Order updateById(@RequestParam Long id, @RequestParam String addressNew,
			@RequestParam LocalDate confirmationDate, @RequestParam String status) {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isPresent()) {
			Order orderNew = new Order();
			orderNew.setId(order.get().getId());
			orderNew.setAddress(addressNew);
			orderNew.setConfirmationDate(confirmationDate);
			orderNew.setStatus(status);
			;
			return orderRepository.save(orderNew);
		}

		return null;
	}

}
