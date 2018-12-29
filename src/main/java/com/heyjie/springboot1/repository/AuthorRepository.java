package com.heyjie.springboot1.repository;

import com.heyjie.springboot1.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
