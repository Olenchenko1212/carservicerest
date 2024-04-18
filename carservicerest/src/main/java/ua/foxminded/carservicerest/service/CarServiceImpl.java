package ua.foxminded.carservicerest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;
import ua.foxminded.carservicerest.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	
	private CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@Override 
	public Optional<Car> getCar(Long id){
		Optional<Car> car = carRepository.findById(id);
		return car;
	}
	
	@Override
	public List<CarDto> getCars(){
		List<Car> cars = carRepository.findAll();
		List<CarDto> carsDto = new ArrayList<>();
		System.out.println(cars);
		System.out.println(cars.get(0).getCategories());
		return carsDto;
	}
	
	@Override
	public Car saveCar(CarDto carDto) {
		Car car = new Car();
		car.setCarCode(carDto.carCode());
		car.setMake(carDto.make());
		car.setModel(carDto.model());
		car.setYear(carDto.year());
		carRepository.save(car);
		return car;
	}
	
	@Override
	public void updateCar(Car car) {
		carRepository.save(car);
	}
	
	@Override
	public void deleteCar(Long id) {
		carRepository.deleteById(id);
	}
}
