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
@Table(name = "cars", schema = "carservicerest")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "car_code")
	private String carCode;

	@Column(name = "make")
	private String make;

	@Column(name = "model")
	private String model;

	@Column(name = "year")
	private int year;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "carservicerest.car_category",
//				joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
//				inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
//	private List<Category> categories = new ArrayList<>();
	
	@OneToMany(mappedBy = "car")
	private List<CarCategory> carCategories = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private List<Catter> catters = new ArrayList<>();

	public Car() {
	}

	public Car(Long id, String carCode, String make, String model, int year, List<CarCategory> carCategories,
			List<Catter> catters) {
		this.id = id;
		this.carCode = carCode;
		this.make = make;
		this.model = model;
		this.year = year;
		this.carCategories = carCategories;
		this.catters = catters;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", carCode=" + carCode.trim() + ", make=" + make.trim() + ", model=" + model.trim()
				+ ", year=" + year + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carCode == null) ? 0 : carCode.hashCode());
//		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
		result = prime * result + ((catters == null) ? 0 : catters.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + year;
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
		Car other = (Car) obj;
		if (carCode == null) {
			if (other.carCode != null) {
				return false;
			}
		} else if (!carCode.equals(other.carCode)) {
			return false;
		}
//		if (categories == null) {
//			if (other.categories != null) {
//				return false;
//			}
//		} else if (!categories.equals(other.categories)) {
//			return false;
//		}
		if (catters == null) {
			if (other.catters != null) {
				return false;
			}
		} else if (!catters.equals(other.catters)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (make == null) {
			if (other.make != null) {
				return false;
			}
		} else if (!make.equals(other.make)) {
			return false;
		}
		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
			return false;
		}
		if (year != other.year) {
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

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

//	public List<Category> getCategories() {
//		return categories;
//	}
//
//	public void setCategories(List<Category> categories) {
//		this.categories = categories;
//	}

	public List<Catter> getCatters() {
		return catters;
	}

	public void setCatters(List<Catter> catters) {
		this.catters = catters;
	}

	public List<CarCategory> getCarCategories() {
		return carCategories;
	}

	public void setCarCategories(List<CarCategory> carCategories) {
		this.carCategories = carCategories;
	}
	
//	public void addCategory(Category category) {
//		categories.add(category);
//		category.getCars().add(this);
//	}
}
