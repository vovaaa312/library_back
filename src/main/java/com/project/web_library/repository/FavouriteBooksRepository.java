package com.project.web_library.repository;

import com.project.web_library.model.data.book.Book;
import com.project.web_library.model.data.book.FavouriteBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Optional;

@Repository
public interface FavouriteBooksRepository extends MongoRepository<FavouriteBook, String> {
    Optional<FavouriteBook> findFavouriteBookById(String id);

    Optional<FavouriteBook> findFavouriteBookByBookAndUser(String book, String user);

    Optional<List<FavouriteBook>> findFavouriteBooksByUser(String userId);
}
