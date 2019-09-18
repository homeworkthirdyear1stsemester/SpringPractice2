package com.kkh.springdemo.dao;

import com.kkh.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    // @Transactional // service에 권한을 위임 하였고, service에서 해당 annoataion을 선언 하기 때문에 필요가 없음
    @Override
    public List<Customer> getCustomers() {

        // get the current hibernate session
        Session currentSession = this.sessionFactory.getCurrentSession();

        // create a query ... sort by last name
        Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName",
                Customer.class);

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the result
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        // get current hibernate session
        Session currentSession = this.sessionFactory.getCurrentSession();

        // save the customer ... finally
        currentSession.save(customer);
    }

    @Override
    public Customer getCustomer(int theId) {

        // get the current hibernate session
        Session currentSession = this.sessionFactory.getCurrentSession();

        // now retrieve/read from database using the primary key
        Customer customer = currentSession.get(Customer.class, theId);

        return customer;
    }
}
