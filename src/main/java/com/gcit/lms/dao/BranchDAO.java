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

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Publisher;
/**
 * 
 * @author hvalipour
 *
 */
@Component
public class BranchDAO {
	Logger logger = LoggerFactory.getLogger(BranchDAO.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void updateBranch(Branch branch) {
		String query = "update newlibrary.tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?;";
		int value = jdbcTemplate.update(query, new Object[] {branch.getName(), branch.getAddress(), branch.getBranchId()});
		logger.debug("returned value from backend is {}", value);
	}


	public List<Branch> getAllBranchs() {
		List<Map<String, Object>> allList =  jdbcTemplate.queryForList("select * from tbl_library_branch");
		List<Branch> branchList = new ArrayList<Branch>();
		for (Map<String, Object> item : allList) {
			Branch branch = new Branch();
			branch.setBranchId((Integer) item.get("branchId"));
			branch.setName((String) item.get("branchName"));
			branch.setAddress((String) item.get("branchAddress"));
			branchList.add(branch);
		}
		
		return branchList;
	}
	public Branch getBranchById(Integer branchId) {
		Branch branch = new Branch();

			List<Map<String, Object>> branchList = jdbcTemplate.queryForList("select * from tbl_library_branch where tbl_library_branch.branchId = ?", 
					new Object[] { branchId });
			
			if (branchList.isEmpty()) {
				return null;
			}
			else {
				branch.setBranchId((Integer) branchList.get(0).get("branchId"));
			}

		logger.debug("Logging a message from Book DAO here is the book object {}",branch);
		return branch;
	}
	public void deleteBranch(Branch branch) {
		String query = "delete from tbl_library_branch where branchId = ?"; 
		int value = jdbcTemplate.update(query, new Object[] { branch.getBranchId() });
		logger.debug("returned value from backend is {}", value);
	}
	public void insertBranch(Branch branch) {
		String query = "INSERT INTO `newlibrary`.`tbl_library_branch` (`branchName`) VALUES (?);";
		int value = jdbcTemplate.update(query, new Object[] {branch.getName() });
		logger.debug("returned value from backend is {}", value);
	}
	public void updateBranchName(Branch branch) {
		String query = "update newlibrary.tbl_library_branch set branchName = ? where branchId = ?;";
		int value = jdbcTemplate.update(query, new Object[] {branch.getName(), branch.getBranchId()});
		logger.debug("returned value from backend is {}", value);
	}
	
}

