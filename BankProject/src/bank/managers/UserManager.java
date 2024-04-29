package bank.managers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import com.security.model.validation.annotations.creators.CreatePrincipalAnnotation;

import bank.models.Location;
import bank.models.User;
import privacyModel.PrincipalScope;
import privacyModel.PrincipalType;

public class UserManager {
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.IN, type = PrincipalType.NATURAL_PERSON, shouldSetBirtday = true)
	public User createEmployee(String username, LocalDate birthday)
	{
		var date = java.util.Date.from(birthday.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		return new User(username, date);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.OUT, type = PrincipalType.NATURAL_PERSON, isLegalAge = true)
	public User createCustomer(String username)
	{
		return new User(username);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.OUT, type = PrincipalType.NATURAL_PERSON)
	public User createMinorCustomer(String username, LocalDate birthday)
	{
		var date = java.util.Date.from(birthday.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		return new User(username, date);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.IN, type = PrincipalType.LEGAL_ENTITY, shouldSetLocation = true)
	public User createLegalEntity(String username, ArrayList<User> employees, Location located)
	{ 
		return new User(username, employees, located);
	}
}