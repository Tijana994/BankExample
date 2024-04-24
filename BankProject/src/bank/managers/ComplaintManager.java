package bank.managers;

import com.security.model.validation.annotations.creators.CreateComplaintBasedOnActionAnnotation;
import com.security.model.validation.annotations.creators.CreateComplaintBasedOnDataAnnotation;

import bank.models.Complaint;
import privacyModel.ComplaintBasedOnDataType;

public class ComplaintManager {

	@CreateComplaintBasedOnDataAnnotation(type = ComplaintBasedOnDataType.RECTIFICATION)
	public Complaint createComplaintOnData()
	{
		var complaint = new Complaint();
		complaint.setName("Name text");
		complaint.setReason("Reason text");
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
