package ua.foxminded.carservicerest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;
import ua.foxminded.carservicerest.model.Category;
import ua.foxminded.carservicerest.repository.CarRepository;
import ua.foxminded.carservicerest.repository.CategoryRepository;
import ua.foxminded.carservicerest.service.CarService;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

	private CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	private MessageSource messageSourse;

	@GetMapping
	public List<CarDto> getCars() {
		List<Car> cars = carService.getCars();
		List<CarDto> carsDto = new ArrayList<>();
		
		System.out.println(cars);
		System.out.println(cars.get(0).getCategories().get(0));
		
		return carsDto;
	}
}
