package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {

	private long id;
	private String bookname;

	@Id
	@Column(name="id")
	public long getId() {
		return id;
	}

	@Column(name="id")
	public void setId(long id) {
		this.id = id;
	}

	@Column(name="bookname")
	public String getBookname() {
		return bookname;
	}

	@Column(name="bookname")
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
}
