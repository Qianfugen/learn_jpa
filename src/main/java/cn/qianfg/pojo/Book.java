package cn.qianfg.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "jpa_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

}
