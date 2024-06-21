package com.project.web_library.controller;

import com.project.web_library.model.data.book.FavouriteBook;
import com.project.web_library.service.FavouriteBooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favourites")
@RequiredArgsConstructor
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class FavouriteBooksController {
    private final FavouriteBooksService favouritesService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(favouritesService.findAll());
    }

    @GetMapping("/findAll/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id){
        return ResponseEntity.ok(favouritesService.findByUserId(id));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        return ResponseEntity.ok(favouritesService.findById(id));
    }
    @GetMapping("/findByBookId/{bookId}/andUserId/{userId}")
    public ResponseEntity<?> findByBookIdAndUserId(@PathVariable String bookId,@PathVariable String userId){
        return ResponseEntity.ok(favouritesService.findByBookIdAndUserId(bookId,userId));
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody FavouriteBook book){
        return ResponseEntity.ok(favouritesService.addFavourite(book));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return ResponseEntity.ok(favouritesService.deleteFavourite(id));
    }


}
