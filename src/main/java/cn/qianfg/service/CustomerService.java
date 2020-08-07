package cn.qianfg.service;

import cn.qianfg.pojo.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer save(Customer customer);

    Customer findById(Integer id);

    void deleteById(Integer id);

    Customer update(Customer customer);

    long count();
}
