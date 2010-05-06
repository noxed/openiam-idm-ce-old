<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="identityUserCmd">
<form:hidden path="perId" />
<table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="3" class="title">         
          <strong>User - Principal List</strong>
      </td>
      <td class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
   <tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight" colspan="3">
			<font color="red"></font>
       </td>
    </tr>
    <tr>
    	<td>
    		<table>
		      <c:forEach items="${identityUserCmd.principalList}" var="principalList" varStatus="principal">
		  
				<tr class="plaintext">
					<td>
						<table cellpadding="1">
							<tr class="plaintext">
								<td class="tddarknormal">Login</td>
								<td  colspan="2"><form:input path="principalList[${principal.index}].id.login" size="30"  />
								<form:hidden path="principalList[${principal.index}].id.domainId" />
								<form:hidden path="principalList[${principal.index}].userId" /></td>
								<td><form:checkbox path="principalList[${principal.index}].selected" />Delete</td>
							</tr>
							<tr class="plaintext">
								<td class="tddarknormal">Managed System</td>
								<td class="tdlight" colspan="3">${principalList.id.managedSysId} </td>
							</tr>

							<tr class="plaintext">
								<td class="tddarknormal">Locked</td>
								<td >${principalList.isLocked}</td>
								<td class="tdlight" >Authn Fail Count</td>
								<td >${principalList.authFailCount}</td>
							</tr>
							<tr class="plaintext">
								<td class="tddarknormal">Last Login</td>
								<td  >${principalList.lastLogin}</td>
								<td class="tdlight" >Last Authn Attempt</td>
								<td  >${principalList.lastAuthAttempt}</td>
							</tr>
							<tr class="plaintext">
								<td class="tddarknormal">First Time Login</td>
								<td >${principalList.firstTimeLogin}</td>
								<td class="tdlight" >Pswd Changed</td>
								<td  >${principalList.pwdChanged}</td>
							</tr>

							<tr class="plaintext">
								<td class="tddarknormal">Pswd Expiration</td>
								<td  >${principalList.pwdExp}</td>
								<td class="tdlight" >Grace Period</td>
								<td  >${principalList.gracePeriod}</td>
							</tr>
							<tr>
								<td colspan="4"><hr></td>
							</tr>

						</table>
					</td>
					
				</tr>
			</c:forEach>
		</table>
	</td>	
	</tr>    
   


    <tr>
    	  <td colspan="4">&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="4" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
    <td colspan="4" align="right"> 
     
          <input type="submit" value="Submit"/>   
    </td>
  </tr>
  
</table>

</form:form>

