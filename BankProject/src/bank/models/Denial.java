package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.DenialAnnotation;

@DenialAnnotation(id="denialId", when="date", reason="explanation")
public class Denial {

	public Denial(String denialId, Date date, String explanation) {
		this.denialId = denialId;
		this.date = date;
		this.explanation = explanation;
	}
	private String denialId;
	private Date date;
	private String explanation;
	public String getDenialId() {
		return denialId;
	}
	public void setDenialId(String denialId) {
		this.denialId = denialId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}