package bank.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username", birthday = "birthday", childrens = "subUsers", inhabits = "location")
public class User {

	private String username;
	private List<User> subUsers = new ArrayList<User>();
	private Location location = null;
	private Date birthday;
	
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
	public User(String username, Date rodjedan, List<User> subUsers){
		this(username, rodjedan);
		this.subUsers = subUsers;
	}
	public User(String username, Date rodjedan){
		this.username = username;
		this.birthday = rodjedan;
	}
	
	public String getUsername() {
		return username;
	}

	public Date getBirthday() {
		return birthday;
	}
	public List<User> getSubUsers() {
		return subUsers;
	}
	public Location getLocation() {
		return location;
	}
}