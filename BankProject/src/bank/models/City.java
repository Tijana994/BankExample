package bank.models;

import com.security.model.validation.annotations.LocationAnnotation;

@LocationAnnotation(id = "name")
public class City {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}