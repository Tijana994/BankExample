package bank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import bank.models.*;
import utility.ProjectConfiguration.Configuration;

public class BankProject {

	private Bank bank;
	
	public BankProject()
	{
		bank = new Bank();
	}
	
	public static void main(String[] args) {
		Configuration.setXmlPath("model/bank.xmi");
		Configuration.createDefaultConfiguration();
		Configuration.setPrivacyPolicyName("Bank example");
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

		
		/*var user = bankProject.bank.createUser("Tommica");
		var start = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
		var consent = bankProject.bank.createConsentDocument("consent for Tommo");
		bankProject.bank.collectingDocuments(bankUser, user.getUsername(), start, bankProject.bank.createPurpose(), consent.getName());*/
		//var complaint = bankProject.bank.createComplaintOnAction();
		/*complaint.CreateDenial();*/
		/*complaint.setConsent(consent);
		
		bankProject.bank.createCity("Novi Sad", "Italy");*/
		//complaint.CreateWithDraw(consent);
		//complaint.CreateWithDraw(new Withdraw());
		//bankProject.bank.createComplaintOnData();
		System.out.println("End");
	}
}
