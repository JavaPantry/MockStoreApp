<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"									prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="panel-group">
    <div class="panel panel-default">

        <!--label.step1.on.top.title-->
        <div class="panel-heading panelGroupHeadBkg">Repair Request (continued)
            <div class="numberedSteps">
                <span class="wizard_stepOn">1</span>
                <span class="wizard_stepOff">2</span>
                <span class="wizard_stepOff">3</span>
                <span class="wizard_stepOff">4</span>
            </div>
        </div>

        <div class="panel-body">

        <div class="well well-sm" style="color: #4d4d4d;">
            <s:text name="label.step1.on.top.msg"></s:text>
        </div>
            <form class="form-horizontal" data-toggle="validator" id="track_existing_repair" method="post"
                  action="/euo/repairOnlineStep2Bs">
                <fieldset>
                    <!--<span class="midblack" id="ErrorSummary"></span>-->
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
                        <label class="control-label col-sm-4" for="estimateCostField"><s:text name="lable.PageStep1On.estimateCost"/>:</label>
                        <div class="col-sm-8">
								<p class="form-control-static" id="estimateCostField">
								<fmt:formatNumber type="currency">
								${repairRecord.labourCost}
								</fmt:formatNumber>
								<s:set var="servicefee">${repairRecord.labourCost}</s:set>
								<s:if test='#servicefee != 0'>
								&nbsp;&nbsp;<s:text name="lable.PageStep1On.plusTax"/>
								</s:if>
                            </p>
                        </div>
                    </div>

                    <div class="well well-sm" style="color: #4d4d4d;"> <!--font-weight:bold;-->
                        <s:text name="label.step1.on.bottom.msg.1" />
                        <p/><s:text name="label.step1.on.bottom.msg.2" />
                        <p/><s:text name="label.step1.on.bottom.msg.3" />
                        <p/><s:text name="label.step1.on.bottom.msg.4" />
                    </div>

                    <div class="form-group form-group-last">
                        <div class="col-sm-offset-4 col-sm-8">
                            <button type="button" class="button_example" 
	                            onClick="window.location.href='/euo/repairOnlineStep1?backflag=true';return false">
	                            &#xe801;&nbsp;&nbsp;<s:text name='label.back'/>&nbsp;
                            </button>

                            <button type="submit" class="button_example" >
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
    </div> <!--closing div class="panel-group"-->
</div> <!--closing div class="panel panel-default"-->
