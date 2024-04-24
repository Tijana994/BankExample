package bank.models;

import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.TimeStatementAnnotation;
import com.security.model.validation.annotations.creators.CreatePolicyStatementAnnotation;
import com.security.model.validation.annotations.enums.Constants;

import bank.managers.*;
import privacyModel.Action;
import privacyModel.TimePreposition;

public class Bank {
	
	private UserManager userManager;
	private DocumentManager documentManager;
	private ComplaintManager complaintManager;
	
	public Bank()
	{
		this.userManager = new UserManager();
		this.documentManager = new DocumentManager();
		this.complaintManager = new ComplaintManager();
	}

	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = Constants.Empty, 
			why ="purpose", when = "start", actions = {Action.STORE, Action.COLLECTING}, datas = {"name" , "email", "account number", "identity number"}, 
			howConsentId = "consentId")
	public Log openAccount(User employee, String userId, Date start, Purpose purpose, String consentId)
	{
		var log = new Log();
		log.setName("Open account for " + userId);
		this.start = start;
		//createChildCustodyDocument("Child custody test");
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = Constants.Empty, 
			why ="purpose", when = "start", actions = {Action.ACCESS}, datas = {"account number"})
	public Log checkAccount(User employee, String userId, Date start, Purpose purpose)
	{
		var log = new Log();
		log.setName("Check account for " + userId);
		return log;
	}
	
	@CreatePolicyStatementAnnotation(who = "employee", whoseId ="userId", whomId = "bank",
			why ="purpose", when = "start, end", actions = {Action.TRANSFER}, datas = {"name" , "email", "account number", "identity number"})
	public Log transferAccount(User employee, String userId, User bank, Date start, Date end, Purpose purpose, Location location)
	{
		var log = new Log();
		log.setName("Transfer account for " + userId);
		return log;
	}

	public Purpose createPurpose(String text, List<Purpose> subpurposes, int reason, int subtype) {
		var purpose = new Purpose(text, subpurposes,reason,subtype);
		return purpose;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}

	public DocumentManager getDocumentManager() {
		return documentManager;
	}

	public ComplaintManager getComplaintManager() {
		return complaintManager;
	}

	@TimeStatementAnnotation(preposition = TimePreposition.AT)
	public Date start;
}
