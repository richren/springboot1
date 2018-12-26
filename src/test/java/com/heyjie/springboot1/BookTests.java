package com.heyjie.springboot1;


import com.heyjie.springboot1.model.Author;
import com.heyjie.springboot1.model.Book;
import com.heyjie.springboot1.model.BookAuthorInfo;
import com.heyjie.springboot1.repository.AuthorRepository;
import com.heyjie.springboot1.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTests {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void Test1() {
        List<Book> books = bookRepository.findAll();
        for (Book b : books) {
            System.out.println(b.getName() + "," + b.getAuthor().getName());
        }
    }

    @Test
    public void Test1_1() {
        List<BookAuthorInfo> books = bookRepository.search2();
        for (BookAuthorInfo b : books) {
            System.out.println(b.getId()+","+b.getName() + "," + b.getAuthorName());
        }
    }

    @Test
    public void Test2() {
        List<Author> authors = authorRepository.findAll();
        for (Author a : authors) {
            System.out.println(a.getName());
            for(Book b:a.getBooks()){
                System.out.println("  "+b.getName());
            }
        }
    }
}
