package com.example.messageboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.example.messageboard.dao.ContentDao;
import com.example.messageboard.entity.Content;


@Service
public class ContentServiceImpl implements ContentService {
	
private final ContentDao dao;
	
	public ContentServiceImpl(ContentDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Content> findAll() {
		return dao.findAll();
	}
	
	@Override
	public void insert(Content content) {
		dao.insertContent(content);
	}
	
	@Override
	public Optional<Content> getContent(int id) {
		return dao.findById(id);

	}
	
	@Override
	public void update(Content content) {
		dao.update(content);
	}
	
	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

}
