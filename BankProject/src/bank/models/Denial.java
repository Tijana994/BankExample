package bank.models;

import java.util.Date;

//import annotation.DenialAnnotation;

//@DenialAnnotation(id = "name", when="date", reason="reason")
public class Denial {

	private String name;
	private Date date;
	private String reason;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}