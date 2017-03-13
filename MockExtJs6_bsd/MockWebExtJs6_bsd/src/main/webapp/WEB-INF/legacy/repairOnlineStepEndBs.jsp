<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"									prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script language="JavaScript">
	function printConfirm() {
		printFriendly();
	}	
</script>

<h3>AVP repairOnlineStepEndBs.jsp</h3>
<!-- Bootstrap version starts here -->
    <div class="panel-group">
        <div class="panel panel-default">

            <div class="panel-heading panelGroupHeadBkg"><s:text name="lable.PageStep4.Title"/>
                <div class="numberedSteps">
                    <span class="wizard_stepOff">1</span>
                    <span class="wizard_stepOff">2</span>
                    <span class="wizard_stepOff">3</span>
                    <span class="wizard_stepOn">4</span>
                </div>
            </div>

			<div class="panel-body">



			<p>
			  <div class="well well-sm" style="color: #4d4d4d;"> <!--font-weight:bold;-->
		                        <%-- <s:text name="label.step2.top.title" /> --%>
		                        <span style="FONT-WEIGHT: bold; FONT-FAMILY:Arial;FONT-SIZE: 12pt;color:#666666">
		                        <s:text name="label.stepend.top.title" />
		                        </span>
		                        <p/><s:text name="label.stepend.top.msg" />
		      </div>
			</p>
			<%-- <span class="midblack" id="ErrorSummary" style="padding:52px;"></span> --%>
			<form class="form-horizontal"
			                		id="track_existing_repair"
					                method="post"  
									action="/euo/repairOnlineHome">

<!-- --------------------------------  Repair Information Section  -------------------------------- -->

								<%--<td class="leftCol" ><label id="lbl_model"><s:text name="lable.PageStep4.orderno"/></label></td>
				                     
				                      <td class="rightCol" ><c:out value = "${repairRecord.orderId}" />&nbsp;
				                      	<div style="display:none"> 
