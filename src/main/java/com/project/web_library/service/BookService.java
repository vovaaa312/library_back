package com.project.web_library.service;

import com.project.web_library.model.data.book.Book;
import com.project.web_library.model.exception.UserNotFoundException;
import com.project.web_library.repository.AuthUserRepository;
import com.project.web_library.repository.BookRepository;
import com.project.web_library.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthUserRepository userRepository;
    private final GenreRepository genreRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    public Book findBookById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new NullPointerException("Book not found."));
    }
    public List<Book> findAllByCreator(String id) {
        return bookRepository.findAllByCreator(id).orElse(Collections.emptyList());
    }
    public List<Book> findByAuthorId(String id) {
        return bookRepository.findByAuthorId(id).orElse(Collections.emptyList());
    }
    public List<Book> findByAuthors(List<String> authors) {
        checkIfAuthorsExists(authors);
        return bookRepository.findByAuthors(authors).orElse(Collections.emptyList());
    }
    public Book saveBook(Book book) {
        validateBook(book);
        return bookRepository.save(book);
    }
    public Book updateBook(String id, Book book) {
        findBookById(id);

        validateBook(book);

        Book updated = Book.builder()
                .id(id)
                .name(book.getName())
                .authors(book.getAuthors())
                .year(book.getYear())
                .rating(book.getRating())
                .genres(book.getGenres())
                .description(book.getDescription())
                .creator(book.getCreator())
                .build();

        return bookRepository.save(updated);
    }

    public Book deleteBook(String id) {
        Book deleted = findBookById(id);
        bookRepository.delete(deleted);

        return deleted;
    }

    private void validateBook(Book book){
        checkIfCreatorExists(book.getCreator());
        checkIfAuthorsExists(book.getAuthors());
        checkIfGenresExists(book.getGenres());
    }

    private void checkIfAuthorsExists(List<String> authors){
        authors.stream()
                .filter(authorId -> userRepository.findAuthUserById(authorId).isEmpty())
                .findFirst()
                .ifPresent(authorId -> {
                    throw new UserNotFoundException("User with id " + authorId + " not found");
                });

    }
    private void checkIfGenresExists(List<String> genres){
        genres.stream()
                .filter(genreId -> genreRepository.findGenreById(genreId).isEmpty())
                .findFirst()
                .ifPresent(genreId -> {
                    throw new UserNotFoundException("Genre with id " + genreId + " not found");
                });

    }
    private void checkIfCreatorExists(String userId){

        userRepository.findAuthUserById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
    }

}
