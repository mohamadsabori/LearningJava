package com.nl.practices.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Library {
    Map<String, Book> booksMap = new HashMap<>();

    void addNewBook(Book newBook) {
        this.booksMap.put(newBook.isbn(), newBook);
    }

    Optional<Book> searchByIsbn(String isbn) {
        return Optional.ofNullable(this.booksMap.get(isbn));
    }

    void lendBook(String isbn) {
        Book book = this.booksMap.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book not found in library.");
        }
        if(!book.isAvailable()){
            throw new IllegalStateException("Book is already lent out.");
        }
        Book newBook = new Book(book.title(), book.author(), book.isbn(), false);
        this.booksMap.put(newBook.isbn(), newBook);
    }

    void returnBook(String isbn) {
        Book book = this.booksMap.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException();
        }
        Book newBook = new Book(book.title(), book.author(), book.isbn(), true);
        this.booksMap.put(newBook.isbn(), newBook);
    }

    List<Book> listAvailableBooks() {
        return this.booksMap.values().stream()
                .filter(Book::isAvailable)
                .toList();
    }
}
