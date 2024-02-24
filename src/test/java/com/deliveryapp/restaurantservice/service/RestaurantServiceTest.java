package com.deliveryapp.restaurantservice.service;


import com.deliveryapp.restaurantservice.dto.RestaurantDto;
import com.deliveryapp.restaurantservice.entity.Restaurant;
import com.deliveryapp.restaurantservice.repository.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Spy
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.modelMapper = new ModelMapper();
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
            RestaurantDto restaurantDto = modelMapper.map(mockedRestaurantList.get(i), RestaurantDto.class);
            System.out.println(restaurantDto);
            Assertions.assertEquals(restaurantDto, response.get(i));
        }

        Mockito.verify(restaurantRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testAddRestaurantInDB(){

        //Mock the data
        RestaurantDto mockedRestaurantDto = new RestaurantDto(1,"Roman","Roman","Roman","Roman");
        Restaurant mockedRestaurant = new Restaurant();
        mockedRestaurant = modelMapper.map(mockedRestaurantDto, Restaurant.class);

        //Mock the repository behavior
        Mockito.when(restaurantRepository.save(mockedRestaurant)).thenReturn(mockedRestaurant);

        //Call the service method
        RestaurantDto response = restaurantService.addRestaurantInDB(mockedRestaurantDto);


        //Verify the response
        Assertions.assertEquals(mockedRestaurantDto, response);
        Assertions.assertNotNull(response);
        Mockito.verify(restaurantRepository, Mockito.times(1)).save(mockedRestaurant);

    }

    @Test
    public void testFetchRestaurantById(){

        //Mock the data
        Long id = 1L;
        Restaurant mockedRestaurant = new Restaurant(
                1, "Roman","Roman","Roman","Roman");
        RestaurantDto mockedRestaurantDto = modelMapper.map(mockedRestaurant, RestaurantDto.class);

        //Mock the repository behavior
        Mockito.when(restaurantRepository.findById(id)).thenReturn(Optional.of(mockedRestaurant));

        //Call the service method
        RestaurantDto response = restaurantService.fetchRestaurantById(id);
        Restaurant mappedResponse = modelMapper.map(response, Restaurant.class);

        //verify the response
        Assertions.assertEquals(1L, mappedResponse.getId());
        Assertions.assertEquals(mockedRestaurantDto, response);
        Assertions.assertNotNull(response);
        Mockito.verify(restaurantRepository, Mockito.times(1)).findById(id);

    }

    @AfterEach
    public void tearDown(){
        Mockito.reset(restaurantRepository);
    }

}
