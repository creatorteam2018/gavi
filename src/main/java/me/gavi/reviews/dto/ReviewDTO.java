package me.gavi.reviews.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private Integer rate;

    private String userName;

    private String imgURL;

    private String reviewMessage;

    private String itemID;

    private Date creationDate;

}
