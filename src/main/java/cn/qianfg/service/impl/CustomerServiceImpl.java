package cn.qianfg.service.impl;

import cn.qianfg.dao.CustomerDao;
import cn.qianfg.pojo.Customer;
import cn.qianfg.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer findById(Integer id) {
        Optional<Customer> rlt = customerDao.findById(id);
        return rlt.orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        customerDao.deleteById(id);
    }

    @Override
    public Customer update(Customer customer) {
        return customerDao.saveAndFlush(customer);
    }

    @Override
    public long count() {
        return customerDao.count();
    }
}
