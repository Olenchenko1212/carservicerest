package ua.foxminded.carservicerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.foxminded.carservicerest.model.CarCategory;

@Repository
public interface CarCategoryRepository extends JpaRepository<CarCategory, Long> {

}
