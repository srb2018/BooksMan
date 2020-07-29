package com.team.booksmanagament.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team.booksmanagament.dto.Book;
import com.team.booksmanagament.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {

	@Autowired
    BookRepository bookRepository;
	
	@Override
	public List<Book> get(DataFetchingEnvironment environment) throws Exception {
		return bookRepository.findAll();
	}

}
