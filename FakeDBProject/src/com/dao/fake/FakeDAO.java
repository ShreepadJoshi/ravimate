package com.dao.fake;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.dao.Idao;
import com.pojo.Book;

public class FakeDAO implements Idao {

	@Override
	public List<Book> getBooks() {
		return FakeDB.getObject().getBooksTable();
	}

	@Override
	public void addBook(Book book) {
		FakeDB.getObject().getBooksTable().add(book);
	}

	public static void UnLoadDB() {
		try {
			FakeDB fakeDB = FakeDB.getObject();
			System.out.println("fakeDB: " + fakeDB);
			FileOutputStream fos = new FileOutputStream("serial");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(fakeDB);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			System.out.println("Exception during serialization: " + e);
			System.exit(0);
		}

	}

	public static void loadDB() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("serial");
			ObjectInputStream ois = new ObjectInputStream(fis);
			FakeDB db = (FakeDB)ois.readObject();
			System.out.println(db.getBooksTable());
			
			FakeDB.getObject().setBooksTable(db.getBooksTable());			
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

	}

}
