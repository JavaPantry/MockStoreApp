<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="/struts-tags"						prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>

<%@page import="ca.canon.euo.constants.GlobalConstant"%>
<%@page import="java.util.Locale"%>

<%
	String lang = ((Locale)session.getAttribute("locale")).getLanguage();
%>
<s:set var="LANG_CD"><%= lang%> </s:set>
<s:set var="LANG_EN"><%= GlobalConstant.LANGUAGE_EN%> </s:set>


<script type="text/javascript" src="/euo/resources/js/repairOnlineStep1BsNew.js"></script>


<%-- AP - dropdowns for family, lines and products are removed from repairOnlineHomeNewBs.jsp 
<script language=Javascript type=text/javascript>
	//DynDropDown_desc('<s:text name="label.choose.family"/>', '<s:text name="label.choose.series"/>', '<s:text name="label.choose.model"/>');
</script>
 --%>
<!-- 
Bootstrap Typeahead -> http://www.tutorialrepublic.com/twitter-bootstrap-tutorial/bootstrap-typeahead.php  
typeahead.bundle.js  -> https://github.com/twitter/typeahead.js/tree/master/dist
 -->
<script  type="text/javascript" src="/euo/node_modules/typeahead.bundle.js"></script>

<%-- <script type="text/javascript">
$(document).ready(function(){
    // Defining the local dataset and typeahead
});  
</script> --%>



<style type="text/css">

    .twitter-typeahead {
        width: 100%;
        position: relative;
    }


    .typeahead, .tt-query, .tt-hint {
        border: 2px solid #CCCCCC;
        /* border-radius: 8px; */
        font-size: 12px; /* Set input font size */
        height: 30px;
        line-height: 30px;
        outline: medium none;
        padding: 8px 12px;
        width: 100%;
    }
    .typeahead {
        background-color: #FFFFFF;
    }
    .typeahead:focus {
        border: 2px solid #0097CF;
    }
    .tt-query {
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
    }
    .tt-hint {
        color: #999999;
    }
    .tt-menu {
        background-color: #FFFFFF;
        border: 1px solid rgba(0, 0, 0, 0.2);
        border-radius: 8px;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
        margin-top: 2px;
        padding: 8px 0;
        width: 422px;
    }
    .tt-suggestion {
        font-size: 12px;  /* Set suggestion dropdown font size */
        padding: 3px 20px;
    }
    .tt-suggestion:hover {
        cursor: pointer;
        background-color: #0097CF;
        color: #FFFFFF;
    }
    .tt-suggestion p {
        margin: 0;
    }
    #scrollable-dropdown-menu .tt-menu {
        max-height: 150px;
        overflow-y: auto;
    }
</style>

<%-- AP - dropdowns for family, lines and products are removed from repairOnlineHomeNewBs.jsp    
		<s:iterator var="cat" value="levelFamilies" status="stat">
			<s:if test='#LANG_CD==#LANG_EN'>
			      <script language=Javascript type=text/javascript>
			      families.AddRow( "${cat.productId}", -1, "<c:out escapeXml="false" value="${cat.ccWebName}"/>", "");
			      </script>	    
			</s:if><s:else>
			      <script language=Javascript type=text/javascript>
			      families.AddRow( "${cat.productId}", -1, "<c:out escapeXml="false" value="${cat.ccWebFName}"/>", "");
			      </script>	    
			 </s:else>
	    </s:iterator>
	    
	    <s:iterator var="cat" value="levelLines" status="stat">
			<s:if test='#LANG_CD==#LANG_EN'>
			      <script language=Javascript type=text/javascript>
					lines.AddRow("${cat.productId}", "${cat.parentProdId}", "<c:out escapeXml="false" value="${cat.ccWebName}"/>", "");
				      </script>
			</s:if><s:else>
			      <script language=Javascript type=text/javascript>
					lines.AddRow("${cat.productId}", "${cat.parentProdId}", "<c:out escapeXml="false" value="${cat.ccWebFName}"/>", "");
				      </script>
			 </s:else>
	    </s:iterator>
	    
	    <s:iterator var="cat" value="levelProducts" status="stat">
			<s:if test='#LANG_CD==#LANG_EN'>
			      <script language=Javascript type=text/javascript>
			      	tempStr = "DD";
			      	products.AddRow("${cat.productId}", "${cat.parentProdId3}", "<c:out escapeXml="false" value="${cat.descr}"/>", tempStr);
			      </script>
			</s:if><s:else>
			      <script language=Javascript type=text/javascript>
			      	tempStr = "DD";
			      	products.AddRow("${cat.productId}", "${cat.parentProdId3}", "<c:out escapeXml="false" value="${cat.descrFr}"/>", tempStr);
			      </script>
			 </s:else>
	    </s:iterator>
 --%>
 
