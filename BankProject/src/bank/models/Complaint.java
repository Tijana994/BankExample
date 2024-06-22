package bank.models;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import com.security.model.validation.annotations.ComplaintAnnotation;
import com.security.model.validation.annotations.creators.CreateDenialAnnotation;

import bank.models.users.User;

@ComplaintAnnotation(id="complaintId", reason = "explanation", when = "time", who = "client")
public class Complaint {

	public Complaint(String complaintId, String explanation, Date time, User client) {
		this.complaintId = complaintId;
		this.explanation = explanation;
		this.time = time;
		this.client = client;
	}

	private String complaintId;
	private String explanation;
	private Date time;
	private User client;
	
	@CreateDenialAnnotation(forComplaintId = "complaintId", approvedById = "approvedById", basedOnStatementId = "task")
	public Denial createDenial(String denialId, String explanation, String complaintId, String approvedById, String task)
	{
		return new Denial(denialId, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), explanation);
	}

	public String getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
}
