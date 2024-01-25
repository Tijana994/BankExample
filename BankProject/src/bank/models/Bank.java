package bank.models;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import com.security.model.validation.annotations.TimeStatementAnnotation;
import com.security.model.validation.annotations.creators.CreateComplaintBasedOnActionAnnotation;
import com.security.model.validation.annotations.creators.CreateComplaintBasedOnDataAnnotation;
import com.security.model.validation.annotations.creators.CreateConsentAnnotation;
import com.security.model.validation.annotations.creators.CreateDocumentAnnotation;
import com.security.model.validation.annotations.creators.CreateLocationAnnotation;
import com.security.model.validation.annotations.creators.CreatePolicyStatementAnnotation;
import com.security.model.validation.annotations.creators.CreatePrincipalAnnotation;
import com.security.model.validation.annotations.enums.Constants;

import privacyModel.Action;
import privacyModel.ComplaintBasedOnDataType;
import privacyModel.ConsentFormat;
import privacyModel.ConsentType;
import privacyModel.DocumentType;
import privacyModel.LocationType;
import privacyModel.PrincipalScope;
import privacyModel.PrincipalType;
import privacyModel.TimePreposition;

public class Bank {

	@CreateLocationAnnotation(locationType = LocationType.REGION)
	public City createCity(String name, String parentId)
	{
		var l =  new City();
		l.setName(name);
		l.setParentId(parentId);
		return l;
	}

	@CreatePolicyStatementAnnotation(who = "admin", whose ="user", whom = Constants.Empty, why ="purpose", when = "start", actions = {Action.STORE, Action.COLLECTING}, datas = {})
	public Log collectingDocuments()
	{
		admin = "City bank";
		user = "Pera";
		var log = new Log();
		log.setName("creating documents for " + user);
		purpose = new Purpose();
		purpose.Text = "Neki random tekst";
		purpose.SubPurposes = new ArrayList<Purpose>();
		var sub = new Purpose();
		sub.Text = "whatever";
		purpose.SubPurposes.add(sub);
		start = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
		//createChildCustodyDocument("Child custody test");
		//createConsentDocument("consent for Tommo");
		return log;
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.IN, type = PrincipalType.NATURAL_PERSON)
	public User createUser(String username)
	{ 
		var date = new Date(2019,10,10);
		var kids = new ArrayList<User>();
		kids.add(new User("Tommo", date));
		kids.add(new User("vepar", date));
		kids.add(new User("Pera", date));
		return new User(username, date, kids);
	}
	
	@CreatePrincipalAnnotation(scope = PrincipalScope.IN, type = PrincipalType.LEGAL_ENTITY)
	public User createLegalEntity(String username)
	{ 
		var date = new Date(2019,10,10);
		return new User(username, date);
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
	
	public String admin;
	public String user;
	public Purpose purpose;
	@TimeStatementAnnotation(preposition = TimePreposition.AT)
	public Date start;
}
