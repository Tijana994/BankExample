package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.WithdrawAnnotation;

@WithdrawAnnotation(id="name", when = "time", reason = "reason")
public class Withdraw {

	private String name;
	private Date time;
	private String reason;
	
	public Withdraw()
	{
		setName("test");
		setTime(new Date());
		setReason("something");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}