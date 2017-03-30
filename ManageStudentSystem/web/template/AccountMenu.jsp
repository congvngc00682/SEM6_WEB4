<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!-- Brand and toggle get grouped for better mobile display -->
<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <c:if test="${sessionScope.account.role eq 1}">
        <a class="navbar-brand" href="Dashboard">Administrator Home</a>
    </c:if>
    <c:if test="${sessionScope.account.role eq 2}">
        <a class="navbar-brand" href="Dashboard">EC Manager Home</a>
    </c:if>
    <c:if test="${sessionScope.account.role eq 3}">
        <a class="navbar-brand" href="Dashboard">EC Coordinator Home</a>
    </c:if>
    <c:if test="${sessionScope.account.role eq 4}">
        <a class="navbar-brand" href="Dashboard">Student Home</a>
    </c:if>
</div>
<!-- Top Menu Items -->
<ul class="nav navbar-right top-nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${sessionScope.account.username} <b class="caret"></b></a>
        <ul class="dropdown-menu">
            <li>
                <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="Logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
            </li>
        </ul>
    </li>
</ul>