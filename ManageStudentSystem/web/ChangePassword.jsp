<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link href="css/login.css" rel="stylesheet">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <!-- Morris Charts CSS -->
        <link href="css/plugins/morris.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <script type="text/javascript">

            function validateForm() {
                if (!validateRequiredField()) {
                    document.getElementById("alert").innerHTML = "Please fill in required fields marked with *";
                    return false;
                } else if (!validateEmail(email)) {
                    document.getElementById("alert").innerHTML = "Invalid email format";
                    return false;
                }
                else if (!ComparePassword()) {
                    document.getElementById("alert").innerHTML = "New Password and Retype Password not match";
                    return false;
                }

                document.getElementById("alert").innerHTML = "";
                document.getElementById("changePasswordForm").submit();
                return true;
            }

            function validateRequiredField() {
                var email = document.getElementById("email").value;
                var oldPassword = document.getElementById("oldPassword").value;
                var newPassword = document.getElementById("newPassword").value;
                var confirmPassword = document.getElementById("confirmPassword").value;
                if ((!email || !oldPassword || !newPassword || !confirmPassword)) {
                    return false;
                }
                return true;
            }


            function validateEmail() {
                var email = document.getElementById("email").value;
                var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
                return email_regex.test(email);
            }

            function ComparePassword() {
                var firstInput = document.getElementById("newPassword").value;
                var secondInput = document.getElementById("confirmPassword").value;
                console.log(firstInput + "/" + secondInput);
                if (firstInput === secondInput) {
                    document.getElementById("alert").innerHTML = "";
                    return true;
                } else if (firstInput !== secondInput) {
                    document.getElementById("alert").innerHTML = "New Password and Retype Password not match";
                    return false;
                }
            }
        </script>
    </head>

    <body>

        <div id="page-wrapper">
            <div class="container-fluid">
                <!-- Page Heading -->
                <!-- /.row -->
                <div class="panel-body">
                    <form id="changePasswordForm" action="changePassword" method="post">
                        <div class="imgcontainer">
                            <img src="images/img_avatar2.png" alt="Avatar" class="avatar">
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="text-align: left; margin-top: 15px">Email:*</label>
                            <div class="col-sm-10">
                                <input type="text" placeholder="example@email.com" name="email" id="email" class="form-control" required="true">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="text-align: left;">Old Password:*</label>
                            <div class="col-sm-10">
                                <input type="password" id="oldPassword" name="oldPassword" placeholder="Old Password:" class="form-control" required="true">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="text-align: left;">New Password:*</label>
                            <div class="col-sm-10">
                                <input type="password" id="newPassword" name="newPassword" placeholder="New Password:" class="form-control" required="true">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="text-align: left;">Confirm Password:*</label>
                            <div class="col-sm-10">
                                <input type="password" id="confirmPassword"  name="confirmPassword" placeholder="Re-enter New Password:" onblur="ComparePassword()" class="form-control" required="true">
                            </div>
                        </div>


                        <label id="alert" style="color: red"></label><br>
                        <button type="button" onclick="return validateForm();">Change</button>

                    </form>
                </div>
                <!-- /.row -->
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
    </body>
    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>

</html>
