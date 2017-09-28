package com.tamll.learn.service;

import com.tamll.learn.entiy.Recive;
import com.tamll.learn.entiy.Review;

import java.util.List;

public interface ReviewService {

    void insertReview(String reviewContent,Integer userId,long productId);

    List<Review> getReviewsByProductId(long productId);

    List<Review> getReviewsByUserId(Integer userId);

    Review getReviewInfo(Integer userId,long productId);

    void updateReviewInfo(String reviewContent,Integer userId,long productId);

    void deleteReview(Integer userId,long productId);
}
