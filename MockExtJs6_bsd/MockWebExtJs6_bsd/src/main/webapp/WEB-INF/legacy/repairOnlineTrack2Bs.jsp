<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"									prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="panel-group">
    <div class="panel panel-default">

        <!--label.step1.on.top.title-->
        <div class="panel-heading panelGroupHeadBkg"><s:text name="lable.PageTrack2.title"/>
            <div class="numberedSteps">
                <span class="wizard_stepOff">1</span>
                <span class="wizard_stepOn">2</span>
            </div>
        </div>

        <div class="panel-body">

	        <div class="well well-sm" style="color: #4d4d4d;">
	            <s:text name="label.track1.top" />
	        </div>

			<form class="form-horizontal" method="post"
					action="/euo/repairOnlineNewHome">
	            <fieldset>

				<div class="form-group">
					<label class="control-label col-sm-4" for="orderno"><s:text name="lable.PageStep4.orderno"/></label>
					<div class="col-sm-8">
						<p class="form-control-static" id="orderno"> ${repairRecord.orderId}</p>
					</div>
				</div>				
                  
				<div class="form-group">
					<label class="control-label col-sm-4" for="TRANSNUM"><s:text name="lable.PageStep4.transcode"/></label>
					<div class="col-sm-8">
						<p class="form-control-static" id="TRANSNUM"> ${paymentRecord.TRANSNUM}</p>
					</div>
				</div>				

				<div class="form-group">
					<label class="control-label col-sm-4" for="Model"><s:text name="lable.PageStep1On.Model"/></label>
					<div class="col-sm-8">
						<p class="form-control-static" id="Model"> ${repairRecord.OURMDNAM}</p>
					</div>
				</div>				

				<div class="form-group">
					<label class="control-label col-sm-4" for="serialNumber"><s:text name="label.serialNumber"/></label>
					<div class="col-sm-8">
						<p class="form-control-static" id="serialNumber"> ${repairRecord.OURSRNUM}</p>
					</div>
				</div>				

				<div class="form-group">
					<label class="control-label col-sm-4" for="serviceType"><s:text name="lable.PageStep1On.serviceType"/></label>
					<div class="col-sm-8">
						<p class="form-control-static" id="serviceType"> ${repairRecord.warrantStatus}</p>
					</div>
				</div>				

				<div class="form-group">
					<label class="control-label col-sm-4" for="estimateCostPlaceHolder"><s:text name="lable.PageStep1On.estimateCost"/></label>
					<div class="col-sm-8" id="estimateCostPlaceHolder">
						<%-- <p class="form-control-static" id="serviceType"> ${repairRecord.warrantStatus}</p> --%>
					</div> 
				</div>				
                  
                <div class="form-group">
                    <label class="control-label col-sm-4" for="serviceFee"><s:text name="repairRequest.summary.cost.labour"/>:</label>
                    <div class="col-sm-8">
                        <p class="form-control-static" id="serviceFee"><fmt:formatNumber type="currency">
									                      	  ${repairRecord.labourCost}
									                      	</fmt:formatNumber></p>
                    </div>
                </div>

				<div class="form-group">
                    <label class="control-label col-sm-4" for="parts"><s:text name="repairRequest.summary.cost.parts"/>:</label>
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
                        <label class="control-label col-sm-4" for="shippingHanding"><s:text name="lable.PageStep2On.ShippingHanding"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="shippingHanding"><fmt:formatNumber type="currency">
													                      	  ${repairRecord.shipping}
													                      	</fmt:formatNumber></p>
                        </div>
                </div>

				<div class="form-group">
                        <label class="control-label col-sm-4" for="subtotal"><s:text name="repairRequest.summary.cost.subtotal"/>:</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="subtotal"><fmt:formatNumber type="currency">
													                      	  ${repairRecord.subTotalCost}
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
                  
				<div class="panel panel-default"  id="paymentSection" >  <!-- style="display:none" -->
									<div class="panel-heading"><s:text name="repairRequest.summary.currentStatus"/></div>
									<div class="panel-body">
											
									<div class="form-group">
					                        <label class="control-label col-sm-4" for="repairDate"><s:text name="label.date"/>:</label>
					                        <div class="col-sm-8">
					                        	<p class="form-control-static" id="repairDate"><c:out value="${repairRecord.currentRepairDate}"/></p>
					                        </div>
					                </div>
					                  				
									<div class="form-group">
					                        <label class="control-label col-sm-4" for="repairStatus"><s:text name="label.status"/>:</label>
					                        <div class="col-sm-8">
					                        	<p class="form-control-static" id="repairStatus"><c:out value="${repairRecord.currentRepairStatus}"/></p>
					                        </div>
					                </div>
					                
					                  <div class="form-group">
					                        <label class="control-label col-sm-4" for="repairStatus"><s:text name="repairRequest.summary.shipingAndTracking"/>:</label>
					                        <div class="col-sm-8">
					                        <% if (session.getAttribute("purolator").toString().equals("true")) { %>
					                        		<p class="form-control-static" id="repairStatus">
														<a href="#" onclick="javascript:window.open('${repairRecord.currentRepairShipping}','ShipInfo','height=400,width=800,status=yes,toolbar=no,menubar=no,location=no,resizable=no'); return false;">
															<span style="color: blue"><u>
																${repairRecord.trackingInfo}&nbsp;</u>
															</span>
														</a>
													</p>
						                      <%} else{ %>
							                      	<p class="form-control-static" id="repairStatus"><c:out value="${repairRecord.currentRepairShipping}"/></p>
						                      <%} %>
					                        	
					                        </div>
					                </div>
					                  				
								</div> <!-- panel-body -->
				</div> <!-- class="panel panel-default" Current repair status panel-->

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
						
						
						<!-- <td class="rightFormNames">
					<label class="label">
						<span id="lbl_name_billing" class="midblack"><s:text name="label.userName"/>:</span>
					</label>
				</td>
				<td id = "id_card_type" class="rightFormInput">${repairRecord.OURBLTO1} &nbsp;${repairRecord.OURBLTO2}</td> -->
								<div class="form-group">
									<label class="control-label col-sm-4" for="billingUserName"><s:text name="label.userName"/>:</label>
									<div class="col-sm-8">
									    <p class="form-control-static" id="billingUserName">${repairRecord.OURBLTO1} &nbsp;${repairRecord.OURBLTO2}</p>
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
									<label class="control-label col-sm-4" for="XZzachemetoZdes"><s:text name="label.postalCode"/>:</label>
									<div class="col-sm-8">
									    <p class="form-control-static" id="XZzachemetoZdes">
											${repairRecord.OURBLPC}&nbsp;					    								
										</p>
									</div>
								</div>
											 			   
						</div> <!-- panel-body -->
					</div>	<!--  panel-default billinginfo -->
				</div> <!-- class="panel-group row" -->
				