<!-- Bootstrap version starts here -->

<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">Repair Request</a></li>
    <li><a data-toggle="tab" href="#trackexistingRepairTab">Track existing Repair</a></li>
</ul>

<div class="tab-content">
    <div id="home" class="tab-pane fade in active">
		        <div class="panel-group">
		        <div class="panel panel-default">
		
		            <div class="panel-heading panelGroupHeadBkg">Step1. Request Estimate
		                <div class="numberedSteps">
		                    <span class="wizard_stepOn">1</span>
		                    <span class="wizard_stepOff">2</span>
		                    <span class="wizard_stepOff">3</span>
		                    <span class="wizard_stepOff">4</span>
		                </div>
		            </div>
		
					<div class="panel-body">
		
		                <form id="new_repair_form_tab" class="form-horizontal" data-toggle="validator"
		                		role="form"
		                		name=pulldowns
				                method="post"  
								action="/euo/repairOnlineStep1OnBs">
		
								<%-- 
								onsubmit="return submitNewRepairFormFromNewHome();"
								 --%>
								
								<input type="HIDDEN" id="LANGUAGE" name="LANGUAGE" value="<%= lang%>">
								<input type="HIDDEN" name="URL" value="">
								<input type="HIDDEN" name="act" value="">
								<input type="HIDDEN" name="actiontype" value="">
								<input type="HIDDEN" name="contrl" value="os">
								<input type="HIDDEN" name="itemcode" value="">
		
		
		
		                    <fieldset>
		                    
							<div class="form-group required">
								<label class="control-label required col-sm-4" for="ProductTypeahead"><s:text name="lable.PageStep1.chooseModel"/>:</label>
								<div id="scrollable-dropdown-menu" class="col-sm-8">
						        	<input type="text" id="ProductTypeahead" name="productNameInput" class="form-control typeahead" autocomplete="off" spellcheck="false"
						        	placeholder="Type your Product"
                                    data-error="Product required" required>
                                    <div class="help-block with-errors"></div>
						        </div>
						    </div>
							<input type="hidden" name="productId" id="productId"/>
							<input type="hidden" name="groupId" id="groupId"/>
            				<input type="hidden" name="productName" id="productName"/>
							
							
		                    <%-- <div class="form-group required">
		                        <label class="control-label required col-sm-4" for="Families"><s:text name="lable.PageStep1.chooseFamily"/>:</label>
		                        <div class="col-sm-8">
		                            <select class="form-control" required 
		                            		id="Families" name="Families"  
		                            		onChange="javascript:ddd.PopulateMenu(getSelectedIndex(this), lines, baseForm.Lines);"
		                            		placeholder="<s:text name='title.dropdown.family'/>"
		                            		data-error="Please specify product family">
		                            		
		                            		<option></option>
		                            </select>
		                            <div class="help-block with-errors"></div>
		                        </div>
		                    </div>
		
		
		                    <div class="form-group required">
		                        <label class="control-label col-sm-4" for="Lines"><s:text name="lable.PageStep1.chooseSeries"/>:</label>
		                        <div class="col-sm-8">
		                            <select class="form-control" 
		                            		id="Lines" name="Lines"
		                            		onChange="javascript:ddd.PopulateMenu(getSelectedIndex(this), products, baseForm.Products);"
		                            		placeholder="<s:text name='title.dropdown.series'/>"
		                            		data-error="Please specify series"  required>
		                            </select>
		                            <div class="help-block with-errors"></div>
		                        </div>
		                    </div>
		
		                    <div class="form-group required">
		                        <label class="control-label col-sm-4" for="Products"><s:text name="lable.PageStep1.chooseModel"/>:</label>
		                        <div class="col-sm-8">
		                            <select class="form-control" 
		                            		id="Products" name="Products" 
		                            		onChange="javascript:DynDropDown_RnFlag(getSelectedIndex(baseForm.Products), products);"
		                            		placeholder="Enter model"
		                            		data-error="Please specify model"  required>
		                            </select>
		                            <div class="help-block with-errors"></div>
		                        </div>
		                    </div> --%>
		
							<!-- pattern="^[_A-z0-9]{1,}$" maxlength="15" minlength="5" required --> 
		                    <div class="form-group">
		                        <label class="control-label col-sm-4" for="serialNo"><s:text name="lable.PageStep1.chooseSerial"/>:</label>
		                        <div class="col-sm-8">
		                            <input type="text" class="form-control"
		                                   id="serialNo" name="repairRecord.OURSRNUM"
		                                   value = "${repairRecord.OURSRNUM}"
		                                   placeholder="<s:text name='title.serial.number'/>"
		                                   pattern="^[_A-z0-9]{1,}$"
		                                   minlength="5"
		                                   data-error="Serial number must be at least 5 alphaNumeric characters">
		                            <div class="help-block with-errors"></div>
		                        </div>
		                    </div>
		
		<%--                     <div class="form-group">
		                        <label class="control-label col-sm-4" for="purchaseDate">Native Purchase Date:</label>
		                        <div class="input-group col-sm-8" id="purchaseDate">
		                            <div class="input-group-addon">
		                                <span class="glyphicon glyphicon-calendar"></span>
		                            </div>
		                            <!--<input type="text" class="date-picker form-control" data-date-start-view="decade" data-date-format="mm/dd/yy" data-date="02/01/99" value="02/01/99" name="birthday" placeholder="MM/DD/YY" />-->
		                            <input type="text" class="date-picker form-control" data-date-format="mm/dd/yy" name="purchaseDate" placeholder="MM/DD/YY" />
		                        </div>
		                    </div> --%>
		
		                    <div class="form-group">
		                        <label class="control-label col-sm-4" for="purchaseDate">Bootstrap Purchase Date:</label>
		                        <!-- define '.input-group#purchaseDate2' in resources/css/global_new.css -->
		                        <input type="HIDDEN" name="bsFlag" value="BS">
		                        <div class="input-group date col-sm-8" id="purchaseDate">
		                            <input	type="text" class="form-control"
		                            		id='orderStartDate' value = "orderStartDate" name="orderStartDate" 
		                            		data-date-format="mm/dd/yy" placeholder="MM/DD/YY" >
		                            <div class="input-group-addon">
		                                <span class="glyphicon glyphicon-calendar"></span>
		                            </div>
		                        </div>
		                    </div>
		
		                    <div class="well well-sm" style="font-weight:bold; color: #4d4d4d;"><s:text name="lable.PageStep1.content"/></div>
		
		                    <div class="form-group form-group-last">
		                        <div class="col-sm-offset-4 col-sm-8">
		                            <button type="button" class="button_example" onClick="window.location.href='/euo/repairOnlineStep1';return false">TRACK EXISTING REPAIR&nbsp;&nbsp;&#xe800;</button>
		                            <button type="button" class="button_example" onClick="window.location.href='/euo/repairOnlineStep1';return false">&nbsp;HOME&nbsp;</button>
		
									<%-- onclick="javascript:euoSearchitem(document.forms[0], getSelectedIndex(baseForm.Products), products, '<%= lang%>');" --%>
		                            <button type="submit" class="btn button_example">
		                            		<s:text name='title.next.step'/>&nbsp;&nbsp;&#xe800;
		                            </button>  <!-- NEXT STEP -->
		
		                        </div>
		                    </div>
		
		                    </fieldset>
		                </form>
		
					</div> <!--closing div class="panel-body" -->
		        </div> <!-- closing div class="panel panel-default"-->
		    </div> <!-- closing div class="panel-group"-->
    
    </div> <!-- home tab -->
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <div id="trackexistingRepairTab" class="tab-pane fade">

			<script language="JavaScript">
			   	function submitTrackForm() {
					var frmVal = new FormValidator(false, "midredbold", "midblack");		
					var noticeMsg = "";
					var langLoc = document.getElementById('LANGUAGE').value;
					
					//check postcode
					if (!document.getElementById('email_text').disabled &&
				    		 document.getElementById('email_text').value == '') {
				 			document.getElementById('lbl_emailAddress').innerHTML = '<label id="lbl_emailAddress" class="midredbold"><s:text name="lable.PageStep2.emailAddress"/></label>';
				     }else {
				 			document.getElementById('lbl_emailAddress').innerHTML = '<label id="lbl_emailAddress" class="midblack"><s:text name="lable.PageStep2.emailAddress"/></label>';
				     }
			        if (!document.getElementById('postCode').disabled) {				
						if (!isNorPostCode(document.getElementById('postCode').value)) {
							document.getElementById('lbl_postCode').innerHTML = '<label id="lbl_postCode" class="midblack"><s:text name="lable.PageStep2.postCode"/></label>';
						}else {
							document.getElementById('lbl_postCode').innerHTML = '<label id="lbl_postCode" class="midredbold"><s:text name="lable.PageStep2.postCode"/></label>';			
						}
			        }
					
			        if(langLoc == "en") {
				    		frmVal.addValidator(new RequiredFieldValidator('repairNo', 'lbl_repairNo',  'Repair No is required', langLoc));		
				   	     if (!document.getElementById('email_text').disabled) {
				   	 		frmVal.addValidator(new RequiredFieldValidator('emailAddress', 'lbl_emailAddress', 'Email Address is required', langLoc));			
				   	     }
				   	     if (!document.getElementById('postCode').disabled) {
				   			frmVal.addValidator(new RequiredFieldValidator('zipCode', 'lbl_postCode',  'Postal Code is required', langLoc));
				   	     }
			        }else{
				    		frmVal.addValidator(new RequiredFieldValidator('repairNo', 'lbl_repairNo',  'Aucune r\u00E9paration n\'est n\u00E9cessaire', langLoc));		
				   	     if (!document.getElementById('email_text').disabled) {
				   	 		frmVal.addValidator(new RequiredFieldValidator('emailAddress', 'lbl_emailAddress', 'Adresse Email est requis', langLoc));			
				   	     }
				   	     if (!document.getElementById('postCode').disabled) {
				   			frmVal.addValidator(new RequiredFieldValidator('zipCode', 'lbl_postCode',  'Code postal est requis', langLoc));
				   	     }
			        	
			        }
					frmVal.init('track_existing_repair','ErrorSummary');	
					frmVal.reset();
					var result = frmVal.validate();	
					if(result){
						//alert("ErrorSummary==" + document.getElementById('ErrorSummary').innerHTML);
						
						if ( document.getElementById('ErrorSummary').innerHTML == ''){
							//check email:dalton
						if (!document.getElementById('email_text').disabled &&
					    		 document.getElementById('email_text').value == '') {
					    	 	noticeMsg += "<span class='midredbold'><s:text name='lable.PageTrack1.emailrequire'/></span>";
					 			document.getElementById('lbl_emailAddress').innerHTML = '<label id="lbl_emailAddress" class="midredbold"><s:text name="lable.PageStep2.emailAddress"/></label>';
					     }else {
					 			document.getElementById('lbl_emailAddress').innerHTML = '<label id="lbl_emailAddress" class="midblack"><s:text name="lable.PageStep2.emailAddress"/></label>';
					     }			
						
							//check postcode
				        if (!document.getElementById('postCode').disabled) {				
							if (isNorPostCode(document.getElementById('postCode').value)) {
					    	 	noticeMsg += "<span class='midredbold'><s:text name='lable.PageStep2.postCodeInvalid'/></span>";
								document.getElementById('lbl_postCode').innerHTML = '<label id="lbl_postCode" class="midredbold"><s:text name="lable.PageStep2.postCode"/></label>';
						     }else {
								document.getElementById('lbl_postCode').innerHTML = '<label id="lbl_postCode" class="midblack"><s:text name="lable.PageStep2.postCode"/></label>';
							}
				        }
							// check return
							if (noticeMsg != "") {	
								document.getElementById('ErrorSummary').innerHTML = '<span class="midblack">' + noticeMsg + '</span>';
								return false;
							}
						}
						document.getElementById('track_existing_repair').submit();			
					}else{
						return false;
					}
				}
				
			   	function switchChoose( activeId, inactiveId, activeChId, inactiveChId) {
			
			   		document.getElementById(activeChId).checked = true;
			   		document.getElementById(inactiveChId).checked = false;
			
			   		if (document.getElementById(activeId).disabled) {
				            document.getElementById(activeId).disabled = false;
					} 
				        if (!document.getElementById(inactiveId).disabled) {
				            document.getElementById(inactiveId).value = '';
				            document.getElementById(inactiveId).disabled = true;
			
				            if ( inactiveId == 'postCode'){
				    			document.getElementById('lbl_postCode').innerHTML = '<label id="lbl_postCode" class="midblack"><s:text name="lable.PageStep2.postCode"/></label>';
				            }else if (inactiveId == 'email_text'){
				    			document.getElementById('lbl_emailAddress').innerHTML = '<label id="lbl_emailAddress" class="midblack"><s:text name="lable.PageStep2.emailAddress"/></label>';
				            }
					    }
			   	}
			</script>
			
			<div class="panel-group">
			    <div class="panel panel-default">
			
			        <!--label.step1.on.top.title-->
			        <div class="panel-heading panelGroupHeadBkg"><s:text name="lable.PageTrack1.title"/>
			            <div class="numberedSteps">
			                <span class="wizard_stepOn">1</span>
			                <span class="wizard_stepOff">2</span>
			            </div>
			        </div>
			
			        <div class="panel-body">
			
				        <div class="well well-sm" style="color: #4d4d4d;">
				            <s:text name="label.track1.top" />
				        </div>
			
						<form class="form-horizontal" data-toggle="validator" id="track_existing_repair" method="post"
						                  action="test/trackRepair.htm"
						                  onsubmit="return submitTrackForm();">  <!-- /euo/repairOnlineTrack2Bs -->
				                <fieldset>
								<input type="HIDDEN" id="LANGUAGE" name="LANGUAGE" value="<%= lang%>">
			
								<div class="form-group required">
									<label class="control-label col-sm-4" for="repairNo"><s:text name="lable.PageStep4.orderno"/></label>
									<div class="col-sm-8">
										<input type="text" class="form-control" maxlength="10"
												name="repairNo" id="repairNo"
												required>
									</div>
								</div>
							
								<div class="form-group required">
									<div class="radio control-label col-sm-4" for="emailAddress">
										<label><input type="radio" id = 'emailAddress' name="choose"
													onclick ="JavaScript:switchChoose('email_text', 'postCode', 'emailAddress', 'zipCode');">
													<s:text name="lable.PageStep2.emailAddress"/>
										</label>
									</div>
									<div class="col-sm-8">
										<input type="email" class="form-control"
												onclick ="JavaScript:switchChoose('email_text', 'postCode', 'emailAddress', 'zipCode');"
												name="emailAddress"  id = "email_text" 
												data-error="That email address is invalid. A vaild email address is needed to update repair status.">
										<div class="help-block with-errors"></div>
									</div>
								</div>
							
			
								<div class="form-group required">
									<div class="radio control-label col-sm-4" for="zipCode">
										<label><input type="radio" id= 'zipCode' name="choose"
													onclick ="JavaScript:switchChoose('postCode', 'email_text', 'zipCode', 'emailAddress');">
													<s:text name="label.postalCode"/>
										</label>
									</div>
									<div class="col-sm-8">
										<input type="email" class="form-control"
												onclick ="JavaScript:switchChoose('postCode', 'email_text', 'zipCode', 'emailAddress');"
												name="zipCode" id = "postCode"
												pattern="[A-Za-z][0-9][A-Za-z]\s{0,1}[0-9][A-Za-z][0-9]"
			                            		data-error="<s:text name="lable.PageStep2.postCodeInvalid"/>">
										<div class="help-block with-errors"></div>
									</div>
								</div>
								
								<div class="form-group form-group-last">
				                        <div class="col-sm-offset-4 col-sm-8">
				                            <button type="button" class="button_example" 
					                            onClick="window.location.href='/euo/repairOnlineHomeBs';return false">
					                            &#xe801;&nbsp;&nbsp;<s:text name='label.back'/>&nbsp;
				                            </button>
				
				                            <button type="submit" class="button_example">
				                            	&nbsp;<s:text name="label.track.repair"/>&nbsp;&nbsp;&nbsp;&#xe800;
				                            </button>
				                            
				                            <button type="submit" class="button_example"
				                            	onclick="JavaScript:confirm_entry('<s:text name='alert.cancel'/>'); return false;">
				                            	&nbsp;<s:text name='label.cancel'/>&nbsp;
				                            </button>
				                        </div>
				                </div>
								
								
				                </fieldset>
				                
						</form>
			
			
			
					</div><!-- class="panel-body" -->
				</div>	<!-- class="panel panel-default" -->
			</div>  <!-- class="panel-group" -->


      
      
    </div> <!-- track existing repair request tab -->
