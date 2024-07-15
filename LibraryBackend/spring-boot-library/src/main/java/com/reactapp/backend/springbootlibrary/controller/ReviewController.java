package com.reactapp.backend.springbootlibrary.controller;


import com.reactapp.backend.springbootlibrary.requestmodels.ReviewRequest;
import com.reactapp.backend.springbootlibrary.service.ReviewService;
import com.reactapp.backend.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/secure")
    public void postReview(@RequestHeader(value="Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws Exception{


        String userEmail=ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if(userEmail == null){
            throw new Exception("User email is missing");
        }
        reviewService.postReview(userEmail,reviewRequest);

    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value="Authorization") String token,
                                 @RequestParam Long bookId) throws Exception{
        String userEmail=ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if(userEmail == null){
            throw new Exception("User email is missing");
        }
        return reviewService.userReviewListed(userEmail,bookId);
    }




}
