package ua.foxminded.carservicerest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;
import ua.foxminded.carservicerest.model.SearchCriteria;

public interface CarService {
	
	boolean findAnyCar();

	Page<Car> findCarsSpec(PageRequest pageRequest, SearchCriteria searchCriteria);

	Car saveCar(CarDto carDto);

	void updateCar(Long id, CarDto carDto);

	void deleteCar(Long id);
}
