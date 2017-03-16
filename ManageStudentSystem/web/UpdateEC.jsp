<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
                                Update EC
                            </h1>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <form id="addNewAccountForm" action="UpdateEC" method="POST">

                                    <table class="table">
                                        <tr>
                                            <td>ID:
                                            </td>
                                            <td>
                                                <input value="${ec.id}" id="id" name="id"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Title:
                                            </td>
                                            <td>
                                                <input value=" ${ec.title}" type="text" id="title" name="title" required="true"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Description:
                                            </td>
                                            <td> 
                                               <input value=" ${ec.description}"  type="text" id="description" name="description" required="true"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Submitted date: 
                                            </td>
                                            <td>
                                                 <input value=" ${ec.submitted_date}"  type="text" id="submitteddate" name="submitteddate" required="true"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Process status:
                                            </td>
                                            <td>
                                                 <input value=" ${ec.process_status}"  type="text" id="processstatus" name="processstatus" required="true"/>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>Account id:
                                            </td>
                                            <td>
                                                 ${ec.account}
                                            </td>
                                        </tr>
                                         <tr>
                                            <td>
                                            </td>
                                            
                                            <td>
                                                <button type="submit" >Update</button>
                                                <button type="reset">Reset</button>
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
