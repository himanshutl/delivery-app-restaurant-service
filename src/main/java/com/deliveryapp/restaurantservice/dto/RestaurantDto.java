package com.deliveryapp.restaurantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private long id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;

}
