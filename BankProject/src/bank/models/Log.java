package bank.models;


import com.security.model.validation.annotations.PolicyStatementAnnotation;

@PolicyStatementAnnotation(id = "logId")
public class Log {

	private String logId;

	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
}