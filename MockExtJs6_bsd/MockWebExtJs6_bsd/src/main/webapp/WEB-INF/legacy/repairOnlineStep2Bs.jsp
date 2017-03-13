<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"									prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>

<%@page import="java.util.Locale"%>
<%
	String lang = ((Locale)session.getAttribute("locale")).getLanguage();
%>

<script type="text/javascript" src="/euo/resources/js/repairOnlineStep2Bs.js"></script>
<script type="text/javascript" src="/euo/resources/js/postalCodeToObtainAddressBs.js"></script>

<!-- Bootstrap version starts here -->
    <div class="panel-group">
        <div class="panel panel-default">

            <div class="panel-heading panelGroupHeadBkg"><s:text name="lable.PageStep2.Title"/>
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
		                        <p/><s:text name="label.step2.top.msg.1" />
		                        <p/><s:text name="label.step2.top.msg.2" />
		      </div>
			</p>
			
			<span class="midblack" id="ErrorSummary" style="padding:52px;"></span>
			
			<form class="form-horizontal" data-toggle="validator"
                		id="track_existing_repair"
		                method="post"  
						action="/euo/repairOnlineStep2OnBs">
					
					<input type="HIDDEN" id="LANGUAGE" name="LANGUAGE" value="<%= lang%>">

					<fieldset>
				
				<div class="form-group required">
                        <label class="control-label col-sm-4" for="firstName_text"><s:text name="lable.PageStep2.firstName"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                            	name="repairRecord.OURSPTO1" id="firstName_text"
                                required
                                value = "${repairRecord.OURSPTO1}">
                            
                        </div>
                </div>
				
                <div class="form-group required">
                        <label class="control-label col-sm-4" for="lastName_text"><s:text name="lable.PageStep2.lastName"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                            	name="repairRecord.OURSPTO2" id="lastName_text" 
                                required
                                value = "${repairRecord.OURSPTO2}">
                		</div>
                </div>
                

				<div class="form-group required">
                        <label class="control-label col-sm-4" for="postCode"><s:text name="lable.PageStep2.postCode"/>:</label>
                        
                        <!--TODO - <AP> need new error message with french translation 
                        	error.PageStep2.postCodeInvalid = postal code format A9A 9A9 
                        	TODO - <AP> autocomplete="off"
                        	-->
                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                            	name="repairRecord.OURSPPC" id="postCode" 
                            	pattern="[A-Za-z][0-9][A-Za-z]\s{0,1}[0-9][A-Za-z][0-9]"
                            	data-error="<s:text name="lable.PageStep2.postCodeInvalid"/>"
								value ="${repairRecord.OURSPPC}" 
                                required>
                            <div class="help-block with-errors"></div>
                		</div>
                		<div class="col-sm-4">
                            <button type="button" class="btn btn-default"
									onclick="JavaScript: postalCodeToObtainAddressBs('shipping', document.getElementById('LANGUAGE').value); return false;"><s:text name="lable.PageStep2.btnGet"/>
							</button>
                		</div>
                </div>


                <div class="form-group required">
                        <label class="control-label col-sm-4" for="address_text"><s:text name="lable.PageStep2.address"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                            	name="repairRecord.OURSPST1" id="address_text" 
								value = "${repairRecord.OURSPST1}">
                		</div>
                </div>


                <div class="form-group">
                        <label class="control-label col-sm-4" for="address2_text"><s:text name="lable.PageStep2.address"/> 2:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                            			name="repairRecord.OURSPST2" id="address2_text"
										value = "${repairRecord.OURSPST2}">
                		</div>
                </div>

                <div class="form-group required">
                        <label class="control-label col-sm-4" for="city_text"><s:text name="lable.PageStep2.city"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                            			name="repairRecord.OURSPCT" id="city_text"
										value = "${repairRecord.OURSPCT}">
                		</div>
                </div>


                <div class="form-group required">
                        <label class="control-label col-sm-4" for="province_text"><s:text name="lable.PageStep2.province"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                            			name="provinceShipping" id="province_text"
										value ="${provinceShipping}">
                		</div>
                </div>

				<div class="form-group required">
                        <label class="control-label col-sm-4" for="phone1_text"><s:text name="lable.PageStep2.phone"/>:</label>
                        <div class="col-sm-8">
	                        <div class="col-sm-3">
	                            <input type="text" class="form-control"
	                            			name="phoneAreaCode" id="phone1_text" 
	                            			pattern="^\d{3}$" required data-error="area code must be 3 digits"
											value ="${phoneAreaCode}">
	                		</div>
	                		<div class="col-sm-3">
	                            <input type="text" class="form-control"
	                            			name="phonePrefix" id="phone2_text" 
	                            			pattern="^\d{3}$" required data-error="phone prefix must be 3 digits"
											value ="${phonePrefix}">
	                		</div>
	                		<div class="col-sm-6">
	                            <input type="text" class="form-control"
	                            			name="phoneLastFour" id="phone3_text" 
	                            			pattern="^\d{4}$" required required data-error="phone must be 4 digits"
											value ="${phoneLastFour}">
	                		</div>
	                		<div class="help-block with-errors"></div>
                		</div>
                		
                </div>

                <div class="form-group required">
                        <label class="control-label col-sm-4" for="email_text"><s:text name="lable.PageStep2.emailAddress"/>:</label>
                        <div class="col-sm-8">
                            <input type="email" class="form-control"
                            			name="repairRecord.OUREMAIL" id="email_text" 
										value = "${repairRecord.OUREMAIL}"
										data-error="That email address is invalid. A vaild email address is needed to update repair status.">
							<div class="help-block with-errors"></div>
                		</div>
                </div>

				<!-- TODO - <AP> autocomplete="off" -->
                <div class="form-group required">
                        <label class="control-label col-sm-4" for="emailConfirm_text"><s:text name="lable.PageStep2.confirmEmail"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                            			name="emailConfirm" id="emailConfirm_text" 
                            			data-match="#email_text" data-match-error="Whoops, confirm email don't match"
										value = "${emailConfirm}">
							<div class="help-block with-errors"></div>
                		</div>
                </div>


                
                <div class="well well-sm" style="color: #4d4d4d; font-weight:bold;"> <!--font-weight:bold;-->
		                        <s:text name="lable.PageStep2.statusText"/>
		      	</div>
                
                <div class="form-group required">
                        <label class="control-label col-sm-4" for="commentArea"><s:text name="lable.PageStep2.comments"/>:</label>
                        <div class="col-sm-8">
                            <textarea class="form-control" rows="5" cols="46" maxlength="256"
									id="commentArea"
									onKeyDown="limitText(this,256);" onKeyUp="limitText(this,256);"
									name="repairRecord.OURCLCOM"></textarea>
                		</div>
                </div>
                
                <div class="form-group form-group-last">
                        <div class="col-sm-offset-4 col-sm-8">
                            <button type="button" class="button_example" 
	                            onClick="window.location.href='/euo/repairOnlineStep1OnBs?backflag=true';return false">
	                            &#xe801;&nbsp;&nbsp;<s:text name='label.back'/>&nbsp;
                            </button>

                            <button type="submit" class="button_example" 
                            onclick = "submitForm();">
                            	&nbsp;<s:text name='title.next.step' />&nbsp;&nbsp;&nbsp;&#xe800;
                            </button>
                            
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
    
    
    
    

