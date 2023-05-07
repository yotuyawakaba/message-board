package com.example.messageboard.dao;

import java.util.List;
import java.util.Optional;

import com.example.messageboard.entity.Content;

public interface ContentDao {
	
	List<Content> findAll();

	Optional<Content> findById(int id);

	void insertContent(Content content);

	void update(Content content);

	int deleteById(int id);

//	List<Content> findByType(int typeId);
	

}
