package ua.foxminded.carservicerest.model;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CarDto(
		@NotNull
		@Size(min = 1, max = 15, message = "carCode : the size must be within the range of 1 to 15 symbols")
		String carCode,
		
		@NotNull
		@Size(min = 1, max = 30, message = "make : the size must be within the range of 1 to 30 symbols")
		String make,
		
		@NotNull
		@Size(min = 1, max = 30, message = "model : the size must be within the range of 1 to 30 symbols")
		String model,
		
		@NotNull
		@Min(value=1900, message = "year : positive number, min 1900 is required")
		int year,
		
		List<Category> categories
		) {
	
}
