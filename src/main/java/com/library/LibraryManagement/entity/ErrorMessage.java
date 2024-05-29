package com.library.LibraryManagement.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ErrorMessage {

	 private int statusCode;
	  private Date timestamp;
	  private String message;
	  private String description;
}
