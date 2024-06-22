package bank.managers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import com.security.model.validation.annotations.creators.CreateComplaintBasedOnActionAnnotation;
import com.security.model.validation.annotations.creators.CreateComplaintBasedOnDataAnnotation;
import com.security.model.validation.annotations.creators.CreateWithdrawAnnotation;

import bank.models.Complaint;
import bank.models.Withdraw;
import bank.models.users.User;
import privacyModel.ComplaintBasedOnDataType;

public class ComplaintManager {

	@CreateComplaintBasedOnDataAnnotation(type = ComplaintBasedOnDataType.RECTIFICATION, subjectId = "data")
	public Complaint createComplaintOnDataForRectification(String complaintId, String explanation, String data, User client)
	{
		return new Complaint(complaintId, explanation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), client);
	}
	
	@CreateComplaintBasedOnDataAnnotation(type = ComplaintBasedOnDataType.ERASURE, subjectId = "data")
	public Complaint createComplaintOnDataForErasure(String complaintId, String explanation, String data, User client)
	{
		return new Complaint(complaintId, explanation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), client);
	}
	
	@CreateComplaintBasedOnActionAnnotation(policyStatementId = "actionId")
	public Complaint createComplaintOnAction(String complaintId, String explanation, String actionId, User client)
	{
		return new Complaint(complaintId, explanation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), client);
	}
	
	@CreateWithdrawAnnotation(consentId = "consentId")
	public Withdraw createComplaintWithWithdraw(String name, String reason, String consentId, String userId)
	{
		var withdraw = new Withdraw();
		withdraw.setName(name);
		withdraw.setReason(reason);
		withdraw.setTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		withdraw.setUserId(userId);
		return withdraw;
	}
}
