package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.PaperAnnotation;

@PaperAnnotation(id="name", startDate="startDate",location="location", providedById ="createdBy")
public class Document {

	private String name;
	private String location;
	private Date startDate;
	private String createdBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
