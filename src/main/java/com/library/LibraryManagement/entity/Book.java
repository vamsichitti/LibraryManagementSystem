package com.library.LibraryManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Getter
public class Book {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
        @Column
		@NotBlank(message = "title cannot be null")
	    private String title;
        @Column
		@NotBlank(message = "authour cannot be null")
	    private String author;
        @Column
		@NotBlank(message = "Isbn cannot be null")
	    private String isbn;
        @Column
		@NotBlank(message = "genre cannot be null")
	    private String genre;

		private BookStatus isIssued;
		
        
        
}
