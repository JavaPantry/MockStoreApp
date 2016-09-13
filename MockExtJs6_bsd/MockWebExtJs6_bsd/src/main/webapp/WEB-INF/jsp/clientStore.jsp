<!DOCTYPE html>
<html>
<head>
  <title>BSD Customer Store</title>
  <style>body{padding-top:70px}</style>

<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/node_modules/bootstrap/dist/css/bootstrap.css">

<%--   
<script src="${pageContext.request.contextPath}/resources/node_modules/zone.js/dist/zone.js"></script>
<script src="${pageContext.request.contextPath}/resources/node_modules/typescript/lib/typescript.js"></script>
<script src="${pageContext.request.contextPath}/resources/node_modules/jquery/dist/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/node_modules/bootstrap/dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/node_modules/reflect-metadata/Reflect.js"></script>
<script src="${pageContext.request.contextPath}/resources/node_modules/systemjs/dist/system.src.js"></script>

<script src="${pageContext.request.contextPath}/resources/systemjs.config.js"></script>
 --%>  
  
</head>
<body>
	<!-- <auction-application></auction-application>
	<script>System.import('app')</script> -->

	<auction-application>Loading...</auction-application>
	<script src="${pageContext.request.contextPath}/resources/dist/vendor.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/resources/dist/bundle.js"></script>


</body>
</html>
