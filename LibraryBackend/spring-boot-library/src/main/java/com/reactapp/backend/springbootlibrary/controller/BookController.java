package com.reactapp.backend.springbootlibrary.controller;


import com.reactapp.backend.springbootlibrary.entity.Book;
import com.reactapp.backend.springbootlibrary.responsemodels.ShelfCurrentLoansResponse;
import com.reactapp.backend.springbootlibrary.service.BookService;
import com.reactapp.backend.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;


    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }



    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestParam Long bookId) throws Exception{
        String userEmail="testuser@email.com";
        return bookService.checkoutBook(userEmail,bookId);
    }


    @GetMapping("/secure/ischeckdout/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId){
        String userEmail="testuser@email.com";
        return bookService.checkoutBookByUser(userEmail,bookId);
    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(){
        String userEmail="testuser@email.com";
        return bookService.currentLoansCount(userEmail);
    }

    @GetMapping("/secure/currentloans")
    public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value="Authorization") String token) throws Exception{
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\sub\"");
        return bookService.currentLoans(userEmail);
    }

    @PutMapping("/secure/return")
    public void returnBook(@RequestHeader(value="Authorization") String token,@RequestParam Long bookId) throws Exception{
        String userEmail=ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        bookService.returnBook(userEmail,bookId);
    }

    @PutMapping("/secure/renew/loan")
    public void renewLoan(@RequestHeader(value="Authorization") String token,
                          @RequestParam Long bookId) throws Exception{
        String userEmail=ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        bookService.renewLoan(userEmail,bookId);
    }



}
