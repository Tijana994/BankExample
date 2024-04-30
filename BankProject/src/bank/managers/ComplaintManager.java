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
	public Complaint createComplaintOnDataForRectification(String name, String reason, List<String> datalist, String userId)
	{
		var complaint = new Complaint();
		complaint.setName(name);
		complaint.setReason(reason);
		complaint.setTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		complaint.setUserId(userId);
		return complaint;
	}
	
	@CreateComplaintBasedOnDataAnnotation(type = ComplaintBasedOnDataType.ERASURE, subjectsIds = "datalist")
	public Complaint createComplaintOnDataForErasure(String name, String reason, List<String> datalist, String userId)
	{
		var complaint = new Complaint();
		complaint.setName(name);
		complaint.setReason(reason);
		complaint.setTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		complaint.setUserId(userId);
		return complaint;
	}
	
	@CreateComplaintBasedOnActionAnnotation(policyStatementId = "actionId")
	public Complaint createComplaintOnAction(String name, String reason, String actionId, String userId)
	{
		var complaint = new Complaint();
		complaint.setName(name);
		complaint.setReason(reason);
		complaint.setTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		complaint.setUserId(userId);
		return complaint;
	}
}
