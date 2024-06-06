package com.library.LibraryManagement.validator;

public class CustomValidator {

    public static boolean isbnIsValid(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            return false; // Allow empty ISBNs (optional)
        }
        // Check length (8 to 10 digits) and only digits using regular expression
        return isbn.matches("^[0-9]{8,10}$");
    }
}
