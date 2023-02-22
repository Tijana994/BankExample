package bank;

import annotation.creators.CreateComplaintBasedOnDataAnnotation;
import annotation.enums.ComplaintBasedOnDataType;
import aop.test.AOPTest;
import aop.test.Complaint;
import bank.models.Bank;

public class BankProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankProject bankProject = new BankProject();
		AOPTest test = new AOPTest();
		AOPTest.main(null);
		Bank bank = new Bank();
		bank.createCity("Novi Sad");
		//var c = new Complaint();
		//c.CreateDenial();
		//c.CreateWithDraw();
		//c.CreateWithDraw(new Withdraw());
		bankProject.createComplaintOnData();
		System.out.println("End");
	}
	
	@CreateComplaintBasedOnDataAnnotation(type = ComplaintBasedOnDataType.RECTIFICATION)
	public Complaint createComplaintOnData()
	{
		var complaint = new Complaint();
		complaint.setName("Name text");
		complaint.setReason("Reason text");
		return complaint;
	}
}
