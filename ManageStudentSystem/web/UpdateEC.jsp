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
        <title>Update EC</title>
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
            function validateForm() {
                var txtTitle = document.getElementById("txtTitle").value;
                if (!txtTitle) {
                    document.getElementById("lblError").innerHTML = "Please fill in required fields marked with *";
                    return false;
                }

                document.getElementById("lblError").innerHTML = "";
                document.getElementById("UpdateECForm").submit();
                return true;
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
                            <h4 class="page-header">
                                Update Extenuating Circumstance
                            </h4>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="panel-body">

                        <form id="UpdateECForm" class="form-horizontal" action="UpdateEC" method="POST" enctype="multipart/form-data">

                            <input type="hidden" id ="hiddenId" name="id" value="${ec.id}"/>
                            <input type="hidden" id ="hiddenStatus" name="status" value="${ec.process_status}"/>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Title*</label>
                                <div class="col-sm-4">
                                    <input type="text" name="title" id="txtTitle" class="form-control" value="${ec.title}" required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Description:</label>
                                <div class="col-sm-4">
                                    <textarea type="textarea" name="description" id="txtDescription" class="form-control" cols="22" rows="5" style="max-width: 335px">${ec.description}</textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left; margin-top: 20px;">Evidence: </label>
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
                                    <br>
                                    <input type="file" name="evidence1" id="evidence1" class="form-control" required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <button type="button" class="btn btn-primary" onclick="return validateForm();">Submit</button>
                                    <button type="reset" class="btn btn-primary" onclick="return resetError();">Reset</button>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <label id="lblError" style="color: red;"/>
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
