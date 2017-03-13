<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"									prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>

<%
	String lang = ((java.util.Locale)session.getAttribute("locale")).getLanguage();
%>

<script language="JavaScript">
   	function submitForm() {
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
			                  onsubmit="return submitForm();"><!-- action="/euo/repairOnlineTrack2Bs" -->
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


<!-- <div id="search_by_category_bottom_extend"></div>
<div id="page_footer_extend" class="clearfix">	</div>
 -->	
<script type="text/javascript">
window.onload = function() 
{
	var errorMsgDis = '${errorMsg}'; 
	if (errorMsgDis != "") {	
		document.getElementById('ErrorSummary').innerHTML = "<span class='midredbold'><s:text name='lable.PageStep2On.msg'/></span>";
	}else {
		document.getElementById('ErrorSummary').innerHTML = '';
	}

}
</script>
	