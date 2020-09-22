package com.review.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class ReviewEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
 private Long reviewID  ;
 

 @Column(name = "review" ,nullable = true )
 private String review;
 
 

 public ReviewEntity() {
	super();
	// TODO Auto-generated constructor stub
}

public ReviewEntity( String review) {
	super();
	this.review = review;
}
 
public Long getReviewID() {
   return reviewID;
 }
 public String getReview() {
   return review;
 }

 public void setReview(String review) {
   this.review = review;
 }

 public void setReviewID(Long reviewID) {
   this.reviewID = reviewID;
 }

}
