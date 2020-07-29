package com.team.booksmanagament.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.team.booksmanagament.datafetcher.AllBooksDataFetcher;
import com.team.booksmanagament.datafetcher.BookDataFetcher;
import com.team.booksmanagament.datafetcher.CreateBookDataFetcher;
import com.team.booksmanagament.dto.Book;
import com.team.booksmanagament.repository.BookRepository;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Value("classpath:bookSchema.graphqls")
	public Resource resource;

	private GraphQL graphQL;

	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;
	@Autowired
	private BookDataFetcher bookDataFetcher;

	@Autowired
	public BookRepository bookRepository;

	@PostConstruct
	private void loadSchema() throws IOException {
		//loadDataIntoHSQL();
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring runtimeWiring = buildRuntimeWiring();
		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}

	private void loadDataIntoHSQL() {
		Stream.of(
				new Book("1001", "The C Programming Language", "1978",
						new String[] { "Brian W. Kernighan (Contributor)", "Dennis M. Ritchie" }, "100"),
				new Book("1002", "Your Guide To Scrivener", " April 21st 2013",
						new String[] { "Nicole Dionisio (Goodreads Author)" }, "200"),
				new Book("1003", "Beyond the Inbox: The Power User Guide to Gmail", "November 19th 2012",
						new String[] { "Shay Shaked", "Justin Pot", "Angela Randall (Goodreads Author)" }, "300"),
				new Book("1004", "Scratch 2.0 Programming", "February 5th 2015",
						new String[] { "Denis Golikov (Goodreads Author)" }, "400"),
				new Book("1005", "Pro Git", "2014", new String[] { "Scott Chacon" }, "500")).forEach(book -> {
					bookRepository.save(book);
				});
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
				.dataFetcher("allBooks", allBooksDataFetcher)
				.dataFetcher("getBook", bookDataFetcher))
				.build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
