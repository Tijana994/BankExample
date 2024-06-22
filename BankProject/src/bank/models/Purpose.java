package bank.models;

import java.util.List;

import com.security.model.validation.annotations.PurposeAnnotation;

@PurposeAnnotation(details = "text", subPurposes = "purposes", reason = "reasonId", reasonSubtype = "subTypeId")
public class Purpose {

	public Purpose(String text, List<Purpose> purposes, int reasonId, int subTypeId) {
		super();
		this.setText(text);
		this.purposes = purposes;
		this.reasonId = reasonId;
		this.subTypeId = subTypeId;
	}

	private String text;
	public List<Purpose> purposes;
	public int reasonId;
	public int subTypeId;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Purpose> getPurposes() {
		return purposes;
	}
	public void setPurposes(List<Purpose> purposes) {
		this.purposes = purposes;
	}
	public int getReasonId() {
		return reasonId;
	}
	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}
	public int getSubTypeId() {
		return subTypeId;
	}
	public void setSubTypeId(int subTypeId) {
		this.subTypeId = subTypeId;
	}
}
