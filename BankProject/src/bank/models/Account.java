package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.TimeStatementAnnotation;

import bank.models.users.User;
import privacyModel.Purpose;
import privacyModel.TimePreposition;

public class Account {
	public Account(User employee, String userId, String whomId, Date start, Purpose purpose, String consentId,
			String logId) {
		super();
		this.employee = employee;
		this.userId = userId;
		this.whomId = whomId;
		this.start = start;
		this.purpose = purpose;
		this.consentId = consentId;
		this.logId = logId;
	}
	User employee;
	String userId; 
	String whomId; 
	@TimeStatementAnnotation(preposition = TimePreposition.AT) Date start; 
	privacyModel.Purpose purpose; 
	String consentId; 
	String logId;
	public User getEmployee() {
		return employee;
	}
	public void setEmployee(User employee) {
		this.employee = employee;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWhomId() {
		return whomId;
	}
	public void setWhomId(String whomId) {
		this.whomId = whomId;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public privacyModel.Purpose getPurpose() {
		return purpose;
	}
	public void setPurpose(privacyModel.Purpose purpose) {
		this.purpose = purpose;
	}
	public String getConsentId() {
		return consentId;
	}
	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
}
