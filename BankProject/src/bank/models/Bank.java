package bank.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import com.security.model.validation.annotations.TimeStatementAnnotation;
import com.security.model.validation.annotations.creators.CreateComplaintBasedOnActionAnnotation;
import com.security.model.validation.annotations.creators.CreateComplaintBasedOnDataAnnotation;
import com.security.model.validation.annotations.creators.CreateConsentAnnotation;
import com.security.model.validation.annotations.creators.CreateDocumentAnnotation;
import com.security.model.validation.annotations.creators.CreatePolicyStatementAnnotation;
import com.security.model.validation.annotations.creators.CreatePrincipalAnnotation;
import com.security.model.validation.annotations.enums.Constants;

import privacyModel.Action;
import privacyModel.ComplaintBasedOnDataType;
import privacyModel.ConsentFormat;
import privacyModel.ConsentType;
import privacyModel.DocumentType;
import privacyModel.PrincipalScope;
import privacyModel.PrincipalType;
import privacyModel.TimePreposition;

public class Bank {

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

	public Purpose createPurpose() {
		var purpose = new Purpose();
		purpose.Text = "Neki random tekst";
		purpose.SubPurposes = new ArrayList<Purpose>();
		var sub = new Purpose();
		sub.Text = "whatever";
		purpose.SubPurposes.add(sub);
		return purpose;
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.IN, type = PrincipalType.NATURAL_PERSON, shouldSetBirtday = true)
	public User createEmployee(String username, LocalDate birthday)
	{
		var date = java.util.Date.from(birthday.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		return new User(username, date);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.OUT, type = PrincipalType.NATURAL_PERSON)
	public User createCustomer(String username)
	{
		return new User(username);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.IN, type = PrincipalType.LEGAL_ENTITY, shouldSetLocation = true)
	public User createLegalEntity(String username, ArrayList<User> employees, Location located)
	{ 
		return new User(username, employees, located);
	}
	
	@CreateDocumentAnnotation(documentType = DocumentType.CHILD_CUSTODY)
	private Document createChildCustodyDocument(String name)
	{
		Document document = new Document();
		document.setName(name);
		document.setLocation("somewhere");
		document.setStartDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		return document;
	}
	
	@CreateConsentAnnotation(consentFormat = ConsentFormat.WRITTEN, consentType = ConsentType.EXPLICIT)
	public Document createConsentDocument(String name)
	{
		Document document = new Document();
		document.setName(name);
		document.setLocation("somewhere");
		document.setStartDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		return document;
	}
	
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
	
	@TimeStatementAnnotation(preposition = TimePreposition.AT)
	public Date start;
}
