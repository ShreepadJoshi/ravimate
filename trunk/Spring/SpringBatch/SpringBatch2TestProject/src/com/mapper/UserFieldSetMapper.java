package com.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.bean.User;

public class UserFieldSetMapper implements FieldSetMapper<User> {

	@Override
	public User mapFieldSet(FieldSet fieldSet) {
		User user = new User();
		user.setId(fieldSet.readString("USER_ID"));
		user.setName(fieldSet.readString("USER_NAME"));
		user.setLastName(fieldSet.readString("USER_LASTNAME"));
		
		return user;
	}

}
