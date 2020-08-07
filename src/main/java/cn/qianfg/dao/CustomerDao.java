package cn.qianfg.dao;

import cn.qianfg.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 1. sql查询
 * 2. jpql查询
 * 3. 方法名约定
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    /**
     * 案例：根据客户名称查询客户
     * 使用jpql查询
     * jpql:    from Customer where custName = ?1
     * 配置jpql语句，使用@Query注解
     */
    @Query("from Customer where custName = ?1")
    public Customer findJpql(String custName);

    /**
     * 案例：根据客户名称和客户id查询
     * 指定占位符参数的位置：
     * ?index 来指定
     * jpal:    from Customer where custName=?1 and custId=?2
     */
    @Query("from Customer where custName=?1 and custId=?2")
    public Customer findCustomerNameAndId(String name, Integer id);

    /**
     * 案例：根据id更新客户的名词
     * sql:     update cst_customer set cust_name = ? where cust_id = ?
     * jpql:    update Customer set custName=?1 where custId=?2
     *
     * @Query: 代表的是查询
     * *声明此方法用来查询
     * @Modifying： *当前执行的是一个更新操作
     */
    @Query("update Customer set custName=?1 where custId=?2")
    @Modifying
    public void updateCustomer(String name, Integer id);

    /**
     * 使用sql的形式查询
     * 查询所有客户
     * sql: select * from cst_customer
     * Query: 配置sql语句
     *      value: sql语句
     *      nativeQuery: 查询方式
     *          true:   sql查询
     *          false:  jpql查询
     *
     */
    @Query(value = "select * from cst_customer", nativeQuery = true)
    public List<Object[]> findSql();

    /**
     * sql模糊查询
     */
    @Query(value = "select * from cst_customer where cust_name like ?1", nativeQuery = true)
    public List<Object[]> findByLike(String name);

    /**
     * jpql的模糊查询
     * @param name
     * @return
     */
    @Query(value = "from Customer where custName like ?1")
    public List<Customer> findByJpqlLike(String name);

    /**
     * 方法名的约定：
     *      findBy: 查询
     *          对象中的属性名称（首字母大写）：查询条件
     *          CustName
     *          *默认情况：使用 = 进行匹配
     *
     *      findByCustName  --  根据客户名称查询
     * 在springdatajpa 运行阶段
     *      会根据方法名称进行解析 findBy from xxx(实体类) where xxx(属性) =
     *
     *      1.findBy + 属性名称 精准匹配
     *      2.findBy + 属性名称 +“查询方式(Like | isnull)”  模糊匹配
     *          findByCustNameLike
     *      3.多条件查询
     *      findBy + 属性名称 +“查询方式"+"多条件的链接符（and | or）"+属性名+"查询方式"
     *
     *
     * 不用写jpql语句
     */

    /**
     * 1.findBy + 属性名称 精准匹配
     */
    public Customer findByCustName(String name);

    /**
     * 2.findBy + 属性名称 +“查询方式(Like | isnull)”  模糊匹配
     */
    public List<Customer> findByCustNameLike(String name);

    /**
     * 3.多条件查询
     *  findBy + 属性名称 +“查询方式"+"多条件的链接符（and | or）"+属性名+"查询方式"
     */
    public List<Customer> findByCustNameLikeAndCustIndustry(String custName,String cusIndustry);

}
