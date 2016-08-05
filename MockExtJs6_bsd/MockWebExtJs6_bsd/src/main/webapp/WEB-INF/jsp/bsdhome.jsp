<!DOCTYPE html>
<html lang="en" ng-app="codecraft" >

<head>
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<title>BSD home</title>
<link rel="icon" href="${pageContext.request.contextPath}/resources/fast/images/chart_curve.png">

<link href="${pageContext.request.contextPath}/resources/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/resources/lib/bootstrap-additions/dist/bootstrap-additions.min.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/resources/lib/angularjs-toaster/toaster.min.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/resources/lib/ladda/dist/ladda-themeless.min.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/resources/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/resources/css/paper.css" rel="stylesheet" >

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta http-equiv="Content-Type"	content="text/html; charset=windows-1252"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<META http-equiv="Content-Style-Type" content="text/css"/>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"/>
<META HTTP-EQUIV="expires" CONTENT="-1"/>


<script type="text/javascript">
var welcomeUserName		= 'Welcome, ' + '${userFullName}';//+ '${pageContext.request.userPrincipal.name}'
var messages = {
	managerIdLabel	: '<spring:message code="label.list.manager.id"/>',
	managerNameLabel: '<spring:message code="label.list.manager.id"/>'
}

var _contextPath_ = '${pageContext.request.contextPath}';
</script>

<security:authorize access="hasRole('BSD_DEALER')">
    <script type="text/javascript">
    	document.write("<br/><br/><br/>Has role BSD_DEALER");    
    </script>
</security:authorize>

<%-- 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/extjs-6.0.1/packages/ux/classic/classic/resources/ux-all-debug.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/extjs-6.0.1/ext-all-debug.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fast/css/app.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/app.js"></script>   
--%>
 
 
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" >
	<div class="container" >
		<div class="navbar-header" >
			<a class="navbar-brand" href="bsdhome" >My Store Supplies</a >
			<a class="navbar-brand" href="exthome" >My Orders</a >
		</div >
		
		
		<div class="collapse navbar-collapse" >
			<ul class="nav navbar-nav navbar-right" >
				<li ui-sref-active="active">
					<a ui-sref="list" >Products</a >
				</li >
				
				<li ui-sref-active="active" >
					<a ui-sref="create"  >Create Product</a >
				</li >
				
				<li ui-sref-active="active">
					<a ui-sref="orderlist" >Orders</a >
				</li >
				
				
			</ul >
		</div >
	</div >
</nav >

	<script>
		document.write("<br/>Loading Business Solution Division"+" root path:"+_contextPath_);
	</script>
	
<div class="container main-content" >

	<toaster-container ></toaster-container >
	
	<!-- need to remove 
	<button class="btn btn-primary pull-right"
	        ng-click="showCreateModal()" >Create
	</button >-->
	
	<div class="row" >
		<div ui-view="main"></div>
	</div >
</div >	
	
	
	
<!-- AngularJs libriries -->	
<script src="${pageContext.request.contextPath}/resources/lib/angular/angular.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/angular-resource/angular-resource.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/angular-animate/angular-animate.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/ngInfiniteScroll/build/ng-infinite-scroll.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/spin.js/spin.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/angular-spinner/angular-spinner.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/angular-auto-validate/dist/jcs-auto-validate.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/ladda/dist/ladda.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/angular-ladda/dist/angular-ladda.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/angular-strap/dist/angular-strap.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/angular-strap/dist/angular-strap.tpl.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/angularjs-toaster/toaster.min.js" ></script >
<script src="${pageContext.request.contextPath}/resources/lib/ui-router/release/angular-ui-router.min.js" ></script >
<!-- my app -->
<script src="${pageContext.request.contextPath}/resources/js/main.js" ></script >
<script src="${pageContext.request.contextPath}/resources/js/services/ProductService.js" ></script >
<script src="${pageContext.request.contextPath}/resources/js/services/OrderService.js" ></script >
<script src="${pageContext.request.contextPath}/resources/js/controllers/OrderListController.js" ></script >
<script src="${pageContext.request.contextPath}/resources/js/controllers/ProductListController.js" ></script >
<script src="${pageContext.request.contextPath}/resources/js/data/Product.js" ></script >
<script src="${pageContext.request.contextPath}/resources/js/data/Order.js" ></script >

</body>
</html>