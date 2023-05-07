package com.example.messageboard.service;

import java.util.List;
import java.util.Optional;

import com.example.messageboard.entity.Content;


public interface ContentService {
	
	List<Content> findAll();

	Optional<Content> getContent(int id);

	void insert(Content content);

	void update(Content content);

	void deleteById(int id);

//	List<Content> findByType(int typeId);

}
