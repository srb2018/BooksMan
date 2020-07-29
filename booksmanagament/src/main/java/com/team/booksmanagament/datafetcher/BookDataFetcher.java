package com.team.booksmanagament.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team.booksmanagament.dto.Book;
import com.team.booksmanagament.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

	@Autowired
    BookRepository bookRepository;

	@Override
	public Book get(DataFetchingEnvironment environment) throws Exception {
		String idn = environment.getArgument("id");
		return bookRepository.findById(idn).get();
	}

}
