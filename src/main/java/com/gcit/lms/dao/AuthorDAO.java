package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.gcit.lms.entity.Author;

@Component
public class AuthorDAO {
	Logger logger = LoggerFactory.getLogger(AuthorDAO.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Author getAuthorById(int authorId) {
		Author author = jdbcTemplate.queryForObject("SELECT * FROM newlibrary.tbl_author where authorId=?",
				new Object[] { authorId }, new AuthorRowMapper());
		logger.debug("Logging a message from Author DAO here is the author object {}", author);
		return author;
	}
	public void insertAuthor(Author author) {
		String query = "INSERT INTO `newlibrary`.`tbl_author` (`authorName`) VALUES (?)";
		int value = jdbcTemplate.update(query, new Object[] {author.getAuthorName() });
		logger.debug("returned value from backend is {}", value);
	}

//	public int insertAuthor(Author author) {
//		final String query = "INSERT INTO `newlibrary`.`tbl_author` (`authorName`) VALUES (?);";
//		KeyHolder holder = new GeneratedKeyHolder();
//		jdbcTemplate.update(new PreparedStatementCreator() {
//
//			@Override
//			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//				ps.setString(1, author.getAuthorName());
//				return ps;
//			}
//		}, holder);
//		logger.debug("returned value from backend is {}");
//		int authorId = holder.getKey().intValue();
//		return authorId;
//	}

	public List<Map<String, Object>> getAuthors() {
		List<Map<String, Object>> authors = jdbcTemplate.queryForList("SELECT * FROM newlibrary.tbl_author");
		return authors;
	}

	public List<Author> getAllAuthors() {
		List<Map<String, Object>> list1 = jdbcTemplate.queryForList("select * from tbl_author");
		List<Author> authorList = new ArrayList<Author>();
		for (Map<String, Object> li : list1) {
			Author author = new Author();
			author.setAuthorId((Integer) li.get("authorId"));
			author.setAuthorName((String) li.get("authorName"));
			authorList.add(author);
		}

		return authorList;
	}

	public void updateAuthor(Author author) {
		String query = "update tbl_author set authorName = ? where authorId = ?";
		int value = jdbcTemplate.update(query, new Object[] { author.getAuthorName(), author.getAuthorId() });
		logger.debug("returned value from backend is {}", value);
	}

	public void deleteAuthor(Author author) {
		String query = "delete from tbl_author where authorId = ?";
		int value = jdbcTemplate.update(query, new Object[] { author.getAuthorId() });
		logger.debug("returned value from backend is {}", value);
	}

}

class AuthorRowMapper implements RowMapper<Author> {
	@Override
	public Author mapRow(ResultSet rs, int arg1) throws SQLException {
		Author author = new Author();
		author.setAuthorId(rs.getInt("authorId"));
		author.setAuthorName(rs.getString("authorName"));
		return author;
	}

}
