package com.project.web_library.controller;

import com.project.web_library.model.data.book.Book;
import com.project.web_library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BookController {
    private final BookService bookService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/findAllByCreator/{id}")
    public ResponseEntity<?> findAllByCreator(@PathVariable String id) {
        return ResponseEntity.ok(bookService.findAllByCreator(id));
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @GetMapping("/findByAuthorId/{id}")
    public ResponseEntity<?> findByAuthorId(@PathVariable String id) {
        return ResponseEntity.ok(bookService.findByAuthorId(id));
    }

    @GetMapping("findByAuthors")
    public ResponseEntity<?> findByAuthors(@RequestBody List<String> authors) {
        return ResponseEntity.ok(bookService.findByAuthors(authors));
    }



    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody Book book
    ) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
}
