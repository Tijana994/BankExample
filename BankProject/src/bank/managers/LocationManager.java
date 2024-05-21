package bank.managers;

import com.security.model.validation.annotations.creators.CreateLocationAnnotation;

import bank.models.Location;
import privacyModel.LocationType;

public class LocationManager {
	
	@CreateLocationAnnotation(locationType = LocationType.REGION)
	public Location createCity(String name, String parentId)
	{
		var l =  new Location();
		l.setName(name);
		l.setParentId(parentId);
		return l;
	}
	
	@CreateLocationAnnotation(locationType = LocationType.COUNTRY, isEUMember = true)
	public Location createCountry(String name)
	{
		var l =  new Location();
		l.setName(name);
		return l;
	}
	
	@CreateLocationAnnotation(locationType = LocationType.COUNTRY, isEUMember = false, legalAgeLimit = 18)
	public Location createNonEUCountry(String name)
	{
		var l =  new Location();
		l.setName(name);
		return l;
	}

}
