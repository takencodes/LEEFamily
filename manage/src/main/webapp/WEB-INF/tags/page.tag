<!DOCTYPE html>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="hideHeader" required="false" type="java.lang.Boolean" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ attribute name="bodyClasses" required="false" type="java.lang.String" %>
<%@ attribute name="css" fragment="true" %>
<%@ attribute name="script" fragment="true" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <link rel="icon" href="favicon.ico"/>
    <base href="${contextPath}/">
    <title>LeeFamily V1.0.0 - <c:out value="${title}"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <link rel="stylesheet" href="../../style/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../style/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="../../style/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../../style/css/jquery-ui.custom.min.css"/>
    <link rel="stylesheet" href="../../style/css/chosen.min.css"/>

    <!-- text fonts -->
    <link rel="stylesheet" href="../../style/css/fonts.googleapis.com.css"/>

    <!-- <![jquery-ui.min]-->
    <link rel="stylesheet" href="../../style/css/jquery-ui.min.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="../../style/css/ace.min.css" class="ace-main-stylesheet"
          id="main-ace-style"/>
    <link rel="stylesheet" href="../../style/css/my.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../../style/css/ace-part2.min.css"
          class="ace-main-stylesheet"/>
    <![endif]-->
    <link rel="stylesheet" href="../../style/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="../../style/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../../style/css/ace-ie.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="../../style/css/gbck.css"/>
    <%--<script src="msg.do"></script>--%>

    <!--[if !IE]> -->
    <script src="../../style/js/jquery-2.1.4.min.js"></script>
    <!-- <![endif]-->
    <script src="../../style/js/ace.min.js"></script>
    <!--[if IE]>
    <script src="../../style/js/jquery-1.11.3.min.js"></script>

    <!-- <![endif]-->
    <script src="../../style/js/jquery-ui.min.js"></script>
    <!-- ace settings handler -->
    <script src="../../style/js/ace-extra.min.js"></script>
    <!--[if lte IE 8]>
    <script src="../../style/js/html5shiv.min.js"></script>
    <script src="../../style/js/respond.min.js"></script>
    <![endif]-->
    <!-- <![datepicker]-->
    <link rel="stylesheet" href="../../style/css/bootstrap-datepicker3.min.css"/>
    <script src="../../style/js/bootstrap-datepicker.min.js"></script>
    <!-- <![moment]-->
    <script src="../../style/js/moment.min.js"></script>
    <!-- <![datetimepicker]-->
    <link rel="stylesheet" href="../../style/css/bootstrap-datetimepicker.min.css"/>
    <script src="../../style/js/bootstrap-datetimepicker.min.js"></script>
    <!-- <![layer]-->
    <link rel="stylesheet" href="../../style/layer/mobile/need/layer.css"/>
    <script src="../../style/layer/layer.js"></script>
    <!-- <![treeview]> -->
    <link rel="stylesheet" href="../../style/css/bootstrap-treeview.css">
    <script src="../../style/js/bootstrap-treeview.js"></script>
    <%-- <![select2]>--%>
    <link rel="stylesheet" href="../../style/css/select2.min.css">
    <script src="../../style/js/select2.min.js"></script>
    <jsp:invoke fragment="css"/>
