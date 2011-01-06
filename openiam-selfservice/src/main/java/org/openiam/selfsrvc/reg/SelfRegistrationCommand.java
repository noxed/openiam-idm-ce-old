package org.openiam.selfsrvc.reg;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.provision.dto.ProvisionUser;

/**
 * Command object for the NewHireController
 * @author suneet
 *
 */
public class SelfRegistrationCommand implements Serializable {
	 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3001967685870249543L;
	String status = new String("PENDING_APPROVAL");

	User user = new User();

	protected String managedBy;
	
	/* phone fields */
	protected String workAreaCode;
	protected String workPhone;
	protected String cellAreaCode;
	protected String cellPhone;
	protected String faxAreaCode;
	protected String faxPhone;

	protected String workPhoneId;
	protected String cellPhoneId;
	protected String faxPhoneId;
	protected String homePhoneIdr;
	protected String altCellNbrId;
	protected String altPhoneNbrId;
	protected String personalNbrId;
	
	protected String homePhoneAreaCode;
	protected String homePhoneNbr;
	protected String altCellAreaCode;
	protected String altCellNbr;
	protected String altPhoneAreaCode;
	protected String altPhoneNbr;
	protected String personalAreaCode;
	protected String personalNbr;

	
	
	/* misc fields */
	protected String userPrincipalName;
	protected String email1Id;
	protected String email2Id;
	protected String email3Id;
	protected String email1;
	protected String email2;
	protected String email3;
	
	/* List of Managers */
	protected List<User> managerList;
	protected List<ReferenceData> jobCodeList;
	protected List<ReferenceData> userTypeList;
	
	/* Access control */
	protected String role;
	protected String group;
	protected List<Group> selectedGroups;
	protected List<Group> unselectedGroups;
	
	protected List<Organization> orgList;
	protected List<Organization> departmentList;
	protected List<Organization> divisionList;
	
	protected List<Role> roleAry; // list of roles a user belongs to
	protected Location[] locationAry;
	
