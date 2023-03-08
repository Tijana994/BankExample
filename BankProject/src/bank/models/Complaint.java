package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.creators.CreateWithdrawAnnotation;
import com.security.model.validation.annotations.enums.CreatedObjectLocation;

//@ComplaintAnnotation(id="name", reason = "reason", when = "time")
public class Complaint {

	private String name;
	private String reason;
	private Date time;
	private Withdraw withdraw;
	
	//@CreateDenialAnnotation()
	public Denial CreateDenial()
	{
		var denial = new Denial();
		denial.setName("testName");
		denial.setReason("something");
		denial.setDate(new Date());
		return denial;
	}
	
	@CreateWithdrawAnnotation(createdObjectLocation = CreatedObjectLocation.Property, name = "withdraw")
	public void CreateWithDraw()
	{
		withdraw = new Withdraw();
	}
	
	@CreateWithdrawAnnotation(createdObjectLocation = CreatedObjectLocation.Parameter, name = "withdraw")
	public void CreateWithDraw(Withdraw withdraw)
	{
		withdraw.setName("Testic");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Withdraw getWithdraw() {
		return withdraw;
	}
}
