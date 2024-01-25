package bank;

import bank.models.*;
import utility.ProjectConfiguration;
import utility.ProjectConfiguration.Configuration;

public class BankProject {

	private Bank bank;
	private Configuration configuration;
	
	public BankProject()
	{
		configuration = new ProjectConfiguration.Configuration();
		Configuration.setXmlPath("model/bank.xmi");
		Configuration.createDefaultConfiguration();
		Configuration.setPrivacyPolicyName("Bank example");
		bank = new Bank();
	}
	
	public static void main(String[] args) {
		BankProject bankProject = new BankProject();
		var bankUser = bankProject.bank.createLegalEntity("City bank");
		bankProject.configuration.setPrivacyPolicyOwner(bankUser.getUsername());
		bankProject.bank.createUser("Tommica");
		bankProject.bank.collectingDocuments();
		//var complaint = bankProject.bank.createComplaintOnAction();
		/*complaint.CreateDenial();*/
		//var consent = bankProject.bank.createConsentDocument("consent test");
		/*complaint.setConsent(consent);
		
		bankProject.bank.createCity("Novi Sad", "Italy");*/
		//complaint.CreateWithDraw(consent);
		//complaint.CreateWithDraw(new Withdraw());
		//bankProject.bank.createComplaintOnData();
		System.out.println("End");
	}
}