<!-- ==================== Available Service Centers ============================== -->
				<div class="panel panel-default row" style="margin-left:8px; margin-right:8px;" > <!-- style="padding:0px;"  -->
						<div class="panel-heading"><s:text name="lable.PageTrack2.avaibale"/></div>
						<div class="panel-body">
						
						<% 
						System.out.print("debug"); 
						%>
						${repairRecord.dealerList}
						
									<table class="table table-striped">
				   						<thead>
				    						<tr>
								                <th><s:text name="lable.PageStep4.servicename"/></th>
								                <th><s:text name="label.address"/></th>
								                <th><s:text name="lable.PageStep4.contact"/></th>
										     </tr>
				   						</thead>
										<tbody> 
										<c:forEach var="cat" items="${repairRecord.dealerList}"> 
    											<tr>
														<td>${cat.ccDealerName}</td>
														<td>${cat.address1}${cat.city}&nbsp;${cat.ccProvince}&nbsp;${cat.postal}
														</td>
														<td>${cat.phone}</td>
												</tr>
										</c:forEach>
										
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
				</div> <!-- panel panel-default Available Service Centers-->



<!-- ************************************ -->

					<div class="form-group form-group-last">
	                        <div class="col-sm-offset-4 col-sm-8">
	                            <button type="button" class="button_example" 
		                            onClick="window.location.href='/euo/repairOnlineTrack1Bs';return false">
		                            &#xe801;&nbsp;&nbsp;<s:text name='label.back'/>&nbsp;
	                            </button>
	
	                            <button type="button" class="button_example" onclick ="JavaScript:printFriendly(); return false;">
	                            	&nbsp;<s:text name='label.print.confirmation' />&nbsp;&nbsp;&nbsp;&#xe800;
	                            </button>
	                        </div>
	                </div>
					</fieldset>
			</form>
		</div> <!-- class="panel-body" -->

    </div> <!-- class="panel panel-default" -->
</div>	<!-- class="panel-group" -->


<span class="midblack" id="ErrorSummary"></span>

<script type="text/javascript">
window.onload = function() 
{
	if ('${repairRecord.isCanpay}' == 'Y') {
		window.location.href='/euo/repairOnlineStep2On';
	}
}
</script>