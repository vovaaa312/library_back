package com.project.web_library;

import com.project.web_library.repository.BookRepository;
import com.project.web_library.service.AuthUserService;
import com.project.web_library.service.BookService;
import com.project.web_library.service.FavouriteBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class WebLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebLibraryApplication.class, args);
	}



//	@Bean
//	public CommandLineRunner commandLineRunner(
//			FavouriteBooksService booksService
//	) {
//		return args -> {
//			System.out.println(booksService.findByBookIdAndUserId("666edf0feea0b55aea61d786","666f2eb0c3e03a3b5222badb"));
//		};
//	}
}
