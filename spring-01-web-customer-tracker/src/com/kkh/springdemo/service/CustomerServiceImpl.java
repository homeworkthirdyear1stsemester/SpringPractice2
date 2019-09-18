package com.kkh.springdemo.service;

import com.kkh.springdemo.dao.CustomerDAO;
import com.kkh.springdemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    // need to inject customer dao
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional // customerDAO로부터 권한을 위임 받는 곳
    public List<Customer> getCustomers() {
        return this.customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        this.customerDAO.saveCustomer(customer);
    }
}
