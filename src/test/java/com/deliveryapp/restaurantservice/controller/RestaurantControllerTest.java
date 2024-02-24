package com.deliveryapp.restaurantservice.controller;

import com.deliveryapp.restaurantservice.dto.RestaurantDto;
import com.deliveryapp.restaurantservice.service.RestaurantService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class RestaurantControllerTest {

    @InjectMocks
    RestaurantController restaurantController;

    @Mock
    RestaurantService restaurantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAllRestaurants() {
        //AAA Arrange Act Assert using when and then
        //Mocked data
        List<RestaurantDto> mockedRestaurants = new ArrayList<>();
        mockedRestaurants.add(new RestaurantDto(1, "Restaurant 1", "Restaurant 1", "Restaurant 1", "Restaurant 1"));
        mockedRestaurants.add(new RestaurantDto(2, "Restaurant 2", "Restaurant 2", "Restaurant 2", "Restaurant 2"));

        //Mocked the service behavior
        Mockito.when(restaurantService.findAllRestaurants()).thenReturn(mockedRestaurants);

        //Call the controller method
        ResponseEntity<List<RestaurantDto>> response = restaurantController.fetchAllRestaurants();

        //Verify the response
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(mockedRestaurants, response.getBody());
        Mockito.verify(restaurantService, Mockito.times(1)).findAllRestaurants();
    }

    @Test
    public void testSaveRestaurant() {
        //Mocked data
        RestaurantDto mockedRestaurant = new RestaurantDto(1, "Restaurant 1", "Restaurant 1", "Restaurant 1", "Restaurant 1");

        //Mocked the service behavior
        Mockito.when(restaurantService.addRestaurantInDB(mockedRestaurant)).thenReturn(mockedRestaurant);

        //Call the controller method
        ResponseEntity<RestaurantDto> response = restaurantController.saveRestaurant(mockedRestaurant);

        //Verify the response
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(mockedRestaurant, response.getBody());
        Mockito.verify(restaurantService, Mockito.times(1)).addRestaurantInDB(mockedRestaurant);
    }

    @Test
    public void testFindRestaurantByID() {
        //Mock the data
        Long id = 1L;
        RestaurantDto mockedRestaurant =  new RestaurantDto(1, "Restaurant 1", "Restaurant 1", "Restaurant 1", "Restaurant 1");

        //Mock the service behavior
        Mockito.when(restaurantService.fetchRestaurantById(id)).thenReturn(mockedRestaurant);

        //Call the controller method
        ResponseEntity<RestaurantDto> response = restaurantController.findRestaurantById(id);

        //Verify the response
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(mockedRestaurant, response.getBody());
    }
    @AfterEach
    public void tearDown() {
        Mockito.reset(restaurantService);
    }

}
