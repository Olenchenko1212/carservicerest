package ua.foxminded.carservicerest.service;

import java.util.Optional;

import ua.foxminded.carservicerest.model.Category;

public interface CategoryService {
	Optional<Category> findByName(String categoryName);
	
	void saveCategory(Category category);
}
