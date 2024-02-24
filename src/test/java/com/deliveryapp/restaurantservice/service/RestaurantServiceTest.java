package com.deliveryapp.restaurantservice.service;


import com.deliveryapp.restaurantservice.dto.RestaurantDto;
import com.deliveryapp.restaurantservice.entity.Restaurant;
import com.deliveryapp.restaurantservice.repository.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown(){
        Mockito.reset(restaurantRepository);
    }

    @Test
    public void testFindAllRestaurants(){

        //Mock the data
        List<Restaurant> mockedRestaurantList = new ArrayList<>();
        mockedRestaurantList.add(new Restaurant(1,"Roman","Roman","Roman","Roman"));

        mockedRestaurantList.add(new Restaurant(2, "Italian","Italian","Italian","Italian"));
        //Mock the repository behavior
        Mockito.when(restaurantRepository.findAll()).thenReturn(mockedRestaurantList);

        //Call the service method
        List<RestaurantDto> response = restaurantService.findAllRestaurants();

        //Verify the response
        Assertions.assertNotNull(response);
        for(int i = 0; i < mockedRestaurantList.size(); i++){
            Assertions.assertEquals(response.get(i), modelMapper.map(mockedRestaurantList.get(i), RestaurantDto.class));
        }

        Mockito.verify(restaurantRepository, Mockito.times(1)).findAll();
    }


    @Test
    public void testAddRestaurantInDB(){

    }

    @Test
    public void testFetchRestaurantById(){

    }
}
