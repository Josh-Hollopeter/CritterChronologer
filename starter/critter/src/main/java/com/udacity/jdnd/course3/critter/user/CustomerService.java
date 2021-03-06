package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

   @Autowired
    private CustomerRepository customerRepo;


    public Customer saveCustomer(Customer customer){
       return customerRepo.save(customer);


    }

    public List<Customer> getAllCustomers (){
        return customerRepo.findAll();

    }
}
