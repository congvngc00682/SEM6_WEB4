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
        <title>EC Detail</title>
        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="css/sb-admin.css" rel="stylesheet">
        <!-- Morris Charts CSS -->
        <link href="css/plugins/morris.css" rel="stylesheet">
        <link href="css/modelLogin.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <script>
            // Get the modal
            var modal = document.getElementById('id01');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

            function processEC(decision, ecId) {
                
                if(decision === 'accept'){
                    window.location.href = 'ProcessEC?decision=accepted&id=' + ecId;
                } else {
                    var confirmRejection = confirm("Do you really want to reject this EC?");
                    if(confirmRejection) {
                        window.location.href = 'ProcessEC?decision=rejected&id=' + ecId;
                    } 
                }
                
            }
        </script>
    </head>
    <body>
        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
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
                                <a href="Login.jsp"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <li class="active">
                            <a href="Dashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                        </li>
                        <c:if test="${sessionScope.account.username eq 4}">
                        <li>
                            <a href="AddNewEC.jsp"><i class="fa fa-fw fa-plus-circle"></i> Add New EC</a>
                        </li>
                        </c:if>
                        <li>
                            <a href="charts.html"><i class="fa fa-fw fa-bar-chart-o"></i> Charts</a>
                        </li>
                        <li>
                            <a href="tables.html"><i class="fa fa-fw fa-table"></i> Tables</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>


            <div id="page-wrapper">
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                EC Detail
                            </h1>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <form id="ECDetailForm" action="" method="POST">
                                    <table class="table">
                                        <tr>
                                            <td><b>Title:</b>
                                            </td>
                                            <td>${ec.title}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><b>Description:</b>
                                            </td>
                                            <td>${ec.description}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><b>Submitted date:</b>
                                            </td>
                                            <td>${ec.submitted_date}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><b>Status:</b> 
                                            </td>
                                            <td>${ec.process_status}
                                            </td>
                                        </tr>
                                        <c:if test="${role ne 3}">
                                        <tr>
                                            <td><b>Assigned Coordinator:</b>
                                            </td>
                                            <td>${ec.coordinatorName}
                                            </td>
                                        </tr>
                                        </c:if>
                                        <tr>
                                            <td><b>Evidence:</b> 
                                            </td>
                                            <td>
                                                <c:if test="${empty evidences}">
                                                    No Evidences
                                                </c:if>
                                                <c:if test="${not empty evidences}">
                                                    <c:forEach items="${evidences}" var="evidence" varStatus="loop">
                                                         <a href="DownloadEvidence?filepath=${evidence.files}">Evidence ${loop.index + 1}</a>
                                                         <br>
                                                    </c:forEach>
                                                </c:if>
                                            </td>
                                        </tr>
                                        <!--check role to display button-->
                                        <tr>
                                            <c:if test="${role eq 3 && ec.process_status eq 'submitted'}">
                                                <td><button type="button" onclick="processEC('accept',${ec.id})">Accept</button>
                                                <button type="button" onclick="processEC('reject',${ec.id})">Reject</button>
                                                </td>
                                            </c:if>
                                            <c:if test="${role eq 4}">
                                                <td><a href="ViewEC?id=${ec.id}&role=4&action=edit">Edit</a>
                                                <a href="Dashboard">Back</a>
                                                </td>
                                            </c:if>
                                            <td>
                                                
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                            </td>
                                            <td><label id="error" style="color: red;"/>
                                            </td>
                                        </tr>
                                    </table>
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
