package ua.foxminded.carservicerest.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.foxminded.carservicerest.model.Category;
import ua.foxminded.carservicerest.repository.CategoryRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public Optional<Category> findByName(String categoryName){
		
		return categoryRepository.findByCategoryName(categoryName);
	}
	
	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
		log.info("Save Category id = {}", category.getId());
	}
}
