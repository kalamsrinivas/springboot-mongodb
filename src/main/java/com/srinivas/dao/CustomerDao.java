package com.srinivas.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.srinivas.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, String> {
	public List<Customer> findByName(String name);
	public List<Customer> findByAge(int age);
}
