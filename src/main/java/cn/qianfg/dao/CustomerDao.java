package cn.qianfg.dao;

import cn.qianfg.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Integer> {
}
