package bank.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.TimeStatementAnnotation;
import com.security.model.validation.annotations.creators.CreatePolicyStatementAnnotation;

import bank.models.Location;
import bank.models.Log;
import bank.models.Purpose;
import bank.models.users.User;
import privacyModel.Action;
import privacyModel.TimePreposition;

public class AccountManager {

	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = "whomId",
			why ="purpose", when = "start", actions = {Action.STORE, Action.COLLECTING}, datas = {"name" , "email", "account number", "identity number"}, 
			howConsentId = "consentId")
	public Log openAccount(User employee, String userId, String whomId, @TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, 
			Purpose purpose, String consentId)
	{
		var log = new Log();
		log.setLogId("Open account for " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = "whomId",
			why ="purpose", when = "start", actions = {Action.STORE, Action.COLLECTING}, datas = {"name" , "email", "account number", "identity number"}, 
			howConsentId = "consentId", howDocumentId = "custodyDocumentId")
	public Log openAccountChild(User employee, String userId, String whomId, @TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, 
			Purpose purpose, String consentId, String custodyDocumentId)
	{
		var log = new Log();
		log.setLogId("Open account for " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId",
			why ="purpose", when = "start", actions = {Action.ACCESS}, datas = {"account number"})
	public Log checkAccount(User employee, String userId,
			@TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, Purpose purpose)
	{
		var log = new Log();
		log.setLogId("Check account for " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", 
			why ="purpose", when = "start", actions = {Action.RECTIFICATION}, datas = {"email"}, causedById = "complaintId")
	public Log rectificationOfData(User employee, String userId,
			@TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, Purpose purpose, String complaintId)
	{
		var log = new Log();
		log.setLogId("Data rectification for " + userId);
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
		log.setLogId("Transfer account for " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", 
			why ="purpose", when = "start", actions = {Action.ACCESS}, datas = {"email"})
	public Log emailSendingForCard(User employee, String userId,
			@TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, Purpose purpose)
	{
		var log = new Log();
		log.setLogId("Email sending for new credit card to " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = "policeStationId", howDocumentsIds = "documentId",
			why ="purpose", isPurposeObject = true, when = "start,to", actions = {Action.ACCESS}, datas = {"name" , "email", "account number", "identity number"}, systemActionId = "logId")
	public void policeInvestigation(User employee, String userId, String policeStationId, ArrayList<String> documentId,
			@TimeStatementAnnotation(preposition = TimePreposition.FROM) Date start, @TimeStatementAnnotation(preposition = TimePreposition.TO) Date to, privacyModel.Purpose purpose, String logId)
	{
		//TODO
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", 
			why ="purpose", when = "start", actions = {Action.STOP_PROCESSING}, datas = {"email"}, causedById = "complaintId")
	public Log stopSendingEmails(User employee, String userId,
			@TimeStatementAnnotation(preposition = TimePreposition.AT) Date start, Purpose purpose, String complaintId)
	{
		var log = new Log();
		log.setLogId("Stop sending mails to " + userId);
		return log;
	}

	public Purpose createPurpose(String text, List<Purpose> subpurposes, int reason, int subtype) {
		var purpose = new Purpose(text, subpurposes,reason,subtype);
		return purpose;
	}
}
