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
import utility.PrivacyDataFactory.DataFactory;
import utility.ProjectConfiguration.Configuration;

public class BankProject {

	private Bank bank;
	
	public BankProject()
	{
		bank = new Bank();
	}
	
	public static void main(String[] args) {
		//configuration and set up
		setUpConfiguration();
		
		BankProject bankProject = new BankProject();
		var locationHelper = new LocationManager();
		locationHelper.createNonEUCountry("Serbia");
		var city1 = locationHelper.createCity("Novi Sad", "Serbia");
		locationHelper.createCountry("Hungary");
		var city2 = locationHelper.createCity("Budapest", "Hungary");
		var employees = new ArrayList<User>();
		var ned = bankProject.bank.getUserManager().createEmployee("Ned - bank", LocalDate.of(1990, 4, 10));
		employees.add(ned);
		var patti = bankProject.bank.getUserManager().createEmployee("Patti - bank", LocalDate.of(1994, 4, 25));
		employees.add(patti);
		var bankUser = bankProject.bank.getUserManager().createLegalEntity("Green bank", employees, city2);
		Configuration.setPrivacyPolicyOwner(bankUser.getUsername());
		
		var defaultPurpose = bankProject.bank.getAccountManager().createPurpose("", new ArrayList<Purpose>(),2,7);
		
		//use case 1
		//1.1
		var eve = bankProject.bank.getUserManager().createClient("Eve");
		
		var consent = bankProject.bank.getDocumentManager().createConsentDocument("Eve consent", eve.getUsername(), "Novi Sad 1");
		bankProject.bank.getAccountManager().openAccount(ned, eve.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose, consent.getName());
		
		//1.2
		bankProject.bank.getAccountManager().checkAccount(ned, eve.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose);
		
		//1.3
		var transferDocument = bankProject.bank.getDocumentManager().createTransferDocument("Transfer consent Eve", eve.getUsername(), "system");
		var from = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
		var to = java.util.Date.from(LocalDate.of(2034, 4, 25).atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		var transferLog = bankProject.bank.getAccountManager().transferAccount(ned, eve.getUsername(), bankUser, from, to,
				defaultPurpose, city2, city1,consent.getName(), new ArrayList<String>(Arrays.asList(transferDocument.getName())));
		
		
		bankProject.bank.getNotificationManager().notifyUserAboutTransfer("Notify Eve about transfer", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				transferLog.getName(), eve.getUsername(), bankUser.getUsername());
		
		//use case 2
		//2.1
		var john = bankProject.bank.getUserManager().createClient("John");
		var alice = bankProject.bank.getUserManager().createMinorClient("Alice", LocalDate.of(2010, 4, 10), 
				new ArrayList<User>(Arrays.asList(john)));
		var consent1 = bankProject.bank.getDocumentManager().createConsentDocument("Alice consent", john.getUsername(), "Novi Sad 1");
		var childCustody = bankProject.bank.getDocumentManager().createChildCustodyDocument("John-Alice child custody", john.getUsername(), "System");
		bankProject.bank.getAccountManager().openAccountChild(ned, alice.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose, consent1.getName(), childCustody.getName());
		
		//2.2
		var log1 = bankProject.bank.getAccountManager().checkAccount(ned, alice.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose);
		
		//2.3
		var complaint1 = bankProject.bank.getComplaintManager().createComplaintOnDataForRectification("Rectification of email","Change email",
				"email", alice.getUsername());
		
		bankProject.bank.getNotificationManager().notifyUserAboutRectification("Notify Alice and Green bank about rectification", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				complaint1.getName(), new ArrayList<String>(Arrays.asList(alice.getUsername(),bankUser.getUsername())), bankUser.getUsername());
		var rectification = bankProject.bank.getAccountManager().rectificationOfData(ned, alice.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose, complaint1.getName());
		bankProject.bank.getNotificationManager().notifyUserAboutExecutedRectification("Notify Alice about rectified email", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				rectification.getName(), alice.getUsername(), bankUser.getUsername());
		
		//2.4
		var complaint2 = bankProject.bank.getComplaintManager().createComplaintOnDataForErasure("Erasure of email - Alice","-",
				"email", alice.getUsername());
		bankProject.bank.getNotificationManager().notifyUserAboutErasure("Notify Alice and Green bank about erasure", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				complaint2.getName(), new ArrayList<String>(Arrays.asList(alice.getUsername(),bankUser.getUsername())), bankUser.getUsername());
		complaint2.createDenial("Erasure cannot be done - need parent persmission","Still processing", complaint2.getName(),
				bankUser.getUsername(), log1.getName());
		
		//use case 3
		//3.1
		var subpurpose = bankProject.bank.getAccountManager().createPurpose("", new ArrayList<Purpose>(),6,0);
		var purpose = bankProject.bank.getAccountManager().createPurpose("", new ArrayList<Purpose>(Arrays.asList(subpurpose)),2,7);
		var consent2 = bankProject.bank.getDocumentManager().createConsentDocument("John consent", john.getUsername(), "Novi Sad 1");
		bankProject.bank.getAccountManager().openAccount(ned, john.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				purpose, consent2.getName());
		
		//3.2
		var emailSending = bankProject.bank.getAccountManager().emailSendingForCard(patti, john.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				subpurpose);
		
		//3.3
		var complaint3 = bankProject.bank.getComplaintManager().createComplaintOnAction("Stop sending emails in marketing purpose","-",
				emailSending.getName(), john.getUsername());
		var stopProcessing = bankProject.bank.getAccountManager().createPurpose("", new ArrayList<Purpose>(),11,0);
		var stopSending = bankProject.bank.getAccountManager().stopSendingEmails(patti, john.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				stopProcessing, complaint3.getName());
		bankProject.bank.getNotificationManager().notifyUserAboutStopSendingMails("Notify John about stop sending mails", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				stopSending.getName(), john.getUsername(), bankUser.getUsername());
		
		//3.4
		var complaint4 = bankProject.bank.getComplaintManager().createComplaintWithWithdraw("John consent withdrawal", "Chaninging bank", 
				consent2.getName(), john.getUsername());
		bankProject.bank.getNotificationManager().notifyUserAboutWithdrawal("Notify John and Ned about withdrawal", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				complaint4.getName(), new ArrayList<String>(Arrays.asList(john.getUsername(),ned.getUsername())), bankUser.getUsername());
		
		//use case 4
		//4.1
		var bob = bankProject.bank.getUserManager().createClient("Bob");
		
		var consent3 = bankProject.bank.getDocumentManager().createConsentDocument("Bob consent", bob.getUsername(), "Novi Sad 1");
		bankProject.bank.getAccountManager().openAccount(patti, bob.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				defaultPurpose, consent3.getName());
		
		//4.2
		var police = bankProject.bank.getUserManager().createLegalEntityOut("Police");
		var court = bankProject.bank.getUserManager().createLegalEntityOut("Budapest court");
		var courtApproval = bankProject.bank.getDocumentManager().createCourtApproval("Police investigation approval - Bob", court.getUsername(), "-");
		var from1 = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
		var to1 = java.util.Date.from(LocalDate.of(2025, 4, 25).atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		
		var investigationPurpose = bankProject.bank.getAccountManager().createPurpose("", new ArrayList<Purpose>(Arrays.asList(subpurpose)),3,2);
		
		bankProject.bank.getAccountManager().policeInvestigation(bankUser, john.getUsername(), police.getUsername(), 
				new ArrayList<String>(Arrays.asList(courtApproval.getName())),from1, to1, investigationPurpose);
		
		System.out.println("End");
	}

	@SuppressWarnings("serial")
	private static void setUpConfiguration() {
		Configuration.setXmlPath("model/bank.xmi");
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
	}
}
