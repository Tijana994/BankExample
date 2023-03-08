package bank.models;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username")
public class User {

	private String username;
	public User(String username){
		this.setUsername(username);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}