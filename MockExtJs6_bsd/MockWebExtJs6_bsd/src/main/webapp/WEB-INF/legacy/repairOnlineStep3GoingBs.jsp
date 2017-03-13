<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"									prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
String payment = (String)request.getAttribute("payment");
String approval = (String)request.getAttribute("approval");
if (payment == null){
	payment = "";
}
if (approval == null){
	approval = "";
}

%>

<script type="text/javascript">
<%if (payment.equals("submitted")){%>
	<%if (approval.equals("0")){%>
	//approval
	window.location.href='/euo/repairOnlineStepEndBs';
	<%} else {%>
	//reject
	window.location.href='/euo/repairOnlineStep3Bs?declineStatus=Y';
	<%}%>
<%} else {%>
	setTimeout("redirectpage()",1000 * 5);
<%}%>

	function redirectpage(){
		//bar3.togglePause();
		checkPayment();
	}
	function checkPayment() {
		window.location.href='/euo/repairOnlineStep3GoingBs';
	}
</script>


<!-- Bootstrap version starts here -->
    <div class="panel-group">
        <div class="panel panel-default">

            <div class="panel-heading panelGroupHeadBkg"><s:text name="lable.PageStep2On.Title"/>
                <div class="numberedSteps">
                    <span class="wizard_stepOff">1</span>
                    <span class="wizard_stepOff">2</span>
                    <span class="wizard_stepOn">3</span>
                    <span class="wizard_stepOff">4</span>
                </div>
            </div>

			<div class="panel-body">

				<p>
				  <div class="well well-sm" style="color: #4d4d4d;"> <!--font-weight:bold;-->
			                        <p/><s:text name="lable.PageStep3.processing"/>
			      </div>
				</p>

				<form class="form-horizontal" id="track_existing_repair" name="track_existing_repair"
						method="post" action="/euo/repairOnlineStep3Bs" >
 					
 					<div class="form-group">
                        <label class="control-label col-sm-4" for="serialNumberField"><s:text name="lable.PageStep1.chooseSerial"/>:</label>
                        <div class="col-sm-8">
		                    <div class="progress">
							  <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:100%">
      							Processing	
    						  </div>
							</div>
                        </div>
                    </div>
                    
                    
				</form>

			</div> <!--closing div class="panel-body" -->
		</div> <!-- closing div class="panel panel-default"-->
	</div> <!-- closing div class="panel-group"-->


<!-- Bootstrap version ends here -->


