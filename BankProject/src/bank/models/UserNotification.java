package bank.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.NotificationAnnotation;

@NotificationAnnotation(id = "id", when ="timestamp", causedById = "causedById", 
receiversIds = "receiversIds", notifiersIds = "notifiersIds")
public class UserNotification {
	
	public UserNotification(String id, Date timestamp, String causedById, String receiverId, String notifierId) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.causedById = causedById;
		this.receiverId = receiverId;
		this.receiversIds = new ArrayList<String>();
		this.receiversIds.add(receiverId);
		this.notifierId = notifierId;
		this.notifiersIds = new ArrayList<String>();
		notifiersIds.add(notifierId);
	}
	
	private String id;
	private Date timestamp;
	private String causedById;
	private String receiverId;
	private List<String> receiversIds;
	private String notifierId;
	private List<String> notifiersIds;
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
	public String getReceiverId() {
		return receiverId;
	}
	public String getNotifierId() {
		return notifierId;
	}
	public List<String> getNotifiersIds() {
		return notifiersIds;
	}
}
