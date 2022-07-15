package me.gavi.reviews.controller;

import me.gavi.reviews.dto.ReviewDTO;
import me.gavi.reviews.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReviewsController {
    private ReviewService reviewService;

    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/review")
    public ModelAndView getReviews(@RequestParam("itemId") String itemId){
        List<ReviewDTO> reviewDTOList = reviewService.getReviewsByItemName(itemId);
        ModelAndView modelAndView = new ModelAndView("review", "reviewsList", reviewDTOList);
        modelAndView.addObject("reviewDto",new ReviewDTO());
        return modelAndView;
    }

    @PostMapping("/review")
    public ModelAndView saveReview(@ModelAttribute("reviewDto") ReviewDTO reviewDTO){
        reviewService.saveReview(reviewDTO);
        return getReviews(reviewDTO.getItemID());
    }

}
