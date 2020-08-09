package cn.qianfg.test_jpa;

import cn.qianfg.dao.CustomerDao;
import cn.qianfg.pojo.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJpql() {
        Customer customer = customerDao.findJpql("葫芦娃");
        System.out.println(customer);
    }

    @Test
    public void testFindCustomerNameAndId() {
        Customer customer = customerDao.findCustomerNameAndId("葫芦娃", 1);
        System.out.println(customer);
    }

    /**
     * 测试更新，需要事务
     *
     * @Transactional：支持事务 SpringDataJpa中使用jpql 完成更新/删除操作
     * *需要手动添加事务支持
     * *默认执行后，回滚事务
     * @Rollback:设置是否自动回滚 false 不回滚，true 回滚
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomer() {
        customerDao.updateCustomer("皮皮虾", 1);
    }

    @Test
    public void testFindSql() {
        List<Object[]> list = customerDao.findSql();
        for (Object[] obj : list) {
            System.out.println(Arrays.toString(obj));
        }
    }

    /**
     * sql的模糊查询
     */
    @Test
    public void testFindByLike() {
        List<Object[]> list = customerDao.findByLike("孙%");
        for (Object[] obj : list) {
            System.out.println(Arrays.toString(obj));
        }
    }

    /**
     * jpql的模糊查询
     */
    @Test
    public void testFindByJpqlLike() {
        List<Customer> list = customerDao.findByJpqlLike("孙%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 方法名约定
     * 不用写jpql语句
     */
    @Test
    public void testFinByName() {
        Customer customer = customerDao.findByCustName("孙悟空");
        System.out.println(customer);
    }

    @Test
    public void testFindByNameLike() {
        List<Customer> list = customerDao.findByCustNameLike("孙%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    public void testFindByCustNameLikeAndCustIndustry(){
        List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("孙%","天庭");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}
