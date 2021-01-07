package com.spring.repository;

import com.spring.beans.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


public class CustomerRepository {
	
	 @Autowired 
	 private JdbcTemplate jdbcTemplate; 
	
	 public void addCustomer(int customerId,String name,String address) 
	 { 

		jdbcTemplate.update("INSERT INTO customer VALUES (?, ?, ?)", customerId, name, address);
		
	 } 
	  
	 public int updateCustomerAddressById(String address,int customerId) 
	 { 
	
		 return jdbcTemplate.update("update customer set address = ? where customerId = ?", address, customerId);
		 
	 } 


	 @SuppressWarnings("unchecked")
	 public List<Customer> findCustomerById(int customerId) 
	 { 
	  
	        String sql = "SELECT * FROM customer WHERE customerId = ?";

	        return jdbcTemplate.query(
	                sql,
	                (rs, rowNum) ->
	                        new Customer(
	    	                        rs.getInt("customerId"),
	    	                        rs.getString("name"),
	    	                        rs.getString("address"))
	                        );
	  } 
	  
	  public List<Customer> findAllCustomers(int customerId) 
	  { 
		 
	        String sql = "SELECT * FROM customer";

	        return jdbcTemplate.query(
	                sql,
	                (rs, rowNum) ->
	                        new Customer(
	    	                        rs.getInt("customerId"),
	    	                        rs.getString("name"),
	    	                        rs.getString("address"))
	                        );
	  } 
	  
	  public int deleteCustomerById(int customerId) 
	  { 
		 
		   String sql = "DELETE FROM customer WHERE id = ?";
		   Object[] args = new Object[] {customerId};

		   return jdbcTemplate.update(sql, args);
	   
	  } 
	  
}
