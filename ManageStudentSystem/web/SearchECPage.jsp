<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Add New Account</title>
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
                            <h1 class="page-header">
                                Search EC
                            </h1>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="panel-body">
                        <form id="searchECForm" action="SearchECNoAjax" method="POST" class="form-horizontal">

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Faculty:</label>
                                <div class="col-sm-4">
                                    <select name="faculty" id="faculty" >
                                        <option value="0" selected="true">---Select faculty---</option>
                                        <option value="1">FPT</option>
                                        <option value="2">Faculty 2</option>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Title:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="title" id="title" class="form-control" />
                                </div>
                            </div>
                            
                           <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Submitted By:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="studentName" id="studentName" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Assigned To:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="coordinatorName" id="coordinatorName" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Submitted Date:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="submittedDate" id="submittedDate" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Status:</label>
                                <div class="col-sm-4">
                                    <select name="status" id="status" >
                                        <option value="">---Select status---</option>
                                        <option value="submitted">submitted</option>
                                        <option value="accepted">accepted</option>
                                        <option value="rejected">rejected</option>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <button type="submit" class="btn btn-primary" id="btnSearch">Search</button>
                                    <button type="reset" class="btn btn-primary">Reset</button>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <label id="emailError" style="color: red;"/>
                                </div>
                            </div>
                            
                            <div id="tablediv">
                                <table cellspacing="0" id="countrytable" class="table"> 
                                </table>
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
