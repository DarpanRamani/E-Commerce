package com.example.shoppingverse.repository;

import com.example.shoppingverse.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByMobNo(String mobileNo);
    public Customer findByEmailId(String email);

    List<Customer> findByAge(int age);

    @Query(value = "select name from customer WHERE Gender = FEMALE AND age Between 20 AND 30 AND orders >= k",nativeQuery = true)
    public List<String> femaleCustomer(int k);

    @Query(value = "select * from Customer c where c.age >=:startAge and c.age <= endAge",nativeQuery = true)
    public List<Customer> findAllAge(int startAge,int endAge);
}
