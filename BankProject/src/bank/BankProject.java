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
		var complaint = bankProject.bank.createComplaintOnAction();
		complaint.CreateDenial();
		complaint.setConsent(bankProject.bank.createConsentDocument("consent test"));
		/*bankProject.bank.collectingDocuments(1);
		bankProject.bank.createCity("Novi Sad", "Italy");
		bankProject.bank.createUser("Tommica");*/
		//complaint.CreateWithDraw();
		/*complaint.CreateWithDraw(new Withdraw());
		bankProject.bank.createComplaintOnData();*/
		System.out.println("End");
	}
}
