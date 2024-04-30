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
		locationHelper.createCountry("Serbia");
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
		
		//use case 1
		//1.1
		var eve = bankProject.bank.getUserManager().createCustomer("Eve");
		
		var consent = bankProject.bank.getDocumentManager().createConsentDocument("Eve consent", eve.getUsername(), "Novi Sad 1");
		bankProject.bank.getAccountManager().openAccount(ned, eve.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				bankProject.bank.getAccountManager().createPurpose("Open account", new ArrayList<Purpose>(),2,7), 
				consent.getName(), new ArrayList<String>());
		
		//1.2
		bankProject.bank.getAccountManager().checkAccount(ned, eve.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				bankProject.bank.getAccountManager().createPurpose("Check account", new ArrayList<Purpose>(),2,7));
		
		//1.3
		var from = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
		var to = java.util.Date.from(LocalDate.of(2034, 4, 25).atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		var transferconsent = bankProject.bank.getDocumentManager().createConsentDocument("Transfer consent Eve", eve.getUsername(), "Novi Sad 1");
		var transferDocument = bankProject.bank.getDocumentManager().createTransferDocument("Eve consent", eve.getUsername(), "system");
		var transferLog = bankProject.bank.getAccountManager().transferAccount(ned, eve.getUsername(), bankUser, from, to,
				bankProject.bank.getAccountManager().createPurpose("Transfer account", new ArrayList<Purpose>(),2,7),
				city2, city1,transferconsent.getName(), new ArrayList<String>(Arrays.asList(transferDocument.getName())));
		
		bankProject.bank.getNotificationManager().notifyUserAboutTransfer("Notify Eve about transfer", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				transferLog.getName(), eve.getUsername(), bankUser.getUsername());
		
		//use case 2
		//2.1
		var john = bankProject.bank.getUserManager().createCustomer("John");
		var alice = bankProject.bank.getUserManager().createMinorCustomer("Alice", LocalDate.of(2010, 4, 10), 
				new ArrayList<User>(Arrays.asList(john)));
		var consent1 = bankProject.bank.getDocumentManager().createConsentDocument("Alice consent", john.getUsername(), "Novi Sad 1");
		var childCustody = bankProject.bank.getDocumentManager().createChildCustodyDocument("John-Alice child custody", john.getUsername(), "System");
		bankProject.bank.getAccountManager().openAccount(ned, alice.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				bankProject.bank.getAccountManager().createPurpose("Open account", new ArrayList<Purpose>(),2,7), 
				consent1.getName(), new ArrayList<String>(Arrays.asList(childCustody.getName())));
		
		//2.2
		bankProject.bank.getAccountManager().checkAccount(ned, alice.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				bankProject.bank.getAccountManager().createPurpose("Check account", new ArrayList<Purpose>(),2,7));
		
		//2.3
		var complaint1 = bankProject.bank.getComplaintManager().createComplaintOnDataForRectification("Rectification of email","Change email",
				new ArrayList<String>(Arrays.asList("email")), alice.getUsername());
		
		bankProject.bank.getNotificationManager().notifyUserAboutRectification("Notify Alice and Green bank about rectification", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				complaint1.getName(), new ArrayList<String>(Arrays.asList(alice.getUsername(),bankUser.getUsername())), bankUser.getUsername());
		var rectification = bankProject.bank.getAccountManager().rectificationOfData(ned, alice.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				bankProject.bank.getAccountManager().createPurpose("Data rectification", new ArrayList<Purpose>(),2,7), complaint1.getName());
		bankProject.bank.getNotificationManager().notifyUserAboutExecutedRectification("Notify Alice about rectified email", Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				rectification.getName(), alice.getUsername(), bankUser.getUsername());
		
		//2.3
		var complaint2 = bankProject.bank.getComplaintManager().createComplaintOnDataForErasure("Erasure of email - Alice","-",
				new ArrayList<String>(Arrays.asList("email")), alice.getUsername());
		
		//3.1
		var consent2 = bankProject.bank.getDocumentManager().createConsentDocument("John consent", john.getUsername(), "Novi Sad 1");
		bankProject.bank.getAccountManager().openAccount(ned, john.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				bankProject.bank.getAccountManager().createPurpose("Open account", new ArrayList<Purpose>(),2,7), 
				consent2.getName(), new ArrayList<String>());
		
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
		
		DataFactory.addPrivacyData("name", DataType.GENERAL);
		DataFactory.addPrivacyData("account number", DataType.GENERAL);
		DataFactory.addPrivacyData("identity number", DataType.BIOMETRIC);
		DataFactory.addPrivacyData("email", DataType.GENERAL);
		
		var encription = new ArrayList<String>()
		{{
			add("Encryption");
		}};
		
		DataFactory.addSharedPrivacyData("name", "name", true, "Identity document", encription);
		DataFactory.addSharedPrivacyData("account number", "account number", true, null, null);
		DataFactory.addSharedPrivacyData("identity number", "identity number", true, "Identity document", encription);
		DataFactory.addSharedPrivacyData("email", "email", true, null, null);
	}
}
