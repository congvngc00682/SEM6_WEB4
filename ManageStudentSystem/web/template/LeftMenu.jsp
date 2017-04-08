<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<ul class="nav navbar-nav side-nav">
    <li>
        <a href="Dashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
    </li>
    <c:if test="${sessionScope.account.role eq 4}">
        <li>
            <a href="PrepareToAddEc"><i class="fa fa-fw fa-plus-circle"></i> Add New EC</a>
        </li>
    </c:if>
    <c:if test="${sessionScope.account.role eq 1}">
        <li>
            <a href="prepare"><i class="fa fa-fw fa-plus-circle"></i> Add New Account</a>
        </li>

    </c:if>
    <c:if test="${sessionScope.account.role ne 4}">
        <li>
            <a href="prepareSearch"><i class="fa fa-fw fa-search"></i> Search EC</a>
        </li>
        <li>
            <a href="PrepareForReport"><i class="fa fa-fw fa-bar-chart-o"></i> Report</a>
        </li>
    </c:if>

</ul>