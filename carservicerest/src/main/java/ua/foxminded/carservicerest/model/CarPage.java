package ua.foxminded.carservicerest.model;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarPage {
	
	private int pageNumber;
	private int pageSize;
	private String sortBy;
//	private String [] sortBy = {"make", "model", "year"};
	private Sort.Direction sortDerection = Direction.ASC;
	
}
