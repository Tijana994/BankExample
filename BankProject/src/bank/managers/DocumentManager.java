package bank.managers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import com.security.model.validation.annotations.creators.CreateConsentAnnotation;
import com.security.model.validation.annotations.creators.CreateContractAnnotation;
import com.security.model.validation.annotations.creators.CreateDocumentAnnotation;
import com.security.model.validation.annotations.enums.ParametersObjectsLocation;
import com.security.model.validation.annotations.updaters.UpdateConsentAnnotation;

import bank.models.Document;
import privacyModel.ConsentFormat;
import privacyModel.ConsentType;
import privacyModel.DocumentType;

public class DocumentManager {
	
	@CreateDocumentAnnotation(documentType = DocumentType.CHILD_CUSTODY)
	public Document createChildCustodyDocument(String documentId, String description, String createdBy, String physicalLocation)
	{
		return new Document(documentId, description, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
	
	@CreateConsentAnnotation(consentFormat = ConsentFormat.WRITTEN, consentType = ConsentType.EXPLICIT)
	public Document createConsentDocument(String documentId, String description, String createdBy, String physicalLocation)
	{
		return new Document(documentId, description, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
	
	@CreateConsentAnnotation(consentFormat = ConsentFormat.WRITTEN, consentType = ConsentType.EXPLICIT, providedById = "providedBy")
	public Document createConsentDocument(String documentId, String description, String createdBy, String physicalLocation, String providedBy)
	{
		return new Document(documentId, description, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
	
	@CreateDocumentAnnotation(documentType = DocumentType.TRANSFER_CERTIFICATE)
	public Document createTransferDocument(String documentId, String description, String createdBy, String physicalLocation)
	{
		return new Document(documentId, description, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
	
	@CreateDocumentAnnotation(documentType = DocumentType.COURT_APPROVAL)
	public Document createCourtApproval(String documentId, String description, String createdBy, String physicalLocation)
	{
		return new Document(documentId, description, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
	
	@CreateDocumentAnnotation(documentType = DocumentType.LEGAL_PROVISION)
	public Document createLegalProvision(String documentId, String description, String createdBy, String physicalLocation)
	{
		return new Document(documentId, description, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
	
	@CreateContractAnnotation(providedById = "providedBy")
	public Document createContractDocument(String documentId, String description, String createdBy, String physicalLocation, String providedBy)
	{
		return new Document(documentId, description, physicalLocation, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), createdBy);
	}
	
	@UpdateConsentAnnotation(terminationDate = "endDate", consentId ="documentId",
			parametersLocation = ParametersObjectsLocation.PropertyInParameterObject, propertyObjectName = "consent")
	public void updateConsent(Document consent) {
		// TODO Auto-generated method stub
		System.out.println("updateConsent");
		
	}
}
