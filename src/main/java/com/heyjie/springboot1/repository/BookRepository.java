package com.heyjie.springboot1.repository;

import com.heyjie.springboot1.entity.Book;
import com.heyjie.springboot1.entity.BookAuthorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("select b.author.name as authorName,b.id as id,b.name as name from t_books b")
    List<BookAuthorInfo> search2();
}
