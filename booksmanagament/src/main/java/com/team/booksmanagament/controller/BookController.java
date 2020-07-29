package com.team.booksmanagament.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.booksmanagament.service.GraphQLService;

import graphql.ExecutionResult;

@RequestMapping("/api/getBooks")
@RestController
public class BookController {
	
	@Autowired
	public GraphQLService graphQLService;

	@PostMapping
	public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
		
		ExecutionResult execute = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(execute, HttpStatus.OK);
	}
}
