package com.tamll.learn.dao;

import com.tamll.learn.entiy.Review;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewMapping {

    void insert(Review review);

    List<Review> selectReviewsByProductId(long productId);

    List<Review> selectReviewsByUserId(Integer userId);

    Review selectReviewInfo(@Param("userId") Integer userId,
                            @Param("productId") long productId);

    void updateReviewInfo(Review review);

    void deleteReview(@Param("userId") Integer userId,
                      @Param("productId") long productId);
}
