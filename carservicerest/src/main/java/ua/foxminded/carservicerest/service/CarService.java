package ua.foxminded.carservicerest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;
import ua.foxminded.carservicerest.model.CarPage;
import ua.foxminded.carservicerest.model.SearchCriteria;

public interface CarService {
	
	boolean findAnyCar();

	Optional<Car> findCar(Long id);

	List<Car> findCars();

	Page<Car> findCars(CarPage carPage);

	Page<Car> findCars(Pageable pageRequest, Specification<Car> specModel);

	Page<Car> findCarsSpec(PageRequest pageRequest, SearchCriteria searchCriteria);

	Car saveCar(CarDto carDto);

	void updateCar(Car car, CarDto carDto);

	void deleteCar(Long id);
}
