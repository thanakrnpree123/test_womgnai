package com.review.demo.Repository;

import java.util.List;

import com.review.demo.entities.ReviewEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
  @Query(value = "SELECT *" + "FROM review" + " WHERE MATCH (review) AGAINST (:review);", nativeQuery = true)
  List<ReviewEntity> findByKeyword(@Param("review") String review);
    
}