</div> <!-- class="tab-content" -->




<!-- Bootstrap version ends here -->

<!-- Date input field -->

<script src="node_modules/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<%-- native mobile datepicker https://github.com/niftylettuce/bootstrap-datepicker-mobile
<script src="bower_components/moment/moment.js"></script>
<script src="bower_components/bootstrap-datepicker-mobile/bootstrap-datepicker-mobile.js"></script>
--%>
<script type="text/javascript">

/* bootstrap date picker require toggle*/
$('.input-group.date').datepicker({
    todayBtn: true,
    clearBtn: true,
    toggleActive: true
});

window.onload = function() 
{
	window_onload(
			'${repairRecord.seledctedProduct.familyId}',
			'${repairRecord.seledctedProduct.lineId}',
			'${repairRecord.seledctedProduct.productId}');
	var submit_new_repair_form = $('#new_repair_form_tab');

	//alert('submit new repair form = '+ submit_new_repair_form);
	var updResult = submit_new_repair_form.validator('update');
	var valResult = submit_new_repair_form.validator('validate');
	//alert('updResult = '+ updResult + ', valResult = '+valResult );

}

	//typeahead init
    // Defining the local dataset
	var productData = [
		{"id":"2200745", group:"4", name:"Powershot S95"},
		{"id":"0002200561", group:"4", name:"PowerShot S90"},
		{"id":"2200956", group:"24", name:"BG-E11"},
		{"id":"2200751", group:"24", name:"BG-E2n"},
		{"id":"2200876", group:"24", name:"BG-E5"},
		{"id":"2200724", group:"24", name:"BG-E6"},
		{"id":"2200723", group:"24", name:"BG-E7"},
		{"id":"2200877", group:"24", name:"BG-E8"},
		{"id":"2200878", group:"24", name:"BG-E9"},
		{"id":"2200706", group:"11", name:"EF 100-400mm f/4.5-5.6L IS USM"},
		{"id":"2200764", group:"11", name:"EF 100mm f/2 USM"},
		{"id":"2200718", group:"11", name:"EF 100mm f/2.8L Macro IS USM"},
		{"id":"2200687", group:"11", name:"EF 135mm f/2.0L USM"},
		{"id":"2200766", group:"11", name:"EF 135mm f/2.8 with Softfocus"},
		{"id":"2200696", group:"11", name:"EF 14mm f/2.8L II USM"},
		{"id":"2200694", group:"11", name:"EF 15mm f/2.8 fisheye"},
		{"id":"2200716", group:"11", name:"EF 16-35mm f/2.8L USM II"},
		{"id":"2200715", group:"11", name:"EF 17-40mm f/4L "},
		{"id":"2200719", group:"11", name:"EF 180mm f/3.5L Macro USM  "},
		{"id":"2200685", group:"11", name:"EF 200mm f/2L IS USM"},
		{"id":"2200767", group:"11", name:"EF 20mm f/2.8 USM"},
		{"id":"2200714", group:"11", name:"EF 24-105mm f/4L IS USM "},
		{"id":"2200713", group:"11", name:"EF 24-70mm f/2.8L USM"},
		{"id":"2200692", group:"11", name:"EF 24mm f/1.4L II USM"},
		{"id":"2200768", group:"11", name:"EF 24mm f/2.8"},
		{"id":"2200769", group:"11", name:"EF 28-135mm f/3.5-5.6 IS USM"},
		{"id":"2200712", group:"11", name:"EF 28-300mm f/3.5-5.6L IS USM"},
		{"id":"2200770", group:"11", name:"EF 28mm f/1.8 USM"},
		{"id":"2200771", group:"11", name:"EF 28mm f/2.8"},
		{"id":"2200683", group:"11", name:"EF 300mm f/2.8L IS USM  "},
		{"id":"2200784", group:"11", name:"EF 300mm f/2.8L IS USM II  "},
		{"id":"2200684", group:"11", name:"EF 300mm f/4L IS USM"},
		{"id":"2200691", group:"11", name:"EF 35mm f/1.4L USM"},
		{"id":"2200772", group:"11", name:"EF 35mm f/2  "},
		{"id":"2200681", group:"11", name:"EF 400mm f/2.8L IS USM  "},
		{"id":"2200682", group:"11", name:"EF 400mm f/4 DO IS USM  "},
		{"id":"2200773", group:"11", name:"EF 400mm f/5.6L USM"},
		{"id":"2200680", group:"11", name:"EF 500mm f/4L IS USM"},
		{"id":"2200690", group:"11", name:"EF 50mm f/1.2L USM"},
		{"id":"2200774", group:"11", name:"EF 50mm f/1.4 USM"},
		{"id":"2200775", group:"11", name:"EF 50mm f/1.8 II"},
		{"id":"2200679", group:"11", name:"EF 600mm f/4L IS USM"},
		{"id":"2200847", group:"11", name:"EF 600mm f/4L IS USM II "},
		{"id":"2200708", group:"11", name:"EF 70-200mm f/2.8L IS USM  "},
		{"id":"2200707", group:"11", name:"EF 70-200mm f/2.8L USM  "},
		{"id":"2200711", group:"11", name:"EF 70-200mm f/4L USM"},
		{"id":"2200777", group:"11", name:"EF 70-300mm f/4-5.6L IS USM"},
		{"id":"2200778", group:"11", name:"EF 70-300mm f/4.5-5.6DO IS USM"},
		{"id":"2200780", group:"11", name:"EF 75-300mm f/4-5.6 III USM"},
		{"id":"2200786", group:"11", name:"EF 8-15mm f/4L fisheye  "},
		{"id":"2200733", group:"11", name:"EF 800mm f/5.6L IS USM  "},
		{"id":"2200688", group:"11", name:"EF 85mm f/1.2L II USM"},
		{"id":"2200781", group:"11", name:"EF 85mm f/1.8 USM"},
		{"id":"2200756", group:"11", name:"EF-S 10-22mm f/3.5-4.5 USM "},
		{"id":"2200757", group:"11", name:"EF-S 15-85mm f/3.5-5.6 IS USM"},
		{"id":"2200758", group:"11", name:"EF-S 17-55mm f/2.8 IS USM  "},
		{"id":"2200759", group:"11", name:"EF-S 17-85mm f/4-5.6 IS USM"},
		{"id":"2200760", group:"11", name:"EF-S 18-200mm f/3.5-5.6 IS "},
		{"id":"2200761", group:"11", name:"EF-S 18-55mm f/3.5-5.6 IS  "},
		{"id":"2200762", group:"11", name:"EF-S 55-250mm f/4-5.6 IS"},
		{"id":"2200763", group:"11", name:"EF-S 60mm f/2.8 Macro USM  "},
		{"id":"2200591", group:"10", name:"EOS 1D Mark IV  "},
		{"id":"2200924", group:"10", name:"EOS 5D Mark III "},
		{"id":"2200750", group:"10", name:"EOS 60D"},
		{"id":"2200941", group:"10", name:"EOS 60Da"},
		{"id":"2200656", group:"10", name:"EOS Rebel T2i"},
		{"id":"2200828", group:"10", name:"EOS Rebel T3i"},
		{"id":"2200697", group:"11", name:"Extender EF 1.4x II"},
		{"id":"2200788", group:"11", name:"Extender EF 1.4x III"},
		{"id":"2200699", group:"11", name:"Extender EF 2.0x II"},
		{"id":"2200787", group:"11", name:"Extender EF 2.0x III"},
		{"id":"2200645", group:"17", name:"FS30 "},
		{"id":"2200625", group:"17", name:"FS300"},
		{"id":"2200641", group:"17", name:"FS31 "},
		{"id":"2200816", group:"17", name:"FS40 "},
		{"id":"2200842", group:"17", name:"FS400"},
		{"id":"2000235", group:"22", name:"GL2  "},
		{"id":"2200796", group:"11", name:"MP-E 65mm f/2.8 1-5X Macro "},
		{"id":"2200821", group:"2", name:"Powershot A1200 "},
		{"id":"2200934", group:"2", name:"Powershot A1300 "},
		{"id":"2200815", group:"2", name:"Powershot A2200 "},
		{"id":"2200933", group:"2", name:"Powershot A2300 "},
		{"id":"2200932", group:"2", name:"Powershot A2400 IS"},
		{"id":"2200819", group:"2", name:"Powershot A3300 IS"},
		{"id":"2200913", group:"2", name:"Powershot A3400 IS"},
		{"id":"2200909", group:"2", name:"Powershot A4000 IS"},
		{"id":"2200810", group:"2", name:"Powershot A800  "},
		{"id":"2200935", group:"2", name:"Powershot A810  "},
		{"id":"2200907", group:"7", name:"Powershot ELPH 110 HS"},
		{"id":"2200925", group:"7", name:"Powershot ELPH 320HS"},
		{"id":"2200832", group:"7", name:"Powershot ELPH 500 HS"},
		{"id":"2200926", group:"7", name:"Powershot ELPH 530 HS"},
		{"id":"2200902", group:"3", name:"Powershot G1 X  "},
		{"id":"2200793", group:"3", name:"Powershot G12"},
		{"id":"2200597", group:"5", name:"Powershot SD1300 IS"},
		{"id":"2200609", group:"5", name:"Powershot SD1400 IS"},
		{"id":"2200648", group:"5", name:"Powershot SD3500 IS"},
		{"id":"2200664", group:"5", name:"Powershot SD4000 IS"},
		{"id":"2200782", group:"5", name:"Powershot SD4500 IS"},
		{"id":"2200862", group:"6", name:"Powershot SX150 IS"},
		{"id":"2200646", group:"6", name:"Powershot SX210 IS"},
		{"id":"2200845", group:"6", name:"Powershot SX220 HS"},
		{"id":"2200833", group:"6", name:"Powershot SX230 HS"},
		{"id":"2200921", group:"6", name:"Powershot SX240 HS"},
		{"id":"2200920", group:"6", name:"Powershot SX260 HS"},
		{"id":"2200744", group:"6", name:"Powershot SX30 IS"},
		{"id":"0002200560", group:"3", name:"Powershot G11"},
		{"id":"0002200567", group:"11", name:"EF-S 18-135mm f/3.5-5.6 IS "},
		{"id":"0002200379", group:"10", name:"EOS 1Ds Mark III"},
		{"id":"0002200486", group:"10", name:"EOS 50D"},
		{"id":"0002200496", group:"10", name:"EOS 5D Mark II  "},
		{"id":"0002200564", group:"10", name:"EOS 7D"},
		{"id":"0002200545", group:"10", name:"EOS Rebel T1i"},
		{"id":"0002200420", group:"10", name:"EOS Rebel XSi"},
		{"id":"0002200533", group:"8", name:"Powershot D10"},
		{"id":"0002200587", group:"6", name:"Powershot SX20 IS"},
		{"id":"0002200522", group:"6", name:"Powershot SX200 IS"},
		{"id":"2200837", group:"12", name:"SPEEDLITE 270EX II"},
		{"id":"2200843", group:"12", name:"SPEEDLITE 320EX "},
		{"id":"2200721", group:"12", name:"SPEEDLITE 430EX II"},
		{"id":"2200720", group:"12", name:"SPEEDLITE 580EX II"},
		{"id":"2200958", group:"12", name:"SPEEDLITE 600EX-RT"},
		{"id":"2200705", group:"11", name:"TS-E 17mm f/4L  "},
		{"id":"2200704", group:"11", name:"TS-E 24mm f/3.5L"},
		{"id":"2200703", group:"11", name:"TS-E 24mm f/3.5L II"},
		{"id":"2200702", group:"11", name:"TS-E 45mm f/2.8 "},
		{"id":"2200701", group:"11", name:"TS-E 90mm f/2.8 "},
		{"id":"2200830", group:"15", name:"VIXIA HF G10 "},
		{"id":"2200606", group:"15", name:"VIXIA HF M30 "},
		{"id":"2200638", group:"15", name:"VIXIA HF M300"},
		{"id":"2200637", group:"15", name:"VIXIA HF M31 "},
		{"id":"2200812", group:"15", name:"VIXIA HF M40 "},
		{"id":"2200813", group:"15", name:"VIXIA HF M400"},
		{"id":"2200811", group:"15", name:"VIXIA HF M41 "},
		{"id":"2200928", group:"15", name:"VIXIA HF M50 "},
		{"id":"2200929", group:"15", name:"VIXIA HF M500"},
		{"id":"2200927", group:"15", name:"VIXIA HF M52 "},
		{"id":"2200639", group:"15", name:"VIXIA HF R10 "},
		{"id":"2200640", group:"15", name:"VIXIA HF R100"},
		{"id":"2200617", group:"15", name:"VIXIA HF R11 "},
		{"id":"2200808", group:"15", name:"VIXIA HF R20 "},
		{"id":"2200809", group:"15", name:"VIXIA HF R200"},
		{"id":"2200807", group:"15", name:"VIXIA HF R21 "},
		{"id":"2200905", group:"15", name:"VIXIA HF R30 "},
		{"id":"2200906", group:"15", name:"VIXIA HF R300"},
		{"id":"2200904", group:"15", name:"VIXIA HF R32 "},
		{"id":"2200636", group:"15", name:"VIXIA HF S20 "},
		{"id":"2200635", group:"15", name:"VIXIA HF S200"},
		{"id":"2200626", group:"15", name:"VIXIA HF S21 "},
		{"id":"2200820", group:"15", name:"VIXIA HF S30 "},
		{"id":"0002200528", group:"15", name:"VIXIA HV40"},
		{"id":"2200955", group:"13", group:"WFT-E7A"},
		{"id":"2200790", group:"22", name:"XF100"},
		{"id":"2200789", group:"22", name:"XF105"},
		{"id":"2200673", group:"22", name:"XF300"},
		{"id":"2200672", group:"22", name:"XF305"},
		{"id":"0002200498", group:"22", name:"XH A1S"},
		{"id":"0002200501", group:"22", name:"XH G1S"},
		{"id":"0002200204", group:"22", name:"XL H1"},
		{"id":"0002200402", group:"22", name:"XL H1A"},
		{"id":"0002200444", group:"22", name:"XL H1S"},
		{"id":"0002100095", group:"22", name:"XL2  "}
       ];

    

        // Constructing the suggestion engine
        var bloodhoundDS = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace(['id','group','name']),
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            local: productData
        });
        bloodhoundDS.initialize();
        // Initializing the typeahead
        $('#scrollable-dropdown-menu .typeahead').typeahead({
                hint: true,
                highlight: true, /* Enable substring highlighting */
                minLength: 1 /* Specify minimum characters required for showing result */
            },
            {
                name: 'searchproduct',
                source: bloodhoundDS.ttAdapter(),
                displayKey: 'name',
                valueKey:"id",
                limit: 20
            });

        $('.typeahead').bind('typeahead:select', function(ev, suggestion) {
            //console.log('Selection: ' + suggestion);
            for(var prop in suggestion){
                console.log(prop + ': ' + suggestion[prop]);
            }
            $('#productId').val(suggestion['id']);
            $('#groupId').val(suggestion['group']);
            $('#productName').val(suggestion['name']);
        });
    //end of typeahead init





/* $(document).ready(function() {
    $("#Families").select({
        placeholder: "Choose an operation..."
    });
});*/

</script>
