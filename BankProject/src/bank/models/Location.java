package bank.models;

import com.security.model.validation.annotations.LocationAnnotation;

@LocationAnnotation(id = "name", parent ="region")
public class Location {
	
	public Location(String name){
		this.name = name;
	}

	public Location(String name, Location region) {
		this(name);
		this.region = region;
	}

	private String name;
	private Location region;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getRegion() {
		return region;
	}

	public void setRegion(Location region) {
		this.region = region;
	}
}