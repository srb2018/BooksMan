package com.team.booksmanagament.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.team.booksmanagament.dto.Book;
import com.team.booksmanagament.repository.BookRepository;

public class CreateBookDataFetcher implements GraphQLMutationResolver  {

	@Autowired
    BookRepository bookRepository;

	public Book createBook(String title, String[] authors, String publishedDate, String price) {
		Book book =  new Book();
		book.setTitle(title);
		book.setPublishedDate(publishedDate);
		book.setAuthors(authors);
		book.setPrice(price);
		return bookRepository.save(book);
		
	}

}
