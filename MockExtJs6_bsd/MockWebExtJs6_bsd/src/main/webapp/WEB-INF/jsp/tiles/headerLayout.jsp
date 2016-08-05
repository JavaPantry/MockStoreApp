<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%@ page import="org.avp.quota.kpi.web.web.IndexController" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        
        <meta http-equiv="X-UA-Compatible" content="IE=8, IE=9, IE=10, IE=edge" />
        
        <title><tiles:insertAttribute name="pageTitle" ignore="true" /></title>
        
        <link type="text/css" 
          href="${pageContext.request.contextPath}/resources/css/blitzer/jquery-ui-1.10.3.custom.css" rel="Stylesheet" />  
        <link type="text/css" 
          href="${pageContext.request.contextPath}/resources/css/ui.jqgrid.css" rel="Stylesheet" />

        <link rel="stylesheet" type="text/css" 
          href="${pageContext.request.contextPath}/resources/js/packages/ext-theme-gray/build/resources/ext-theme-gray-all.css">
  
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.3.custom.min.js"></script>
          
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/i18n/grid.locale-${pageContext.response.locale}.js"></script>
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/jquery.jqGrid.min.js"></script>
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/jquery.fileDownload.js"></script>      
        
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/MessageBus.js"></script>            
        
    </head>
    
    <body>
    
		<div class="main">
            <div class="header">
                <table style="height: 28px; width: 100%">
                    <tr>
                        <td style="width: 134px" valign="middle"><div class="company_logo"></div></td>
                        <td style="width: 220px" valign="middle">
                            <span class="thick" style="margin-left: 10px">
                                <spring:message code="label.main.welcome"/>
                            </span>
                            <span class="thick" style="margin-left: 2px">
                                ${appSessionObject.appUser.userId}
                            </span>
                        </td>
                        <td valign="middle">
                            <span class="head_title">
                                <a href="${pageContext.request.contextPath}/requests/${fn:escapeXml(appSessionObject.appUser.userId)}">
                                    <spring:message code="label.main.title"/>
                                </a>
                            </span>
                        </td>
                        <td style="width: 50px" valign="middle">
                            <div class="logout"> 
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            
            <div class="container">
                <div class="menu">
                    <%@include file="appMenu.jsp" %>
                </div>

                <div class="content">
    
    				<tiles:insertAttribute name="body" />

                    <div class="item">
                        <div class="user_list">
                        <%-- <c:forEach items="${authorizedUsers}" var="authorizedUser">
                            <a href="${pageContext.request.contextPath}/requests/${fn:escapeXml(authorizedUser.userId)}">
                                <span>${authorizedUser.userId}</span>
                            </a>
                        </c:forEach> --%>
                        </div>
                    </div>
                    
                </div>

		        <div id="alert-message" title="<spring:message code="label.alert.title" />" style="display:none">
		          <p>
		            <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		            <spring:message code="label.alert.body" />
		          </p>
		        </div>

                <%@include file="defaultFooter.jsp" %>        
            </div>    
        </div>
    </body>
</html>
