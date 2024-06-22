package bank.managers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import com.security.model.validation.annotations.creators.CreateConsentAnnotation;
import com.security.model.validation.annotations.creators.CreateDocumentAnnotation;

import bank.models.Document;
import bank.models.users.User;
import privacyModel.ConsentFormat;
import privacyModel.ConsentType;
import privacyModel.DocumentType;

public class DocumentManager {
	
	@CreateDocumentAnnotation(documentType = DocumentType.CHILD_CUSTODY)
	public Document createChildCustodyDocument(String documentId, User createdBy, String physicalLocation)
	{
		return new Document(documentId, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
	
	@CreateConsentAnnotation(consentFormat = ConsentFormat.WRITTEN, consentType = ConsentType.EXPLICIT)
	public Document createConsentDocument(String documentId, User createdBy, String physicalLocation)
	{
		return new Document(documentId, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
	
	@CreateDocumentAnnotation(documentType = DocumentType.TRANSFER_CERTIFICATE)
	public Document createTransferDocument(String documentId, User createdBy, String physicalLocation)
	{
		return new Document(documentId, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
	
	@CreateDocumentAnnotation(documentType = DocumentType.COURT_APPROVAL)
	public Document createCourtApproval(String documentId, User createdBy, String physicalLocation)
	{
		return new Document(documentId, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
}
