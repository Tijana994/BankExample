package bank.models;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import com.security.model.validation.annotations.TimeStatementAnnotation;
import com.security.model.validation.annotations.creators.CreateLocationAnnotation;
import com.security.model.validation.annotations.creators.CreatePolicyStatementAnnotation;
import com.security.model.validation.annotations.creators.CreatePrincipalAnnotation;
import com.security.model.validation.annotations.enums.tobedeleted.Action;
import com.security.model.validation.annotations.enums.tobedeleted.LocationType;
import com.security.model.validation.annotations.enums.tobedeleted.TimePreposition;

public class Bank {

	@CreateLocationAnnotation(locationType = LocationType.REGION)
	public City createCity(String name)
	{
		var l =  new City();
		l.setName(name);
		return l;
	}

	@CreatePolicyStatementAnnotation(who = "admin", whose ="user", why ="purpose", when = "start,end", actions = {Action.STORE, Action.COLLECTING}, datas = {})
	public Log collectingDocuments(int i)
	{
		admin = "Tommo" + i;
		user = "Pera" + i;
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
	
	@CreatePrincipalAnnotation()
	public User createUser(String username)
	{
		return new User(username);
	}
	
	public String admin;
	public String user;
	public Purpose purpose;
	@TimeStatementAnnotation(preposition = TimePreposition.AT)
	public Date start;
}
