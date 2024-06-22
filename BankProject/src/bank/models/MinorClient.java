package bank.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username", birthday = "birthday", responsiblePersons = "responsiblePersons", childrens = "subUsers", inhabits = "location" )
public class MinorClient extends Client {

	public MinorClient(String username, Date birthday, List<User> responsiblePersons) {
		super(username, birthday);
		this.responsiblePersons = responsiblePersons;
	}

	public List<User> getResponsiblePersons() {
		return responsiblePersons;
	}

	private List<User> responsiblePersons = new ArrayList<User>();
}
