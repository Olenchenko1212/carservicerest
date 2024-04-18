package ua.foxminded.carservicerest.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories", schema = "carservicerest")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "category_name")
	private String categoryName;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
	private List<Car> cars = new ArrayList<>();
	
	public Category() {
	}

	public Category(Long id, String categoryName, List<Car> cars) {
		this.id = id;
		this.categoryName = categoryName;
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName.trim() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cars == null) ? 0 : cars.hashCode());
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Category other = (Category) obj;
		if (cars == null) {
			if (other.cars != null) {
				return false;
			}
		} else if (!cars.equals(other.cars)) {
			return false;
		}
		if (categoryName == null) {
			if (other.categoryName != null) {
				return false;
			}
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName.trim();
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName.trim();
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}

