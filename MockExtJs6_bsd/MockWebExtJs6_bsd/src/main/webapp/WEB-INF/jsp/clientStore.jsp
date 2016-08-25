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


  <!-- to start in WebStorm Add '../' src and href -->

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/node_modules/bootstrap/dist/css/bootstrap.css">
  <script src="${pageContext.request.contextPath}/resources/node_modules/zone.js/dist/zone.js"></script>
  <script src="${pageContext.request.contextPath}/resources/node_modules/typescript/lib/typescript.js"></script>
  <script src="${pageContext.request.contextPath}/resources/node_modules/jquery/dist/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/resources/node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="${pageContext.request.contextPath}/resources/node_modules/reflect-metadata/Reflect.js"></script>
  <script src="${pageContext.request.contextPath}/resources/node_modules/systemjs/dist/system.src.js"></script>

  <script src="${pageContext.request.contextPath}/resources/systemjs.config.js"></script>
  
<%-- 
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/node_modules/datatables.net-bs\css\dataTables.bootstrap.css">
  <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/node_modules/datatables.net\js\jquery.dataTables.js"></script>
datatables from CDN
--%>  
 
<!-- 	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.css"/>
	<script type="text/javascript" src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js"></script>
 -->
 
 <%-- 
Problem with datatables is that following script does not see not loaded page with table
see https://datatables.net/forums/discussion/27286/initializing-datatable-after-div-element-loaded
  

<script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
		$('#clientOrders').DataTable();
	} );
</script>
--%>
 
  
  
  
  
</head>
<body>
<auction-application></auction-application>
<script>System.import('app')</script>
</body>
</html>
