package ua.foxminded.carservicerest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
	
	private String make;
	private String model;
	private Integer minYear;
	private Integer maxYear;
	private String category;
}
