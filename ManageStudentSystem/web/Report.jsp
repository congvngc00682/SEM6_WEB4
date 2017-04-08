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
        <title>Admin Controller</title>
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
        <script>
            // Get the modal
            var modal = document.getElementById('id01');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
            $(document).ready(function () {
                var faculty = $("#facultyDiv");
                var year = $("#yearDiv");
                faculty.hide();
                year.hide();
                
                $("#btnReport").click(function () {
                    var report = $("#report").val();
                    var faculty = $("#faculty").val();
                    var year = $("#year").val();
                    var required = true;
                    console.log("reprot" + report + "faculty" + faculty + "year" + year);
                    if (report === '0') {
                        alert("Report is required");
                        return false;
                    } else if (report === '1') {
                        if (!faculty) {
                            alert("Faculty is required");
                            return false;
                        }
                        required = false;
                    } else if (report === '2') {
                        if (!year) {
                            alert("Year is required");
                            return false;
                        }
                        required = false;
                    } else if (report === '3') {
                        if (!year || !faculty) {
                            alert("Faculty and year are required");
                            return false;
                        }
                        required = false;
                    }


                    if (!required) {
                        $.ajax({
                            url: 'Report?faculty=' + faculty + '&year=' + year + '&report=' + report,
                            dataType: "json",
                            success: function (responseJson) {
                                console.log("reportData1: " + responseJson);
                                var dataSet = responseJson;

                                $.plot('#flot-placeholder', dataSet, {
                                    series: {
                                        pie: {
                                            show: true,
                                            label: {
                                                show: true,
                                                radius: 0.8,
                                                formatter: function (label, series) {
                                                    return '<div style="border:1px solid grey;font-size:8pt;text-align:center;padding:5px;color:white;">' +
                                                            Math.round(series.percent) +
                                                            '%</div>';
                                                },
                                                background: {
                                                    opacity: 0.8,
                                                    color: '#000'
                                                }
                                            }
                                        }
                                    }
                                });  
                            }

                        });
                    }

                });
            });

            function showOtherSelect() {
                var report = $("#report").val();
                var faculty = $("#facultyDiv");
                var year = $("#yearDiv");
                console.log("report: " + report);
                if (report === '0') {
                    faculty.hide();
                    year.hide();
                } else if (report === '1') {
                    faculty.show();
                    year.hide();
                } else if (report === '2') {
                    faculty.hide();
                    year.show();
                } else {
                    faculty.show();
                    year.show();
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
                            <h1 class="page-header">
                                <small>Statistics Overview</small>
                            </h1>
                        </div>
                    </div>
                    <div class="panel-body">
                        <form id="reportFrom" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="text-align: left">Report:*</label>
                                <div class="col-sm-4">
                                    <select name="report" id="report" required="true" placeholder="Select role" style="width: 200px;" onchange="showOtherSelect()">
                                        <option value="0">---Select report---</option>
                                        <option value="1">Claims within each Faculty</option>
                                        <option value="2">Claims for any academic year</option>
                                        <option value="3">Number of students making a claim</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group" id="facultyDiv">
                                <label class="col-sm-2 control-label" style="text-align: left">Faculty:*</label>
                                <div class="col-sm-4">
                                    <select name="faculty" id="faculty" required="true">
                                        <option value="">---Select faculty---</option>
                                        <c:forEach items="${faculties}" var="faculty">
                                            <option value="${faculty.id}">${faculty.facultyName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group" id="yearDiv">
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
                                <div class="col-sm-10 col-sm-offset-2">
                                    <button type="button" class="btn btn-primary" id="btnReport">Show report</button>
                                    <button type="reset" class="btn btn-primary" >Reset</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="row" id="reportDiv">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i>Chart</h3>
                                </div>
                                <div class="panel-body">
                                    <div id="flot-placeholder" style="max-width: 400px;height:300px"></div>
                                </div>

                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="panel panel-default" id="ecByFaccultyDetails">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-table fa-fw"></i>Details</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped" id="detailTable">

                                        </table>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- /.row -->

                </div>
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
        <script type="text/javascript" src="js/plugins/flot/jquery.flot.js"></script>
        <script type="text/javascript" src="js/plugins/flot/jquery.flot.pie.js"></script>
    </body>

</html>
