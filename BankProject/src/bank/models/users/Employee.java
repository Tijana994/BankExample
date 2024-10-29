package bank.models.users;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username")
public class Employee extends User{
	
	public Employee(String username) {
		super(username);
	}
}