??????	                      							<s:textarea key="txtArea" readonly= "true"
??????																name="repairRecord.OURCLCOM"
??????																cols="46" rows="0" />
										</div></td>--%>


							<div class="panel panel-default">
							  <div class="panel-body">

								<div class="form-group">
				                        <label class="control-label col-sm-4" for="orderno"><s:text name="lable.PageStep4.orderno"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="orderno"><c:out value = "${repairRecord.orderId}" /></p>
				                        </div>
				                        <!-- ???????????????????????????????????????????????????????? -->
				                        <div style="display:none"> 
	                      							<s:textarea key="txtArea" readonly= "true"
																name="repairRecord.OURCLCOM"
																cols="46" rows="0" />
										</div>
										<!-- ???????????????????????????????????????????????????????? -->
				                </div>
				                  
								<div class="form-group">
				                        <label class="control-label col-sm-4" for="transcode"><s:text name="lable.PageStep4.transcode"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="transcode"> ${paymentRecord.TRANSNUM}</p>
				                        </div>
				                </div>
				                  
								<div class="form-group">
				                        <label class="control-label col-sm-4" for="model"><s:text name="lable.PageStep1On.Model"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="model"> ${repairRecord.OURMDNAM}</p>
				                        </div>
				                </div>				                  

								<div class="form-group">
				                        <label class="control-label col-sm-4" for="serialNumber"><s:text name="label.serialNumber"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="serialNumber"> <c:out value="${repairRecord.OURSRNUM}"/></p>
				                        </div>
				                </div>				                  
				                  
				                <div class="form-group">
				                        <label class="control-label col-sm-4" for="serviceType"><s:text name="lable.PageStep1On.serviceType"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="serviceType"> ${repairRecord.warrantStatus}</p>
				                        </div>
				                </div>  

				                <div class="form-group">
				                        <label class="control-label col-sm-4"><s:text name="lable.PageStep1On.estimateCost"/></label>
				                        <div class="col-sm-8">
				                        </div>
				                </div>  
				                  
				                <div class="form-group">
				                        <label class="control-label col-sm-4" for="serviceFee"><s:text name="lable.PageStep2On.serviceFee"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="serviceFee"> <fmt:formatNumber type="currency">
																		                      	  ${repairRecord.labourCost}
																		                      	</fmt:formatNumber></p>
				                        </div>
				                </div>  

				                <div class="form-group">
				                        <label class="control-label col-sm-4" for="ShipAndHandle"><s:text name="label.PageFooter.ShipAndHandle"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="ShipAndHandle"> <fmt:formatNumber type="currency">
																		                      	  ${repairRecord.shipping}
																		                      	</fmt:formatNumber></p>
				                        </div>
				                </div>
				                <div class="form-group">
				                        <label class="control-label col-sm-4" for="partsfee"><s:text name="lable.PageStep4.partsfee"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="partsfee"> <fmt:formatNumber type="currency">
																		                      	  ${repairRecord.partsCost}
																		                      	</fmt:formatNumber></p>
				                        </div>
				                </div>
				                  
				                <div class="form-group">
				                        <label class="control-label col-sm-4" for="gsthst"><s:text name="lable.PageStep2On.gsthst"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="gsthst"> <fmt:formatNumber type="currency">
																		                      	  ${repairRecord.OURGST}
																		                      	</fmt:formatNumber></p>
				                        </div>
				                </div>
				                  
				                <div class="form-group">
				                        <label class="control-label col-sm-4" for="pst"><s:text name="lable.PageStep2On.pst"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="pst"> <fmt:formatNumber type="currency">
																		                      	  ${repairRecord.OURPST}
																		                      	</fmt:formatNumber></p>
				                        </div>
				                </div>
				                  
 								<div class="form-group">
				                        <label class="control-label col-sm-4" for="total"><s:text name="lable.PageStep2On.Total"/></label>
				                        <div class="col-sm-8">
				                            <p class="form-control-static" id="total"> <fmt:formatNumber type="currency">
																		                      	  ${repairRecord.totalCost}
																		                      	</fmt:formatNumber></p>
				                        </div>
				                </div>
				                
					  			</div> <!-- class="panel-body" -->
							</div> <!-- class="panel panel-default" -->
				                
				                
				                <br>
						<!-- --------------------------------  Payment Section -------------------------------- -->
								<div class="panel panel-default"  id="paymentSection" style="display:none">
									<div class="panel-heading"><s:text name="lable.PageStep4.payment"/></div>
									<div class="panel-body">
											<div class="form-group">
							                        <label class="control-label col-sm-4" for="cardType"><s:text name="lable.PageStep3.cardType"/></label>
							                        <div class="col-sm-8">
							                            <p class="form-control-static" id="cardType">${paymentInfo.type}</p>
							                        </div>
							                </div>
											  
						          			<div class="form-group">
							                        <label class="control-label col-sm-4" for="cardNumber"><s:text name="lable.PageStep3.cardNumber"/></label>
							                        <div class="col-sm-8">
							                            <p class="form-control-static" id="cardNumber">
								                            <c:if test="${paymentInfo.number !=null}">
							                             			<c:set var="cardNum" value="${paymentInfo.number}"/>
							                             			xxxxxx<c:out value="${fn:substring(paymentInfo.cardNumberMasked, 12,-4)}"/>
										           			</c:if>
							                            </p>
							                        </div>
							                </div>
								  
											<div class="form-group">
							                        <label class="control-label col-sm-4" for="expireDate"><s:text name="lable.PageStep3.expireDate"/>:</label>
							                        <div class="col-sm-8">
							                            <p class="form-control-static" id="expireDate">${paymentInfo.expMonth}&#8218;&nbsp;${paymentInfo.expYear}</p>
							                        </div>
							                </div>
									</div> <!-- panel-body -->
								</div> <!-- class="panel panel-default" -->
				<br>                
