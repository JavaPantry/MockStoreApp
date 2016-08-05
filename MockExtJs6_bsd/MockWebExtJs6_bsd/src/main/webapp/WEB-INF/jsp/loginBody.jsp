<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="Pragma" content="no-cache" />
        
        <meta http-equiv="X-UA-Compatible" content="IE=8, IE=9, IE=10, IE=edge" />
		
		<link rel="icon" href="${pageContext.request.contextPath}/resources/fast/images/chart_curve.png">
        <title><spring:message code="label.login.page_title"/></title>

        <link href="${pageContext.request.contextPath}/resources/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" >
		<link href="${pageContext.request.contextPath}/resources/lib/bootstrap-additions/dist/bootstrap-additions.min.css" rel="stylesheet" >
		<link href="${pageContext.request.contextPath}/resources/lib/angularjs-toaster/toaster.min.css" rel="stylesheet" >
		<link href="${pageContext.request.contextPath}/resources/lib/ladda/dist/ladda-themeless.min.css" rel="stylesheet" >
		<link href="${pageContext.request.contextPath}/resources/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" >
		<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" >
		<link href="${pageContext.request.contextPath}/resources/css/paper.css" rel="stylesheet" >
        <link type="text/css" href="${pageContext.request.contextPath}/resources/css/signin.css" rel="stylesheet">
        
    </head>
    
    <body>
    
     <div class="container">
      <form class="form-signin" method="post" modelAttribute="loginForm">
			<h5 class="form-signin-heading"><spring:message code="label.login.title"/></h2>
			
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			
			<label for="inputUserName" class="sr-only">Email address</label>
			<input id="inputUserName" name="userName" class="form-control" placeholder="User Id" required autofocus>  <!-- type="email"  -->
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
			<!-- <div class="checkbox"><label><input type="checkbox" value="remember-me"> Remember me</label></div> -->
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			<div class="footer">
				<div>
					<span>&copy; 2013-2015 </span>
					<a href="http://www.google.com"><span>ABC Canada Inc.</span></a><span> - <spring:message code="label.main.footer.project_title_short"/></span>
				</div>
			</div>
      </form>
    </div> <!-- /container -->
    
    </body>

</html>