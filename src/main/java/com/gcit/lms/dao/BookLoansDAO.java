package com.gcit.lms.dao;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.utils.DateManipulation;

///**
// * 
// * @author hvalipour
// *
// */
@Component
public class BookLoansDAO {

	Logger logger = LoggerFactory.getLogger(AuthorDAO.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DateManipulation DM;
	// check out a book
	public void saveBookLoans(Integer bookId, Integer branchId, Integer cardNo) {
		String query = "insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values (?, ?, ?, ?, ?);";

		String dateOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

		// current_timeStamp_dueDate
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 10);
		String dueDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());

		int value = jdbcTemplate.update(query,
				new Object[] { bookId, branchId, cardNo, dateOut, dueDate });
		logger.debug("returned value from backend is {}", value);
	}
	//return a book
	public void updateDateInBookLoans(Integer bookId, Integer branchId, Integer cardNo) {
		String query = "update tbl_book_loans set dateIn = ? where bookId = ? AND branchId = ? AND cardNo = ? AND dateIn = \"1900-01-01 00:00:00\"";
		String dateIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		int value = jdbcTemplate.update(query, new Object[] {dateIn, bookId, branchId, cardNo });
		logger.debug("returned value from backend is {}", value);
	}
	// override duedate of a book
	public void updateDueDateBookLoans(String dueDate, Integer bookId, Integer branchId, Integer cardNo) throws DataAccessException, ParseException {
		String query = "update tbl_book_loans set dueDate = ? where bookId = ? AND branchId = ? AND cardNo = ? AND dateIn = \"1900-01-01 00:00:00\"";
		int value = jdbcTemplate.update(query, new Object[] { DM.getSQLDate(dueDate), bookId, branchId, cardNo});
		logger.debug("returned value from backend is {}", value);
	}

}
