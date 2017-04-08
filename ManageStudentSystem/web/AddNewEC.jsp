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
        <title>Add New EC</title>
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
                var year = document.getElementById("year").value;
                if (!txtTitle || !year) {
                    document.getElementById("lblError").innerHTML = "Please fill in required fields marked with *";
                    return false;
                }

                document.getElementById("lblError").innerHTML = "";
                document.getElementById("addNewECForm").submit();
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
                                Add New Extenuating Circumstance
                            </h4>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="panel-body">
                        <form id="addNewECForm" action="AddNewEC" class="form-horizontal" role="form" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Academic year:*</label>
                                <div class="col-sm-4">
                                    <select name="year" id="year" required="true">
                                        <option value="">---Select year---</option>
                                        <c:forEach items="${years}" var="year">
                                            <option value="${year.id}">${year.yearName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Title:*</label>
                                <div class="col-sm-4">
                                    <input type="text" name="title" id="txtTitle" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Description:</label>
                                <div class="col-sm-4">
                                    <textarea type="textarea" name="description" id="txtDescription" class="form-control" cols="22" rows="5" style="max-width: 335px">${ec.description}</textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Evidence 1:</label>
                                <div class="col-sm-4">
                                    <input type="file" name="evidence1" id="evidence1" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Evidence 2:</label>
                                <div class="col-sm-4">
                                    <input type="file" name="evidence2" id="evidence2" class="form-control">
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <button type="submit" class="btn btn-primary" onclick="return validateForm();">Add New</button>
                                    <button type="reset" class="btn btn-primary" onclick="resetError();">Reset</button>
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
