package me.gavi.reviews.controller;

import me.gavi.reviews.dto.ReviewDTO;
import me.gavi.reviews.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReviewsController {
    private ReviewService reviewService;

    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/hi")
    public ModelAndView home(){
        List<ReviewDTO> reviewDTOList = reviewService.getReviewsByItemName("a");
        return new ModelAndView("review","reviewsList",reviewDTOList);
    }

}
