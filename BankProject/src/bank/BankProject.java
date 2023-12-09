package bank;

import bank.models.*;
import utility.ProjectConfiguration;

public class BankProject {

	private Bank bank;
	
	public BankProject()
	{
		var config = new ProjectConfiguration.Configuration();
		bank = new Bank();
	}
	
	public static void main(String[] args) {
		BankProject bankProject = new BankProject();
		bankProject.bank.createUser("Tommica");
		var complaint = bankProject.bank.createComplaintOnAction();
		/*complaint.CreateDenial();*/
		var consent = bankProject.bank.createConsentDocument("consent test");
		/*complaint.setConsent(consent);
		bankProject.bank.collectingDocuments(1);
		bankProject.bank.createCity("Novi Sad", "Italy");*/
		complaint.CreateWithDraw(consent);
		//complaint.CreateWithDraw(new Withdraw());
		/*bankProject.bank.createComplaintOnData();*/
		System.out.println("End");
	}
}
