package com.library.LibraryManagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.library.LibraryManagement.entity.*;
import com.library.LibraryManagement.exceptions.ResourceNotFoundException;
import com.library.LibraryManagement.repository.LoanRepository;
import com.library.LibraryManagement.repository.UserRepository;
import com.library.LibraryManagement.validator.CustomValidator;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.LibraryManagement.exceptions.ResourceAlreadyExistsException;
import com.library.LibraryManagement.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
    @Autowired
	private BookRepository bookRepo;
	@Autowired
	private LoanRepository loanRepo;
	@Autowired
	private UserRepository userRepo;

    
	@Override
	public Book createBook(Book book) throws ResourceAlreadyExistsException, BadRequestException {
		if(!CustomValidator.isbnIsValid(book.getIsbn())){
			throw new BadRequestException("ISBN value has to be Integers and length 8 to 10");
		}

		if(isBookExists(book) && CustomValidator.isbnIsValid(book.getIsbn())) {
			throw new ResourceAlreadyExistsException("Book with name "+book.getTitle()+" already exists");
		}
		return bookRepo.save(book);
	}

	@Override
	public Book updateBook(Book book) throws ResourceNotFoundException {
		Optional<Book> bookToBeUpdated = bookRepo.findByIsbn(book.getIsbn());
		if(bookToBeUpdated.isPresent()){
			 bookToBeUpdated.get().setTitle(book.getTitle());
			bookToBeUpdated.get().setAuthor(book.getAuthor());
			bookToBeUpdated.get().setGenre(book.getGenre());
			return bookRepo.save(bookToBeUpdated.get());
		}
		else{
			throw new ResourceNotFoundException("book with isbn "+book.getIsbn()+" not present");
		}
	}

	@Override
	public List<Book> getAllBooks() throws ResourceNotFoundException {
		List<Book> booksList = bookRepo.findAll();
		if(booksList.isEmpty()) {
			throw new ResourceNotFoundException("No Books are there");
		}
		return booksList;
	}

	@Override
	public Book getBookById(long id) throws ResourceNotFoundException {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isPresent()) {
			return book.get();
		}
		throw new ResourceNotFoundException("Book with ID "+id+" is not present");
	}

	@Override
	public void deleteBookById(String isbn) throws ResourceNotFoundException {
		Optional<Book> book = bookRepo.findByIsbn(isbn);
		if(book.isPresent()){
			Long id = book.get().getId();
			bookRepo.deleteById(id);
		}
		else {
			throw new ResourceNotFoundException("Book with ISBN: " + isbn + " not found");
		}
	}

	@Override
	public List<Book> searchBooksByTitle(String title) {
		return bookRepo.findByTitleContainingIgnoreCase(title);
	}

	public Boolean isBookExists(Book book){
    	String isbn = book.getIsbn();
    	Optional<Book> createdBook = bookRepo.findByIsbn(isbn);
    	if(createdBook.isPresent()) {
    		return true;
    	}
    	return false;
    }

	@Override
	public List<Book> searchBooksByAuthor(String author) {
		return bookRepo.findByAuthorContainingIgnoreCase(author);
	}
	@Override
	public List<Book> searchBooksByGenre(String genre) {
		return bookRepo.findByGenreContainingIgnoreCase(genre);
	}
    @Override
	public Loan issueBook(String isbn, long userId){
		Book book = bookRepo.findByIsbn(isbn).get();
		User user = userRepo.findById(userId).get();
		book.setIsIssued(BookStatus.ISSUED);
		Loan loan = new Loan();
		loan.setBook(book);
		loan.setUser(user);
		loan.setLoanDate(LocalDate.now());
		loan.setLoanStatus(LoanStatus.OPEN);
		return loanRepo.save(loan);
	}


}
