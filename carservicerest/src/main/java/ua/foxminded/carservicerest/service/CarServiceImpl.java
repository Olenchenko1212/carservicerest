package ua.foxminded.carservicerest.service;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;
import ua.foxminded.carservicerest.model.CarPage;
import ua.foxminded.carservicerest.model.SearchCriteria;
import ua.foxminded.carservicerest.repository.CarRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
	
	private final CarRepository carRepository;
	
	@Override
	public boolean findAnyCar(){
		return carRepository.findAnyCar();
	}
	
	@Override 
	public Optional<Car> findCar(Long id){
		Optional<Car> car = carRepository.findById(id);
		log.info("Get Car by id = {}", id);
		return car;
	}
	
	@Override
	public List<Car> findCars(){
		List<Car> cars = carRepository.findAll();
		log.info("Find {} Cars for page", cars.size());
		return cars;
	}
	
	@Override
	public Page<Car> findCars(CarPage carPage){
		Sort sort = Sort.by(carPage.getSortDerection(), carPage.getSortBy());
		Pageable pageable = PageRequest.of(carPage.getPageNumber(), carPage.getPageSize(), sort);
		return carRepository.findAll(pageable);
	}
	
	@Override
	public Page<Car> findCars(Pageable pageRequest, Specification<Car> specModel){
		return carRepository.findAll(specModel, pageRequest);
	}
	
	@Override
	public Page<Car> findCarsSpec(PageRequest pageRequest, SearchCriteria searchCriteria) {
		
		Specification<Car> specCar = Specification
				.where(CarSpecification.filterByMake(searchCriteria.getMake()))
				.and(CarSpecification.filterByMake(searchCriteria.getModel()))
				.and(CarSpecification.filterByMinYear(searchCriteria.getMinYear()))
				.and(CarSpecification.filterByMaxYear(searchCriteria.getMaxYear()))
				.and(CarSpecification.filterByCategory(searchCriteria.getCategory()));
		
		return carRepository.findAll(specCar, pageRequest);
				
	}
	
	@Override
	public Car saveCar(CarDto carDto) {
		Car car = new Car();
		setCarDtoToCar(car, carDto);
		carRepository.save(car);
		log.info("Save Car id = {}", car.getId());
		return car;
	}
	
	@Override
	public void updateCar(Car car, CarDto carDto) {
		setCarDtoToCar(car, carDto);
		carRepository.save(car);
		log.info("Update Car id = {}", car.getId());
	}
	
	@Override
	public void deleteCar(Long id) {
		carRepository.deleteById(id);
		log.info("Delete Car id = {}", id);
	}
	
	private void setCarDtoToCar(Car car, CarDto carDto) {
		car.setCarCode(carDto.carCode());
		car.setMake(carDto.make());
		car.setModel(carDto.model());
		car.setYear(carDto.year());
		car.setCategories(carDto.categories());
	}
}
