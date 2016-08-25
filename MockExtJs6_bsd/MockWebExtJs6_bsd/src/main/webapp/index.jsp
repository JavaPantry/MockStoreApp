<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="hasRole('QuotaKPI_QUOTA')">
<c:redirect url="/exthome"/>
</security:authorize>

<%-- <security:authorize access="hasRole('QuotaKPI_ADMIN_ANGULAR') or hasRole('BSD_DEALER')"> --%>
<security:authorize access="hasAnyRole('QuotaKPI_ADMIN_ANGULAR', 'BSD_DEALER')">
<c:redirect url="/clientStore"/>
</security:authorize>
