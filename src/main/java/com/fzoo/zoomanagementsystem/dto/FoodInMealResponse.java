package com.fzoo.zoomanagementsystem.dto;

import com.fzoo.zoomanagementsystem.model.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodInMealResponse {
    private  int id;
    private LocalDateTime dateTime;
    private String cageName;
    private String expertEmail;
    private Set<Food> haveFood;
}
