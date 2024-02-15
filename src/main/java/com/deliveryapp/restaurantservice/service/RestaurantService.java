package com.deliveryapp.restaurantservice.service;

import com.deliveryapp.restaurantservice.dto.RestaurantDto;
import com.deliveryapp.restaurantservice.entity.Restaurant;
import com.deliveryapp.restaurantservice.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    public List<RestaurantDto> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDto> restaurantDtos = restaurants
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .collect(Collectors.toList());

        return restaurantDtos;
    }

    public RestaurantDto addRestaurantInDB(RestaurantDto restaurantDto) {
        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        Restaurant savedRestaurant =  restaurantRepository.save(restaurant);
        return modelMapper.map(savedRestaurant, RestaurantDto.class);
    }

    public RestaurantDto fetchRestaurantById(Long id) {
        List<RestaurantDto> restaurantDto = new ArrayList<>();
        restaurantRepository.findById(id)
                .ifPresent(
                        restaurant -> {
                            RestaurantDto dto = modelMapper.map(restaurant, RestaurantDto.class);
                            restaurantDto.add(dto);
                        }
                );
        return restaurantDto.get(0);
    }
}
