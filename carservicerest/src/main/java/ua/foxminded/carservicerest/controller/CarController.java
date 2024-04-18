package ua.foxminded.carservicerest.controller;

import java.sql.SQLException;
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

import lombok.RequiredArgsConstructor;
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
		return carService.getCars();
	}

	@PostMapping
	public ResponseEntity<?> createCar(@RequestBody CarDto carDto,
											BindingResult bindingResult,
											UriComponentsBuilder uriComponentsBuilder) {
		if (bindingResult.hasErrors()) {
			ProblemDetail problemDetail = ProblemDetail
					.forStatusAndDetail(HttpStatus.BAD_REQUEST, messageSourse
							.getMessage("errors.400.title", new Object[0], "errors.400.title", LocaleContextHolder.getLocale()));
			problemDetail.setProperty("errors", bindingResult.getAllErrors().stream()
										.map(ObjectError::getDefaultMessage)
										.toList());
			return ResponseEntity.badRequest().body(problemDetail);
		} else {
			Car car = carService.saveCar(carDto);
			return ResponseEntity
					.created(uriComponentsBuilder
							.replacePath("/api/v1/cars/{carId}")
							.build(Map.of("carId", car.getId())))
					.body(car);
		}
	}

//	@GetMapping("/api/v1/cars")
//	public List<Car> getCars() {
//		
//		List<Car> cars =  carRepo.findAll();
//		
//		return cars;
//	}
//		List<Category> categories = caterRepo.findAll();

//		System.out.println(catRepo.findByCarModel("Pacifica"));
//		System.out.println(catRepo.findAll());

//		List<Category> categories = categoryService.getCategories();
//		System.out.println(categories);
//		System.out.println(categories.get(0).getCars());
//		System.out.println(cars.get(0));

//		CarCategory carCat = new CarCategory();
//		carCat.setCar(cars.get(0));
//		carCat.setCategory(categories.get(3));
//		carCatRepo.save(carCat);
//		System.out.println(cars.get(0).getCarCategories());

//		model.addAttribute("cars", cars);
//		return "cars";

}
