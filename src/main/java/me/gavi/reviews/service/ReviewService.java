package me.gavi.reviews.service;

import me.gavi.reviews.dto.ReviewDTO;
import me.gavi.reviews.entity.Review;
import me.gavi.reviews.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private ModelMapper modelMapper;

    public ReviewService(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    public List<ReviewDTO> getReviewsByItemName(String itemID) {
        return reviewRepository.getByItemID(itemID)
                .stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        return modelMapper.map(reviewRepository.save(review), ReviewDTO.class);
    }
}
