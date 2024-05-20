package ua.foxminded.carservicerest.model;

import java.util.List;

public record CarDto(
		String carCode,
		String make,
		String model,
		int year,
		List<Category> categories
		) {

}
