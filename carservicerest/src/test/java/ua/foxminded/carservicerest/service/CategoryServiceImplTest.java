package ua.foxminded.carservicerest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.foxminded.carservicerest.model.Category;
import ua.foxminded.carservicerest.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void findByName_delegatesToRepository() {
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("Sedan");

        when(categoryRepository.findByCategoryName("Sedan")).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.findByName("Sedan");

        assertTrue(result.isPresent());
        assertEquals("Sedan", result.get().getCategoryName());
        verify(categoryRepository, times(1)).findByCategoryName("Sedan");
    }

    @Test
    void saveCategory_callsRepositorySave() {
        Category category = new Category();
        category.setId(2L);
        category.setCategoryName("SUV");

        categoryService.saveCategory(category);

        verify(categoryRepository, times(1)).save(category);
    }
}
