package ua.foxminded.carservicerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.foxminded.carservicerest.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
