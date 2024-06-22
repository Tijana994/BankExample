package bank.managers;

import com.security.model.validation.annotations.creators.CreateLocationAnnotation;

import bank.models.Location;
import privacyModel.LocationType;

public class LocationManager {
	
	@CreateLocationAnnotation(locationType = LocationType.REGION)
	public Location createCity(String name, Location region)
	{
		return  new Location(name, region);
	}
	
	@CreateLocationAnnotation(locationType = LocationType.COUNTRY, isEUMember = true)
	public Location createCountry(String name)
	{
		return  new Location(name);
	}
	
	@CreateLocationAnnotation(locationType = LocationType.COUNTRY, isEUMember = false, legalAgeLimit = 18)
	public Location createNonEUCountry(String name)
	{
		return  new Location(name);
	}

}
