package bank;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

		var eve = bankProject.bank.getUserManager().createCustomer("Eve");
		
		//use case 1
		//1.1
		var consent = bankProject.bank.getDocumentManager().createConsentDocument("Eve consent", eve.getUsername(), "Novi Sad 1");
		bankProject.bank.getAccountManager().openAccount(ned, eve.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				bankProject.bank.getAccountManager().createPurpose("Open account", new ArrayList<Purpose>(),2,7), 
				consent.getName());
		
		//1.2
		bankProject.bank.getAccountManager().checkAccount(ned, eve.getUsername(), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), 
				bankProject.bank.getAccountManager().createPurpose("Check account", new ArrayList<Purpose>(),2,7));
		
		//1.3
		var transferDocument = bankProject.bank.getDocumentManager().createTransferDocument("Eve consent", eve.getUsername(), "system");
		bankProject.bank.getAccountManager().transferAccount(ned, eve.getUsername(), bankUser,
				Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
				bankProject.bank.getAccountManager().createPurpose("Transfer account", new ArrayList<Purpose>(),2,7),
				city2, city1,consent.getName(), new ArrayList<String>(Arrays.asList(transferDocument.getName())));
		//var complaint = bankProject.bank.createComplaintOnAction();
		/*complaint.CreateDenial();*/
		/*complaint.setConsent(consent);*/
		//complaint.CreateWithDraw(consent);
		//complaint.CreateWithDraw(new Withdraw());
		//bankProject.bank.createComplaintOnData();
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
