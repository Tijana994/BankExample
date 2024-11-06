package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.PaperAnnotation;

import bank.models.users.User;

@PaperAnnotation(id="documentId", startDate="startDate",location="physicalLocation", providedBy ="createdBy")
public class Document {

	public Document(String documentId, String physicalLocation, Date startDate, User createdBy) {
		this.documentId = documentId;
		this.physicalLocation = physicalLocation;
		this.startDate = startDate;
		this.createdBy = createdBy;
	}
	
	private String documentId;
	private String physicalLocation;
	private Date startDate;
	private Date endDate;
	private User createdBy;
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getPhysicalLocation() {
		return physicalLocation;
	}
	public void setPhysicalLocation(String physicalLocation) {
		this.physicalLocation = physicalLocation;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
