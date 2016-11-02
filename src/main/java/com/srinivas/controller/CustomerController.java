package com.srinivas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srinivas.dao.CustomerDao;
import com.srinivas.dao.CustomerSearch;
import com.srinivas.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	CustomerDao customerdaoimpl;
	
	@Autowired
	CustomerSearch customersearchimpl;
	
	@RequestMapping(value="/customers", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getCustomers() {
		return (List<Customer>) customerdaoimpl.findAll();
	}
	//public Customer getCustomers(Model model) {
		//model.addAttribute("customerList", customerdaoimpl.findAll());
		//return "home";
	//}

	@RequestMapping("/customers/name/{name}")
	public String getCustomerByName(@PathVariable("name") String name, Model model) {
		
		model.addAttribute("customerList", customerdaoimpl.findByName(name));
		return "home";
	}

	@RequestMapping("/customers/age/{age}")
	public String getCustomerByAge(@PathVariable("age") int age, Model model) {
		
		model.addAttribute("customerList", customerdaoimpl.findByAge(age));
		return "home";
	}

	@RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute Customer customer) {
		customerdaoimpl.save(customer);
		return "redirect:customers";
	}

	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam String search) {
		model.addAttribute("customerList", customersearchimpl.searchCustomers(search));
		model.addAttribute("search", search);
		return "home";
	}

}