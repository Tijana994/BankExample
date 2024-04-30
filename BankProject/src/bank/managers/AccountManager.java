package bank.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.TimeStatementAnnotation;
import com.security.model.validation.annotations.creators.CreatePolicyStatementAnnotation;
import com.security.model.validation.annotations.enums.Constants;

import bank.models.Location;
import bank.models.Log;
import bank.models.Purpose;
import bank.models.User;
import privacyModel.Action;
import privacyModel.TimePreposition;

public class AccountManager {

	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = Constants.Empty, 
			why ="purpose", when = "start", actions = {Action.STORE, Action.COLLECTING}, datas = {"name" , "email", "account number", "identity number"}, 
			howConsentId = "consentId", howDocumentsIds = "custodyocumentId")
	public Log openAccount(User employee, String userId,@TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, 
			Purpose purpose, String consentId, ArrayList<String> custodyocumentId)
	{
		var log = new Log();
		log.setName("Open account for " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = Constants.Empty, 
			why ="purpose", when = "start", actions = {Action.ACCESS}, datas = {"account number"})
	public Log checkAccount(User employee, String userId,
			@TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, Purpose purpose)
	{
		var log = new Log();
		log.setName("Check account for " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = Constants.Empty, 
			why ="purpose", when = "start", actions = {Action.RECTIFICATION}, datas = {"email"}, causedById = "complaintId")
	public Log rectificationOfData(User employee, String userId,
			@TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, Purpose purpose, String complaintId)
	{
		var log = new Log();
		log.setName("Data rectification for " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whom = "bank",
			why ="purpose", when = "start,end", actions = {Action.TRANSFER}, 
			howConsentId = "consentId", howDocumentsIds = "transferDocumentId", whereSource = "locationSource", 
			whereDestination = "locationDestination", datas = {"name" , "email", "account number", "identity number"})
	public Log transferAccount(User employee, String userId, User bank,
			@TimeStatementAnnotation(preposition = TimePreposition.FROM) Date start,
			@TimeStatementAnnotation(preposition = TimePreposition.TO) Date end, Purpose purpose, 
			Location locationSource, Location locationDestination, String consentId, ArrayList<String> transferDocumentId)
	{
		var log = new Log();
		log.setName("Transfer account for " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = Constants.Empty, 
			why ="purpose", when = "start", actions = {Action.ACCESS}, datas = {"email"})
	public Log emailSendingForCard(User employee, String userId,
			@TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, Purpose purpose)
	{
		var log = new Log();
		log.setName("Email sending for new credit card to " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = Constants.Empty, 
			why ="purpose", when = "start", actions = {Action.STOP_PROCESSING}, datas = {"email"}, causedById = "complaintId")
	public Log stopSendingEmails(User employee, String userId,
			@TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, Purpose purpose, String complaintId)
	{
		var log = new Log();
		log.setName("Stop sending mails to " + userId);
		return log;
	}

	public Purpose createPurpose(String text, List<Purpose> subpurposes, int reason, int subtype) {
		var purpose = new Purpose(text, subpurposes,reason,subtype);
		return purpose;
	}
}
