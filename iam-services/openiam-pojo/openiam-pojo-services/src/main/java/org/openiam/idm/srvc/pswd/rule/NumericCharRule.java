/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
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
package org.openiam.idm.srvc.pswd.rule;


import org.apache.commons.lang.StringUtils;
import org.openiam.base.ws.ResponseCode;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.pswd.dto.PasswordRule;

/**
 * Validates a password to ensure that it contains the appropriate number of numeric characters in 
 * the password.
 * @author suneet
 *
 */
public class NumericCharRule extends AbstractPasswordRule {


    @Override
    public String getAttributeName() {
        return "NUMERIC_CHARS";
    }

    @Override
	public void validate(PolicyAttribute attribute) throws PasswordRuleException {
		int minChar = getValue1(attribute);
		int maxChar = getValue2(attribute);

		final PasswordRuleException ex = createException();
		if(ex == null) {
			return;
		}
		
		// count the number of characters in the password
		if (password == null) {
			throw new PasswordRuleException(ResponseCode.FAIL_NUMERIC_CHAR_RULE);
		}
		int charCtr = 0;
		for (int i=0; i < password.length(); i++) {
			int ch = password.charAt(i);
			if (ch >= 48 && ch <= 57) {
				charCtr++;
			}
		}
		
		if (minChar > 0 ) {
			if (charCtr  < minChar) {
				throw ex;
			}
		}
		if (maxChar > 0 ) {
			if (charCtr > maxChar ) {
				throw ex;
			}
		}
	}

	@Override
	public PasswordRuleException createException(PolicyAttribute attribute) {
		int minChar = getValue1(attribute);
		int maxChar = getValue2(attribute);
		return createException(ResponseCode.FAIL_NUMERIC_CHAR_RULE, minChar, maxChar);
	}

	@Override
	public PasswordRule createRule(PolicyAttribute attribute) {
		int minChar = getValue1(attribute);
		int maxChar = getValue2(attribute);
		if(minChar <= 0 && maxChar <= 0) {
			return null;
		} else {
			return createRule(ResponseCode.FAIL_NUMERIC_CHAR_RULE, minChar, maxChar);
		}
	}	
}
