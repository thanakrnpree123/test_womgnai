package com.review.demo.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import com.review.demo.Service.ReviewService;
import com.review.demo.entities.ReviewEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MainController {

	@Autowired
	private ReviewService reviewService;
	private BufferedReader buffer;

	@GetMapping(path = "")
	public String[] getIndex(Model model) {
		String[] arr = null;
		List<String> itemsKeywords = new Vector<>();

		try {
			Resource file = new ClassPathResource("food_dictionary.txt");
			InputStream inputStream = file.getInputStream();
			DataInputStream data_input = new DataInputStream(inputStream);
			buffer = new BufferedReader(new InputStreamReader(data_input));
			String str_line;

			while ((str_line = buffer.readLine()) != null) {
				str_line = str_line.trim();
				if ((str_line.length() != 0)) {
					itemsKeywords.add(str_line);
				}
			}

			arr = (String[]) itemsKeywords.toArray(new String[itemsKeywords.size()]);
		} catch (Exception e) {
			System.out.println(e);
		}
		return arr;
	}

	@GetMapping("/all")
	public List<ReviewEntity> getAllReview() {
		List<ReviewEntity> reviewList = reviewService.findAll();
		return reviewList;
	}

	@GetMapping(path = "/reviews/{id}")
	public Optional <ReviewEntity> getReview(@PathVariable Long id) {
		Optional <ReviewEntity> review = reviewService.findById(id);
		return review;
	}

	@PutMapping(path = "/reviews/{id}")
	public Optional <ReviewEntity> updateReview(@RequestBody ReviewEntity newReview, @PathVariable Long id) {
		Optional <ReviewEntity> review = reviewService.findById(id);
		review.get().setReview(newReview.getReview());
		reviewService.save(review.get());
		return review;
	}

	@GetMapping(path = "/reviews/search")
	public List<ReviewEntity> getByKeyword(@RequestParam(value = "query") String text) {
		if (text != null && text != "") {
			List<ReviewEntity> reviewList = reviewService.findByKeyword(text);
			return reviewList;
		}
		return null;
	}

}
