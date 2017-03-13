<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"									prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%-- <div id="static_text_area">	<br>
	<div id="hometop" style="display:block">
		<span style="FONT-SIZE: 11pt; LINE-HEIGHT: 115%; FONT-FAMILY: ARIAL;FONT-WEIGHT: color: #666666">
			<spring:message code="label.home.top"/>
		</span>
	</div><br><br>
</div> --%>

<div class="panel panel-default">

        <div class="panel-body">
	        <div class="well well-sm" style="color: #4d4d4d;">
	            <spring:message code="label.home.top"/>
	        </div>
	        
			<div class="form-group">
				<label class="control-label col-sm-4" for="makeRequest"><spring:message code="label.home.request"/>:</label>
				<div class="col-sm-8">
					<p class="form-control-static" id="makeRequest">
					<button type="button" class="button_example" onClick="window.location.href='/euo/repairOnlineStep1Bs';return false">
                            	&nbsp;<spring:message code="label.repair.request"/>&nbsp;&nbsp;&nbsp;&#xe800;
                    </button>
					</p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="trackRequest"><spring:message code="label.home.track"/>:</label>
				<div class="col-sm-8">
					<p class="form-control-static" id="trackRequest">
					<button type="button" class="button_example" onClick="window.location.href='/euo/repairOnlineTrack1Bs';return false">
                            	&nbsp;<spring:message code="label.track.repair"/>&nbsp;&nbsp;&nbsp;&#xe800;
                    </button>
					</p>
				</div>
			</div>
		</div> <!-- class="panel-body" -->
</div>	<!-- class="panel panel-default" -->





<div class="panel panel-default">

        <div class="panel-body">
	        <div class="well well-sm" style="color: #4d4d4d;">
	            <spring:message code="label.home.top"/>
	        </div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="makeRequest1"><spring:message code="label.home.request"/>:</label>
				<div class="col-sm-8">
					<!-- <p class="form-control-static" id="makeRequest1"> -->
					<button type="button" id="makeRequest1" class="button_example form-control-static" onClick="window.location.href='/euo/repairOnlineStep1Bs';return false">
                            	&nbsp;<spring:message code="label.go"/>&nbsp;&nbsp;&nbsp;&#xe800;
                    </button>
					<!-- </p> -->
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="trackRequest1"><spring:message code="label.home.track"/>:</label>
				<div class="col-sm-8">
					<!-- <p class="form-control-static" id="trackRequest1"> -->
					<button type="button" id="trackRequest1" class="button_example form-control-static" onClick="window.location.href='/euo/repairOnlineTrack1Bs';return false">
                            	&nbsp;<spring:message code="label.go"/>&nbsp;&nbsp;&nbsp;&#xe800;
                    </button>
					<!-- </p> -->
				</div>
			</div>
		</div> <!-- class="panel-body" -->
</div>	<!-- class="panel panel-default" -->


<!-- TODO - <AP> remove "ErrorSummary" span -->
<span class="midblack" id="ErrorSummary"></span>



AVP_BS			