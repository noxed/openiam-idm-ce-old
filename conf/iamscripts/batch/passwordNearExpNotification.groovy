// Sends a notification to users whose passwords are going to expire
// runs every night

System.out.println("passwordExpirationNotification.groovy");

import org.openiam.idm.srvc.msg.dto.NotificationRequest
import org.openiam.idm.srvc.msg.dto.NotificationParam

import java.util.Date
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.idm.srvc.msg.dto.NotificationRequest
import org.openiam.idm.groovy.helper.ServiceHelper;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.base.id.UUIDGen;


def MailService mailService = context.getBean("mailService");
def LoginDataWebService loginManager = context.getBean("loginWS");

currentDate = new Date(System.currentTimeMillis())

loginList = loginManager.getUsersNearPswdExpiration().principalList


if (loginList != null ) {
        for ( lg in loginList) {
        
         expDate = lg.pwdExp
        
         diffInMilleseconds = expDate.getTime() - currentDate.getTime();
         diffInSeconds = diffInMilleseconds/1000;
         diffInMinutes = diffInSeconds/60;
         diffInHours = diffInMinutes/60;
         diffInDays = diffInHours/24;
        // setup email notification    
        NotificationRequest req = new NotificationRequest();
		req.userId  = lg.userId;
		req.notificationType = "PASSWORD_NEAR_EXPIRATION";
		req.paramList.add(new NotificationParam("daysToExpiration", String.valueOf((Integer)diffInDays), (Integer)diffInDays));
		req.requestId = UUIDGen.getUUID();
		println("send Notification");
        mailService.sendNotification(req);
                
        }
}

output=0
