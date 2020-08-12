package cn.qianfg.test_jpa;

import cn.qianfg.dao.BookDao;
import cn.qianfg.pojo.Book;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SpecTest2 {

    @Autowired
    private BookDao bookDao;

    @Test
    public void testFindAll(){
        Specification<Book> spec = new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p2 = cb.le(root.get("id"), 100);
                query.where(p2);

                query.orderBy(cb.asc(cb.function("convert",String.class,root.get("author"))));

                return query.getRestriction();
            }
        };

        List<Book> all = bookDao.findAll(spec);
        all.forEach(e-> System.out.println(e));
    }

    @Test
    public void testFindAllOrderByAuthor(){
        List<Book> all = bookDao.findAllOrderByAuthor();
        all.forEach(e-> System.out.println(e));
    }

    @Test
    public void testFindAllOrderByAuthor2(){
        List<Book> all = bookDao.findAllOrderByAuthor2("author");
        all.forEach(e-> System.out.println(e));
    }
}
