<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"						prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>

<%@page import="ca.canon.euo.constants.GlobalConstant"%>
<%@page import="java.util.Locale"%>

<%
	String lang = ((Locale)session.getAttribute("locale")).getLanguage();
%>
<s:set var="LANG_CD"><%= lang%> </s:set>
<s:set var="LANG_EN"><%= GlobalConstant.LANGUAGE_EN%> </s:set>

<script type="text/javascript" src="/euo/resources/js/repairOnlineStep1Bs.js"></script>

<script language=Javascript type=text/javascript>
	DynDropDown_desc('<s:text name="label.choose.family"/>', '<s:text name="label.choose.series"/>', '<s:text name="label.choose.model"/>');
</script>

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

<!-- Bootstrap version starts here -->
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

                <form id="new_repair_form" class="form-horizontal" data-toggle="validator"
                		name=pulldowns
		                method="post"  
						action="/euo/repairOnlineStep1OnBs"
						onsubmit="return submitNewRepairForm();"	>
						<!-- onsubmit="return submitForm();" -->

 						<input type="HIDDEN" id="LANGUAGE" name="LANGUAGE" value="<%= lang%>">
						<input type="HIDDEN" name="URL" value="">
						<input type="HIDDEN" name="act" value="">
						<input type="HIDDEN" name="actiontype" value="">
						<input type="HIDDEN" name="contrl" value="os">
						<input type="HIDDEN" name="itemcode" value="">
 
                    <fieldset>
                    
                    
                    <div class="form-group required">
                        <label class="control-label required col-sm-4" for="Families"><s:text name="lable.PageStep1.chooseFamily"/>:</label>
                        <div class="col-sm-8">
                            <select class="form-control" 
                            		id="Families" name="Families"  
                            		onChange="javascript:ddd.PopulateMenu(getSelectedIndex(this), lines, baseForm.Lines);"
                            		placeholder="<s:text name='title.dropdown.family'/>"
                            		required>
                            </select>
                        </div>
                    </div>


                    <div class="form-group required">
                        <label class="control-label col-sm-4" for="Lines"><s:text name="lable.PageStep1.chooseSeries"/>:</label>
                        <div class="col-sm-8">
                            <select class="form-control" 
                            		id="Lines" name="Lines"
                            		onChange="javascript:ddd.PopulateMenu(getSelectedIndex(this), products, baseForm.Products);"
                            		placeholder="<s:text name='title.dropdown.series'/>"
                            		required>
                            </select>
                        </div>
                    </div>

                    <div class="form-group required">
                        <label class="control-label col-sm-4" for="Products"><s:text name="lable.PageStep1.chooseModel"/>:</label>
                        <div class="col-sm-8">
                            <select class="form-control" 
                            		id="Products" name="Products" 
                            		onChange="javascript:DynDropDown_RnFlag(getSelectedIndex(baseForm.Products), products);"
                            		placeholder="Enter model"
                            		required>
                            </select>
                        </div>
                    </div>

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

					<%--  Mobile oriented date-picker (miss some functionality on desktop browsers)              
					<div class="form-group">
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
                            <button type="button" class="button_example" onClick="window.location.href='/euo/repairOnlineStep1';return false">&nbsp;<s:text name='label.home'/>&nbsp;</button>

                            <button type="submit" class="button_example" 
                            		onclick="javascript:euoSearchitem(document.forms[0], getSelectedIndex(baseForm.Products), products, '<%= lang%>');">
                            		<s:text name='title.next.step'/>&nbsp;&nbsp;&#xe800;
                            </button>  <!-- NEXT STEP -->

                        </div>
                    </div>

                    </fieldset>
                </form>

			</div> <!--closing div class="panel-body" -->
        </div> <!-- closing div class="panel panel-default"-->
    </div> <!-- closing div class="panel-group"-->
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
}

</script>
