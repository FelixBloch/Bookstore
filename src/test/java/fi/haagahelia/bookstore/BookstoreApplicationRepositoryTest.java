package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreApplicationRepositoryTest {
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void findByCategoryShouldReturnTitle() {
		List<Category> categories = crepository.findByName("Fiction");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getBooks().get(0).getTitle()).isEqualTo("A Farewell to Arms");
	}
	
	@Test
	public void findByUsernameShouldReturnRole() {
		User users = urepository.findByUsername("admin");
		assertThat(users.getRole()).isEqualTo("ADMIN");
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("The Test Book", "Felix Bloch", 2019, "1234567-89", 19.99, crepository.findByName("Science").get(0));
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Cooking");
		crepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test
	public void createNewUser() {
		User user = new User("Test", "Test", "USER");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		List<Book> books = brepository.findByTitle("A Farewell to Arms");
		assertThat(books).hasSize(1);
		brepository.deleteById(books.get(0).getId());
		List<Book> deletedBooks = brepository.findByTitle("A Farewell to Arms");
		assertThat(deletedBooks).hasSize(0);
	}
	
	@Test
	public void deleteCategory() {
		List<Category> categories = crepository.findByName("Science");
		assertThat(categories).hasSize(1);
		crepository.deleteById(categories.get(0).getCategoryid());
		List<Category> deletedCategory = crepository.findByName("Science");
		assertThat(deletedCategory).hasSize(0);
	}
	
	@Test
	public void deleteUser() {
		User user = urepository.findByUsername("user");
		assertThat(user).isNotNull();
		urepository.deleteById(user.getId());
		User deletedUser = urepository.findByUsername("user");
		assertThat(deletedUser).isNull();
	}

}
