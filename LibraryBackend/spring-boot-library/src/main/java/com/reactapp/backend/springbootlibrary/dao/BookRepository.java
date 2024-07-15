package com.reactapp.backend.springbootlibrary.dao;

import com.reactapp.backend.springbootlibrary.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable page);
    Page<Book> findByCategory(@RequestParam("category") String category, Pageable page);

    @Query("select o from Book o where id in :book_ids")
    List<Book> findBooksByBookIds(@Param("Book_ids") List<Long> bookId);
}
