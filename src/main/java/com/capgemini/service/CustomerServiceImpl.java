package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Customer;
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository repository;

	@Override
	public Customer addCustomer(Customer customer) {
		return repository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer updated = null;
		if (repository.existsById(customer.getCustomerId())
				&& repository.findById(customer.getCustomerId()).get().isActive())
			updated = repository.save(customer);
		return updated;
	}

	@Override
	public Customer removeCustomer(Customer customer) {
		Customer delCustomer = customer;
		if (repository.existsById(customer.getCustomerId())) {
			customer.setActive(false);
			repository.save(customer);
			return delCustomer;
		}
		return null;
	}

	@Override
	public Customer viewCustomer(Customer customer) {
		Customer showCustomer = repository.findById(customer.getCustomerId()).get();
		if (showCustomer.isActive())
			return showCustomer;
		return null;
	}

	@Override
	public List<Customer> viewAllCustomer(Restaurant rest) {
		List<Customer> list = repository.viewAllCustomers(rest.getRestaurantId());
		List<Customer> newList = new ArrayList<>();
		for (Customer c : list) {
			if (c.isActive())
				newList.add(c);

		}
		return newList;
	}

}