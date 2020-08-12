package cn.qianfg.dao;

import cn.qianfg.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book,Integer>, JpaSpecificationExecutor<Book> {

    @Query(value = "select * from jpa_book order by convert(author using gbk) limit 1,100",nativeQuery = true)
    List<Book> findAllOrderByAuthor();

    @Query("select b from Book b order by function('convert',b.author)")
    List<Book> findAllOrderByAuthor2(String author);
}
