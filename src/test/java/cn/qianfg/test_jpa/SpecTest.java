package cn.qianfg.test_jpa;

import cn.qianfg.dao.CustomerDao;
import cn.qianfg.pojo.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
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
                return predicate;
            }
        };
        Optional<Customer> list = customerDao.findOne(spec);
        System.out.println(list.orElse(null));
    }

    /**
     * 案例：根据客户名称模糊匹配，返回客户列表
     * equal: 直接得到 path 对象，然后进行比较
     * gt, lt, le, like: 得到path对象，根据path指定比较的参数模型，再进行比较
     */
    @Test
    public void testSpec3(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate p = cb.like(custName.as(String.class), "孙%");
                return p;
            }
        };
//        List<Customer> list = customerDao.findAll(spec);
//        list.forEach(e-> System.out.println(e));

        Sort sort = Sort.by(Sort.Direction.DESC,"custId");
        List<Customer> list = customerDao.findAll(spec, sort);
        list.forEach(e-> System.out.println(e));
    }

    /**
     * 分页查询
     *      Specification: 查询条件
     *      Pageable:   分页参数
     *          分页参数：查询的页码，每页查询的条数
     *
     */
    @Test
    public void testSpec4(){
        Specification<Customer> spec = null;
        Pageable pageable = PageRequest.of(0,2);
        Page<Customer> page = customerDao.findAll(spec, pageable);
        //总条数
        System.out.println(page.getTotalElements());
        //总页数
        System.out.println(page.getTotalPages());
        //数据
        System.out.println(page.getContent());
    }



}
