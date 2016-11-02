package com.srinivas.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.srinivas.model.Customer;

@Repository
public class CustomerSearch {

	@Autowired
	MongoTemplate mongoTemplate;

	public Collection<Customer> searchCustomers(String text) {
		return mongoTemplate.find(Query.query(new Criteria()
						.orOperator(Criteria.where("name").regex(text, "i"))
						), Customer.class);
	}
}
