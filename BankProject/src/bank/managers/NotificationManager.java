package bank.managers;

import java.util.Date;

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

}
