package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Branch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author hvalipour
 *
 */
@Component
public class BookCopiesDAO{
	Logger logger = LoggerFactory.getLogger(BookCopiesDAO.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	BookDAO bookDao;
	@Autowired
	BranchDAO branchDao;

	
	public void AddBookCopies(BookCopies bookCopies) {
		String query = "INSERT INTO `newlibrary`.`tbl_book_copies` (`NoOfCopies`) VALUES (?);";
		int value = jdbcTemplate.update(query, new Object[] {bookCopies.getNoCopies() });
		logger.debug("returned value from backend is {}", value);
	}
	
	public BookCopies readBookCopies(Integer bookId, Integer branchId) {
		BookCopies bookCopies = new BookCopies();

			List<Map<String, Object>> bookCopiesList = jdbcTemplate.queryForList("select * from tbl_book left join tbl_book_copies on tbl_book.bookId = tbl_book_copies.bookId left join tbl_library_branch on tbl_library_branch.branchId = tbl_book_copies.branchId where tbl_book.bookId = ?  and tbl_library_branch.branchId = ?", 
					new Object[] { bookId ,branchId });
			
			if (bookCopiesList.isEmpty()) {
				return null;
			}
			else {
				Book book = new Book(bookId);
				//book = bookDao.getBookById(bookId);
				
				Branch branch = new Branch(bookId);
				//branch = branchDao.getBranchById(branchId);
				
				bookCopies.setBook(book);
				bookCopies.setNoCopies((Integer) bookCopiesList.get(0).get("noOfCopies"));
			}

		logger.debug("Logging a message from Book DAO here is the book object {}",bookCopies);
		return bookCopies;
	}
	public void updateBookCopies(BookCopies bookCopies, Integer bookId, Integer branchId) {
			
		String query = "update tbl_book_copies set NoOfCopies = ? where bookId = ? AND branchId = ?";
		int value = jdbcTemplate.update(query, new Object[]{ bookCopies.getNoCopies(), bookId, branchId});
	}

}

