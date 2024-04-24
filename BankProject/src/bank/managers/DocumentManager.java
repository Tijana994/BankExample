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
	private Document createChildCustodyDocument(String name)
	{
		Document document = new Document();
		document.setName(name);
		document.setLocation("somewhere");
		document.setStartDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		return document;
	}
	
	@CreateConsentAnnotation(consentFormat = ConsentFormat.WRITTEN, consentType = ConsentType.EXPLICIT)
	public Document createConsentDocument(String name, String createdByUsername)
	{
		Document document = new Document();
		document.setName(name);
		document.setLocation("somewhere");
		document.setStartDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		document.setCreatedBy(createdByUsername);
		return document;
	}
}
