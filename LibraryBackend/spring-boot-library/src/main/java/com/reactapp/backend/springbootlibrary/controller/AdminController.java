package com.reactapp.backend.springbootlibrary.controller;


import com.reactapp.backend.springbootlibrary.entity.Message;
import com.reactapp.backend.springbootlibrary.requestmodels.AddBookRequest;
import com.reactapp.backend.springbootlibrary.service.AdminService;
import com.reactapp.backend.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader(value="Authorization") String token,
                            @RequestBody AddBookRequest addBookRequest) throws Exception {

        String admin= ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if(admin==null || !admin.equals("admin")){
            throw new Exception("Admin page only");
        }

        adminService.postBook(addBookRequest);

    }


    @PutMapping("/secure/increase/book/quantity")
    public void increaseBookQuantity(@RequestHeader(value="Authorization") String token,
                            @RequestParam Long bookId) throws Exception {

        String admin= ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if(admin==null || !admin.equals("admin")){
            throw new Exception("Admin page only");
        }

        adminService.increaseBookQuantity(bookId);

    }

    @PutMapping("/secure/decrease/book/quantity")
    public void decreaseBookQuantity(@RequestHeader(value="Authorization") String token,
                                     @RequestParam Long bookId) throws Exception {

        String admin= ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if(admin==null || !admin.equals("admin")){
            throw new Exception("Admin page only");
        }

        adminService.descreaseBookQuantity(bookId);

    }

    @DeleteMapping("/secure/delete/book")
    public void deleteBook(@RequestHeader(value="Authorization") String token,
                           @RequestParam Long bookId) throws Exception {
        String admin= ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if(admin==null || !admin.equals("admin")){
            throw new Exception("Admin page only");
        }

        adminService.deleteBook(bookId);
    }


}
