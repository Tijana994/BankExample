package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username", birthday = "rodjendan")
public class User {

	private String username;
	public User(String username){
		this.setUsername(username);
	}
	public User(String username, Date rodjedan){
		this.setUsername(username);
		this.setRodjedan(rodjedan);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private Date rodjendan;
	public Date getRodjendan() {
		return rodjendan;
	}
	public void setRodjedan(Date rodjendan) {
		this.rodjendan = rodjendan;
	}
}