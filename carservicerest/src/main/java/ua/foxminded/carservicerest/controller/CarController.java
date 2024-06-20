package ua.foxminded.carservicerest.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;
import ua.foxminded.carservicerest.model.FieldSort;
import ua.foxminded.carservicerest.model.SearchCriteria;
import ua.foxminded.carservicerest.service.CarService;

@RestController
@RequestMapping("api/v1/cars")
@RequiredArgsConstructor
public class CarController {

	private final CarService carService;

	@GetMapping
	public Page<Car> findCarsSpec(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,
			@RequestParam(value = "sortDerection", defaultValue = "DESC", required = false) Sort.Direction sortDerection,
			@RequestParam(value = "sortBy", defaultValue = "ID", required = false) FieldSort fieldSort,
			@RequestParam(value = "make", required = false) String make,
			@RequestParam(value = "model", required = false) String model,
			@RequestParam(value = "minYear", required = false) Integer minYear,
			@RequestParam(value = "maxYear", required = false) Integer maxYear,
			@RequestParam(value = "category", required = false) String category) {

		PageRequest pr = PageRequest.of(page, size, sortDerection, fieldSort.getFieldSort());
		SearchCriteria searchCriteria = new SearchCriteria(make, model, minYear, maxYear, category);

		return carService.findCarsSpec(pr, searchCriteria);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createCar(@Valid @RequestBody CarDto carDto, BindingResult bindingResult,
			UriComponentsBuilder uriComponentsBuilder) throws BindException {
		if (bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		} else {
			Car car = carService.saveCar(carDto);
			return ResponseEntity
					.created(uriComponentsBuilder.replacePath("/api/v1/cars/makers/{make}/models/{model}/year/{year}")
							.build(Map.of("make", car.getMake(), "model", car.getModel(), "year", car.getYear())))
					.body(car);
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateCar(@PathVariable("id") Long id, @Valid @RequestBody CarDto carDto,
			BindingResult bindingResult) throws BindException {

		if (bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		} else {
			carService.updateCar(id, carDto);
		}
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
		carService.deleteCar(id);
		return ResponseEntity.noContent().build();
	}
}
