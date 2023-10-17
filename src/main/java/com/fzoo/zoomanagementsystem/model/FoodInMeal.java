package com.fzoo.zoomanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "food_in_meal")
public class FoodInMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int meal_id;
    private int food_id;


}