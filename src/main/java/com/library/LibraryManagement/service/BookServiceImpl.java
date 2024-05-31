package com.library.LibraryManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.LibraryManagement.entity.Book;
import com.library.LibraryManagement.exceptions.NoResourceFoundException;
import com.library.LibraryManagement.exceptions.ResourceAlreadyExistsException;
import com.library.LibraryManagement.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
    @Autowired
	public BookRepository bookRepo;
    
	@Override
	public Book createBook(Book book) throws ResourceAlreadyExistsException {
		if(isBookExists(book)) {
			throw new ResourceAlreadyExistsException("Book with name "+book.getTitle()+" already exists");
		}
		return bookRepo.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllBooks() throws NoResourceFoundException {
		List<Book> booksList = bookRepo.findAll();
		if(booksList.isEmpty()) {
			throw new NoResourceFoundException("No Books are there");
		}
		return booksList;
	}

	@Override
	public Book getBookById(long id) throws NoResourceFoundException {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isPresent()) {
			return (Book)book.get();
		}
		throw new NoResourceFoundException("Book with ID "+id+" is not present");
	}

	@Override
	public void deleteBookById(int id) {
		// TODO Auto-generated method stub
		
	}
    public Boolean isBookExists(Book book){
    	String author = book.getAuthor();
    	String title = book.getTitle();
    	String isbn = book.getIsbn();
    	Optional<Book> createdBook = bookRepo.findByIsbn(isbn);
    	if(createdBook.isPresent()) {
    		return true;
    	}
    	return false;
    }
}
