package com.review.demo.controllers;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.review.demo.entities.ReviewEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainControllerTest {
	@LocalServerPort
	int randomServerPort;
	String url ="http://localhost:";
	RestTemplate restTemplate = new RestTemplate();
	
	@Test
		public void getAllReviewSuccess() throws URISyntaxException{
		final String baseUrl = url + randomServerPort + "/all";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("reviewID"));
		Assert.assertEquals(true, result.getBody().contains("review"));
		
	}
	
	@Test
	public void getReviewSuccess() throws URISyntaxException{
		long id = 1;
		final String baseUrl = url + randomServerPort + "/reviews/"+id;
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
	}
	
	@Test
	public void updateReviewSuccess() throws URISyntaxException{
		long id = 1;
		final String baseUrl = url + randomServerPort + "/reviews/"+id;
		URI uri = new URI(baseUrl);
		
		ReviewEntity review = new ReviewEntity( "test");
		 HttpEntity<ReviewEntity> entity = new HttpEntity<ReviewEntity>(review);
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
	}
	
	@Test
	public void getByKeywordSuccess() throws URISyntaxException{
		String keywords = "โรตี";
		final String baseUrl = url + randomServerPort + "/reviews/search?query={keywords}";
		
		restTemplate.getForObject(baseUrl, String.class,keywords );

		
	}
	
	
	
	
	
}
