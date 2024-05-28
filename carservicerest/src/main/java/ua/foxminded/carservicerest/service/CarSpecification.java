package ua.foxminded.carservicerest.util;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ua.foxminded.carservicerest.model.Car;

public class CarSpecification {
	
	private CarSpecification() {
		throw new IllegalStateException("Utility class");
	}

	public static Specification<Car> filterByMake(String keyword) {
		return StringUtils.hasText(keyword)
				? (Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> criteriaBuilder
						.like(criteriaBuilder.lower(root.get("make")), "%" + keyword.toLowerCase() + "%")
				: null;
	}

	public static Specification<Car> filterByModel(String keyword) {
		return StringUtils.hasText(keyword)
				? (Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> criteriaBuilder
						.like(criteriaBuilder.lower(root.get("model")), "%" + keyword.toLowerCase() + "%")
				: null;
	}

	public static Specification<Car> filterByMinYear(Integer minYear) {
		return minYear != null ? (Root<Car> root, CriteriaQuery<?> query,
				CriteriaBuilder criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("year"), minYear)
				: null;
	}

	public static Specification<Car> filterByMaxYear(Integer maxYear) {
		return maxYear != null ? (Root<Car> root, CriteriaQuery<?> query,
				CriteriaBuilder criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("year"), maxYear) : null;
	}

	public static Specification<Car> filterByCategory(String keyword) {
		return StringUtils.hasText(keyword)
				? (Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> criteriaBuilder
						.like(criteriaBuilder.lower((root.join("categories")).get("categoryName")),
						"%" + keyword.toLowerCase() + "%")
				: null;
	}
}
