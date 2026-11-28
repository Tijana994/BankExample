package bank.models.users;

import java.util.Date;

import com.security.model.validation.annotations.PrincipalAnnotation;

@PrincipalAnnotation(id = "username", birthday = "dateOfBirth")
public class Client extends User {
	
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	
	public Client(String username) {
		super(username);
	}

	public Client(String username, Date dateOfBirth) {
		super(username);
		this.dateOfBirth = dateOfBirth;
	}

	public Client(String firstName, String lastName, String username, Date dateOfBirth) {
		super(username);
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthday() {
		return dateOfBirth;
	}
}
