package cn.qianfg.service;

import cn.qianfg.pojo.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book addBook(Book book);

}
