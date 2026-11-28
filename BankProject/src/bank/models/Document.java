package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.PaperAnnotation;

@PaperAnnotation(id="documentId", startDate="startDate", location="physicalLocation", description="description", providedById="createdBy")
public class Document {

	public Document(String documentId, String physicalLocation, Date startDate, String createdBy) {
		this.documentId = documentId;
		this.physicalLocation = physicalLocation;
		this.startDate = startDate;
		this.createdBy = createdBy;
	}
	
	private String documentId;
	private String description;
	private String physicalLocation;
	private Date startDate;

	private String createdBy;
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