</head>
<body class="${empty bodyClasses ? 'no-skin' : bodyClasses}">
<c:choose>
    <c:when test="${hideHeader}">
        <jsp:doBody/>
    </c:when>
    <c:otherwise>
        <sec:authentication property="principal.newMenus" var="menus"/>
        <div id="navbar" class="navbar navbar-default navbar-collapse ace-save-state"
             style="background: #F8F8F8;border-bottom: 1px solid #79B0CE;">
            <div class="navbar-header pull-left" style="width: 190px;height:45px;background: #CC3333">
                <img src="https://gbck-production.oss-cn-beijing.aliyuncs.com/logo_320x320.png" alt=""
                     style="width: 32px;float: left; margin-top: 5px">
                <a href="welcome.do" class="navbar-brand" style="padding-left: 0">
                    <small>
                        @LeeFamily
                    </small>
                </a>
                <button class="pull-right navbar-toggle navbar-toggle-img collapsed" type="button"
                        data-toggle="collapse" data-target=".navbar-buttons,.navbar-menu">
                    <span class="sr-only">Toggle user menu</span>

                    <img src="static-resource/ace/assets/images/avatars/user.jpg" alt="Jason's Photo">
                </button>
                <button class="pull-right navbar-toggle menu-toggler" type="button" data-toggle="collapse"
                        data-target="#sidebar" aria-expanded="true">
                    <span class="sr-only">Toggle sidebar</span>

                    <span class="icon-bar"></span>

                    <span class="icon-bar"></span>

                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-header pull-left">
                <div id="sidebar1" style="margin-top: 0px;"
                     class="sidebar h-sidebar navbar-collapse collapse ace-save-state">
                    <ul class="nav nav-list" id="one_nav" style="border-bottom:0px;">
                        <c:forEach var="menu" items="${menus}" varStatus="menuStatus">
                            <li class="hover ${_activeOneMenu_ eq menuStatus.index ? 'active' : ''}">
                                <a class="one-li-cur" data-one-menu="${menuStatus.index}"
                                   id="oneLevel_${menuStatus.index}"
                                   href="javacript:void(0);" data-container="${menu.name}">
                                    <span class="menu-text"
                                          style="font-size: 15px;font-weight: bold;"> ${menu.name} </span>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="navbar-buttons navbar-header pull-right navbar-collapse collapse" role="navigation"
                 style="padding: 0px;">
                <ul class="nav ace-nav">
                    <li class="light-blue dropdown-modal">
                        <a data-toggle="dropdown" href="#" class="dropdown-toggle" style="width:155px;">
                            <img class="nav-user-photo" src="static-resource/ace/assets/images/avatars/user.jpg"
                                 alt="Jason's Photo">
                            <span class="user-info">
									<small>欢迎,</small>
									<sec:authentication property="principal" var="userOfLogin"/>
                                    <c:out value="${userOfLogin.name}"/>
								</span>

                            <i class="ace-icon fa fa-caret-down"></i>
                        </a>
                        <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                            <li>
                                    <%--<a href="setting.do">WEB-INF\views\manage\admin\updatePassword.jsp--%>
                                <a href="manage/users/intoUpdatePassword.do">
                                    <i class="ace-icon fa fa-cog"></i>
                                    修改密码
                                </a>
                            </li>
                            <li>
                                <a href="manage/users/intoUpdateProfile.do">
                                    <i class="ace-icon fa fa-cog"></i>
                                    profile
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="logout">
                                    <i class="ace-icon fa fa-power-off"></i>
                                    注销
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>

        <div id="main-container" class="main-container ace-save-state">
            <script type="text/javascript">
                try {
                    ace.settings.loadState('main-container')
                } catch (e) {
                }
            </script>
            <div id="sidebar" class="sidebar responsive ace-save-state">
                <script type="text/javascript">
                    try {
                        ace.settings.loadState('sidebar')
                    } catch (e) {
                    }
                </script>
                <c:forEach var="menu" items="${menus}" varStatus="menuStatus">
                    <ul class="nav nav-list one-nav-one" id="sub_one_${menuStatus.index}"
                        style="display: ${_activeOneMenu_ eq menuStatus.index ? 'block' : 'none'}">
                        <c:forEach var="subMenu" items="${menu.children}" varStatus="subMenuStatus">
                            <li class="${_activeTwoMenu_ eq subMenuStatus.index ? 'active open' : ''}"
                                data-container="${subMenu.name}">
                                <a href="#" class="dropdown-toggle">
                                    <i class="menu-icon ${empty subMenu.icon ? 'fa fa-list' : subMenu.icon}"></i>
                                    <span class="menu-text"><c:out value="${subMenu.name}"/></span>
                                    <b class="arrow fa fa-angle-down"></b>
                                </a>
                                <b class="arrow"></b>
                                <ul class="submenu">
                                    <c:forEach var="child" items="${subMenu.children}" varStatus="childMenuStatus">
                                        <c:choose>
                                            <c:when test="${child.url eq '/partner/register.do' }">
                                                <li ${_activeTwoMenu_ eq subMenuStatus.index  and _activeThreeMenu_ eq childMenuStatus.index ? 'class="active"' : ''}
                                                        id="${child.url}" value="${child.url}">
                                                    <a data-one-menu="${menuStatus.index}"
                                                       data-main-menu="${subMenuStatus.index}"
                                                       data-sub-menu="${childMenuStatus.index}"
                                                       data-href="${pageContext.request.contextPath}${child.url}"
                                                       data-target="_blank" class="navable-a-tag">
                                                        <c:out value="${child.name}"/>
                                                    </a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li ${_activeTwoMenu_ eq subMenuStatus.index  and _activeThreeMenu_ eq childMenuStatus.index ? 'class="active"' : ''}
                                                        id="${child.url}" value="${child.url}">
                                                    <a data-one-menu="${menuStatus.index}"
                                                       data-main-menu="${subMenuStatus.index}"
                                                       data-sub-menu="${childMenuStatus.index}"
                                                       data-href="${pageContext.request.contextPath}${child.url}"
                                                       class="navable-a-tag">
                                                        <c:out value="${child.name}"/>
                                                    </a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>
                </c:forEach>
                <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                    <i id="sidebar-toggle-icon" class="ace-save-state ace-icon fa fa-angle-double-left"
                       data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
                </div>
            </div>
            <div class="main-content gbck-container">
                <script type="text/javascript">
                    try {
                        ace.settings.loadState('main-container')
                    } catch (e) {
                    }
                </script>
                <div class="main-content-inner">
                    <div class="page-content">
                        <div class="ace-settings-container" id="ace-settings-container">
                            <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                                <i class="ace-icon fa fa-cog bigger-130"></i>
                            </div>

                            <div class="ace-settings-box clearfix" id="ace-settings-box">
                                <div class="pull-left width-50">
                                    <div class="ace-settings-item">
                                        <div class="pull-left">
                                            <select id="skin-colorpicker" class="hide">
                                                <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                                                <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                                <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                                <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                                            </select>
                                        </div>
                                        <span>&nbsp; Choose Skin</span>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
                                               id="ace-settings-navbar" autocomplete="off">
                                        <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
                                               id="ace-settings-sidebar" autocomplete="off">
                                        <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
                                               id="ace-settings-breadcrumbs" autocomplete="off">
                                        <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"
                                               autocomplete="off">
                                        <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
                                               id="ace-settings-add-container" autocomplete="off">
                                        <label class="lbl" for="ace-settings-add-container">
                                            Inside
                                            <b>.container</b>
                                        </label>
                                    </div>
                                </div><!-- /.pull-left -->

                                <div class="pull-left width-50">
                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover"
                                               autocomplete="off">
                                        <label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact"
                                               autocomplete="off">
                                        <label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight"
                                               autocomplete="off">
                                        <label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
                                    </div>
                                </div><!-- /.pull-left -->
                            </div><!-- /.ace-settings-box -->
                        </div>
                        <jsp:doBody/>
                    </div>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
