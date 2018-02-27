package com.gcit.lms;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.AuthorDAO;
//import com.gcit.lms.dao.BookDAO;
//import com.gcit.lms.dao.BookLoansDAO;
//import com.gcit.lms.dao.BorrowerDAO;
//import com.gcit.lms.dao.BranchDAO;
//import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
//import com.gcit.lms.entity.Book;
//import com.gcit.lms.entity.Borrower;
//import com.gcit.lms.entity.Branch;
//import com.gcit.lms.entity.Publisher;

/**
 * Handles requests for the application home page.
 */
@RestController
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	AuthorDAO authorDao;
//	@Autowired
//	BookDAO bookDao;
//	@Autowired
//	BookLoansDAO bookLoansDao;
//	@Autowired
//	PublisherDAO publisherDao;
//	@Autowired
//	BranchDAO branchDao;
//	@Autowired
//	BorrowerDAO borrowerDao;
	/**
	 * Author 
	 */
	@RequestMapping(value = "/authors/{authorId}", method = RequestMethod.GET,produces=  { "application/XML","application/JSON" })
	public Author getAuthor(@PathVariable int authorId) {
		logger.info("Welcome home! The client locale is {}.");
		return authorDao.getAuthorById(authorId);
	}
	// add author
//	@RequestMapping(value = "/author", method = RequestMethod.POST)
//	public void addAuthor(@RequestBody Author author, HttpServletResponse response) throws IOException {
//		logger.info("Welcome home! Message from the POST Method ");
//		try {
//			int value = authorDao.insertAuthor(author);	
//			if (value != 0) {
//				logger.info("Author saved with id: " + value);
//				response.setHeader("location", "/author/" + value);
//				response.setStatus(201);
//			}
//        } catch (Exception e) {
//				response.sendError(400, "Invalid request caused by invalid body parameters.");
//        }
//	}
	@RequestMapping(value = "/author", method = RequestMethod.POST)
	public void addAuthor(@RequestBody Author author) throws IOException {
		logger.info("Welcome home! Message from the POST Method ");
		authorDao.insertAuthor(author);	
	}
	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public List<Map<String, Object>> getAuthors() {
		logger.info("Welcome home! Message from the POST Method "); 
		return authorDao.getAuthors();
		
	}
	@RequestMapping(value = "/allauthors", method = RequestMethod.GET)
	public List<Author> getAllAuthors() {
		logger.info("Welcome home! Message from the POST Method "); 
		return authorDao.getAllAuthors();
		
	}
	@RequestMapping(value = "/author/{authorId}", method = RequestMethod.DELETE)
	public void deleteAuthor(@PathVariable Integer authorId) {
		logger.info("Welcome home! Message from the POST Method "); 
		Author author = new Author();
		author.setAuthorId(authorId);
		authorDao.deleteAuthor(author);
		
	}
	// edit author
	@RequestMapping(value = "/author", method = RequestMethod.PUT)
	public void updateAuthor(@RequestBody Author author) {
		logger.info("Welcome home! Message from the POST Method ");
		authorDao.updateAuthor(author);		
	}
	// upadte duedate
