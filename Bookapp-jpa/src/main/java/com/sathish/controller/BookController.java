package com.sathish.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathish.model.Book;
import com.sathish.service.BookService;

@Controller
@RequestMapping("books")
public class BookController {
	private static final Logger LOGGER = Logger.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@GetMapping
	public String list(@RequestParam(value = "price", required = false) String priceFilter,
			@RequestParam(value = "releaseddate", required = false) String releasedDateFilter, HttpSession session) {
		LOGGER.info("Entering list");
		List<Book> books = null;

		if (priceFilter != null) {
			if (priceFilter.equals("desc")) {
				books = bookService.findByPriceDesc();
			} else if (priceFilter.equals("asc")) {
				books = bookService.findByPriceAsc();
			}
		} else if (releasedDateFilter != null) {
			if (releasedDateFilter.equals("desc")) {
				books = bookService.findByReleasedDateDesc();
			}
		} else {
			books = bookService.findAll();
		}
		System.out.println(books);
		session.setAttribute("books", books);
		return "book/list";
	}
}