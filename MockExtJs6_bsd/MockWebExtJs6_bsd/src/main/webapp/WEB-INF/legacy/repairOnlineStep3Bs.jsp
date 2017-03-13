<%@ taglib uri="/struts-tags"									prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page import="java.util.Locale"%>
<%
	String lang = ((Locale)session.getAttribute("locale")).getLanguage();
%>

<script type="text/javascript" src="/euo/resources/js/postalCodeToObtainAddressBs.js"></script>

<script language="JavaScript">
	function ltrim(str) { 
		for(var k = 0; k < str.length && isWhitespace(str.charAt(k)); k++);
		return str.substring(k, str.length);
	}
	function rtrim(str) {
		for(var j=str.length-1; j>=0 && isWhitespace(str.charAt(j)) ; j--) ;
		return str.substring(0,j+1);
	}
	function trim(str) {
		return ltrim(rtrim(str));
	}
	function isWhitespace(charToCheck) {
		var whitespaceChars = " \t\n\r\f";
		return (whitespaceChars.indexOf(charToCheck) != -1);
	}

	if (!Array.prototype.indexOf)
	{
	  Array.prototype.indexOf = function(elt)
	  {
	    var len = this.length;

	    var from = Number(arguments[1]) || 0;
	    from = (from < 0)
	         ? Math.ceil(from)
	         : Math.floor(from);
	    if (from < 0)
	      from += len;

	    for (; from < len; from++)
	    {
	      if (from in this &&
	          this[from] === elt)
	        return from;
	    }
	    return -1;
	  };
	}
	
   	function submitForm() {
		var langLoc = document.getElementById('LANGUAGE').value;

		var now = new Date();
		var today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
		var month = document.getElementById('expMonth_text').value;
		var year = document.getElementById('expYear_text').value;			
		var indexMonth = [	'01', '02', '03', '04', '05', '06', 
	             			'07', '08','09','10', '11', '12' ].indexOf(month);
		var expireDate=new Date(year, indexMonth, now.getDate());
		var phoneNum = 	document.getElementById('phone1_text').value +
										document.getElementById('phone2_text').value +
										document.getElementById('phone3_text').value;

   		frmVal = new FormValidator(false, "midredbold", "midblack");		
		//check postcode
		if (!isNorPostCode(document.getElementById('postCode').value)) {
			document.getElementById('lbl_postCode').innerHTML = '<label id="lbl_postCode" class="midblack"><s:text name="lable.PageStep2.postCode"/></label>';
		}else {
			document.getElementById('lbl_postCode').innerHTML = '<label id="lbl_postCode" class="midredbold"><s:text name="lable.PageStep2.postCode"/></label>';			
		}
		//check phone number
		if (!isNorPhoneNumber(phoneNum)) {
			document.getElementById('lbl_phone').innerHTML = '<label id="lbl_phone" class="midblack"><s:text name="lable.PageStep2.phone"/></label>';
		}else {
			document.getElementById('lbl_phone').innerHTML = '<label id="lbl_phone" class="midredbold"><s:text name="lable.PageStep2.phone"/></label>';			
		}
		//check card type
		if (!(document.getElementById('cardtype_text').value == '-1')) {
			document.getElementById('lbl_cardtype').innerHTML = "<label id='lbl_cardtype' class='midblack'><s:text name='lable.PageStep3.cardType'/></label>";
		}else {
			document.getElementById('lbl_cardtype').innerHTML = "<label id='lbl_cardtype' class='midredbold'><s:text name='lable.PageStep3.cardType'/></label>";			
		}
		//check credit card
		if (isCreditCardMod10(document.getElementById('cardNumber_text').value)) {
			document.getElementById('lbl_cardNumber').innerHTML = "<label id='lbl_cardNumber' class='midblack'><s:text name='lable.PageStep3.cardNumber'/></label>";
		}else {
			document.getElementById('lbl_cardNumber').innerHTML = "<label id='lbl_cardNumber' class='midredbold'><s:text name='lable.PageStep3.cardNumber'/></label>";			
		}
		//check cvn
		if (!isNorCvn(document.getElementById('cardCvn_text').value)) {
			document.getElementById('lbl_cvn').innerHTML = "<label id='lbl_cvn' class='midblack'><s:text name='lable.PageStep3.cvn'/></label>";
		}else {
			document.getElementById('lbl_cvn').innerHTML = "<label id='lbl_cvn' class='midredbold'><s:text name='lable.PageStep3.cvn'/></label>";			
		}
		//check exp month, year, compare Today
		if (	!(document.getElementById('expMonth_text').value == '-1') &&
				!(document.getElementById('expYear_text').value == '-1') &&
				!(expireDate.getTime() < today.getTime())) {
			document.getElementById('lbl_expdate').innerHTML = "<label id='lbl_expdate' class='midblack'><s:text name='lable.PageStep3.expireDate'/></label>";
		}else {
			document.getElementById('lbl_expdate').innerHTML = "<label id='lbl_expdate' class='midredbold'><s:text name='lable.PageStep3.expireDate'/></label>";			
		}
		
		if(langLoc == "en"){
	   		frmVal.addValidator(new RequiredFieldValidator('repairRecord.OURBLTO1', 'lbl_firstName',  'Name is required', langLoc));	
			
			frmVal.addValidator(new RequiredFieldValidator('repairRecord.OURBLPC', 'lbl_postCode',  'Postal Code is required', langLoc));	
			frmVal.addValidator(new RequiredFieldValidator('repairRecord.OURBLST1', 'lbl_address',  'Address is required', langLoc));
			frmVal.addValidator(new RequiredFieldValidator('repairRecord.OURBLCT', 'lbl_city',  'City is required', langLoc));
				
			frmVal.addValidator(new RequiredFieldValidator('paymentInfo.number', 'lbl_cardNumber',  'Card Number is required', langLoc));	
			frmVal.addValidator(new RequiredFieldValidator('paymentInfo.cvn', 'lbl_cvn',  'CVN is required', langLoc));	

			frmVal.addValidator(new RequiredFieldValidator('phoneAreaCode', 'lbl_phone',  'Phone AreaCode is required', langLoc));	
			frmVal.addValidator(new RequiredFieldValidator('phonePrefix', 'lbl_phone',  'Phone Prefix is required', langLoc));	
			frmVal.addValidator(new RequiredFieldValidator('phoneLastFour', 'lbl_phone',  'Phone Last Four is required', langLoc));	
		}else{
	   		frmVal.addValidator(new RequiredFieldValidator('repairRecord.OURBLTO1', 'lbl_firstName',  'Nom est requis', langLoc));	
			
			frmVal.addValidator(new RequiredFieldValidator('repairRecord.OURBLPC', 'lbl_postCode',  'Code postal est requis', langLoc));	
			frmVal.addValidator(new RequiredFieldValidator('repairRecord.OURBLST1', 'lbl_address',  'Adresse est requise', langLoc));
			frmVal.addValidator(new RequiredFieldValidator('repairRecord.OURBLCT', 'lbl_city',  'Ville est nécessaire', langLoc));
				
			frmVal.addValidator(new RequiredFieldValidator('paymentInfo.number', 'lbl_cardNumber',  'Numéro de la carte est nécessaire', langLoc));	
			frmVal.addValidator(new RequiredFieldValidator('paymentInfo.cvn', 'lbl_cvn',  'CVN est nécessaire', langLoc));	

			frmVal.addValidator(new RequiredFieldValidator('phoneAreaCode', 'lbl_phone',  'Code régional de téléphone est requis', langLoc));	
			frmVal.addValidator(new RequiredFieldValidator('phonePrefix', 'lbl_phone',  'Préfixe du téléphone est obligatoire', langLoc));	
			frmVal.addValidator(new RequiredFieldValidator('phoneLastFour', 'lbl_phone',  'Quatre Téléphone dernière est requise', langLoc));	
		}
		
		frmVal.init('track_existing_repair','ErrorSummary');	
		frmVal.reset();
		var result = frmVal.validate();	
		if(result){
			if ( document.getElementById('ErrorSummary').innerHTML == ''){
				//check postcode
				if (isNorPostCode(document.getElementById('postCode').value)) {
					document.getElementById('lbl_postCode').innerHTML = "<label id='lbl_postCode' class='midredbold'><s:text name='lable.PageStep2.postCode'/></label>";
					document.getElementById('ErrorSummary').innerHTML = "<span class='midredbold'><s:text name='lable.PageStep3.postcodeInvalid'/></span>";
					return false;
				}
				//check phone number
				if (isNorPhoneNumber(phoneNum)) {
					document.getElementById('lbl_phone').innerHTML = "<label id='lbl_phone' class='midredbold'><s:text name='lable.PageStep2.phone'/></label>";
					document.getElementById('ErrorSummary').innerHTML = "<span class='midredbold'><s:text name='lable.PageStep3.phoneInvalid'/></span>";
					return false;
				}
				//check card type
				if (document.getElementById('cardtype_text').value == '-1') {
					document.getElementById('lbl_cardtype').innerHTML = "<label id='lbl_cardtype' class='midredbold'><s:text name='lable.PageStep3.cardType'/></label>";
					document.getElementById('ErrorSummary').innerHTML = "<span class='midredbold'><s:text name='lable.PageStep3.selectCardtype'/></span>";
					return false;
				}
				//check credit card
				if (!isCreditCardMod10(document.getElementById('cardNumber_text').value)) {
					document.getElementById('lbl_cardNumber').innerHTML = "<label id='lbl_cardNumber' class='midredbold'><s:text name='lable.PageStep3.cardNumber'/></label>";
					document.getElementById('ErrorSummary').innerHTML = "<span class='midredbold'><s:text name='lable.PageStep3.cardtypeInvalid'/></span>";
					return false;
				}
				//check cvn
				if (isNorCvn(document.getElementById('cardCvn_text').value)) {
					document.getElementById('lbl_cvn').innerHTML = "<label id='lbl_cvn' class='midredbold'><s:text name='lable.PageStep3.cvn'/></label>";
					document.getElementById('ErrorSummary').innerHTML = "<span class='midredbold'><s:text name='lable.PageStep3.cvnInvalid'/></span>";
					return false;
				}
				//check exp month
				if (document.getElementById('expMonth_text').value == '-1') {
					document.getElementById('lbl_expdate').innerHTML = "<label id='lbl_expdate' class='midredbold'><s:text name='lable.PageStep3.expireDate'/></label>";
					document.getElementById('ErrorSummary').innerHTML = "<span class='midredbold'><s:text name='lable.PageStep3.selectDate'/></span>";
					return false;
				}
				//check exp month
				if (document.getElementById('expYear_text').value == '-1') {
					document.getElementById('lbl_expdate').innerHTML = "<label id='lbl_expdate' class='midredbold'><s:text name='lable.PageStep3.expireDate'/></label>";
					document.getElementById('ErrorSummary').innerHTML = "<span class='midredbold'><s:text name='lable.PageStep3.selectDate'/></span>";
					return false;
				}
				//check expireDate
				if (expireDate.getTime() < today.getTime()) {//cardtype_text
					document.getElementById('lbl_expdate').innerHTML = "<label id='lbl_expdate' class='midredbold'><s:text name='lable.PageStep3.expireDate'/></label>";
					document.getElementById('ErrorSummary').innerHTML = "<span class='midredbold'><s:text name='lable.PageStep3.expireDatePast'/></span>";
					return false;
				}
			}
			document.getElementById('processPayment').style.display='block';
			submitFormEuo(document.forms['track_existing_repair'],'euoHomeStep3Payment');
		}else{
			return false;
		}
	}
	
	function setCustomerInfo(){
		var varFirstName = document.getElementById("firstName_text");
		var varPostCode = document.getElementById("postCode");
		var varAddress = document.getElementById("address_text");
		var varAddress2 = document.getElementById("address2_text");
		
		var varCity = document.getElementById("city_text");
		var varProvince = document.getElementById("province_text");
		var varPhone1 = document.getElementById("phone1_text");
		var varPhone2 = document.getElementById("phone2_text");
		var varPhone3 = document.getElementById("phone3_text");
		var varPhone  = "${repairRecord.OURSPTEL}";
	
		if(document.track_existing_repair.sameInfo.checked == true){
			var tmpStr = trim("${repairRecord.OURSPTO1}") + " " + trim("${repairRecord.OURSPTO2}");
			if (tmpStr.length > 60){
				tmpStr = tmpStr.substring(0,60);
			}
			varFirstName.value = tmpStr;
			varPostCode.value = "${repairRecord.OURSPPC}";
			varAddress.value = "${repairRecord.OURSPST1}";
			varAddress2.value = "${repairRecord.OURSPST2}";
			varCity.value = "${repairRecord.OURSPCT}";
			varProvince.value = "${provinceShipping}";
			
			if (varPhone != "") {
				varPhone1.value = varPhone.substring(0, 3);
				varPhone2.value = varPhone.substring(3, 6);
				varPhone3.value = varPhone.substring(6, 10);
			}
		}
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
			                        <p/><s:text name="label.step3.top.title" />
			                        <p/><s:text name="label.step3.top.msg" />
			      </div>
				</p>

				<form class="form-horizontal" data-toggle="validator"
	                		id="track_existing_repair" name="track_existing_repair"
			                method="post"  
							action="/euo/paymentInProgressBs"
							onsubmit="return submitForm();">
							
						<!-- Form Error Text STARTS -->				
						<span class="midblack" id="ErrorSummary" style="padding:52px;"></span>			
						<input type="HIDDEN" id="LANGUAGE" name="LANGUAGE" value="<%= lang%>">
	
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
                      
				<!-- ====================================================== -->
				
<!-- ********* Billing & Cardholder Information   ********* -->				
<div class="panel panel-default"  id="billingSection" ><!-- style="display:none" -->
					<div class="panel-heading"><s:text name="lable.PageStep3.billInformation"/></div>
					<div class="panel-body">
				
				   <%-- <td style="padding-left: 10px;" colspan="4">
					  <input 	type="checkbox" style="width: 10px;"
					  				 id="sameInfo" name="sameInfo" 
					  				onclick="javascript:setCustomerInfo();" >
  				  <span class="smallGreyText"><s:text name="lable.PageStep3.useSame"/></span>                            
				   </td> --%>
`					<div class="checkbox">
						<label><input type="checkbox" value=""
								id="sameInfo" name="sameInfo" 
								onclick="javascript:setCustomerInfo();"><s:text name="lable.PageStep3.useSame"/></label>
					</div>
				   

                     <%-- <td class="leftCol"><label id="lbl_firstName"><s:text name="label.name"/></label><span class="midred">*</span></td>
                      <td class="rightCol">
							<input 	type="text" maxlength="40" style="width: 250px;"  class="form_input" 
										name="repairRecord.OURBLTO1" id="firstName_text" autocomplete="off"
										value = "${repairRecord.OURBLTO1}" >
	                  </td> --%>
	                  
	                  <div class="form-group required">
                        <label class="control-label col-sm-4" for="firstName_text"><s:text name="label.name"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                            	name="repairRecord.OURBLTO1" id="firstName_text"
                                required
                                value = "${repairRecord.OURBLTO1}">
                        </div>
                	</div>

					<div class="form-group required">
                        <label class="control-label col-sm-4" id="lbl_postCode" for="postCode"><s:text name="lable.PageStep2.postCode"/>:</label>
                        
                        <!--TODO - <AP> need new error message with french translation 
                        	error.PageStep2.postCodeInvalid = postal code format A9A 9A9 
                        	TODO - <AP> autocomplete="off"
                        	-->
                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                            	name="repairRecord.OURBLPC" id="postCode" 
                            	pattern="[A-Za-z][0-9][A-Za-z]\s{0,1}[0-9][A-Za-z][0-9]"
                            	required data-error="<s:text name="lable.PageStep2.postCodeInvalid"/>"
								value ="${repairRecord.OURBLPC}" 
                                required>
                            <div class="help-block with-errors"></div>
                		</div>
                		<div class="col-sm-4">
                            <button type="button" class="btn btn-default"
									onclick="JavaScript: postalCodeToObtainAddressBs('billing', document.getElementById('LANGUAGE').value); return false;"><s:text name="lable.PageStep2.btnGet"/>
							</button>
                		</div>
                	</div>
					<div class="form-group required">
						<label class="control-label col-sm-4" for="address_text"><s:text name="lable.PageStep2.address"/>:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"
							name="repairRecord.OURBLST1" id="address_text" 
							value = "${repairRecord.OURBLST1}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="address_text"><s:text name="lable.PageStep2.address"/>2:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"
							name="repairRecord.OURBLST2" id="address_text" 
							value = "${repairRecord.OURBLST2}">
						</div>
					</div>

					<div class="form-group required">
						<label class="control-label col-sm-4" for="city_text"><s:text name="lable.PageStep2.city"/>:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"
							name="repairRecord.OURBLCT" id="city_text"
							value = "${repairRecord.OURBLCT}">
						</div>
					</div>


					<div class="form-group required">
						<label class="control-label col-sm-4" for="province_text"><s:text name="lable.PageStep2.province"/>:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"
							name="provinceBilling" id="province_text"
							value ="${provinceBilling}">
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
	</div> <!-- panel-body -->
</div> <!-- class="panel panel-default" -->
				
<!-- ********* EOF Billing & Cardholder Information   ********* -->


<!-- ********* Payment Information ********* -->
<div class="panel panel-default"  id="paymentSection" > <!-- style="display:none" -->
					<div class="panel-heading"><s:text name="lable.PageStep3.paymentInformation"/></div>
					<div class="panel-body">
				
					<div class="form-group">
						  <label class="control-label col-sm-4" for="cardtype_text"><s:text name="lable.PageStep3.cardType"/></label>
						  <div class="col-sm-8">
						  <select class="form-control" id="cardtype_text" name="paymentInfo.type">
						    	<option value="-1"><s:text name="lable.PageStep3.chooseOne"/></option>
							    <option value="VISA">VISA</option>
							    <option value="MASTER CARD">Master Card</option>
						  </select>
						  </div>
					</div>
									
                	<div class="form-group required">
                        <label class="control-label col-sm-4" id="lbl_cardNumber" for="cardNumber_text"><s:text name="lable.PageStep3.cardNumber"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                            	name="paymentInfo.number" id="cardNumber_text" autocomplete="off" 
                            	pattern="[0-9]{16}"
                            	required data-error="<s:text name="lable.PageStep2.postCodeInvalid"/>"
								value ="${paymentInfo.number}" 
                                required>
                            <div class="help-block with-errors"></div>
                		</div>
                	</div>
				
                	<div class="form-group required">
                        <label class="control-label col-sm-4" id="lbl_cvn" for="cardCvn_text"><s:text name="lable.PageStep3.cvn"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                            	name="paymentInfo.cvn" id="cardCvn_text" autocomplete="off" 
                            	pattern="[0-9]{3}"
                            	required data-error="<s:text name="lable.PageStep2.postCodeInvalid"/>"
								value ="${paymentInfo.cvn}" 
                                required>
                            <div class="help-block with-errors"></div>
                		</div>
                	</div>

					<div class="form-group">
					  <label class="control-label col-sm-4" for="expMonth_text"><s:text name="lable.PageStep3.expireDate"/></label>
						  <div class="col-sm-4">
						  <select class="form-control" id="expMonth_text" name="paymentInfo.expMonth">
									    <option value="-1"><s:text name="label.month" /></option>
									    <option value="01"><s:text name="label.month.1" /></option>
									    <option value="02"><s:text name="label.month.2" /></option>
									    <option value="03"><s:text name="label.month.3" /></option>
									    <option value="04"><s:text name="label.month.4" /></option>
									    <option value="05"><s:text name="label.month.5" /></option>
									    <option value="06"><s:text name="label.month.6" /></option>
									    <option value="07"><s:text name="label.month.7" /></option>
									    <option value="08"><s:text name="label.month.8" /></option>
									    <option value="09"><s:text name="label.month.9" /></option>
									    <option value="10"><s:text name="label.month.10" /></option>
									    <option value="11"><s:text name="label.month.11" /></option>
									    <option value="12"><s:text name="label.month.12" /></option>
							</select>
							</div>

						  <div class="col-sm-4">
								<s:select class="form-control" name="paymentInfo.expYear"
										  id="expYear_text"
										  list="creditCardYears"
								          headerKey="-1" 
								          headerValue="%{getText('label.year')}"/>
						 </div>

					  </div>
				
	</div> <!-- panel-body -->
</div> <!-- class="panel panel-default" -->
<!-- ********* EOF Payment Information ********* -->




                 
	
				

<!-- ============================== Bottom message & BUTTONS ==================================== -->
	
	                <div class="well well-sm" style="color: #4d4d4d; margin-top:5px;"> <!--font-weight:bold;-->
                        <s:text name="label.step3.bottom.msg" />
                    </div>

                    <div class="form-group form-group-last">
                        <div class="col-sm-offset-4 col-sm-8">
                            
                            <button type="button" class="button_example" 
	                            onClick="window.location.href='/euo/repairOnlineStep2';return false">
	                            &#xe801;&nbsp;&nbsp;<s:text name='label.back'/>&nbsp;
                            </button>

                            <button type="submit" class="button_example" >
                            	&nbsp;<s:text name='title.accept' />&nbsp;&nbsp;&nbsp;&#xe800;
                            </button>
                            
                            <button type="button" class="button_example"
                            	onclick="JavaScript:confirm_entry('<s:text name='alert.cancel'/>'); return false;">
                            	&nbsp;<s:text name='label.cancel'/>&nbsp;
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
	var totalCost = ${repairRecord.totalCost}; 
	var approvalStatus = '${approvalStatus}';
	var paymentStatus = '${paymentStatus};'
	var declineStatus = '${repairRecord.declineStatus};'
	
	if (declineStatus.indexOf("Y") != -1 ){//status: D, E, C ==> Decline
		document.getElementById('ErrorSummary').innerHTML = "<span class='midredbold'><s:text name='err.payment_declined'/></span>";
	}else {
		document.getElementById('ErrorSummary').innerHTML = "";
		if (totalCost.indexOf("$0.00") != -1  ) { //'D': Decline	
			window.location.href='/euo/repairOnlineStepEnd';
		}
	}
	
}
</script>