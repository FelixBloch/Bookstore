package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			User user1 = new User("user","$2a$06$qvCiDKZiA63g7hrfDvgdk.CWhW7g0OUqEcKvzZTKfYqBHqFLmBPgW", "USER");
			User user2 = new User("admin","$2a$10$btV5WBcok1SlkJxpGm82H.iT48n0JpWeiVNsBYQnVB/YBWYoxPNuq", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			crepository.save(new Category("Adventure"));
			crepository.save(new Category("Children"));
			crepository.save(new Category("Comic"));
			crepository.save(new Category("Crime"));
			crepository.save(new Category("Education"));
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Romance"));
			crepository.save(new Category("Science"));
			crepository.save(new Category("Sci-Fi"));
			
			repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 9.99, crepository.findByName("Fiction").get(0)));
			repository.save(new Book("Animal Farm", "George Orwell", 1945, "2212343-5", 7.99, crepository.findByName("Fiction").get(0)));
			
			
		};
	}

}