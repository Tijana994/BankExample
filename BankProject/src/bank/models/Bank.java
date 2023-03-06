package bank.models;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import com.security.model.validation.annotations.creators.CreatePolicyStatementAnnotation;

//import annotation.creators.CreateLocationAnnotation;
//import annotation.enums.LocationType;

public class Bank {

	//@CreateLocationAnnotation(locationType = LocationType.REGION)
	public City createCity(String name)
	{
		var l =  new City();
		l.setName(name);
		return l;
	}

	@CreatePolicyStatementAnnotation(who = "admin", whose ="user", why ="purpose", when = "start,end", /*actions = {Action.STORE, Action.COLLECTING},*/ datas = {})
	public Log collectingDocuments(int i)
	{
		admin = "Tommo" + i;
		purpose = new Purpose();
		purpose.Text = "Neki random tekst";
		purpose.SubPurposes = new ArrayList<Purpose>();
		var sub = new Purpose();
		sub.Text = "whatever";
		purpose.SubPurposes.add(sub);
		start = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
		var log = new Log();
		log.setName("something");
		return log;
	}
	
	public String admin;
	public Purpose purpose;
	//@TimeStatementAnnotation(preposition = TimePreposition.AT)
	public Date start;
}
