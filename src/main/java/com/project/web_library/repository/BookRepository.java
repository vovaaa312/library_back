package com.project.web_library.repository;

import com.project.web_library.model.data.book.Book;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Optional<List<Book>>findAllByCreator(String id);

    @Query("{ 'authors' : { $in : [ ?0 ] } }")
    Optional<List<Book>> findByAuthorId(String authorId);

    @Query("{ 'authors' : { $all : ?0 } }")
    Optional<List<Book>> findByAuthors(List<String> authorIds);


}
