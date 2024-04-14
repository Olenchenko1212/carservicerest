package ua.foxminded.carservicerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.foxminded.carservicerest.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
