package ua.foxminded.carservicerest.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;
import ua.foxminded.carservicerest.model.SearchCriteria;
import ua.foxminded.carservicerest.repository.CarRepository;
import ua.foxminded.carservicerest.util.CarSpecification;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

	private final CarRepository carRepository;

	@Override
	public boolean findAnyCar() {
		return carRepository.findAnyCar();
	}

	@Override
	public Page<Car> findCarsSpec(PageRequest pageRequest, SearchCriteria searchCriteria) {

		Specification<Car> specCar = Specification.where(CarSpecification.filterByMake(searchCriteria.getMake()))
				.and(CarSpecification.filterByMake(searchCriteria.getModel()))
				.and(CarSpecification.filterByMinYear(searchCriteria.getMinYear()))
				.and(CarSpecification.filterByMaxYear(searchCriteria.getMaxYear()))
				.and(CarSpecification.filterByCategory(searchCriteria.getCategory()));

		return carRepository.findAll(specCar, pageRequest);

	}

	@Override
	@Transactional
	public Car saveCar(CarDto carDto) {
		
		Car car = new Car();
		setCarDtoToCar(car, carDto);
		carRepository.save(car);
		log.info("Save Car id = {}", car.getId());
		return car;
	}

	@Override
	@Transactional
	public void updateCar(Long id, CarDto carDto) {

		Optional<Car> car = carRepository.findById(id);
		if (car.isPresent()) {
			setCarDtoToCar(car.get(), carDto);
			log.info("Update Car id = {}", id);
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	@Transactional
	public void deleteCar(Long id) {

		Optional<Car> car = carRepository.findById(id);
		if (car.isPresent()) {
			carRepository.deleteById(id);
			log.info("Delete Car id = {}", id);
		} else {
			throw new NoSuchElementException();
		}
	}

	private void setCarDtoToCar(Car car, CarDto carDto) {

		car.setCarCode(carDto.carCode());
		car.setMake(carDto.make());
		car.setModel(carDto.model());
		car.setYear(carDto.year());
		car.setCategories(carDto.categories());
	}
}