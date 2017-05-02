package com.sathish.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathish.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	public List<Book> findByOrderByPriceDesc();

	public List<Book> findByOrderByPriceAsc();
	public List<Book> findByOrderByReleasedDateDesc();

}
