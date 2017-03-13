<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"									prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>

<%@page import="java.util.Locale"%>
<%
	String lang = ((Locale)session.getAttribute("locale")).getLanguage();
%>

<script type="text/javascript" src="/euo/resources/js/repairOnlineStep2Bs.js"></script>


<!-- Bootstrap version starts here -->
    <div class="panel-group">
        <div class="panel panel-default">

            <div class="panel-heading panelGroupHeadBkg"><s:text name="lable.PageStep2On.Title"/>
                <div class="numberedSteps">
                    <span class="wizard_stepOff">1</span>
                    <span class="wizard_stepOn">2</span>
                    <span class="wizard_stepOff">3</span>
                    <span class="wizard_stepOff">4</span>
                </div>
            </div>

			<div class="panel-body">

			<p>
			  <div class="well well-sm" style="color: #4d4d4d;"> <!--font-weight:bold;-->
		                        <%-- <s:text name="label.step2.top.title" /> --%>
		                        <p/><s:text name="label.step2.on.top.title" />
		                        <p/><s:text name="label.step2.on.top.msg" />
		      </div>
			</p>
			
			<%-- <span class="midblack" id="ErrorSummary" style="padding:52px;"></span> --%>
			
			<form class="form-horizontal" data-toggle="validator"
                		id="track_existing_repair"
		                method="post"  
						action="/euo/repairOnlineStep3Bs">
					<!-- Form Error Text STARTS -->				
					<span class="midblack" id="ErrorSummary"></span>			
					<input type="HIDDEN" id="LANGUAGE" name="LANGUAGE" value="<%= lang%>">

