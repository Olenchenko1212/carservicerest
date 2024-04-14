package ua.foxminded.carservicerest.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarCategory;
import ua.foxminded.carservicerest.model.Category;
import ua.foxminded.carservicerest.repository.CarCategoryRepository;
import ua.foxminded.carservicerest.repository.CarRepository;
import ua.foxminded.carservicerest.repository.CategoryRepository;
import ua.foxminded.carservicerest.repository.CatterRepository;

@Controller
@RequestMapping
public class CarController {
	
	
	@Autowired
	private CatterRepository catRepo;
	
	@Autowired
	private CategoryRepository caterRepo;
	
	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	private CarCategoryRepository carCatRepo;
	
	@GetMapping("/h")
	public String getCars(Model model) throws SQLException {
		
		List<Car> cars =  carRepo.findAll();
		List<Category> categories = caterRepo.findAll();
//		System.out.println(cars.get(0).getCategories().get(0));
		
//		System.out.println(cars);
		System.out.println(catRepo.findByCarModel("Pacifica"));
		System.out.println(catRepo.findAll());
		
//		List<Category> categories = categoryService.getCategories();
//		System.out.println(categories);
//		System.out.println(categories.get(0).getCars());
		System.out.println(cars.get(0));

		CarCategory carCat = new CarCategory();
		carCat.setCar(cars.get(0));
		carCat.setCategory(categories.get(3));
		carCatRepo.save(carCat);
		System.out.println(cars.get(0).getCarCategories());
		
		
//		model.addAttribute("cars", cars);
		return "cars";
	}
}	
