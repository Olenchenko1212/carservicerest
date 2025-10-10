package ua.foxminded.carservicerest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import ua.foxminded.carservicerest.model.Car;
import ua.foxminded.carservicerest.model.CarDto;
import ua.foxminded.carservicerest.model.SearchCriteria;
import ua.foxminded.carservicerest.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void findAnyCar_delegatesToRepository() {
        when(carRepository.findAnyCar()).thenReturn(true);

        boolean result = carService.findAnyCar();

        assertTrue(result);
        verify(carRepository, times(1)).findAnyCar();
    }

    @Test
    void findCarsSpec_callsRepositoryWithSpecAndPageRequest() {
        SearchCriteria searchCriteria = new SearchCriteria("Ford", "Ranger", 2015, 2020, "Pickup");
        PageRequest pageRequest = PageRequest.of(0, 10);

        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        cars.add(new Car());
        Page<Car> page = new PageImpl<>(cars);
        when(carRepository.findAll(any(Specification.class), eq(pageRequest))).thenReturn(page);

        Page<Car> result = carService.findCarsSpec(pageRequest, searchCriteria);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(carRepository, times(1)).findAll(any(Specification.class), eq(pageRequest));
    }

    @Test
    void saveCar_mapsFieldsAndSavesAndReturnsEntity() {
        CarDto dto = new CarDto("ABC123", "Ford", "Ranger", 2020, List.of());

        when(carRepository.save(any(Car.class))).thenAnswer(invocation -> {
            Car toSave = invocation.getArgument(0);
            toSave.setId(42L);
            return toSave;
        });

        Car saved = carService.saveCar(dto);

        assertNotNull(saved);
        assertEquals(42L, saved.getId());
        assertEquals("ABC123", saved.getCarCode());
        assertEquals("Ford", saved.getMake());
        assertEquals("Ranger", saved.getModel());
        assertEquals(2020, saved.getYear());
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    void updateCar_whenFound_updatesEntityFields() {
        Car existing = new Car();
        existing.setId(1L);
        existing.setCarCode("OLD");
        existing.setMake("OldMake");
        existing.setModel("OldModel");
        existing.setYear(1990);
        existing.setCategories(List.of());

        when(carRepository.findById(1L)).thenReturn(Optional.of(existing));

        CarDto dto = new CarDto("NEWCODE", "NewMake", "NewModel", 2021, List.of());
        carService.updateCar(1L, dto);

        assertEquals("NEWCODE", existing.getCarCode());
        assertEquals("NewMake", existing.getMake());
        assertEquals("NewModel", existing.getModel());
        assertEquals(2021, existing.getYear());
        verify(carRepository, never()).save(any(Car.class));
    }

    @Test
    void updateCar_whenNotFound_throwsNoSuchElementException() {
        when(carRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> carService.updateCar(99L, new CarDto("A","B","C", 2000, List.of())));
    }

    @Test
    void deleteCar_whenFound_deletesById() {
        Car existing = new Car();
        existing.setId(5L);
        when(carRepository.findById(5L)).thenReturn(Optional.of(existing));

        carService.deleteCar(5L);

        verify(carRepository, times(1)).deleteById(5L);
    }

    @Test
    void deleteCar_whenNotFound_throwsNoSuchElementException() {
        when(carRepository.findById(7L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> carService.deleteCar(7L));
        verify(carRepository, never()).deleteById(anyLong());
    }
}
