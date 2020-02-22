package com.geekbrains.Readerbook.repositories;

import com.geekbrains.Readerbook.entities.Author;
import com.geekbrains.Readerbook.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorAdminRepository extends CrudRepository<Author, Long> {

}