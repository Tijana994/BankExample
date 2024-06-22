package bank.models;

import java.util.ArrayList;
import java.util.List;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username", childrens = "subUsers", inhabits = "location")
public class User {

	private String username;
	private List<User> subUsers = new ArrayList<User>();
	private Location location = null;
	
	public User(String username){
		this.username = username;
	}
	public User(String username, List<User> subUsers, Location location){
		this(username,subUsers);
		this.location = location;
	}
	public User(String username, List<User> subUsers){
		this.username = username;
		this.subUsers = subUsers;
		
	}
	
	public String getUsername() {
		return username;
	}

	public List<User> getSubUsers() {
		return subUsers;
	}
	public Location getLocation() {
		return location;
	}
}