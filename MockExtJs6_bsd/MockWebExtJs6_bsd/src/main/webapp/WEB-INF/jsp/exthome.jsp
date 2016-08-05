<html>
<head>

<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" -->
<!-- taglib prefix="s" uri="/struts-tags" -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- page import="org.avp.fast.constants.GlobalConstant" -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta http-equiv="Content-Type"	content="text/html; charset=windows-1252"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<META http-equiv="Content-Style-Type" content="text/css"/>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"/>
<META HTTP-EQUIV="expires" CONTENT="-1"/>
<title>Quota KPI</title>
<link rel="icon" href="${pageContext.request.contextPath}/resources/fast/images/chart_curve.png">
<!-- 
	* for Spring security tag security:authorize access="hasRole('QuotaKPI_XXXX')" groups/roles should have 'ROLE_' prefix
	* group				user
	* QuotaKPI_QUOTA		sptestuser603
	* QuotaKPI_BUDGET		sptestuser601
	* QuotaKPI_COMPANY		sptestuser602
	* QuotaKPI_REPORT		sptestuser604
 -->

<script type="text/javascript">
var welcomeUserName		= 'Welcome, ' + '${userFullName}';//+ '${pageContext.request.userPrincipal.name}'
var FaST_Version_Number = 'v 0.0.1';

var quotaUser = true;    
var budgetUser = false;    
var companyUser = false;    
var reportUser = false;    
var adminUser = false;    

var messages = {
	managerIdLabel	: '<spring:message code="label.list.manager.id"/>',
	managerNameLabel: '<spring:message code="label.list.manager.id"/>'
}
</script>

<security:authorize access="hasRole('QuotaKPI_QUOTA')">
    <script type="text/javascript">
		quotaUser = true;    
    </script>
</security:authorize>

<security:authorize access="hasRole('QuotaKPI_BUDGET')">
    <script type="text/javascript">
		budgetUser = true;    
    </script>
</security:authorize>

<security:authorize access="hasRole('QuotaKPI_REPORT')">
    <script type="text/javascript">
		reportUser = true;    
    </script>
</security:authorize>

<security:authorize access="hasRole('QuotaKPI_COMPANY')">
    <script type="text/javascript">
		companyUser = true;    
    </script>
</security:authorize>

<security:authorize access="hasRole('QuotaKPI_ADMIN')">
    <script type="text/javascript">
		quotaUser = true;    
		budgetUser = true;    
		companyUser = true;    
		reportUser = true;    
		adminUser = true;    
    </script>
</security:authorize>

 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/extjs-6.0.1/classic/theme-classic/resources/theme-classic-all-debug.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/extjs-6.0.1/packages/charts/classic/classic/resources/charts-all-debug.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/extjs-6.0.1/packages/ux/classic/classic/resources/ux-all-debug.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/extjs-6.0.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/extjs-6.0.1/packages/charts/classic/charts-debug.js"></script>

  
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fast/css/app.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/app.js"></script>   

</head>

<body>



<script>
document.write("Loading Quota KPI "+FaST_Version_Number+'.<p>'+'<spring:message code="label.list.manager.id"/>');
</script>
</body>
</html>