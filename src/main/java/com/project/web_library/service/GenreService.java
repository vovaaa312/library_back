package com.project.web_library.service;

import com.project.web_library.model.data.book.Genre;
import com.project.web_library.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }
    public Genre findById(String id){
        return genreRepository.findGenreById(id).orElse(null);
    }
}
