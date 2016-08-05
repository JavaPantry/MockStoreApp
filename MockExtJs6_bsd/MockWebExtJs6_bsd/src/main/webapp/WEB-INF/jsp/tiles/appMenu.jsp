<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
#pcm{display:none;}
ul.pureCssMenu ul{display:none}
ul.pureCssMenu li:hover>ul{display:block}
ul.pureCssMenu ul{position: absolute;left:-1px;top:98%;}
ul.pureCssMenu ul ul{position: absolute;left:98%;top:-2px;}
ul.pureCssMenu,ul.pureCssMenu ul {
    margin:0px;
    list-style:none;
    padding:0px 2px 2px 0px;
    background-color:#ffffff;
    background-repeat:repeat;
    border-color:#AAAAAA;
    border-width:1px;
    border-style:solid;
    z-index: 99999;
}
ul.pureCssMenu table {border-collapse:collapse}
ul.pureCssMenu {
    display:block;
    zoom:1;
    float: left;
}
ul.pureCssMenu ul{
    width:153.3px;
}
ul.pureCssMenu li{
    display:block;
    margin:2px 0px 0px 2px;
    font-size:0px;
}
ul.pureCssMenu a:active, ul.pureCssMenu a:focus {
outline-style:none;
}
ul.pureCssMenu a, ul.pureCssMenu li.dis a:hover, ul.pureCssMenu li.sep a:hover {
    display:block;
    vertical-align:middle;
    background-color:#ffffff;
    border-width:0px;
    border-color:#6655ff;
    border-style:solid;
    text-align:left;
    text-decoration:none;
    padding:4px;
    _padding-left:0;
    font:12px Arial;
    color: #000000;
    text-decoration:none;
    cursor:default;
}
ul.pureCssMenu span{
    overflow:hidden;
}
ul.pureCssMenu li {
    float:left;
}
ul.pureCssMenu ul li {
    float:none;
}
ul.pureCssMenu ul a {
    text-align:left;
    white-space:nowrap;
}
ul.pureCssMenu li.sep{
    text-align:center;
    padding:0px;
    line-height:0;
    height:100%;
}
ul.pureCssMenu li.sep span{
    float:none; padding-right:0;
    width:5;
    height:16;
    display:inline-block;
    background-color:#AAAAAA;   background-image:none;}
ul.pureCssMenu ul li.sep span{
    width:80%;
    height:3;
}
ul.pureCssMenu li:hover{
    position:relative;
}
ul.pureCssMenu li:hover>a{
    background-color:#C80000;
    border-color:#665500;
    border-style:solid;
    font:12px Arial;
    color: #ffffff;
    text-decoration:none;
}
ul.pureCssMenu li a:hover{
    position:relative;
    background-color:#C80000;
    border-color:#665500;
    border-style:solid;
    font:12px Arial;
    color: #ffffff;
    text-decoration:none;
}
ul.pureCssMenu li.dis a {
    color: #AAAAAA !important;
}
ul.pureCssMenu img {border: none;float:left;_float:none;margin-right:4px;width:16px;
height:16px;
}
ul.pureCssMenu ul img {width:16px;
height:16px;
}
ul.pureCssMenu img.over{display:none}
ul.pureCssMenu li.dis a:hover img.over{display:none !important}
ul.pureCssMenu li.dis a:hover img.def {display:inline !important}
ul.pureCssMenu li:hover > a img.def  {display:none}
ul.pureCssMenu li:hover > a img.over {display:inline}
ul.pureCssMenu a:hover img.over,ul.pureCssMenu a:hover ul img.def,ul.pureCssMenu a:hover a:hover img.over{display:inline}
ul.pureCssMenu a:hover img.def,ul.pureCssMenu a:hover ul img.over,ul.pureCssMenu a:hover a:hover img.def{display:none}
ul.pureCssMenu a:hover ul{display:block}
ul.pureCssMenu span{
    display:block;
    background-image:url(${pageContext.request.contextPath}/resources/images/menuimages/arrv_anim_1.gif);
    background-position:right center;
    background-repeat: no-repeat;
   padding-right:11px;}
ul.pureCssMenu li:hover>a>span{ background-image:url(${pageContext.request.contextPath}/resources/images/menuimages/arrv_anim_1o.gif);
}
ul.pureCssMenu a:hover span{    _background-image:url(${pageContext.request.contextPath}/resources/images/menuimages/arrv_anim_1o.gif)}
ul.pureCssMenu ul span,ul.pureCssMenu a:hover table span{background-image:url(${pageContext.request.contextPath}/resources/images/menuimages/arr_double_1.gif)}
ul.pureCssMenu ul li:hover > a span{    background-image:url(${pageContext.request.contextPath}/resources/images/menuimages/arr_double_1o.gif);}
ul.pureCssMenu table a:hover span{background-image:url(${pageContext.request.contextPath}/resources/images/menuimages/arr_double_1o.gif)}
</style>
<!-- End PureCSSMenu.com STYLE -->

<!-- Start PureCSSMenu.com MENU -->
<ul class="pureCssMenu pureCssMenum">
    <li class="pureCssMenui"><a class="pureCssMenui" href="#"><span><spring:message code="label.menu.request"/></span><![if gt IE 6]></a><![endif]><!--[if lte IE 6]><table><tr><td><![endif]-->
    <ul class="pureCssMenum">
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/requests/${fn:escapeXml(appSessionObject.appUser.userId)}"><spring:message code="label.menu.list"/></a></li>
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/request/new"><spring:message code="label.menu.new"/></a></li>
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/search/requests"><spring:message code="label.menu.search"/></a></li>
    </ul>
    <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
    <li class="pureCssMenui"><a class="pureCssMenui" href="#"><span><spring:message code="label.menu.price_book"/></span><![if gt IE 6]></a><![endif]><!--[if lte IE 6]><table><tr><td><![endif]-->
    <ul class="pureCssMenum">
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/reports/machines/en"><spring:message code="label.menu.machines_en"/></a></li>
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/reports/machines/fr"><spring:message code="label.menu.machines_fr"/></a></li>
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/reports/solutions/en"><spring:message code="label.menu.solutions_en"/></a></li>
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/reports/solutions/fr"><spring:message code="label.menu.solutions_fr"/></a></li>
    </ul>
    <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
    <li class="pureCssMenui"><a class="pureCssMenui" href="#"><span><spring:message code="label.menu.backup_solution"/></span><![if gt IE 6]></a><![endif]><!--[if lte IE 6]><table><tr><td><![endif]-->
    <ul class="pureCssMenum">
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/reports/downloaditems"><spring:message code="label.menu.download_items"/></a></li>
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/resources/excel/ProfitLost Backup Generator.xls"><spring:message code="label.menu.download_template"/></a></li>
    </ul>
    <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
    <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/reports/summaries"><spring:message code="label.menu.reports"/></a></li>
    <c:if test="${appSessionObject.appUser.userAdmin eq true}">
      <li class="pureCssMenui"><a class="pureCssMenui" href="#"><span><spring:message code="label.menu.administration"/></span><![if gt IE 6]></a><![endif]><!--[if lte IE 6]><table><tr><td><![endif]-->
      <ul class="pureCssMenum">
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/sc"><spring:message code="label.menu.sales_channels"/></a></li>
        <li class="pureCssMenui"><a class="pureCssMenui" href="${pageContext.request.contextPath}/users"><spring:message code="label.menu.users"/></a></li>
      </ul>
    </c:if>
</ul>