package ua.foxminded.carservicerest;

import static org.assertj.core.api.Assertions.contentOf;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.carservicerest.controller.CarController;
import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;
import ua.foxminded.carservicerest.model.Category;
import ua.foxminded.carservicerest.service.CarService;

import static org.mockito.Mockito.when;


//@Transactional
//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(controllers = CarController.class)
public class CarControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private CarService carService;

	@Test
	void whenRequestForCreateIsValidExpectNewCar() {
		List<Category> categories = new ArrayList<>();
		
		CarDto carDto = new CarDto("i6jT5ByjBF", "Ford", "Ranger SuperCab", 2020, categories);
		
		Car car = new Car();
		car.setId(1L);
		car.setCarCode("i6jT5ByjBF");
		car.setMake("Ford");
		car.setModel("Ranger SuperCab");
		car.setYear(2020);
		car.setCategories(categories);
		
		when(carService.saveCar(carDto)).thenReturn(car);
		
		
		
		var requestBuilder = MockMvcRequestBuilders.post("api/v1/cars")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{"carCode": "i6jT5ByjBF", "make" : "Ford", "model" : "Ranger SuperCab", "year" : 2020, "categories": [{}]}
						""");
		
		mockMvc.perform(requestBuilder)
					.andDo(print())
					.andExpectAll(
							status().isCreated(),
							header().string(HttpHeaders.LOCATION, "http://localhost:8080/api/v1/cars/makers/Ford/models/Ranger%20SuperCab/year/2020"),
							content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
							content().json("""
								{
									"id": 94643,
									"carCode": "i6jT5ByjBF",
									"make": "Ford",
									"model": "Ranger SuperCab",
									"year": 2020,
									"categories": [{}]
								}"""
							));
	}
}