<!-- **************************  Shipping Information  ***  Billing Information  **************************  -->
				<div class="panel-group row" style="margin-left:8px; margin-right:8px;"><!-- class="col-sm-12" -->
					<div class="panel panel-default col-sm-6" style="padding:0px;">
						<div class="panel-heading"><s:text name="lable.PageStep4.shippinginfo"/></div>
						<div class="panel-body">
				
						<div class="form-group">
							<label class="control-label col-sm-4" for="id_name_shipping"><s:text name="label.userName"/>:</label>
							<div class="col-sm-8">
							    <p class="form-control-static" id="id_name_shipping">${repairRecord.OURSPTO1} &nbsp;${repairRecord.OURSPTO2}</p>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-4" for="id_address_shpping"><s:text name="label.shippingAddress"/>:</label>
							<div class="col-sm-8">
							    <p class="form-control-static" id="id_address_shpping">${repairRecord.OURSPST1}&nbsp;${repairRecord.OURSPST2}&nbsp;<br>${repairRecord.OURSPCT}&nbsp;${provinceShipping}&nbsp;${repairRecord.OURSPPC}</p>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-4" for="emailAddress_shpping"><s:text name="lable.PageStep2.emailAddress"/>:</label>
							<div class="col-sm-8">
							    <p class="form-control-static" id="emailAddress_shpping"><c:if test="${repairRecord.OUREMAIL != 'SQS'}">
																						${repairRecord.OUREMAIL}&nbsp;
											               				      			</c:if>
								</p>
							</div>
						</div>
						</div> <!-- panel-body -->
					</div> <!--  panel-default shipinginfo -->
					
					<div class="panel panel-default col-sm-6"  style="padding:0px;" >
						<div class="panel-heading"><s:text name="lable.PageStep4.billinginfo"/></div>
						<div class="panel-body">
						
								<div class="form-group">
									<label class="control-label col-sm-4" for="billingUserName"><s:text name="label.userName"/>:</label>
									<div class="col-sm-8">
									    <p class="form-control-static" id="billingUserName">
									    								<c:choose>
																	      <c:when test="${repairRecord.OURBLTO1 == null}">
																				<% int n; for(n=25; n>0; n--)  {%>  &nbsp; <% } %> 
																	      </c:when>
																	      <c:otherwise>
																				${repairRecord.OURBLTO1} &nbsp;${repairRecord.OURBLTO2}
																	      </c:otherwise>
																	    </c:choose>
										</p>
									</div>
								</div>
				
								<div class="form-group">
									<label class="control-label col-sm-4" for="billingAddress"><s:text name="label.billingAddress"/></label>
									<div class="col-sm-8">
									    <p class="form-control-static" id="billingAddress">
											${repairRecord.OURBLST1}&nbsp;${repairRecord.OURBLST2}&nbsp;<br>${repairRecord.OURBLCT}&nbsp;${provinceBilling}&nbsp;${repairRecord.OURBLPC}					    								
										</p>
									</div>
								</div>
				
								<div class="form-group">
									<label class="control-label col-sm-4" for="XZzachemetoZdes">:</label>
									<div class="col-sm-8">
									    <p class="form-control-static" id="XZzachemetoZdes">
											${repairRecord.OURBLPC}&nbsp;					    								
										</p>
									</div>
								</div>
											 			   
						</div> <!-- panel-body -->
					</div>	<!--  panel-default billinginfo -->
				</div> <!-- class="panel-group row" -->
				
<!-- ==================== PLEASE SHIP YOUR PRODUCT TO THE ADDRESS BELOW  ============================== -->
				<div class="panel panel-default row" style="margin-left:8px; margin-right:8px;" > <!-- style="padding:0px;"  -->
						<div class="panel-heading"><s:text name="lable.PageStep4.comments"/></div>
						<div class="panel-body">
									<table class="table table-striped">
				   						<thead>
				    						<tr>
								                <th><s:text name="lable.PageStep4.servicename"/></th>
								                <th><s:text name="label.address"/></th>
								                <th><s:text name="lable.PageStep4.contact"/></th>
										     </tr>
				   						</thead>
										<tbody> 
										    <s:iterator var="cat" value="repairRecord.dealerList" status="stat">
																<tr>
																	<td>${cat.ccDealerName}</td>
																	<td>${cat.address1}${cat.city}&nbsp;${cat.ccProvince}&nbsp;${cat.postal}
																	</td>
																	<td>${cat.phone}</td>
																</tr>	    
										    </s:iterator>			        	
										</tbody>
									</table>
						</div> <!-- panel-body -->
				</div> <!-- panel panel-default -->
				<br>
				<div class="panel panel-default"  style="margin-left:8px; margin-right:8px;" id="paymentSection" style="display:none">
					<div class="panel-heading"><s:text name="label.stepend.bottom.title" /></div>
					<div class="panel-body">
						<s:text name="label.stepend.bottom.msg" />		
					</div> <!-- panel-body -->
				</div> <!-- panel panel-default -->

				<div class="form-group form-group-last">
				        <div class="col-sm-offset-4 col-sm-8">
							<button type="button" class="button_example" onclick ="JavaScript:printFriendly(); return false;">
								<s:text name='label.print.confirmation'/>&nbsp;&nbsp;&nbsp;&#xe800;
							</button>
							<button type="button" class="button_example" onclick="JavaScript:backhome(); return false;">
								<s:text name='label.home'/>&nbsp;&nbsp;&nbsp;&#xe800;
					        </button>
						</div>
				</div>
									
			</form>

			</div> <!--closing div class="panel-body" -->
		</div> <!-- closing div class="panel panel-default"-->
	</div> <!-- closing div class="panel-group"-->
<!-- Bootstrap version ends here -->    

<script type="text/javascript">	
window.onload = function() 
{
	var errorMsgDis = ${repairRecord.totalCost}; 
	//alert(errorMsgDis.indexOf("$0.00"));
	
	var paymentSection = document.getElementById("paymentSection");
	if (errorMsgDis != 0.0) {
		paymentSection.style.display = 'block';
	}else {
		paymentSection.style.display = 'none';
	}
}
</script>	