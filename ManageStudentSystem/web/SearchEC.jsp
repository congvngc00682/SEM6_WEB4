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
        <script type="text/javascript">
            $(document).ready(function () {
                $("#tablediv").hide();
                $("#btnSearch").click(function () {
                    var faculty = $("#faculty").val();
                    var title = $("#title").val();
                    var studentName = $("#studentName").val();
                    var coordinatorName = $("#coordinatorName").val();
                    var submittedDate = $("#submittedDate").val();
                    var status = $("#status").val();
                    $.ajax({
                        url: 'SearchEC?faculty=' + faculty + '&title=' + title + '&studentName=' + studentName + '&coordinatorName=' + coordinatorName + '&submittedDate=' + submittedDate + '&status=' + status,
                        dataType: "json",
                        success: function (responseJson) {
                            if (responseJson.length !== 0) {
                                                $("#countrytable").html("");
                                                var table1 = $("#countrytable");
                                var th = $("<tr><th>Id</th><th>Title</th><th>Description</th><th>Status</th><th>Date</th><th>Assigned To</th></tr>");
                                th.appendTo(table1);

                                                $.each(responseJson, function (key, value) {
                                                        var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                                                            rowNew.children().eq(0).text(value['id']);
                                                            rowNew.children().eq(1).html("<a href='ViewEC?id=" + value['id'] + "'>" + value['title'] + "</a>");
                                                            rowNew.children().eq(2).text(value['description']);
                                                            rowNew.children().eq(3).text(value['process_status']);
                                                            rowNew.children().eq(4).text(value['submitted_date']);
                                                            rowNew.children().eq(5).text(value['coordinatorName']);

                                                            rowNew.appendTo(table1);
                                                });
                             } else {
                                $("#countrytable").html("No Record found!");
                            }
                             $("#tablediv").show();          
                        }
                    }
                    );
                });
            });

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
                                Search EC
                            </h1>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="panel-body">
                        <form id="searchECForm" action="" method="POST" class="form-horizontal">

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Faculty:</label>
                                <div class="col-sm-4">
                                    <select name="faculty" id="faculty" required="true">
                                        <option value="0" selected="true">---Select faculty---</option>
                                        <option value="1">FPT</option>
                                        <option value="2">Faculty 2</option>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Title:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="title" id="title" class="form-control" required="true"/>
                                </div>
                            </div>
                            
                           <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Submitted By:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="studentName" id="studentName" class="form-control" required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Assigned To:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="coordinatorName" id="coordinatorName" class="form-control" required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Submitted Date:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="submittedDate" id="submittedDate" class="form-control" required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Status:</label>
                                <div class="col-sm-4">
                                    <select name="status" id="status" required="true">
                                        <option value="">---Select status---</option>
                                        <option value="submitted">submitted</option>
                                        <option value="accepted">accepted</option>
                                        <option value="rejected">rejected</option>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <button type="button" class="btn btn-primary" id="btnSearch">Search</button>
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