//	@RequestMapping(value = "/book/{bookId}/branch/{branchId}/cardno/{cardNo}/duedate", method = RequestMethod.PUT)
//	public void updateDueDateBookLoans(@RequestParam ("duedate")String dueDate,@PathVariable Integer bookId,@PathVariable Integer branchId,@PathVariable Integer cardNo) throws DataAccessException, ParseException {
//		logger.info("Welcome home! Message from the POST Method ");
//		bookLoansDao.updateDueDateBookLoans(dueDate, bookId, branchId, cardNo);		
//	}
//	/**
//	 * Book 
//	 */
//	@RequestMapping(value = "/book", method = RequestMethod.POST)
//	public void addBook(@RequestBody Book book) {
//		logger.info("Welcome home! Message from the POST Method ");
//		bookDao.insertBook(book);		
//	}
//	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
//	public void deleteBook(Book book) {
//		logger.info("Welcome home! Message from the POST Method "); 
//		bookDao.deleteBook(book);
//		
//	}
//	@RequestMapping(value = "/book", method = RequestMethod.PUT)
//	public void updateBook(@RequestBody Book book) {
//		logger.info("Welcome home! Message from the POST Method ");
//		bookDao.updateBook(book);		
//	}
//	@RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET,produces=  { "application/XML","application/JSON" })
//	public Book getBook(@PathVariable int bookId) {
//		logger.info("Welcome home! The client locale is {}.");
//		return bookDao.getBookById(bookId);
//	}
//	/**
//	 * Publisher 
//	 */
//	@RequestMapping(value = "/publisher", method = RequestMethod.POST)
//	public void addPublisher(@RequestBody Publisher publisher) {
//		logger.info("Welcome home! Message from the POST Method ");
//		publisherDao.insertPublisher(publisher);		
//	}
//	@RequestMapping(value = "/publishers/{publisherId}", method = RequestMethod.GET,produces=  { "application/XML","application/JSON" })
//	public Publisher getPublisher(@PathVariable int publisherId) {
//		logger.info("Welcome home! The client locale is {}.");
//		return publisherDao.getPublisherById(publisherId);
//	}
//	@RequestMapping(value = "/publisher/{publisherId}", method = RequestMethod.DELETE)
//	public void deletePublisher(Publisher publisher) {
//		logger.info("Welcome home! Message from the POST Method "); 
//		publisherDao.deletePublisher(publisher);		
//	}
//	@RequestMapping(value = "/publisher", method = RequestMethod.PUT)
//	public void updatePublisher(@RequestBody Publisher publisher) {
//		logger.info("Welcome home! Message from the POST Method ");
//		publisherDao.updatePublisher(publisher);		
//	}
//	/**
//	 * Branch
//	 */
//	@RequestMapping(value = "/branch", method = RequestMethod.POST)
//	public void addBranch(@RequestBody Branch branch) {
//		logger.info("Welcome home! Message from the POST Method ");
//		branchDao.insertBranch(branch);		
//	}
//	@RequestMapping(value = "/branchs/{branchId}", method = RequestMethod.GET,produces=  { "application/XML","application/JSON" })
//	public Branch getBranch(@PathVariable int branchId) {
//		logger.info("Welcome home! The client locale is {}.");
//		return branchDao.getBranchById(branchId);
//	}
//	@RequestMapping(value = "/branch/{branchId}", method = RequestMethod.DELETE)
//	public void deleteBranch(Branch branch) {
//		logger.info("Welcome home! Message from the POST Method "); 
//		branchDao.deleteBranch(branch);
//		
//	}
//	@RequestMapping(value = "/branch/name", method = RequestMethod.PUT)
//	public void updateBranchName(@RequestBody Branch branch) {
//		logger.info("Welcome home! Message from the POST Method ");
//		branchDao.updateBranchName(branch);		
//	}
//	/**
//	 * Borrower
//	 */
//	@RequestMapping(value = "/borrowers/{cardNo}", method = RequestMethod.GET,produces=  { "application/XML","application/JSON" })
//	public Borrower getBorrower(@PathVariable int cardNo) {
//		logger.info("Welcome home! The client locale is {}.");
//		return borrowerDao.getBorrowerById(cardNo);
//	}
//	@RequestMapping(value = "/borrower", method = RequestMethod.POST)
//	public void addBorrower(@RequestBody Borrower borrower) {
//		logger.info("Welcome home! Message from the POST Method ");
//		borrowerDao.insertBorrower(borrower);		
//	}
//	@RequestMapping(value = "/borrower/{cardNo}", method = RequestMethod.DELETE)
//	public void deleteBorrower(Borrower borrower) {
//		logger.info("Welcome home! Message from the POST Method "); 
//		borrowerDao.deleteBorrower(borrower);		
//	}
//	@RequestMapping(value = "/borrower", method = RequestMethod.PUT)
//	public void updateBorrower(@RequestBody Borrower borrower) {
//		logger.info("Welcome home! Message from the POST Method ");
//		borrowerDao.updateBorrower(borrower);		
//	}
	
}
