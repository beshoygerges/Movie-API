package com.movie.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(name = "Title")
    private String title;

    @NotNull
    @NotEmpty
    @Column(name = "Category")
    private String category;

    @DecimalMax("5.0")
    @DecimalMin("0.5")
    @Column(name = "Rating")
    private double rating;


}
