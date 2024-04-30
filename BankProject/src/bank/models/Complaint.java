package bank.models;

import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.ComplaintAnnotation;
import com.security.model.validation.annotations.creators.CreateDenialAnnotation;
import com.security.model.validation.annotations.creators.CreateWithdrawAnnotation;
import com.security.model.validation.annotations.enums.CreatedObjectLocation;
import com.security.model.validation.annotations.enums.ParametersObjectsLocation;

@ComplaintAnnotation(id="name", reason = "reason", when = "time", whoId = "userId")
public class Complaint {

	private String name;
	private String reason;
	private Date time;
	private Withdraw withdraw;
	private Document consent;
	private String consentId;
	private String userId;
	
	@CreateDenialAnnotation(forComplaintId = "complaintId", approvedById = "approvedById", basedOnStatemetsIds = "tasks")
	public Denial createDenial(String name, String reason, String complaintId, String approvedById, List<String> tasks)
	{
		var denial = new Denial();
		denial.setName(name);
		denial.setReason(reason);
		denial.setDate(new Date());
		return denial;
	}
	
	@CreateWithdrawAnnotation(createdObjectLocation = CreatedObjectLocation.Property, name = "withdraw", consent = "consent", parametersLocation = ParametersObjectsLocation.Property)
	public void createWithDraw()
	{
		withdraw = new Withdraw();
	}
	
	@CreateWithdrawAnnotation(createdObjectLocation = CreatedObjectLocation.Property, name = "withdraw", consent = "consent", parametersLocation = ParametersObjectsLocation.Parameter)
	public void createWithDraw(Document consent)
	{
		withdraw = new Withdraw();
	}
	
	@CreateWithdrawAnnotation(createdObjectLocation = CreatedObjectLocation.Parameter, name = "withdraw", consentId = "consentId")
	public void createWithDraw(Withdraw withdraw)
	{
	    setConsentId("1234");
		withdraw.setName("Testic");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Withdraw getWithdraw() {
		return withdraw;
	}

	public Document getConsent() {
		return consent;
	}

	public void setConsent(Document consent) {
		this.consent = consent;
	}

	public String getConsentId() {
		return consentId;
	}

	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
