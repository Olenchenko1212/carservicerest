package ua.foxminded.carservicerest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldSort {
	ID("id"),
	MAKE("make"),
	MODEL("model"),
	YEAR("year");
	
	private final String fieldSort;	
}
