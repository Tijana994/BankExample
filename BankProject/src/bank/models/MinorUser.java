package bank.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username", birthday = "birthday", responsiblePersons = "responsiblePersons" )
public class MinorUser {

	public MinorUser(String username, Date birthday, List<User> responsiblePersons) {
		super();
		this.username = username;
		this.birthday = birthday;
		this.responsiblePersons = responsiblePersons;
	}
	public String getUsername() {
		return username;
	}
	public Date getBirthday() {
		return birthday;
	}
	public List<User> getResponsiblePersons() {
		return responsiblePersons;
	}
	private String username;
	private Date birthday;
	private List<User> responsiblePersons = new ArrayList<User>();
}
