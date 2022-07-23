package me.gavi.reviews.controller;

import me.gavi.reviews.dto.ReviewDTO;
import me.gavi.reviews.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ReviewsController {
    private ReviewService reviewService;

    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/review")
    public ModelAndView getReviews(@RequestParam("itemId") String itemId) {
        List<ReviewDTO> reviewDTOList = reviewService.getReviewsByItemName(itemId);
        Map<Integer, Long> rateMap = reviewDTOList.stream().collect(Collectors.groupingBy(ReviewDTO::getRate, Collectors.counting()));
        long rateCount = rateMap.values().stream().mapToLong(Long::longValue).sum();
        Double totalRate = rateMap.entrySet().stream().reduce(0l, (counter, map) -> counter += map.getKey() * map.getValue(), Long::sum).doubleValue()/rateCount;
        ModelAndView modelAndView = new ModelAndView("review", "reviewsList", reviewDTOList);
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setItemID(itemId);
        modelAndView.addObject("reviewDto", reviewDTO);
        modelAndView.addObject("rateMap", rateMap);
        modelAndView.addObject("rateCount", rateCount);
        modelAndView.addObject("totalRate", totalRate);
        return modelAndView;
    }

    @PostMapping("/review")
    public ModelAndView saveReview(@ModelAttribute("reviewDto") ReviewDTO reviewDTO) {
        reviewService.saveReview(reviewDTO);
        return getReviews(reviewDTO.getItemID());
    }

}
