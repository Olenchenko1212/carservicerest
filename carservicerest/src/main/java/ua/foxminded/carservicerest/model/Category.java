package ua.foxminded.carservicerest.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "categories", schema = "carservicerest")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "category_name")
	private String categoryName;
	
//	@ManyToMany(
//			fetch = FetchType.LAZY,
//			cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
//			CascadeType.REFRESH })
//	@JoinTable(name = "carservicerest.car_category",
//				joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
//				inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
//	@ManyToMany( fetch = FetchType.LAZY, mappedBy = "categories")
//	private List<Car> cars = new ArrayList<>();
	
	@OneToMany(mappedBy = "category")
	private List<CarCategory> carCategories = new ArrayList<>();

	public Category(Long id, String categoryName, List<CarCategory> carCategories) {
		this.id = id;
		this.categoryName = categoryName;
		this.carCategories = carCategories;
	}

	public Category() {
	}

	public Category(Long id, String categoryName) {
		this.id = id;
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName.trim() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carCategories == null) ? 0 : carCategories.hashCode());
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
		if (carCategories == null) {
			if (other.carCategories != null) {
				return false;
			}
		} else if (!carCategories.equals(other.carCategories)) {
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

//	public List<Car> getCars() {
//		return cars;
//	}
//
//	public void setCars(List<Car> cars) {
//		this.cars = cars;
//	}
	
	public List<CarCategory> getCarCategory() {
		return carCategories;
	}

	public void setCarCategory(List<CarCategory> carCategories) {
		this.carCategories = carCategories;
	}

}

