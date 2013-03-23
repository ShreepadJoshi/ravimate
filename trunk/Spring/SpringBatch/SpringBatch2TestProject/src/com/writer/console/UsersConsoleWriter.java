package com.writer.console;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.bean.Users;

public class UsersConsoleWriter implements ItemWriter<Users> {
	@Override
	public void write(List<? extends Users> users)
			throws Exception {
		System.out.println("In Console writer " + users);
	}

}
