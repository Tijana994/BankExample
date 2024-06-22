package bank.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.NotificationAnnotation;

import bank.models.users.User;

@NotificationAnnotation(id = "id", when ="timestamp", causedById = "causedById", 
receivers = "receivers", notifiers = "notifiers")
public class UserNotification {
	
	public UserNotification(String id, Date timestamp, String causedById, User receiver, User notifier) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.causedById = causedById;
		this.receivers = new ArrayList<User>();
		this.receivers.add(receiver);
		this.notifiers = new ArrayList<User>();
		this.notifiers.add(notifier);
	}
	
	public UserNotification(String id, Date timestamp, String causedById, List<User> receivers, User notifier) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.causedById = causedById;
		this.receivers = receivers;
		this.notifiers = new ArrayList<User>();
		this.notifiers.add(notifier);
	}
	
	private String id;
	private Date timestamp;
	private String causedById;
	private List<User> receivers;
	private List<User> notifiers;
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
	public String getCausedById() {
		return causedById;
	}
}
