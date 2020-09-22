package com.review.demo.Service;

import java.util.List;
import java.util.Optional;

import com.review.demo.Repository.ReviewRepository;
import com.review.demo.entities.ReviewEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private  ReviewRepository  reviewRepository;
    
    @Override
    public Optional <ReviewEntity> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List <ReviewEntity> findByKeyword(String text ) {
        return reviewRepository.findByKeyword(text);
    }

    @Override
    public void save(ReviewEntity employee) {
        reviewRepository.save(employee);
    }

    @Override
    public List <ReviewEntity> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public void delete(long id) {
        reviewRepository.deleteById(id);
    }
    
}
