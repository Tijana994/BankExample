package bank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

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
		var locationHelper = new LocationHelper();
		locationHelper.createCountry("Serbia");
		locationHelper.createCity("Novi Sad", "Serbia");
		locationHelper.createCountry("Hungary");
		var city2 = locationHelper.createCity("Budapest", "Hungary");
		var employees = new ArrayList<User>();
		employees.add(bankProject.bank.createEmployee("Ned - bank", LocalDate.of(1990, 4, 10)));
		employees.add(bankProject.bank.createEmployee("Patti - bank", LocalDate.of(1994, 4, 25)));
		var bankUser = bankProject.bank.createLegalEntity("Green bank", employees, city2);
		Configuration.setPrivacyPolicyOwner(bankUser.getUsername());

		var eve = bankProject.bank.createCustomer("Eve");
		
		//use case 1
		var start = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
		var consent = bankProject.bank.createConsentDocument("Eve consent", eve.getUsername());
		bankProject.bank.openAccount(bankUser, eve.getUsername(), start, bankProject.bank.createPurpose("Open account 1", new ArrayList<Purpose>(),2,7), 
				consent.getName());
		//var complaint = bankProject.bank.createComplaintOnAction();
		/*complaint.CreateDenial();*/
		/*complaint.setConsent(consent);
		
		bankProject.bank.createCity("Novi Sad", "Italy");*/
		//complaint.CreateWithDraw(consent);
		//complaint.CreateWithDraw(new Withdraw());
		//bankProject.bank.createComplaintOnData();
		System.out.println("End");
	}

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
