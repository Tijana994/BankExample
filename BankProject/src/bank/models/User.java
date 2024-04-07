package bank.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username", birthday = "rodjendan", childrens = "subUsers", inhabits = "location")
public class User {

	private String username;
	private List<User> subUsers = new ArrayList<User>();
	private Location location = null;
	public User(String username){
		this.setUsername(username);
	}
	public User(String username, Date rodjedan, List<User> subUsers, Location location){
		this(username, rodjedan, subUsers);
		this.setLocation(location);
	}
	public User(String username, Date rodjedan, List<User> subUsers){
		this(username, rodjedan);
		this.setSubUsers(subUsers);
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
	public List<User> getSubUsers() {
		return subUsers;
	}
	public void setSubUsers(List<User> subUsers) {
		this.subUsers = subUsers;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}