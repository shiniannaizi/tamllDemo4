package com.tamll.learn.service.impl;

import com.tamll.learn.dao.ProductMapping;
import com.tamll.learn.dao.ReviewMapping;
import com.tamll.learn.dao.UserMapping;
import com.tamll.learn.entiy.Recive;
import com.tamll.learn.entiy.Review;
import com.tamll.learn.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ReviewMapping reviewMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapping productMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapping userMapping;

    @Override
    public void insertReview(String reviewContent,Integer userId,long productId) {
        Review review = new Review();
        review.setReview_Content(reviewContent);
        review.setProduct(productMapping.selectProductById(productId));
        review.setUser(userMapping.selectByUserId(userId));
        review.setReview_Create_Date(new Date());
        reviewMapping.insert(review);
    }

    @Override
    public List<Review> getReviewsByProductId(long productId) {
        return reviewMapping.selectReviewsByProductId(productId);
    }

    @Override
    public List<Review> getReviewsByUserId(Integer userId) {
        return reviewMapping.selectReviewsByUserId(userId);
    }

    @Override
    public Review getReviewInfo(Integer userId, long productId) {
        return reviewMapping.selectReviewInfo(userId,productId);
    }

    @Override
    public void updateReviewInfo(String reviewContent, Integer userId, long productId) {
        Review review = reviewMapping.selectReviewInfo(userId,productId);
        review.setReview_Content(reviewContent);
        review.setReview_Create_Date(new Date());
        reviewMapping.updateReviewInfo(review);
    }

    @Override
    public void deleteReview(Integer userId, long productId) {
        reviewMapping.deleteReview(userId,productId);
    }
}
