package com.review.demo.Service;

import java.util.List;
import java.util.Optional;

import com.review.demo.entities.ReviewEntity;

public interface ReviewService {
    
   public List <ReviewEntity> findAll();

   public void save(ReviewEntity review);

   public  Optional <ReviewEntity> findById(Long id);

   public void delete(long id);

   public List <ReviewEntity> findByKeyword(String text);
    
}
