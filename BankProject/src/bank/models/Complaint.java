package bank.models;

import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.ComplaintAnnotation;
import com.security.model.validation.annotations.creators.CreateDenialAnnotation;

@ComplaintAnnotation(id="name", reason = "reason", when = "time", whoId = "userId")
public class Complaint {

	private String name;
	private String reason;
	private Date time;
	private String userId;
	
	@CreateDenialAnnotation(forComplaintId = "complaintId", approvedById = "approvedById", basedOnStatemetsIds = "tasks")
	public Denial createDenial(String name, String reason, String complaintId, String approvedById, List<String> tasks)
	{
		var denial = new Denial();
		denial.setName(name);
		denial.setReason(reason);
		denial.setDate(new Date());
		return denial;
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
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
