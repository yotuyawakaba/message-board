package com.example.messageboard.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.messageboard.entity.Content;

@Repository
public class ContentDaoImpl implements ContentDao {
	
	private final JdbcTemplate jdbcTemplate;

	public ContentDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Content> findAll() {
		String sql = "SELECT id, content, created, updated FROM messages order by id desc";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		List<Content> list = new ArrayList<Content>();
		
		//第１引数にキー：カラム名。第２引数に値：テーブルの値
		for(Map<String, Object> result : resultList) {
			Content content = new Content();
			content.setId((int)result.get("id"));
			content.setContent((String)result.get("content"));
			content.setCreated(((Timestamp) result.get("created")).toLocalDateTime());
			content.setUpdated(((Timestamp) result.get("updated")).toLocalDateTime());
			list.add(content);
		}
		
		return list;
	}
	
	@Override
	public void insertContent(Content content) {
		//hands-on	
		jdbcTemplate.update("INSERT INTO messages(content, created, updated) VALUES(?, ?, ?)", 
				content.getContent(), content.getCreated(), content.getUpdated());

	}
	
	@Override
	public Optional<Content> findById(int id) {
		String sql = "SELECT id, content FROM messages  WHERE id = ?";

		//メッセージを一件取得
		//idは上の？に入る（プリペアードステートメント）
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
		System.out.println(result);

		Content content = new Content();
		content.setId((int)result.get("id"));
		content.setContent((String)result.get("content"));

		//ラップする
		Optional<Content> contentOpt = Optional.ofNullable(content);

		return contentOpt;
	}
	
	@Override
	public void update(Content content) {
		 jdbcTemplate.update("UPDATE messages SET content = ? WHERE id = ?",
				content.getContent(), content.getId() );
	}
	
	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM messages WHERE id = ?", id);
	}

}