<!-- ============================== section-start ==================================== -->
				<fieldset>



				<%--<td class="leftCol"><label id="lbl_model"><s:text name="lable.PageStep1On.Model"/></label></td>
                      <td class="rightCol">${repairRecord.OURMDNAM}&nbsp;</td>--%>
                <div class="form-group">
                        <label class="control-label col-sm-4" for="modelField"><s:text name="lable.PageStep1On.Model"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="modelField"><c:out value="${repairRecord.OURMDNAM}"/></p>
                        </div>
                </div>  
                  
				<div class="form-group">
                        <label class="control-label col-sm-4" for="serialNumberField"><s:text name="lable.PageStep1.chooseSerial"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="serialNumberField"><c:out value="${repairRecord.OURSRNUM}"/></p>
                        </div>
                </div>
				
                <div class="form-group">
                        <label class="control-label col-sm-4" for="warrantStatusField"><s:text name="lable.PageStep1On.serviceType"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="warrantStatusField"><c:out value="${repairRecord.warrantStatus}"/></p>
                        </div>
                </div>
                    
                <div class="form-group">
                        <label class="control-label col-sm-4" for="estimateCostPlaceHolder"><s:text name="lable.PageStep1On.estimateCost"/>:</label>
                        <div class="col-sm-8" id="estimateCostPlaceHolder">
                            <!--  -->
                        </div>
                </div>
                
                    
                <div class="form-group">
                        <label class="control-label col-sm-4" for="serviceFee"><s:text name="lable.PageStep2On.serviceFee"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="serviceFee"><fmt:formatNumber type="currency">
													                      	  ${repairRecord.labourCost}
													                      	</fmt:formatNumber></p>
                        </div>
                </div>
                      	
                <div class="form-group">
                        <label class="control-label col-sm-4" for="shippingHanding"><s:text name="lable.PageStep2On.ShippingHanding"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="shippingHanding"><fmt:formatNumber type="currency">
													                      	  ${repairRecord.shipping}
													                      	</fmt:formatNumber></p>
                        </div>
                </div>
                
				<div class="form-group">
                        <label class="control-label col-sm-4" for="parts"><s:text name="lable.PageStep2On.parts"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="parts"><fmt:formatNumber type="currency">
													                      	  ${repairRecord.partsCost}
													                      	</fmt:formatNumber></p>
                        </div>
                </div>
                      	
				<div class="form-group">
                        <label class="control-label col-sm-4" for="costAdj"><s:text name="repairRequest.summary.cost.adj"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="costAdj"><fmt:formatNumber type="currency">
													                      	  ${repairRecord.OURADJ}
													                      	</fmt:formatNumber></p>
                        </div>
                </div>
                
				<div class="form-group">
                        <label class="control-label col-sm-4" for="gsthst"><s:text name="lable.PageStep2On.gsthst"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="gsthst"><fmt:formatNumber type="currency">
													                      	  ${repairRecord.OURGST}
													                      	</fmt:formatNumber></p>
                        </div>
                </div>

				<div class="form-group">
                        <label class="control-label col-sm-4" for="pst"><s:text name="lable.PageStep2On.pst"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="pst"><fmt:formatNumber type="currency">
													                      	  ${repairRecord.OURPST}
													                      	</fmt:formatNumber></p>
                        </div>
                </div>

				<div class="form-group">
                        <label class="control-label col-sm-4" for="totalCost"><s:text name="lable.PageStep2On.Total"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="totalCost"><fmt:formatNumber type="currency">
													                      	  ${repairRecord.totalCost}
													                      	</fmt:formatNumber></p>
                        </div>
                </div>

				<div class="form-group">
                        <label class="control-label col-sm-4" for="dueTopay"><s:text name="lable.PageStep2On.dueTopay"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="dueTopay"><fmt:formatNumber type="currency">
													                      	  ${repairRecord.OURAMT}
													                      	</fmt:formatNumber></p>
                        </div>
                </div>

				<div class="form-group">
                        <label class="control-label col-sm-4" for="firstName"><s:text name="lable.PageStep2.firstName"/>:</label>
                        <div class="col-sm-8">
                        	<p class="form-control-static" id="firstName"><c:out value="${repairRecord.OURSPTO1}"/></p>
                        </div>
                </div>

				<div class="form-group">
                        <label class="control-label col-sm-4" for="lastName"><s:text name="lable.PageStep2.lastName"/>:</label>
                        <div class="col-sm-8">
                        	<p class="form-control-static" id="lastName"><c:out value="${repairRecord.OURSPTO2}"/></p>
                        </div>
                </div>

				<div class="form-group">
                        <label class="control-label col-sm-4" for="postCode"><s:text name="lable.PageStep2.postCode"/>:</label>
                        <div class="col-sm-8">
                        	<p class="form-control-static" id="postCode"><c:out value="${repairRecord.OURSPPC}"/></p>
                        </div>
                </div>					

				<div class="form-group">
                        <label class="control-label col-sm-4" for="address"><s:text name="lable.PageStep2.address"/>:</label>
                        <div class="col-sm-8">
                        	<p class="form-control-static" id="address"><c:out value="${repairRecord.OURSPST1}"/></p>
                        </div>
                </div>

				<div class="form-group">
                        <label class="control-label col-sm-4" for="address2"><s:text name="lable.PageStep2.address"/> 2:</label>
                        <div class="col-sm-8">
                        	<p class="form-control-static" id="address2"><c:out value="${repairRecord.OURSPST2}"/></p>
                        </div>
                </div>
                
				<div class="form-group">
                        <label class="control-label col-sm-4" for="city"><s:text name="lable.PageStep2.city"/> 2:</label>
                        <div class="col-sm-8">
                        	<p class="form-control-static" id="city"><c:out value="${repairRecord.OURSPCT}"/></p>
                        </div>
                </div>
				<%--<td class="leftCol"><label id="lbl_model"><s:text name="lable.PageStep2.province"/></label></td>
                      <td class="rightCol">${provinceShipping}
??????????????????????????????????????????????????????????????????????????????????????????????????                      
							<input type="hidden" maxlength="40" style="width: 250px;"  class="form_input" 
										name="repairRecord.OURSPSTA" id="state_text" autocomplete="off"
										value = "${repairRecord.OURSPSTA}" ></td>
