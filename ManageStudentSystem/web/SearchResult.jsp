<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>EC Manager Home Page</title>
        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="css/sb-admin.css" rel="stylesheet">
        <!-- Morris Charts CSS -->
        <link href="css/plugins/morris.css" rel="stylesheet">
        <link href="css/modelLogin.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <script src="js/jquery.js" type="text/javascript"></script>
        <script type="text/javascript">

        </script>
    </head>
    <body>
        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <jsp:include page="template/AccountMenu.jsp"/>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <jsp:include page="template/LeftMenu.jsp"/>
                </div>
                <!-- /.navbar-collapse -->
            </nav>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h4 class="page-header">
                                Search Result
                            </h4>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <form id="searchECForm" action="" method="POST">
                                    <div class="table-responsive">
                                        <table class="table table-hover">
                                            <tr>
                                                <th>Title</th>
                                                <th>Description</th>
                                                <th>Status</th>
                                                <th>Submitted Date</th>
                                                    <c:if test="${sessionScope.account.role ne 4}">
                                                    <th>Submitted By</th>
                                                    </c:if>
                                                    <c:if test="${sessionScope.account.role ne 3}">
                                                    <th>Assigned To</th>
                                                    </c:if>
                                                <th>Processed Date</th>
                                                <th>Active</th>
                                                    <c:if test="${sessionScope.account.role eq 4}">
                                                    <th></th>
                                                    </c:if>
                                            </tr>
                                            <c:forEach items="${ecs}" var="ec">
                                                <tr>
                                                    <td><a href="ViewEC?id=${ec.id}&role=${sessionScope.account.role}">${ec.title}</a></td>
                                                    <td>${ec.description}</td>
                                                    <td>${ec.process_status}</td>
                                                    <td>${ec.submitted_date}</td>
                                                    <c:if test="${sessionScope.account.role ne 4}">
                                                        <td>${ec.studentName}</td>
                                                    </c:if>
                                                    <c:if test="${sessionScope.account.role ne 3}">
                                                        <td>${ec.coordinatorName}</td>
                                                    </c:if>
                                                    <td>${ec.processedDate}</td>
                                                    <td>
                                                        <c:if test="${ec.isActive eq 'true'}">
                                                            Yes
                                                        </c:if>
                                                        <c:if test="${ec.isActive eq 'false'}">
                                                            No
                                                        </c:if> </p>
                                                    </td>
                                                    <c:if test="${sessionScope.account.role eq 4}">
                                                        <td><c:if test="${ec.process_status eq 'submitted' && ec.isActive eq true}">
                                                                <a href="ViewEC?id=${ec.id}&role=4&action=edit"><i class="fa fa-fw fa-edit"></i>edit</a>
                                                            </c:if>
                                                        </td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- /.row -->
                    <!-- /.container-fluid -->
                </div>
                <!-- /#page-wrapper -->
            </div>
            <!-- /#wrapper -->

            <!-- jQuery -->
            <script src="js/jquery.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="js/bootstrap.min.js"></script>

            <!-- Morris Charts JavaScript -->
            <script src="js/plugins/morris/raphael.min.js"></script>
            <script src="js/plugins/morris/morris.min.js"></script>
            <script src="js/plugins/morris/morris-data.js"></script>


    </body>

</html>
