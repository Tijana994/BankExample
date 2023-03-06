package bank.models;

import com.security.model.validation.annotations.PolicyStatementAnnotation;

@PolicyStatementAnnotation(id = "name")
public class Log {

	private String name;
	public Log()
	{
		setName("Some generic name");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}