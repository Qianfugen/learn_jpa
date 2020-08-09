package cn.qianfg.test_jpa;

import cn.qianfg.dao.CustomerDao;
import cn.qianfg.dao.LinkManDao;
import cn.qianfg.pojo.Customer;
import cn.qianfg.pojo.LinkMan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一个客户
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        Customer customer = new Customer();
        customer.setCustName("客户_小王");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("联系人_小张");

        /**
         * 配置了客户到联系人的关系
         * insert into cst_customer (cust_address, cust_industry, cust_level, cust_name, cust_phone, cust_source) values (?, ?, ?, ?, ?, ?)
         * insert into cst_linkman (lkm_cust_id, lkm_email, lkm_gender, lkm_memo, lkm_mobile, lkm_name, lkm_phone, lkm_position) values (?, ?, ?, ?, ?, ?, ?, ?)
         * update cst_linkman set lkm_cust_id=? where lkm_id=?
         */
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd1(){
        Customer customer = new Customer();
        customer.setCustName("客户_小王2");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("联系人_小张2");


        /**
         * 配置联系人到客户的关系（多对一）
         * insert into cst_customer (cust_address, cust_industry, cust_level, cust_name, cust_phone, cust_source) values (?, ?, ?, ?, ?, ?)
         * insert into cst_linkman (lkm_cust_id, lkm_email, lkm_gender, lkm_memo, lkm_mobile, lkm_name, lkm_phone, lkm_position) values (?, ?, ?, ?, ?, ?, ?, ?)
         */
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 级联添加
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCasCadeAdd(){
        Customer customer = new Customer();
        customer.setCustName("客户_小王");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("联系人_小张");


        /**
         * 配置联系人到客户的关系（多对一）
         * Hibernate: insert into cst_customer (cust_address, cust_industry, cust_level, cust_name, cust_phone, cust_source) values (?, ?, ?, ?, ?, ?)
         * Hibernate: insert into cst_linkman (lkm_cust_id, lkm_email, lkm_gender, lkm_memo, lkm_mobile, lkm_name, lkm_phone, lkm_position) values (?, ?, ?, ?, ?, ?, ?, ?)
         * Hibernate: update cst_linkman set lkm_cust_id=? where lkm_id=?
         */
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
    }

}
