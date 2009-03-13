package org.openiam.selfsrvc.login;


import java.io.*;
import java.util.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;

import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.user.service.UserMgr;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.selfsrvc.AppConfiguration;
import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.AuthenticatorAccess;
import org.openiam.webadmin.busdel.security.TokenAccess;
import org.springframework.web.context.WebApplicationContext;
import org.openiam.idm.srvc.continfo.dto.Phone;

import diamelle.security.auth.*;
import diamelle.security.token.*;
import diamelle.util.Log;
import diamelle.ebc.user.*;


/**
 * <p>
 * <code>LoginAction</code> <font face="arial">
 * Forwarded from AuthenticateAction or AuthFilter
 * Gets login, service and password from login.jsp
 * If service not given, reads from env variable serviceId in the web.xml file
 * If user is authenticated, service, user and token are saved in session
 * for extraction in PermissionAction. User and token are also saved in cookies.
 *
 * Control is passed to PermissionAction for extracting applications the
 * authenticated user is allowed to go to.
 * </font>
 * </p>
 */

public class LoginAction extends NavigationDispatchAction {  //NavigationAction {

	protected LoginAccess loginAccess = new LoginAccess();
	//protected UserAccess userAccess = null;
	protected AuthenticatorAccess authAccess = null;
	protected TokenAccess tknAccess = null;
	protected AppConfiguration appConfiguration;
	SecurityDomainAccess secDomainAccess = null;

	
	public LoginAction() {
		try {
		//	userAccess = new UserAccess();
			authAccess = new AuthenticatorAccess();
			tknAccess = new TokenAccess();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		// local variables
	UserData ud = null;
	LoginValue loginValue = null;
	LoginValue lv = null;
	SSOSubject sub = null;	
	ActionErrors errors = new ActionErrors();

	String reqSessionId = request.getRequestedSessionId();
	HttpSession session = request.getSession();
	
	appConfiguration = (AppConfiguration)getWebApplicationContext().getBean("appConfiguration");
	
	
	
	if (reqSessionId != null) {
		session.invalidate();
		session = request.getSession(true);
	}
	
	Locale locale = getLocale(request);
	String langCd = locale.getLanguage();
	
	if (langCd.length() > 3) {
		langCd = langCd.substring(0, 2);	
	}

	// save appId for permission action
	String appId = request.getParameter("appId");
	
	// rootMenu to get permissions
	String rootMenu = request.getParameter("rootPermissionMenu");

	// get Parameters
	String serviceId = ((LoginForm) form).getServiceId();
	String actionCommand = ((LoginForm) form).getSubmit();
	String login = ((LoginForm) form).getLogin();
	String password = ((LoginForm) form).getPassword();
	String domainId = ((LoginForm)form).getDomainId();
//	String userId = null;
	
	secDomainAccess = new SecurityDomainAccess(getWebApplicationContext());	

	// put the configuration object in session
	session.setAttribute("logoUrl", appConfiguration.getLogoUrl());
	session.setAttribute("title", appConfiguration.getTitle());

	
	String userId = request.getParameter("userId");
	String token = request.getParameter("token");
	
	try {

		//if service not provided, get default service from environment entry
		if (serviceId == null || serviceId.length() < 1) {
			Context ctx = new InitialContext();
			serviceId = (String) ctx.lookup("java:comp/env/serviceId");
		}
		session.setAttribute("serviceId", domainId);
		session.setAttribute("domainId", domainId);
		
		session.setAttribute("domainList", secDomainAccess.getAllDomainsWithExclude("IDM"));

		//if rootMenu not provided, get default rootMenu from environment entry
		if (rootMenu == null) {
			Context ctx = new InitialContext();
			rootMenu = (String) ctx.lookup("java:comp/env/rootPermissionMenu");
		}
		//session.setAttribute("rootMenu", rootMenu);

		// When the action command is 'Login'
		if (actionCommand != null && actionCommand.equalsIgnoreCase("login")) { 
			loginValue = authAccess.getLogin(domainId,login);
			if (loginValue == null) {
		       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.id"));
		       return (mapping.findForward("login"));				
			}

			// creating an instance of LoginValue and setting the details
			lv = new LoginValue();
			lv.setLogin(login);
			lv.setPassword(password);
			lv.setService(domainId);
			sub = loginAccess.authenticate(lv);
			
			session.setAttribute("token", sub.getToken());
			session.setAttribute("userId", sub.getUserId());
			session.setAttribute("login", login);
			session.setAttribute("password",password);
			request.setAttribute("subject", sub);
			
			
			
			WebApplicationContext webCtx = getWebApplicationContext();
			//metadataSrvc = (MetadataService)webCtx.getBean("metadataService");
			UserDataService userDataSrvc = (UserDataService)webCtx.getBean("userManager");
			Map attrMap = userDataSrvc.getAllAttributes(sub.getUserId());
			org.openiam.idm.srvc.user.dto.User usr = userDataSrvc.getUserWithDependent(sub.getUserId(), true);
			
			session.setAttribute("userattr", attrMap);
			session.setAttribute("firstname", usr.getFirstName());
			session.setAttribute("lastname", usr.getLastName());
			session.setAttribute("user", usr);
			session.setAttribute("lv", lv);
			
			
			//UserAttribute appUid = userDataSrvc.getAttribute("APPS_UID");
			//UserAttribute appPassword = userDataSrvc.getAttribute("APPS_PASSWORD");
			
			if (attrMap != null) {
				Set keySet = attrMap.keySet();
				if (keySet != null) {
					Iterator<String> it = keySet.iterator();
					while (it.hasNext()) {
						String key = (String)it.next();
						System.out.println("Key=" + key);
						if (key.equalsIgnoreCase("APPS_UID")) {
							UserAttribute attr = (UserAttribute)attrMap.get(key);
							session.setAttribute("apps_uid", attr.getValue());
						}
						if (key.equalsIgnoreCase("APPS_PASSWORD")) {
							UserAttribute attr = (UserAttribute)attrMap.get(key);
							session.setAttribute("apps_password", attr.getValue());
						}					
					}
				}
			}
			
			
	/*		System.out.println("uid=" + appUid);
			
			if (appUid != null) {
				System.out.println("apps_uid" + appUid.getValue());
				session.setAttribute("apps_uid", appUid.getValue());
			}
			if (appPassword != null) {
				session.setAttribute("apps_password", appPassword.getValue());
			}
		*/	
			
			
			// save user and token in cookie
			Cookie uidCookie = new javax.servlet.http.Cookie("userId", sub.getUserId());
			Cookie tokenCookie = new javax.servlet.http.Cookie("token", sub.getToken());
		}else {
			if (token != null) {
				System.out.println("Found token. SSO...");
				sub = loginAccess.authenticate(userId, token);
			}
		}

		List menuList = loginAccess.getPermissions(sub.getUserId(), rootMenu, langCd);
		

		
		session.setAttribute("permissions", menuList);
		


		
	}catch(diamelle.security.auth.AuthenticationException ae) {

		
		String message = "Invalid user id and password combination. Please try again";
		request.setAttribute("message", message);
		ae.printStackTrace();
		String errorCd = ae.getErrorCode();
		 errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.id"));
		 
		if (errorCd.equals( AuthenticationResultCode.INVALID_LOGIN )) {
	       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.id"));
		}
		if (errorCd.equals( AuthenticationResultCode.INVALID_PASSWORD )) {
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.pw"));
		}
		if (errorCd.equals( AuthenticationResultCode.INVALID_USER_STATUS )) {
	       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.user.status"));
		}
		if (errorCd.equals( AuthenticationResultCode.LOGIN_LOCKED )) {
	       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.locked"));
		}
		if (errorCd.equals( AuthenticationResultCode.PASSWORD_EXPIRED )) {
		       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.pswdexp"));
		}
		if (errorCd.equals( AuthenticationResultCode.SERVICE_NOT_FOUND )) {
		       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.service"));
		}
		saveErrors(request, errors);
		return (mapping.findForward("login"));		
	}catch (Exception e) {
		e.printStackTrace();
		Log.error(e.getMessage(),e);
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.problem"));
		saveErrors(request, errors);
		return (mapping.findForward("login"));
	}

	// if its a first time login, then force the user to change the password.
	//if (loginValue.isResetPassword()) {
	//	return (mapping.findForward("changepswd"));
	//}
	return (mapping.findForward("permissionfwd"));
	}

}