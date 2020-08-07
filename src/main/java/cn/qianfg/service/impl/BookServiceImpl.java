package cn.qianfg.service.impl;

import cn.qianfg.dao.BookDao;
import cn.qianfg.pojo.Book;
import cn.qianfg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return bookDao.save(book);
    }
}