<!-- basic scripts -->

<script type="text/javascript">
    if ('ontouchstart'
        in document.documentElement) document.write("<script src='../../style/js/jquery.mobile.custom.min.js'>"
        + "<"
        + "/script>");
</script>
<script src="../../style/js/bootstrap.min.js"></script>
<script src="../../style/js/bootstrap-table.min.js"></script>
<script src="../../style/js/bootstrap-table-zh-CN.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="../../style/js/excanvas.min.js"></script>
<![endif]-->
<script src="../../style/js/jquery-ui.custom.min.js"></script>
<script src="../../style/js/jquery.ui.touch-punch.min.js"></script>
<script src="../../style/js/jquery.easypiechart.min.js"></script>
<script src="../../style/js/jquery.sparkline.index.min.js"></script>
<script src="../../style/js/jquery.flot.min.js"></script>
<script src="../../style/js/jquery.flot.pie.min.js"></script>
<script src="../../style/js/jquery.flot.resize.min.js"></script>
<script src="../../style/js/chosen.jquery.min.js"></script>
<script src="../../style/js/jquery.dataTables.min.js"></script>
<script src="../../style/js/jquery.dataTables.bootstrap.min.js"></script>

<!-- ace scripts -->
<script src="../../style/js/ace-elements.min.js"></script>

<!-- support for jquery validator -->
<script src="../../style/js/jquery.validate.min.js"></script>

<!-- support for act beetbox -->
<script src="../../style/js/bootbox.js"></script>

<script src="../../style/js/gbck.js?ver=123"></script>

<script src="../../style/common/js/gbck-img.js"></script>
<script src="../../style/common/js/sockjs.min.js"></script>
<script src="../../style/common/js/stomp.js"></script>
<script type="text/javascript">
    var userId = '${userOfLogin.company.id}';
</script>
<script src="../../style/common/js/remind.js"></script>
<jsp:invoke fragment="script"/>
<audio id='newOrderMessage' src="../../style/remind.mp3" align='center' border='0' width='1' height='1'></audio>
<audio id='newMessage' src="../../style/newMessage.mp3" align='center' border='0' width='1' height='1'></audio>
</html>
