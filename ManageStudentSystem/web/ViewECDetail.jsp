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

                if (decision === 'accept') {
                    window.location.href = 'ProcessEC?decision=accepted&id=' + ecId;
                } else {
                    var confirmRejection = confirm("Do you really want to reject this EC?");
                    if (confirmRejection) {
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
                            <h4 class="page-header" style="float: left;">
                                Extenuating Circumstance Detail
                            </h4>
                            <a class="page-header" href="" onclick="window.history.go(-1)" style="float: right">Back</a>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="panel-body">

                        <form id="ECDetailForm" class="form-horizontal" action="" method="POST">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Title:</label>
                                <div class="col-sm-4">
                                    <p style="margin-top: 5px">${ec.title}</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Description:</label>
                                <div class="col-sm-4">
                                    <p style="margin-top: 5px">${ec.description}</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Submitted date:</label>
                                <div class="col-sm-4">
                                    <p style="margin-top: 5px">${ec.submitted_date}</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Process Status:</label>
                                <div class="col-sm-4">
                                    <p style="margin-top: 5px">${ec.process_status}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Active:</label>
                                <div class="col-sm-4">
                                    <p style="margin-top: 5px"> 
                                        <c:if test="${ec.isActive eq 'true'}">
                                            Yes
                                        </c:if>
                                        <c:if test="${ec.isActive eq 'false'}">
                                            No
                                        </c:if> </p>
                                </div>
                            </div>

                            <c:if test="${role ne 3}">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" style="text-align: left">Assigned Coordinator:</label>
                                    <div class="col-sm-4">
                                        <p style="margin-top: 15px">${ec.coordinatorName}</p>
                                    </div>
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Evidence:</label>
                                <div class="col-sm-4">
                                    <c:if test="${empty evidences}">
                                        No Evidences
                                    </c:if>
                                    <c:if test="${not empty evidences}">
                                        <c:forEach items="${evidences}" var="evidence" varStatus="loop">
                                            <a href="DownloadEvidence?filepath=${evidence.files}">Evidence ${loop.index + 1}</a>
                                            <br>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div> 

                            <!--check role to display button-->
                            <div class="form-group">
                                <c:if test="${role eq 3 && ec.process_status eq 'submitted' && ec.isActive eq true}">
                                    <div class="col-sm-10 col-sm-offset-2">
                                        <a class="btn btn-primary" onclick="processEC('accept',${ec.id})">Accept</a>
                                        <a class="btn btn-primary" onclick="processEC('reject',${ec.id})">Reject</a>
                                    </div>
                                </c:if>
                                <c:if test="${role eq 4 && ec.process_status eq 'submitted' && ec.isActive eq true}">
                                    <div class="col-sm-10 col-sm-offset-2">
                                        <a href="ViewEC?id=${ec.id}&role=4&action=edit" class="btn btn-primary">Edit</a>
                                        <a class="btn btn-primary" href="Dashboard">Back</a>
                                    </div>
                                </c:if>

                            </div>

                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <label id="error" style="color: red;"/>
                                </div>
                            </div>

                        </form>

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
