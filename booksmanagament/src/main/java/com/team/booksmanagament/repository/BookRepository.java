package com.team.booksmanagament.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.booksmanagament.dto.Book;

public interface BookRepository extends JpaRepository<Book, String>{

}
