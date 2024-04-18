package ua.foxminded.carservicerest.service;

import java.util.List;
import java.util.Optional;

import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;

public interface CarService {
	Optional<Car> getCar(Long id);
	List<CarDto> getCars();
	Car saveCar(CarDto carDto);
	void updateCar(Car car);
	void deleteCar(Long id);
}
