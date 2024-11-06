package bank.models.users;

import java.util.ArrayList;
import java.util.List;

import com.security.model.validation.annotations.PrincipalAnnotation;

import bank.models.Location;

@PrincipalAnnotation(id = "username", children = "subUsers", inhabits = "location")
public class LegalEntity extends User{
	
	private List<User> subUsers = new ArrayList<User>();
	private Location location = null;

	public LegalEntity(String username) {
		super(username);
	}
	
	public LegalEntity(String username, List<User> subUsers, Location location){
		this(username,subUsers);
		this.location = location;
	}
	public LegalEntity(String username, List<User> subUsers){
		this(username);
		this.subUsers = subUsers;
	}

	public List<User> getSubUsers() {
		return subUsers;
	}
	public Location getLocation() {
		return location;
	}

}
