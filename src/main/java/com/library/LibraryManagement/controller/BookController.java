package com.library.LibraryManagement.controller;

import java.util.Collections;
import java.util.List;

import com.library.LibraryManagement.exceptions.ResourceNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.LibraryManagement.entity.Book;
import com.library.LibraryManagement.exceptions.ResourceAlreadyExistsException;
import com.library.LibraryManagement.service.BookService;

@RestController
@RequestMapping(value="book")
public class BookController {

	@Autowired
	public BookService bookService;
	
	@PostMapping(value="/createbook")
	public ResponseEntity<Book> registerNewBook(@RequestBody Book book) throws ResourceAlreadyExistsException, BadRequestException {
		Book createdBook = bookService.createBook(book);
		return new ResponseEntity<>(createdBook,HttpStatus.CREATED);
	}
	
	@GetMapping(value="/getallbooks")
	public ResponseEntity<List<Book>> listOfAllBooks() throws ResourceNotFoundException {
		List<Book> booksList = bookService.getAllBooks();
		return new ResponseEntity<>(booksList,HttpStatus.OK);
	}
	
	@GetMapping(value="/getBookByID/{id}")
	public ResponseEntity<Book> bookById(@PathVariable("id") int id) throws ResourceNotFoundException{
		Book newBook = bookService.getBookById(id);
		return new ResponseEntity<Book>(newBook,HttpStatus.OK);
	}

	@DeleteMapping("/deleteBook/{isbn}")
	public ResponseEntity<?> deleteBookById(@PathVariable String isbn) throws ResourceNotFoundException {
		bookService.deleteBookById(isbn);
		return new ResponseEntity<>("Book is successfully deleted",HttpStatus.OK);
	}

	@PutMapping("/updateBook/isbn/{isbn}")
	public ResponseEntity<Book> updateBookByIsbn(@PathVariable String isbn,@RequestBody Book updatedBook ) throws BadRequestException,ResourceNotFoundException {
		if(updatedBook.getIsbn().equals(isbn)){
			Book book = bookService.updateBook(updatedBook);
			return new ResponseEntity<Book>(book,HttpStatus.ACCEPTED);
		}
		else {
			throw new BadRequestException("ISBN cannot be changed during update");
		}
	}

	@GetMapping("/search")
	public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
												  @RequestParam(required = false) String author,
												  @RequestParam(required = false) String isbn,
												  @RequestParam(required = false) String genre) {
		List<Book> searchResults;
		if (title != null) {
			searchResults = bookService.searchBooksByTitle(title);
		}
		else if (author != null) {
			searchResults = bookService.searchBooksByAuthor(author);
		}
//		else if (isbn != null) {
//			searchResults = bookService.searchBooksByIsbn(isbn);
//		}
		else if (genre != null) {
			searchResults = bookService.searchBooksByGenre(genre);
		}
		else {
			// Handle empty search case (optional)
			searchResults = Collections.emptyList();
		}
		return ResponseEntity.ok(searchResults);
	}


}
