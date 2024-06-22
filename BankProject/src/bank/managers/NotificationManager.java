package bank.managers;

import java.util.Date;
import java.util.List;

import com.security.model.validation.annotations.creators.CreateNotificationAnnotation;
import com.security.model.validation.annotations.enums.TargetType;

import bank.models.UserNotification;
import bank.models.users.User;
import privacyModel.NotificationType;

public class NotificationManager {
	
	@CreateNotificationAnnotation(causedByType = TargetType.PolicyStatement, type = NotificationType.THIRD_PARTY_TRANSFER)
	public UserNotification notifyUserAboutTransfer(String name, Date timestamp, String causedById, User receiver, User notifier)
	{
		return new UserNotification(name,timestamp, causedById, receiver, notifier);
	}

	@CreateNotificationAnnotation(causedByType = TargetType.ComplaintBasedOnData, type = NotificationType.RECTIFICATION)
	public UserNotification notifyUserAboutRectification(String name, Date timestamp, String causedById, List<User> receivers, User notifier)
	{
		return new UserNotification(name,timestamp, causedById, receivers, notifier);
	}
	
	@CreateNotificationAnnotation(causedByType = TargetType.PolicyStatement, type = NotificationType.EXECUTED_RECTIFICATION)
	public UserNotification notifyUserAboutExecutedRectification(String name, Date timestamp, String causedById, User receiver, User notifier)
	{
		return new UserNotification(name,timestamp, causedById, receiver, notifier);
	}
	
	@CreateNotificationAnnotation(causedByType = TargetType.ComplaintBasedOnData, type = NotificationType.ERASURE)
	public UserNotification notifyUserAboutErasure(String name, Date timestamp, String causedById, List<User> receivers, User notifier)
	{
		return new UserNotification(name,timestamp, causedById, receivers, notifier);
	}
	
	@CreateNotificationAnnotation(causedByType = TargetType.PolicyStatement, type = NotificationType.STOP_PROCESSING)
	public UserNotification notifyUserAboutStopSendingMails(String name, Date timestamp, String causedById, User receiver, User notifier)
	{
		return new UserNotification(name,timestamp, causedById, receiver, notifier);
	}
	
	@CreateNotificationAnnotation(causedByType = TargetType.Withdraw, type = NotificationType.WITHDRAW)
	public UserNotification notifyUserAboutWithdrawal(String name, Date timestamp, String causedById, List<User> receivers, User notifier)
	{
		return new UserNotification(name,timestamp, causedById, receivers, notifier);
	}
}
