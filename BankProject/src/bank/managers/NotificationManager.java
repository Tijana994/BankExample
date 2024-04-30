package bank.managers;

import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.creators.CreateNotificationAnnotation;
import com.security.model.validation.annotations.enums.TargetType;

import bank.models.UserNotification;
import privacyModel.NotificationType;

public class NotificationManager {
	
	@CreateNotificationAnnotation(causedByType = TargetType.PolicyStatement, type = NotificationType.THIRD_PARTY_TRANSFER)
	public UserNotification notifyUserAboutTransfer(String name, Date timestamp, String causedById, 
			String receiverId, String notifierId)
	{
		return new UserNotification(name,timestamp, causedById, receiverId, notifierId);
	}

	@CreateNotificationAnnotation(causedByType = TargetType.ComplaintBasedOnData, type = NotificationType.RECTIFICATION)
	public UserNotification notifyUserAboutRectification(String name, Date timestamp, String causedById, 
			List<String> receiversIds, String notifierId)
	{
		return new UserNotification(name,timestamp, causedById, receiversIds, notifierId);
	}
	
	@CreateNotificationAnnotation(causedByType = TargetType.PolicyStatement, type = NotificationType.EXECUTED_RECTIFICATION)
	public UserNotification notifyUserAboutExecutedRectification(String name, Date timestamp, String causedById, 
			String receiverId, String notifierId)
	{
		return new UserNotification(name,timestamp, causedById, receiverId, notifierId);
	}
}
