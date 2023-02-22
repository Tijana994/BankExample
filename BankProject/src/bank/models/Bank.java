package bank.models;

import annotation.creators.CreateLocationAnnotation;
import annotation.enums.LocationType;
import aop.test.City;

public class Bank {

	@CreateLocationAnnotation(locationType = LocationType.REGION)
	public City createCity(String name)
	{
		var l =  new City();
		l.setName(name);
		return l;
	}

}
