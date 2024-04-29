package bank.managers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import com.security.model.validation.annotations.creators.CreateConsentAnnotation;
import com.security.model.validation.annotations.creators.CreateDocumentAnnotation;

import bank.models.Document;
import privacyModel.ConsentFormat;
import privacyModel.ConsentType;
import privacyModel.DocumentType;

public class DocumentManager {
	
	@CreateDocumentAnnotation(documentType = DocumentType.CHILD_CUSTODY)
	public Document createChildCustodyDocument(String name, String createdByUsername, String location)
	{
		Document document = new Document();
		document.setName(name);
		document.setLocation(location);
		document.setStartDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		document.setCreatedBy(createdByUsername);
		return document;
	}
	
	@CreateConsentAnnotation(consentFormat = ConsentFormat.WRITTEN, consentType = ConsentType.EXPLICIT)
	public Document createConsentDocument(String name, String createdByUsername, String location)
	{
		Document document = new Document();
		document.setName(name);
		document.setLocation(location);
		document.setStartDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		document.setCreatedBy(createdByUsername);
		return document;
	}
	
	@CreateDocumentAnnotation(documentType = DocumentType.TRANSFER_CERTIFICATE)
	public Document createTransferDocument(String name, String createdByUsername, String location)
	{
		Document document = new Document();
		document.setName(name);
		document.setLocation(location);
		document.setStartDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		document.setCreatedBy(createdByUsername);
		return document;
	}
}
