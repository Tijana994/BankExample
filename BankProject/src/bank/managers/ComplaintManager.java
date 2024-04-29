package bank.managers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.creators.CreateComplaintBasedOnActionAnnotation;
import com.security.model.validation.annotations.creators.CreateComplaintBasedOnDataAnnotation;

import bank.models.Complaint;
import privacyModel.ComplaintBasedOnDataType;

public class ComplaintManager {

	@CreateComplaintBasedOnDataAnnotation(type = ComplaintBasedOnDataType.RECTIFICATION, subjectsIds = "datalist")
	public Complaint createComplaintOnData(String name, String reason, List<String> datalist)
	{
		var complaint = new Complaint();
		complaint.setName(name);
		complaint.setReason(reason);
		complaint.setTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		return complaint;
	}
	
	@CreateComplaintBasedOnActionAnnotation()
	public Complaint createComplaintOnAction()
	{
		var complaint = new Complaint();
		complaint.setName("Name text");
		complaint.setReason("Reason text");
		return complaint;
	}
}
