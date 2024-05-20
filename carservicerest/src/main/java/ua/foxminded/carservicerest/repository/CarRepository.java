package ua.foxminded.carservicerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ua.foxminded.carservicerest.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, PagingAndSortingRepository<Car, Long>, JpaSpecificationExecutor<Car> {
	@Query(value = "select exists(select 1 from carservicerest.cars) as Output;", nativeQuery = true)
	boolean findAnyCar();
}
