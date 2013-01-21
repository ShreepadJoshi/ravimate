package com.writer.fileWriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class FileWriter<User> implements ItemWriter<User> {

	@Override
	public void write(List<? extends User> users) throws Exception {
		System.out.println("In write " + users);
	}

}
