package com.gcit.lms.dao;
import java.util.List;
import java.util.Map;

import com.gcit.lms.entity.Author;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Branch;
/**
 * 
 * @author hvalipour
 *
 */
@Component
public class BookDAO{
	Logger logger = LoggerFactory.getLogger(BookDAO.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insertBook(Book book) {
		String query = "INSERT INTO `newlibrary`.`tbl_book` (`title`) VALUES (?);";
		int value = jdbcTemplate.update(query, new Object[] {book.getTitle() });
		logger.debug("returned value from backend is {}", value);
	}
	public void updateBook(Book book) {
		String query = "update tbl_book set title = ? where bookId = ?";
		int value = jdbcTemplate.update(query,new Object[] { book.getTitle(), book.getBookId() });
		logger.debug("returned value from backend is {}", value);
	}
	public void deleteBook(Book book) {
		String query = "delete from tbl_book where bookId = ?"; 
		int value = jdbcTemplate.update(query, new Object[] { book.getBookId() });
		logger.debug("returned value from backend is {}", value);
	}

	public Book getBookById(int bookId) {
		Book book = jdbcTemplate.queryForObject("SELECT * FROM newlibrary.tbl_book where bookId=?", new Object[] { bookId }, new BookRowMapper());
		logger.debug("Logging a message from Book DAO here is the author object {}",book);
		return book;
	}
	public Book getBookByIdAndBranchId(Integer bookId, Integer branchId) {
		Book book = new Book();

	List<Map<String, Object>> bookList = jdbcTemplate.queryForList("select * from tbl_book left join tbl_book_copies on tbl_book.bookId = tbl_book_copies.bookId left join tbl_library_branch on tbl_library_branch.branchId = tbl_book_copies.branchId where tbl_book.bookId = ?  and tbl_library_branch.branchId = ?", 
			new Object[] { bookId ,branchId });
	
	if (bookList.isEmpty()) {
		return null;
	}
	else {
		book.setBookId((Integer) bookList.get(0).get("bookId"));
		book.setTitle((String) bookList.get(0).get("title"));
	}

		logger.debug("Logging a message from Book DAO here is the book object {}",book);
		return book;
	}

	public List<Book> getAllBooks() {
		List<Map<String, Object>> bookMap =  jdbcTemplate.queryForList("select * from tbl_book");
		List<Book> bookList = new ArrayList<Book>();
		for (Map<String, Object> item : bookMap) {
			Book book = new Book();
			book.setBookId((Integer) item.get("bookId"));
			book.setTitle((String) item.get("title"));
			bookList.add(book);
		}
		
		return bookList;
	}
	public List<Book> readAllBooksAvailable(Integer branchId){
		List<Map<String, Object>> bookMap =  jdbcTemplate.queryForList("select b.* from tbl_book_copies a join tbl_book b on a.bookId = b.bookId left join (select l.bookId, count(l.bookId) as rented from tbl_book_loans l where l.branchId = ? and l.dateIn= \"1900-01-01 00:00\" group by l.bookId) c on a.bookId = c.bookId where a.noOfCopies > 0 and a.branchId = ? and a.noOfCopies > ifnull(rented,0)", new Object[]{branchId, branchId});
		List<Book> bookList = new ArrayList<Book>();
		for (Map<String, Object> item : bookMap) {
			Book book = new Book();
			book.setBookId((Integer) item.get("bookId"));
			book.setTitle((String) item.get("title"));
			bookList.add(book);
		}
		return bookList;
	}
	
	
	
	class BookRowMapper implements RowMapper<Book>{
		@Override
		public Book mapRow(ResultSet rs, int arg1) throws SQLException {
			
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			return book;
		}
		
	}
}
