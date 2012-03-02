package org.openiam.spml2.spi.ldap.dirtype;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.SysConfiguration;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.pswd.service.PasswordGenerator;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.openiam.spml2.msg.suspend.ResumeRequestType;
import org.openiam.spml2.msg.suspend.SuspendRequestType;
import org.openiam.exception.EncryptionException;

import javax.naming.directory.ModificationItem;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;


/**
 * Implements directory specific extensions for standard LDAP v3
 * User: suneetshah
 */
public class LdapV3 implements Directory{
    
    Map<String, Object> objectMap = new HashMap<String, Object>();
    private static final Log log = LogFactory.getLog(LdapV3.class);
    

    public ModificationItem[] setPassword(SetPasswordRequestType reqType) throws UnsupportedEncodingException {

        ModificationItem[] mods = new ModificationItem[1];
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", reqType.getPassword()));

        return mods;
    }

    public ModificationItem[] suspend(SuspendRequestType request)  {

        String scrambledPswd =	PasswordGenerator.generatePassword(10);

        ModificationItem[] mods = new ModificationItem[1];
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", scrambledPswd));

        return mods;
    }

    public ModificationItem[] resume(ResumeRequestType request) {

        String ldapName = (String)objectMap.get("LDAP_NAME");
        LoginDataService loginManager = (LoginDataService)objectMap.get("LOGIN_MANAGER");
        SysConfiguration sysConfiguration = (SysConfiguration)objectMap.get("CONFIGURATION");
        String targetID = (String)objectMap.get("TARGET_ID");

        try {

             // get the current password for the user.
            Login login = loginManager.getLoginByManagedSys(
                    sysConfiguration.getDefaultSecurityDomain(),
                    ldapName,
                    targetID);
            String encPassword = login.getPassword();
            String decPassword = loginManager.decryptPassword(encPassword);

            ModificationItem[] mods = new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", decPassword));
            return mods;
        }catch(EncryptionException e) {
            log.error(e.toString());
            return null;

        }

    }

    public void setAttributes(String name, Object obj) {
        objectMap.put(name,obj);
    }
}
