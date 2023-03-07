package bank;

import bank.models.*;

public class BankProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankProject bankProject = new BankProject();
		Bank bank = new Bank();
		bank.createCity("Novi Sad");
		bank.collectingDocuments(1);
		/*var c = new Complaint();
		c.CreateDenial();
		c.CreateWithDraw();
		c.CreateWithDraw(new Withdraw());
		bankProject.createComplaintOnData();*/
		System.out.println("End");
	}
	
	//@CreateComplaintBasedOnDataAnnotation(type = ComplaintBasedOnDataType.RECTIFICATION)
	public Complaint createComplaintOnData()
	{
		var complaint = new Complaint();
		complaint.setName("Name text");
		complaint.setReason("Reason text");
		return complaint;
	}
}
