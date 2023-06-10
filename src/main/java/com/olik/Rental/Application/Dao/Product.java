package com.olik.Rental.Application.Dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Product {

    private Integer id;
    private String name;
    private String image;
    private Double cost;

    public Product(Integer id, String name, String image, Double cost) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.cost = cost;
    }

}
