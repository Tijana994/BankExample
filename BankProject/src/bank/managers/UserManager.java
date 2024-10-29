package bank.managers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.security.model.validation.annotations.creators.CreatePrincipalAnnotation;

import bank.models.Location;
import bank.models.users.LegalEntity;
import bank.models.users.Client;
import bank.models.users.Employee;
import bank.models.users.MinorClient;
import bank.models.users.User;
import privacyModel.PrincipalScope;
import privacyModel.PrincipalType;

public class UserManager {
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.IN, type = PrincipalType.NATURAL_PERSON, isLegalAge = true)
	public Employee createEmployee(String username)
	{
		return new Employee(username);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.OUT, type = PrincipalType.NATURAL_PERSON, isLegalAge = true)
	public Client createClient(String username)
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
	public LegalEntity createLegalEntity(String username, ArrayList<User> employees, Location located)
	{ 
		return new LegalEntity(username, employees, located);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.OUT, type = PrincipalType.LEGAL_ENTITY)
	public LegalEntity createLegalEntityOut(String username)
	{ 
		return new LegalEntity(username);
	}
}
