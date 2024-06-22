package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username", birthday = "birthday", childrens = "subUsers", inhabits = "location")
public class Client extends User {
	
	private Date birthday;
	
	public Client(String username) {
		super(username);
	}

	public Client(String username, Date birthday) {
		super(username);
		this.birthday = birthday;
	}

	public Date getBirthday() {
		return birthday;
	}
}
