package bank.models;

import java.util.Date;

import com.security.model.validation.annotations.NotificationAnnotation;

@NotificationAnnotation(id = "id", when ="timestamp")
public class UserNotification {
	
	public UserNotification(String id, Date timestamp) {
		super();
		this.id = id;
		this.timestamp = timestamp;
	}
	private String id;
	private Date timestamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
