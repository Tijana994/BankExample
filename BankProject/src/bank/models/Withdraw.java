package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.WithdrawAnnotation;

import bank.models.users.User;

@WithdrawAnnotation(id="withdrawId", when = "time", reason = "explanation", who = "client")
public class Withdraw {
	public Withdraw(String withdrawId, Date time, String explanation, User client) {
		super();
		this.withdrawId = withdrawId;
		this.time = time;
		this.explanation = explanation;
		this.client = client;
	}
	private String withdrawId;
	private Date time;
	private String explanation;
	private User client;
	
	public String getWithdrawId() {
		return withdrawId;
	}
	public void setWithdrawId(String withdrawId) {
		this.withdrawId = withdrawId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
}