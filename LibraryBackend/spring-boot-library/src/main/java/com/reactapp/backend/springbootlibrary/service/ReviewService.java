package com.reactapp.backend.springbootlibrary.service;

import java.sql.Date;
import java.time.LocalDate;

import com.reactapp.backend.springbootlibrary.dao.BookRepository;
import com.reactapp.backend.springbootlibrary.dao.ReviewRepository;
import com.reactapp.backend.springbootlibrary.entity.Review;
import com.reactapp.backend.springbootlibrary.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {
    


    @Autowired
    private ReviewRepository reviewRepository;

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception{
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail,reviewRequest.getBookId());

        if(validateReview != null){
            throw new Exception("Review already created");
        }

        Review review=new Review();

        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);

        if(reviewRequest.getReviewDescription().isPresent()){
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                Object::toString
            ).orElse(null));
        }

        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);


    }

    public Boolean userReviewListed(String userEmail,Long bookId) {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);

        if(validateReview != null){
            return true;
        }else{
            return false;
        }
    }
}
