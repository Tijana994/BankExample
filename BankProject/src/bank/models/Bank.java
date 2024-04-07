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

	@CreatePolicyStatementAnnotation(who = "admin", whoseId ="userId", whomId = Constants.Empty, 
			why ="purpose", when = "start", actions = {Action.STORE, Action.COLLECTING}, datas = {}, howConsentId = "consentId")
	public Log collectingDocuments(User admin, String userId, Date start, Purpose purpose, String consentId)
	{
		var log = new Log();
		log.setName("creating documents for " + admin.getUsername());
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
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.IN, type = PrincipalType.NATURAL_PERSON)
	public User createEmployee(String username, LocalDate birthday)
	{
		var date = java.util.Date.from(birthday.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		return new User(username, date);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.IN, type = PrincipalType.LEGAL_ENTITY)
	public User createLegalEntity(String username, ArrayList<User> employees, Location located)
	{ 
		var localDate = LocalDate.of(2019, 10, 10);
		var date = java.util.Date.from(localDate.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		return new User(username, date, employees, located);
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