	protected List<Resource> resourceList;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	} 


	public List<Organization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}



	public List<User> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<User> managerList) {
		this.managerList = managerList;
	}

	public List<ReferenceData> getJobCodeList() {
		return jobCodeList;
	}

	public void setJobCodeList(List<ReferenceData> jobCodeList) {
		this.jobCodeList = jobCodeList;
	}

	public List<ReferenceData> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<ReferenceData> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public List<Group> getSelectedGroups() {
		return selectedGroups;
	}

	public void setSelectedGroups(List<Group> selectedGroups) {
		this.selectedGroups = selectedGroups;
	}

	public List<Group> getUnselectedGroups() {
		return unselectedGroups;
	}

	public void setUnselectedGroups(List<Group> unselectedGroups) {
		this.unselectedGroups = unselectedGroups;
	}



	public List<Organization> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Organization> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Organization> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(List<Organization> divisionList) {
		this.divisionList = divisionList;
	}

	public List<Role> getRoleAry() {
		return roleAry;
	}

	public void setRoleAry(List<Role> roleAry) {
		this.roleAry = roleAry;
	}

	public Location[] getLocationAry() {
		return locationAry;
	}

	public void setLocationAry(Location[] locationAry) {
		this.locationAry = locationAry;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	public User getUser() {
/*		Set emailSet = new HashSet<EmailAddress>();
		
		// add in the dependant object
		if (email1 != null && !email1.isEmpty()) {
			EmailAddress adr1 = new EmailAddress();
			adr1.setEmailAddress(email1);
			emailSet.add(adr1);
		}
		
		if (email2 != null && !email2.isEmpty()) {
			EmailAddress adr2 = new EmailAddress();
			emailSet.add(adr2);
			adr2.setEmailAddress(email2);
		}
		if (email3 != null && !email3.isEmpty()) {
			EmailAddress adr3 = new EmailAddress();
			adr3.setEmailAddress(email3);			
			emailSet.add(adr3);
		}
		user.setEmailAddress(emailSet);
*/		
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}

	public String getWorkAreaCode() {
		return workAreaCode;
	}

	public void setWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getCellAreaCode() {
		return cellAreaCode;
	}

	public void setCellAreaCode(String cellAreaCode) {
		this.cellAreaCode = cellAreaCode;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getFaxAreaCode() {
		return faxAreaCode;
	}

	public void setFaxAreaCode(String faxAreaCode) {
		this.faxAreaCode = faxAreaCode;
	}

	public String getFaxPhone() {
		return faxPhone;
	}

	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}

	public String getWorkPhoneId() {
		return workPhoneId;
	}

	public void setWorkPhoneId(String workPhoneId) {
		this.workPhoneId = workPhoneId;
	}

	public String getCellPhoneId() {
		return cellPhoneId;
	}

	public void setCellPhoneId(String cellPhoneId) {
		this.cellPhoneId = cellPhoneId;
	}

	public String getFaxPhoneId() {
		return faxPhoneId;
	}

	public void setFaxPhoneId(String faxPhoneId) {
		this.faxPhoneId = faxPhoneId;
	}

	public String getHomePhoneIdr() {
		return homePhoneIdr;
	}

	public void setHomePhoneIdr(String homePhoneIdr) {
		this.homePhoneIdr = homePhoneIdr;
	}

	public String getAltCellNbrId() {
		return altCellNbrId;
	}

	public void setAltCellNbrId(String altCellNbrId) {
		this.altCellNbrId = altCellNbrId;
	}

	public String getAltPhoneNbrId() {
		return altPhoneNbrId;
	}

	public void setAltPhoneNbrId(String altPhoneNbrId) {
		this.altPhoneNbrId = altPhoneNbrId;
	}

	public String getPersonalNbrId() {
		return personalNbrId;
	}

	public void setPersonalNbrId(String personalNbrId) {
		this.personalNbrId = personalNbrId;
	}

	public String getHomePhoneAreaCode() {
		return homePhoneAreaCode;
	}

	public void setHomePhoneAreaCode(String homePhoneAreaCode) {
		this.homePhoneAreaCode = homePhoneAreaCode;
	}

	public String getHomePhoneNbr() {
		return homePhoneNbr;
	}

	public void setHomePhoneNbr(String homePhoneNbr) {
		this.homePhoneNbr = homePhoneNbr;
	}

	public String getAltCellAreaCode() {
		return altCellAreaCode;
	}

	public void setAltCellAreaCode(String altCellAreaCode) {
		this.altCellAreaCode = altCellAreaCode;
	}

	public String getAltCellNbr() {
		return altCellNbr;
	}

	public void setAltCellNbr(String altCellNbr) {
		this.altCellNbr = altCellNbr;
	}

	public String getAltPhoneAreaCode() {
		return altPhoneAreaCode;
	}

	public void setAltPhoneAreaCode(String altPhoneAreaCode) {
		this.altPhoneAreaCode = altPhoneAreaCode;
	}

	public String getAltPhoneNbr() {
		return altPhoneNbr;
	}

	public void setAltPhoneNbr(String altPhoneNbr) {
		this.altPhoneNbr = altPhoneNbr;
	}

	public String getPersonalAreaCode() {
		return personalAreaCode;
	}

	public void setPersonalAreaCode(String personalAreaCode) {
		this.personalAreaCode = personalAreaCode;
	}

	public String getPersonalNbr() {
		return personalNbr;
	}

	public void setPersonalNbr(String personalNbr) {
		this.personalNbr = personalNbr;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getEmail1Id() {
		return email1Id;
	}

	public void setEmail1Id(String email1Id) {
		this.email1Id = email1Id;
	}

	public String getEmail2Id() {
		return email2Id;
	}

	public void setEmail2Id(String email2Id) {
		this.email2Id = email2Id;
	}

	public String getEmail3Id() {
		return email3Id;
	}

	public void setEmail3Id(String email3Id) {
		this.email3Id = email3Id;
	}

	public String getEmail3() {
		return email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}


	

}
