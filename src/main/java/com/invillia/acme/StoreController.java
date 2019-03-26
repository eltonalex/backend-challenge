package com.invillia.acme;

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

import com.invillia.acme.entity.Store;
import com.invillia.acme.entity.repository.StoreRepository;

@Controller
@RequestMapping(path = "/store")
public class StoreController {

	@Autowired
	private StoreRepository repository;

	public StoreController(StoreRepository storeRepository) {
		this.repository = storeRepository;
	}
	
	public StoreController() {}

	@GetMapping(path = "/list/all")
	@ResponseBody
	public Iterable<Store> listAll() {
		return repository.findAll();
	}

	@GetMapping(path = "/list/one/{id}")
	@ResponseBody
	public Optional<Store> listOne(@PathVariable Long id) {
		return repository.findById(id);
	}

	@GetMapping(path = "/list/name/{name}")
	@ResponseBody
	public Store listName(@PathVariable String name) {
		return repository.findByName(name);
	}

	@PostMapping
	@ResponseBody
	public Store create(@RequestParam String name, @RequestParam String address) {
		Store store = new Store(name, address);

		if (name != null && name.length() > 0 && address != null && address.length() > 0) {
			repository.save(store);
			return store;
		}

		return null;
	}

	@GetMapping(path = "/name/{name}")
	@ResponseBody
	public Store findByName(@PathVariable String name) {
		return repository.findByName(name);
	}

	@PutMapping(path = "/update/name")
	@ResponseBody
	public Store updateByName(@RequestParam String name, @RequestParam String nameNew,
			@RequestParam String addressNew) {
		if(!name.isEmpty()&& name != null) {
			Store storeAtual = repository.findByName(name);
			storeAtual.setName(nameNew);
			storeAtual.setAddress(addressNew);
			return repository.save(storeAtual);
		}
		return null;
	}

	@PutMapping(path = "/update/id")
	@ResponseBody
	public Store updateById(@RequestParam Long id, @RequestParam String nameNew, @RequestParam String addressNew) {
		Optional<Store> store = repository.findById(id);
		if (store.isPresent()) {
			Store storeNew = new Store();
			storeNew.setId(store.get().getId());
			storeNew.setName(nameNew);
			storeNew.setAddress(addressNew);
			return repository.save(storeNew);
		}
		return null;
	}

}
