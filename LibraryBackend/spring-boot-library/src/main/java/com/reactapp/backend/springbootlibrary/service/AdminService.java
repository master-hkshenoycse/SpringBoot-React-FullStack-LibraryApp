package com.reactapp.backend.springbootlibrary.service;


import com.reactapp.backend.springbootlibrary.dao.BookRepository;
import com.reactapp.backend.springbootlibrary.dao.CheckoutRepository;
import com.reactapp.backend.springbootlibrary.dao.ReviewRepository;
import com.reactapp.backend.springbootlibrary.entity.Book;
import com.reactapp.backend.springbootlibrary.requestmodels.AddBookRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;


    public void postBook(AddBookRequest addBookRequest){
        Book book=new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        book.setDescription(addBookRequest.getDescription());
        book.setCopies(addBookRequest.getCopies());
        book.setCopiesAvailable(addBookRequest.getCopies());
        book.setCategory(addBookRequest.getCategory());
        book.setImg(addBookRequest.getImg());
        bookRepository.save(book);
    }


    public void increaseBookQuantity(Long bookId) throws Exception{
        Optional<Book> book=bookRepository.findById(bookId);

        if(!book.isPresent()){
            throw new Exception("Book not found");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable()+1);
        book.get().setCopies(book.get().getCopies()+1);

        bookRepository.save(book.get());


    }

    public void descreaseBookQuantity(Long bookId) throws Exception{
        Optional<Book> book=bookRepository.findById(bookId);

        if(!book.isPresent() || book.get().getCopiesAvailable()<=0 || book.get().getCopies()<=0){
            throw new Exception("Book not found");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable()-1);
        book.get().setCopies(book.get().getCopies()-1);

        bookRepository.save(book.get());


    }

    public void deleteBook(Long bookId) throws Exception{
        Optional<Book> book=bookRepository.findById(bookId);

        if(!book.isPresent()){
            throw new Exception("Book not found");
        }

        bookRepository.delete(book.get());
        checkoutRepository.deleteAllByBookId(bookId);
        reviewRepository.deleteAllByBookId(bookId);
    }


};

