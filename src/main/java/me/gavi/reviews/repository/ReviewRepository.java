package me.gavi.reviews.repository;

import me.gavi.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> getByItemID(String itemID);
}
