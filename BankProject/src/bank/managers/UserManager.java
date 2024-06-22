package bank.managers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.security.model.validation.annotations.creators.CreatePrincipalAnnotation;

import bank.models.Client;
import bank.models.Employee;
import bank.models.Location;
import bank.models.MinorClient;
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
		return new Employee(username, date);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.OUT, type = PrincipalType.NATURAL_PERSON, isLegalAge = true)
	public User createClient(String username)
	{
		return new Client(username);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.OUT, type = PrincipalType.NATURAL_PERSON, shouldSetBirtday = true)
	public MinorClient createMinorClient(String username, LocalDate birthday, List<User> responsiblePersons)
	{
		var date = java.util.Date.from(birthday.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		return new MinorClient(username, date, responsiblePersons);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.IN, type = PrincipalType.LEGAL_ENTITY, shouldSetLocation = true)
	public User createLegalEntity(String username, ArrayList<User> employees, Location located)
	{ 
		return new User(username, employees, located);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.OUT, type = PrincipalType.LEGAL_ENTITY)
	public User createLegalEntityOut(String username)
	{ 
		return new User(username);
	}
}
