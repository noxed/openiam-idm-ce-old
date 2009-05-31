/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.auth.sso;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.openiam.util.encrypt.Cryptor;

/**
 * Module to create and manage the default token structure used by OpenIAM
 * @author suneet
 *
 */
public class DefaultTokenModule implements SSOTokenModule {

	static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	
	protected Cryptor cryptor;
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOToken#createToken(java.util.Map)
	 */
	public String createToken(Map tokenParam) {
		StringBuffer buf = new StringBuffer();
		buf.append((String)tokenParam.get("USER_ID"));
		// add separator between user id and time component
		buf.append(":");
		buf.append( String.valueOf( getExpirationTime() ) );

		return  cryptor.encrypt(buf.toString());

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOToken#isTokenValid(java.lang.String, java.lang.String)
	 */
	public boolean isTokenValid(String userId, String token) {
		String decUserId;		// decrypted userid
		String decTime;			// decrypted time

		String decString = cryptor.decrypt(token);
		// tokenize this string
		StringTokenizer tokenizer = new StringTokenizer(decString,":");
		if (tokenizer.hasMoreTokens()) {
			decUserId =  tokenizer.nextToken();
		}else {
			return false;
		}
		if (tokenizer.hasMoreTokens()) {
			decTime =  tokenizer.nextToken();
		}else  {
			return false;
		}
		if (!decUserId.equalsIgnoreCase(userId))
			return false;
		
		long ldecTime = Long.parseLong( decTime );
		if (ldecTime < System.currentTimeMillis()) {
			//current time is greater then the allowed idle time
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOToken#refreshToken(java.lang.String, java.lang.String)
	 */
	public String refreshToken(String userId, String token) {
		if (isTokenValid(userId, token)) {
			Map tokenParam = new HashMap();
			tokenParam.put("USER_ID", userId);
			return createToken(tokenParam);
		}else {
			return "-1";
		}
	}

	/**
	 * Determines when the token will expire
	 * @return
	 */
	protected long getExpirationTime() {
		long idleTime = 0l;
		long curTime = System.currentTimeMillis();
		
		String strIdleTime =  res.getString("SSO_TOKEN_IDLE_TIME");
		int idleItem = Integer.parseInt(strIdleTime.trim());
		
		idleTime = curTime + ( 1000 * 60 * idleTime);
		
		return idleTime;
	}

	public Cryptor getCryptor() {
		return cryptor;
	}

	public void setCryptor(Cryptor cryptor) {
		this.cryptor = cryptor;
	}
}