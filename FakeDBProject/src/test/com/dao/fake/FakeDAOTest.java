package test.com.dao.fake;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.dao.fake.FakeDAO;
import com.dao.fake.FakeDB;
import com.pojo.Book;

public class FakeDAOTest {

	

	@Test
	public void testLoadDB() {
		FakeDAO.deserializeDB();
		List<Book> books = FakeDB.getInstance().getBooksTable();
		assertNull(books);
	}
	
	@Test
	public void testUnLoadDB() {
		FakeDAO.serializeDB();
	}

	@Test
	public void testUnLoadAndLoad() {
		Book javaBook = new Book();
		javaBook.setId(1);
		javaBook.setBookName("Any Java Book");

		Book vbBook = new Book();
		vbBook.setId(2);
		vbBook.setBookName("Any VB Book");

		FakeDAO fakeDAO = new FakeDAO();
		fakeDAO.addBook(javaBook);
		fakeDAO.addBook(vbBook);

		List<Book> booksBeforesave = fakeDAO.getBooks();
		System.out.println(booksBeforesave);
		FakeDAO.serializeDB();

	}
	
	@Test
	public void testAddBookToDB() {
		FakeDAO.deserializeDB();
		
		Book javaBook = new Book();
		javaBook.setId(3);
		javaBook.setBookName("Any Java Book part 2");
		
		FakeDAO fakeDAO = new FakeDAO();
		fakeDAO.addBook(javaBook);
		
		FakeDAO.serializeDB();
	}

}
