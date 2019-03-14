package crm.demo.api.RestController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.demo.entity.Customer;
import crm.demo.service.CustomerService;
import crm.demo.exceptionHandler.*;


@RestController
@RequestMapping("/api")
public class ApiController {
	
	//autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	
	//add mapping get "/customer"
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	//add mapping for get"/customer/{customerId}"
	@GetMapping("/customers/{customerId}")
	public Customer theCustomer(@PathVariable int customerId) {
		Customer customer=customerService.getCustomer(customerId);
		
		if(customer==null) {
			throw new CustomerNotFoundException("Customer id not found: "+customerId);
		}
		return customer;
	}	
	
	// add mapping to post /customer: add new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		/* also in case the pass an id in JSON hence set id to 0
		this will force to save new item instead of update: method saveOrUpdate
		if id is empty means 0 then "insert" new customer*/
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	// add mapping for put /customer - update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	// add mapping to delete /customer/{customerId} - delete an existing customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		// to check customer existence otherwise throw exception
		Customer tempCustomer=customerService.getCustomer(customerId);
		if(tempCustomer==null) {
			throw new CustomerNotFoundException("Customer id not found: "+customerId);
		}
		
		customerService.deleteCustomer(customerId);
		return "Deleted customer, \nid: "+customerId+"\nname: "+tempCustomer.getFirstName();
	}
}
