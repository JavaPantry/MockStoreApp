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

        <title><spring:message code="label.login.page_title"/></title>
        <link type="text/css" href="${pageContext.request.contextPath}/resources/css/blitzer/jquery-ui-1.10.3.custom.css" rel="Stylesheet" />  
        <link type="text/css" href="${pageContext.request.contextPath}/resources/css/appCustom.css" rel="Stylesheet" />  
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.3.custom.min.js"></script>
    </head>
    
    <body>
    
        <table style="height: 100%; width: 100%">
	        <tr>
		        <td valign="middle">
			        <div class="login">
			        
			            <div class="login_container">
			                <div class="content">
			    
			                    <div>
			                        <spring:message code="label.login.title"/>
			                    </div>
			                    
			                    <c:if test="${not empty error}">
									<div class="error">${error}</div>
								</c:if>
								<c:if test="${not empty msg}">
									<div class="msg">${msg}</div>
								</c:if>
			                    
			                    <form:form method="post" modelAttribute="loginForm">
			                        <div class="item">                      
			                           <div>
				                           <table>
				                               <tr>
				                                   <td class="labelcell"><form:label path="userName"><spring:message code="label.login.username"/></form:label></td>
				                                   <td class="inputcell"><form:input path="userName" class="textinput" /></td>
				                               </tr>
				                               <tr>
				                                   <td class="labelcell"><form:label path="password"><spring:message code="label.login.password"/></form:label></td>
				                                   <td class="inputcell"><form:password path="password" class="textinput" /></td>
				                               </tr>
				                           </table>
				                           <%-- TODO - <AP> find how to moke this field in ExtJs input type="hidden" name="{_c s r f.parameterName}" value="{_c s r f.token}" --%>
			                           </div>
			                           <div class="center">
			                               <button id="button_login" type="submit"><spring:message code="button.login"/></button>
			                           </div>                                       
			                        </div>                
			                    </form:form>
			                    
			                </div>
			        
			                <div class="footer">
			                    <div>
			                       <span>&copy; 2013-2015 </span>
			                       <a href="http://www.google.ca"><span>AVP company.</span></a><span> - <spring:message code="label.main.footer.project_title_short"/></span>
			                    </div>
			                </div>
			            </div>
			              
			        </div>
		        </td>
	        </tr>
        </table>
        
        <script type="text/javascript">
            $(document).ready(function() {
                $("#button_login").click(function() {
                      return true;
                });

                $("#userName").focus();
            });
        </script>
        
    </body>

</html>