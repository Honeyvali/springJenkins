package com.gcit.lms.dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Publisher;
/**
 * 
 * @author hvalipour
 *
 */
@Component
public class BorrowerDAO {


	Logger logger = LoggerFactory.getLogger(BorrowerDAO.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insertBorrower(Borrower borrower) {
		String query = "INSERT INTO `newlibrary`.`tbl_borrower` (`name`) VALUES (?);";
		int value = jdbcTemplate.update(query, new Object[] {borrower.getName() });
		logger.debug("returned value from backend is {}", value);
	}
	public void updateBorrower(Borrower borrower) {
		String query = "update tbl_borrower set name = ? where cardNo = ?";
		int value = jdbcTemplate.update(query,new Object[] { borrower.getName(), borrower.getCardNo() });
		logger.debug("returned value from backend is {}", value);
	}
	public void deleteBorrower(Borrower borrower) {
		String query = "delete from tbl_borrower where cardNo = ?"; 
		int value = jdbcTemplate.update(query, new Object[] { borrower.getCardNo() });
		logger.debug("returned value from backend is {}", value);
	}
	public Borrower getBorrowerById(int cardNo) {
		Borrower borrower = new Borrower();

			List<Map<String, Object>> borrowerList = jdbcTemplate.queryForList("SELECT * FROM newlibrary.tbl_borrower where cardNo=?", 
					new Object[] { cardNo });
			
			if (borrowerList.isEmpty()) {
				return null;
			}
			else {
				borrower.setCardNo((Integer) borrowerList.get(0).get("cardNo"));
			}

		return borrower;
	}

//	public void saveBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
//		save("insert into tbl_borrower (name, address, phone) values (?, ?, ?)", new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone()});
//	}
//
//	public void updateBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
//		save("update tbl_borrower set Name = ? where cardNo = ?",
//				new Object[] { borrower.getName(), borrower.getCardNo() });
//	}
//
//	public void deleteBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
//		save("delete from tbl_borrower where cardNo = ?", new Object[] { borrower.getCardNo() });
//	}
//
//	public List<Borrower> readAllBorrowers() throws SQLException, ClassNotFoundException {
//		return read("select * from tbl_borrower", null);
//	}
//
//	public List<Borrower> readBorrowersByName(String borrowerName) throws SQLException, ClassNotFoundException {
//		return read("select * from tbl_borrower  where borrowerName = ?", new Object[] { borrowerName });
//	}
//	
//	public List<Borrower> readBorrowerByCardNo(Integer cardNo) throws SQLException, ClassNotFoundException {
//	return read("select * from tbl_borrower  where cardNo = ?", new Object[] { cardNo });
//}
//	
//	@Override
//	public List<Borrower> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
//		BookLoansDAO bdao = new BookLoansDAO(conn);
//		List<Borrower> borrowers = new ArrayList<>();
//		while (rs.next()) {
//			Borrower b = new Borrower();
//			b.setCardNo(rs.getInt("cardNo"));
//			b.setName(rs.getString("name"));
//			b.setAddress(rs.getString("address"));
//			b.setPhone(rs.getString("phone"));
////			b.setBookLoans(bdao.readFirstLevel(
////					"select * from tbl_book_loans where cardNo IN (select cardNo from tbl_borrower where cardNo = ?)",
////					new Object[] { b.getCardNo() }));
//			borrowers.add(b);
//		}
//		return borrowers;
//		
//	}
////
//	@Override
//	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws ClassNotFoundException, SQLException {
//		List<Borrower> borrowers = new ArrayList<>();
//		while (rs.next()) {
//			Borrower b = new Borrower();
//			b.setCardNo(rs.getInt("cardNo"));
//			b.setName(rs.getString("name"));
//			b.setAddress(rs.getString("address"));
//			b.setPhone(rs.getString("phone"));
//			
//			borrowers.add(b);
//		}
//		return borrowers;
//	}

}
