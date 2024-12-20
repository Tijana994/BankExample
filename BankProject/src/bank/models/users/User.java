package bank.models.users;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username")
public class User {

	private String username;

	public User(String username){
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}