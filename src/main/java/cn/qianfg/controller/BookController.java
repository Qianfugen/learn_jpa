package cn.qianfg.controller;

import cn.qianfg.dto.BaseDataResult;
import cn.qianfg.pojo.Book;
import cn.qianfg.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
@Api(tags = "书籍层", value = "书籍层")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "查找所有书籍", notes = "查找所有书籍")
    @GetMapping("/findAll")
    public BaseDataResult<List<Book>> findAll() {
        BaseDataResult<List<Book>> rlt = new BaseDataResult<>();
        rlt.setData(bookService.findAll());
        return rlt;
    }

    @ApiOperation(value = "添加书籍", notes = "添加书籍")
    @PostMapping("/addBook")
    public BaseDataResult<Book> addBook(Book book) {
        BaseDataResult<Book> rlt = new BaseDataResult<>();
        rlt.setData(bookService.addBook(book));
        return rlt;
    }

}
