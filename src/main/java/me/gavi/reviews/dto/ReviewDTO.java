package me.gavi.reviews.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private Integer id;

    private Integer rate;

    private String userName;

    private String userEmail;

    private String imgURL;

    private String reviewMessage;

    private String itemID;

    private Date creationDate;

}
