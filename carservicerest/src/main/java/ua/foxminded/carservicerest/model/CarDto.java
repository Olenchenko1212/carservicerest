package ua.foxminded.carservicerest.model;

public record CarDto(
		Long carId,
		String carCode,
		String make,
		String model,
		int year
		) {
}