??????????????????????????????????????????????????????????????????????????????????????????????????
				--%>
				<div class="form-group">
                        <label class="control-label col-sm-4" for="province"><s:text name="lable.PageStep2.province"/> 2:</label>
                        <div class="col-sm-8">
                        	<p class="form-control-static" id="province"><c:out value="${provinceShipping}"/></p>
                        </div>
                </div>
										
				<div class="form-group">
                        <label class="control-label col-sm-4" for="phone"><s:text name="lable.PageStep2.phone"/> 2:</label>
                        <div class="col-sm-8">
                        	<p class="form-control-static" id="phone">${phoneAreaCode}&nbsp;-&nbsp;${phonePrefix}&nbsp;-&nbsp;${phoneLastFour}&nbsp;</p>
                        </div>
                </div>
					
				<div class="form-group">
                        <label class="control-label col-sm-4" for="emailAddress"><s:text name="lable.PageStep2.emailAddress"/>:</label>
                        <div class="col-sm-8">
                        	<p class="form-control-static" id="emailAddress"><c:out value="${repairRecord.OUREMAIL}"/></p>
                        </div>
                </div>
               				      
				<div class="form-group">
                        <label class="control-label col-sm-4" for="comments"><s:text name="lable.PageStep2.comments"/>:</label>
                        <div class="col-sm-8">
                        	<textarea class="form-control" rows="5" cols="46" maxlength="256"
									id="comments"
									readonly= "true"
									name="repairRecord.OURCLCOM"><c:out value="${repairRecord.OURCLCOM}"/></textarea>
                        </div>
                </div>
				

	                <div class="form-group form-group-last">
	                        <div class="col-sm-offset-4 col-sm-8">
	                            
				                    <c:if test="${repairRecord.routeStatus !=null}">
									    <c:choose>
									      <c:when test="${repairRecord.routeStatus == 'Track'}">
											<button type="button" class="button_example" 
						                            onClick="window.location.href='/euo/repairOnlineTrack1';;return false">
						                            &#xe801;&nbsp;&nbsp;<s:text name='label.back'/>&nbsp;
					                        </button>			
									      </c:when>
									      <c:otherwise>
											<button type="button" class="button_example" 
						                            onClick="window.location.href='/euo/repairOnlineStep2';;return false">
						                            &#xe801;&nbsp;&nbsp;<s:text name='label.back'/>&nbsp;
					                        </button>
									      </c:otherwise>
									    </c:choose>
								    </c:if>
	                            
	
									<% 	String estatus = (String) session.getAttribute("Reestimated");
										String astatus = (String) session.getAttribute("AcceptRefund");
										if (estatus.equals("true")) { %>		
										<button type="button" class="button_example"
												onclick="JavaScript:confirm_decline(); return false;"
				                            	&nbsp;<s:text name='label.decline'/>&nbsp;&nbsp;&nbsp;&#xe800;
				                        </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<%} %> 
										<% if (estatus.equals("true") && astatus.equals("true")) {%>
										<button type="button" class="button_example"
												onclick="JavaScript:confirm_acceptRefund(); return false;">
				                            	&nbsp;<s:text name='label.accept'/>&nbsp;&nbsp;&nbsp;&#xe800;
				                        </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<%} else {%>
											<!-- onclick = "submitForm();" -->
											<button type="submit" class="button_example">
				                            	&nbsp;<s:text name='title.next.step' />&nbsp;&nbsp;&nbsp;&#xe800;
				                            </button>
	
										<%} %>
	
			                      <button type="submit" class="button_example"
			                      	onclick="JavaScript:confirm_entry('<s:text name='alert.cancel'/>'); return false;">
			                      	&nbsp;<s:text name='label.cancel'/>&nbsp;
			                      </button>
	                  </div>
	                </div>


					</fieldset>
                </form>
			
			</div> <!--closing div class="panel-body" -->
		</div> <!-- closing div class="panel panel-default"-->
	</div> <!-- closing div class="panel-group"-->
<!-- Bootstrap version ends here -->    


	