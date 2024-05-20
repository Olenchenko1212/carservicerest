package ua.foxminded.carservicerest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;
import ua.foxminded.carservicerest.model.Category;
import ua.foxminded.carservicerest.service.CarService;
import ua.foxminded.carservicerest.service.CategoryService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	static final String FILE_NAME = "file.csv";
	static final int ROWS_FOR_CARS = 4;
	
	private final CarService carService;
	private final CategoryService categoryService;

	@Override
	public void run(String... args) throws Exception {
		
		boolean existsAnyCar = carService.findAnyCar();
		if(existsAnyCar) {
			log.info("NOT load data because is already exists");
		} else {
			log.info("Load data...");
			
			try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
				String line;
				boolean skipFirstRow = true;
				while ((line = br.readLine()) != null) {
					if (!skipFirstRow && !line.equals(",,,,")) {
						String[] data = line.split(",");
						Car car = getCar(data);				
						CarDto carDto = new CarDto(car.getCarCode(), car.getMake(), car.getModel(), car.getYear(), getCategories(data));			
						carService.saveCar(carDto);					
					} else {
						skipFirstRow = false;
					}		
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private List<Category> getCategories(String[] data) {	
		List<Category> categories = new ArrayList<>();
		for (int i = ROWS_FOR_CARS; i < data.length; i++) {			
			categories.add(saveCategory(data[i].replaceAll("[\"'\\s+]", "")));
		}
		return categories;
	}
	
	private Category saveCategory(String categoryName) {
		Optional<Category> category = categoryService.findByName(categoryName);
		if(category.isEmpty()) {
			Category newCategory = new Category(null, categoryName, null);
			categoryService.saveCategory(newCategory);
			return newCategory;
		} 
		return category.get();
	}

 	private Car getCar(String[] data) {	
		Car car = new Car();		
		for (int i = 0; i < ROWS_FOR_CARS; i++) {
			if (i == 0) {
				car.setCarCode(data[i]);
			} else if (i == 1) {
				car.setMake(data[i]);
			} else if (i == 2) {
				car.setYear(Integer.parseInt(data[i]));
			} else if (i == 3) {
				car.setModel(data[i]);
			}
		}
		return car;
	}
}
