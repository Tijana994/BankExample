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
	
	@CreateLocationAnnotation(locationType = LocationType.COUNTRY)
	public Location createCountry(String name)
	{
		var l =  new Location();
		l.setName(name);
		return l;
	}

}
