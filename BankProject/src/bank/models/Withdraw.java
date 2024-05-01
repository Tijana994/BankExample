package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.WithdrawAnnotation;

@WithdrawAnnotation(id="name", when = "time", reason = "reason", whoId = "userId")
public class Withdraw {

	private String name;
	private Date time;
	private String reason;
	private String userId;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}