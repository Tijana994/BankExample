package bank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import bank.managers.LocationManager;
import bank.models.*;
import bank.models.users.User;
import privacyModel.DataType;
import privacyModel.ProcessingReason;
import privacyModel.ProcessingReasonSubtype;
import utility.PrivacyDataFactory.DataFactory;
import utility.ProjectConfiguration.Configuration;

public class BankProject {

	private BankUtility bank;
	
	public BankProject()
	{
		bank = new BankUtility();
	}
	
	public static void main(String[] args) {
		//configuration and set up
		setUpConfiguration();
		
		BankProject bankProject = new BankProject();
		var locationHelper = new LocationManager();
		var serbia = locationHelper.createNonEUCountry("Serbia");
		var city1 = locationHelper.createCity("Novi Sad", serbia);
		var hungary = locationHelper.createCountry("Hungary");
		var city2 = locationHelper.createCity("Budapest", hungary);
		var employees = new ArrayList<User>();
		var bankUser = bankProject.bank.getUserManager().createLegalEntity("Green bank", employees, city2);
		var ned = bankProject.bank.getUserManager().createEmployee("Ned - bank");
		DataFactory.addSubPrincipal("Ned - bank", "Green bank");
		var patti = bankProject.bank.getUserManager().createEmployee("Patti - bank");
		DataFactory.addSubPrincipal("Patti - bank", "Green bank");
		Configuration.setPrivacyPolicyOwner(bankUser.getUsername());
		
		//the other way for instantiating purpose
		var defaultPurpose = bankProject.bank.getAccountManager().createPurpose("", new ArrayList<Purpose>(),2,7);
		//easier way for instantiating purpose
		var defaultPersonalPurpose = DataFactory.createPurpose(null, ProcessingReason.OUT_OF_SCOPE, ProcessingReasonSubtype.PERSONAL_ACTIVITY);
		
		//use case 1
		//1.1
		var eve = bankProject.bank.getUserManager().createClient("Eve");
		
		var consent = bankProject.bank.getDocumentManager().createConsentDocument("Eve consent", eve, "Novi Sad 1");
		var account = new Account(ned, eve.getUsername(), bankUser.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPersonalPurpose, consent.getDocumentId(), "Open account for " + eve.getUsername());
		bankProject.bank.getAccountManager().openAccount(account);
		
		//1.2
		bankProject.bank.getAccountManager().checkAccount(ned, eve.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose);
		
		//1.3
		var transferDocument = bankProject.bank.getDocumentManager().createTransferDocument("Transfer consent Eve", eve, "system");
		var from = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
		var to = java.util.Date.from(LocalDate.of(2034, 4, 25).atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		var transferLog = bankProject.bank.getAccountManager().transferAccount(ned, eve.getUsername(), bankUser, from, to,
				defaultPurpose, city2, city1, consent.getDocumentId(), new ArrayList<String>(Arrays.asList(transferDocument.getDocumentId())));
		
		
		bankProject.bank.getNotificationManager().notifyUserAboutTransfer("Notify Eve about transfer", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				transferLog.getLogId(), eve, bankUser);
		
		//use case 2
		//2.1
		var john = bankProject.bank.getUserManager().createClient("John");
		var alice = bankProject.bank.getUserManager().createMinorClient("Alice", LocalDate.of(2010, 4, 10), 
				new ArrayList<User>(Arrays.asList(john)));
		var consent1 = bankProject.bank.getDocumentManager().createConsentDocument("Alice consent", john, "Novi Sad 1");
		var childCustody = bankProject.bank.getDocumentManager().createChildCustodyDocument("John-Alice child custody", john, "System");
		bankProject.bank.getAccountManager().openAccountChild(ned, alice.getUsername(), bankUser.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose, consent1.getDocumentId(), childCustody.getDocumentId());
		
		//2.2
		var log1 = bankProject.bank.getAccountManager().checkAccount(ned, alice.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose);
		
		//2.3
		var complaint1 = bankProject.bank.getComplaintManager().createComplaintOnDataForRectification("Rectification of email","Change email",
				"email", alice);
		
		bankProject.bank.getNotificationManager().notifyUserAboutRectification("Notify Alice and Green bank about rectification", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				complaint1.getComplaintId(), new ArrayList<User>(Arrays.asList(alice, bankUser)), bankUser);
		var rectification = bankProject.bank.getAccountManager().rectificationOfData(ned, alice.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose, complaint1.getComplaintId());
		bankProject.bank.getNotificationManager().notifyUserAboutExecutedRectification("Notify Alice about rectified email", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				rectification.getLogId(), alice, bankUser);
		
		//2.4
		var complaint2 = bankProject.bank.getComplaintManager().createComplaintOnDataForErasure("Erasure of email - Alice","-",
				"email", alice);
		bankProject.bank.getNotificationManager().notifyUserAboutErasure("Notify Alice and Green bank about erasure", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				complaint2.getComplaintId(), new ArrayList<User>(Arrays.asList(alice, bankUser)), bankUser);
		complaint2.createDenial("Erasure cannot be done - need parent persmission","Still processing", complaint2.getComplaintId(),
				bankUser.getUsername(), log1.getLogId());
		
		//use case 3
		//3.1
		var marketingPurpose = DataFactory.createPurpose("test", ProcessingReason.MARKETING, ProcessingReasonSubtype.NONE);
		var personalPurpose = DataFactory.createPurpose(null, ProcessingReason.OUT_OF_SCOPE, ProcessingReasonSubtype.PERSONAL_ACTIVITY);
		personalPurpose.getSubPurposes().add(marketingPurpose);
		var consent2 = bankProject.bank.getDocumentManager().createConsentDocument("John consent", john, "Novi Sad 1");
		bankProject.bank.getAccountManager().openAccount(ned, john.getUsername(), bankUser.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				personalPurpose, consent2.getDocumentId());
		
		//3.2
		var emailSending = bankProject.bank.getAccountManager().emailSendingForCard(patti, john.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				marketingPurpose);
		
		//3.3
		var complaint3 = bankProject.bank.getComplaintManager().createComplaintOnAction("Stop sending emails in marketing purpose","-",
				emailSending.getLogId(), john);
		var stopProcessing = bankProject.bank.getAccountManager().createPurpose("", new ArrayList<Purpose>(),11,0);
		var stopSending = bankProject.bank.getAccountManager().stopSendingEmails(patti, john.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				stopProcessing, complaint3.getComplaintId());
		bankProject.bank.getNotificationManager().notifyUserAboutStopSendingMails("Notify John about stop sending mails", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				stopSending.getLogId(), john, bankUser);
		
		//3.4
		var complaint4 = bankProject.bank.getComplaintManager().createComplaintWithWithdraw("John consent withdrawal", "Changing bank", 
				consent2.getDocumentId(), john);
		bankProject.bank.getNotificationManager().notifyUserAboutWithdrawal("Notify John and Ned about withdrawal", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				complaint4.getWithdrawId(), new ArrayList<User>(Arrays.asList(john, ned)), bankUser);
		
		//use case 4
		//4.1
		var bob = bankProject.bank.getUserManager().createClient("Bob");
		
		var consent3 = bankProject.bank.getDocumentManager().createConsentDocument("Bob consent", bob, "Novi Sad 1");
		bankProject.bank.getAccountManager().openAccount(patti, bob.getUsername(), bankUser.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose, consent3.getDocumentId());
		
		//4.2
		var police = bankProject.bank.getUserManager().createLegalEntityOut("Police");
		var court = bankProject.bank.getUserManager().createLegalEntityOut("Budapest court");
		var courtApproval = bankProject.bank.getDocumentManager().createCourtApproval("Police investigation approval - Bob", court, "-");
		var from1 = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
		var to1 = java.util.Date.from(LocalDate.of(2025, 4, 25).atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		
		var investigationPurpose = DataFactory.createPurpose(null, ProcessingReason.PUBLIC_INTEREST, ProcessingReasonSubtype.INVESTIGATION);
		
		bankProject.bank.getAccountManager().policeInvestigation(bankUser, bob.getUsername(), police.getUsername(), 
				new ArrayList<String>(Arrays.asList(courtApproval.getDocumentId())),from1, to1, investigationPurpose, "Police investigation for " + bob.getUsername());
		
		System.out.println("End");
	}

	@SuppressWarnings("serial")
	private static void setUpConfiguration() {
		Configuration.setXmlPath("model/bankTest.xmi");
		Configuration.createDefaultConfiguration();
		Configuration.setPrivacyPolicyName("Bank example");
		Configuration.setDataSources(new ArrayList<String>()
		{{
			add("PubliclyAccessibleSources"); 
			add("Identity document");
			add("Internal");
		}});
		Configuration.setProtectionControlMethods(new ArrayList<String>()
		{{
			add("OriginalData"); 
			add("Pseudonymisation");
			add("Encryption");
		}});
		Configuration.setDefaultProtectionControls(new ArrayList<String>()
		{{
			add("OriginalData");
		}});
		
		var encription = new ArrayList<String>()
		{{
			add("Encryption");
		}};
		
		DataFactory.addPrivacyDataWithSharedPrivacyData("name", DataType.GENERAL, true, "Identity document", encription);
		DataFactory.addPrivacyDataWithSharedPrivacyData("account number", DataType.GENERAL, true, null, null);
		DataFactory.addPrivacyDataWithSharedPrivacyData("identity number", DataType.BIOMETRIC, true, "Identity document", encription);
		DataFactory.addPrivacyDataWithSharedPrivacyData("email", DataType.GENERAL, true, null, null);
		
		DataFactory.addCountry("USA", false, 21);
		DataFactory.addRegion("NYC", "USA");
	}
}
