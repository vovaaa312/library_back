package com.project.web_library.service;

import com.project.web_library.model.data.book.FavouriteBook;
import com.project.web_library.repository.AuthUserRepository;
import com.project.web_library.repository.BookRepository;
import com.project.web_library.repository.FavouriteBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FavouriteBooksService {
    private final FavouriteBooksRepository favouriteBooksRepository;
    private final BookRepository bookRepository;
    private final AuthUserRepository userRepository;

    public List<FavouriteBook> findAll() {
        return favouriteBooksRepository.findAll();
    }

    public FavouriteBook findById(String id) {
        return favouriteBooksRepository.findFavouriteBookById(id).orElse(null);
    }

    public List<FavouriteBook> findByUserId(String userId) {
        return favouriteBooksRepository.findFavouriteBooksByUser(userId).orElse(null);
    }

    public FavouriteBook findByBookIdAndUserId(String bookId, String userId) {
        return favouriteBooksRepository.findFavouriteBookByBookAndUser(bookId, userId).orElse(null);
    }

    public FavouriteBook addFavourite(FavouriteBook favouriteBook) {
        if(findByBookIdAndUserId(favouriteBook.getBook(), favouriteBook.getUser()) !=null) return null;
        return favouriteBooksRepository.save(favouriteBook);
    }

    public FavouriteBook deleteFavourite(String id) {
        FavouriteBook deleted = findById(id);
        favouriteBooksRepository.deleteById(id);
        return deleted;
    }


}
