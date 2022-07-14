package me.gavi.reviews.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer rate;

    private String userName;

    @Column(length = 2000)
    private String imgURL;

    @Column(length = 4000)
    private String reviewMessage;

    private String itemID;

    private Date creationDate;

}
