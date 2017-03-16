<%@page import="entities.ExtenuatingCircumstance"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Add New ExtenuatingCircumstance</title>
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
                if (!validateRequiredField()) {
                    document.getElementById("emailError").innerHTML = "Please fill in required fields marked with *";
                    return false;
                } else if (!validateEmail(email)) {
                    document.getElementById("emailError").innerHTML = "Invalid email format";
                    return false;
                }

                document.getElementById("emailError").innerHTML = "";
                document.getElementById("addNewAccountForm").submit();
                return true;
            }

            function validateRequiredField() {
                var role = document.getElementById("role").value;
                var faculty = document.getElementById("faculty").value;
                var firstname = document.getElementById("firstname").value;
                var lastname = document.getElementById("lastname").value;
                var email = document.getElementById("email").value;
                if ((!role || !faculty || !firstname || !lastname || !email)) {
                    return false;
                }
                return true;
            }

            function validateEmail() {
                var email = document.getElementById("email").value;
                var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
                return email_regex.test(email);
            }
        </script>
    </head>
    <body>
        <jsp:useBean id="beanExtenuatingCircumstance" class="ExtenuatingCircumstance" scope="session"></jsp:useBean>
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
                    <a class="navbar-brand" href="AdminManager.jsp">Administrator</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
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
                            <a href="AdminManager.jsp"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="AddNewAccount.jsp"><i class="fa fa-fw fa-plus-circle"></i> Add New Account</a>
                        </li>
                        <li>
                            <a href="ViewEC"><i class="fa fa-fw fa-bar-chart-o"></i> View EC</a>
                        </li>
                        <li>
                            <a href="LoadEC"><i class="fa fa-fw fa-table"></i> Load EC</a>
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
                                Add new ExtenuatingCircumstance
                            </h1>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="panel-body">
                        <form method="POST" class="form-horizontal" role="form" action="AddExtenuatingCircumstance">

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Title:</label>
                                <div class="col-sm-10">
                                    <input type="text" name="title" class="form-control"/>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Description:</label>
                                <div class="col-sm-10">
                                    <input type="text" name="description" class="form-control">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Submitted date:</label>
                                <div class="col-sm-10">
                                    <input type="text" name="submitted_date" class="form-control">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Process status:</label>
                                <div class="col-sm-10">
                                    <input type="text" name="process_status" class="form-control">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Processed date:</label>
                                <div class="col-sm-10">
                                    <input type="text" name="processed_date" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Coordinator:</label>
                                <div class="col-sm-10">
                                    <select name="coordinator">
                                        <c:forEach var="c" items="${beanProduct.listP}">
                                            <option value="${c.pId}">${c.pName}</option>
                                        </c:forEach><br>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <button type="submit" class="btn btn-primary" onclick="alert('Add new Success !')">Add New</button>
                                    <button type="reset" class="btn btn-primary">Reset</button>
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
