package cn.qianfg.test_jpa;

import cn.qianfg.dao.CustomerDao;
import cn.qianfg.pojo.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Optional;

@SpringBootTest
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据条件，查询单个对象
     */
    @Test
    public void testSpec(){

        /**
         * 自定义查询条件
         *  1.实现Specification接口（提供泛型，查询的对象类型）
         *  2.实现toPredicate 方法（构造查询条件）
         *  3.需要借助方法参数中的两个参数
         *      root:获取需要查询的对象属性
         *      criteriaBuilder：构造查询条件，内部封装了跟多的查询条件（模糊匹配，精准匹配）
         *
         *  案例：根据客户名查询
         *      查询条件
         *          1.查询方式
         *              cb对象
         *          2.比较的属性名称
         *              root对象
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //1.获取比较的对象
                Path<Object> custName = root.get("custName");
                //2.构造查询条件
                Predicate predicate = cb.equal(custName, "孙悟空");
                return predicate;
            }
        };

        Optional<Customer> list = customerDao.findOne(spec);
        System.out.println(list.orElse(null));

    }

    /**
     *  多条件查询：
     *      案例：根据客户名和客户所属行业查询
     *          1.构造客户名的精准查询
     *          2.构造所属行业的精准查询
     *          3.将以上两个联合
     *
     */
    @Test
    public void testSpec1(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");

                Predicate p1 = cb.equal(custName, "孙悟空");
                Predicate p2 = cb.equal(custIndustry, "天庭");

                //将多个查询条件组合（and | or）
                Predicate predicate = cb.and(p1, p2);
//                Predicate predicate = cb.or(p1, p2);

                return predicate;
            }
        };
        Optional<Customer> list = customerDao.findOne(spec);
        System.out.println(list.orElse(null));
    }

    @Test
    public void testSpec3(){

    }


}
