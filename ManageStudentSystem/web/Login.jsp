<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
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
//                if (!validateRequiredField()) {
//                    document.getElementById("alert").innerHTML = "Please fill in required fields marked with *";
//                    return false;
//                } else if (!validateEmail(email)) {
//                    document.getElementById("alert").innerHTML = "Invalid email format";
//                    return false;
//                }
//                else if (!ComparePassword()) {
//                    document.getElementById("alert").innerHTML = "New Password and Retype Password not match";
//                    return false;
//                }

                document.getElementById("alert").innerHTML = "";
                document.getElementById("LoginForm").submit();
                return true;
            }

            function validateRequiredField() {
                var email = document.getElementById("uname").value;
                var password = document.getElementById("pws").value;
                if ((!email || !password)) {
                    return false;
                }
                return true;
            }

           
        </script>
    </head>
    <body>
        <div id="page-wrapper">
            <div class="container-fluid">
                <!-- Page Heading -->
                <!-- /.row -->
                <div class="panel-body">
                    <form id="LoginForm" action="Login" method="post">
                        <div class="imgcontainer">
                            <img src="images/img_avatar2.png" alt="Avatar" class="avatar">
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="text-align: left; margin-top: 15px">Username:*</label>
                            <div class="col-sm-10">
                                <input type="text" placeholder="example@email.com" name="uname" id="uname" class="form-control" required="true">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="text-align: left; margin-top: 15px">Password:*</label>
                            <div class="col-sm-10">
                                <input type="password" id="psw" name="psw" placeholder="Old Password:" class="form-control" required="true">
                            </div>
                        </div>

                        <label id="alert" style="color: red"></label><br>
                        <button type="submit" >Login</button>
                        <a href="ChangePassword.jsp">Change password</a>
                        <span class="psw">Forgot <a href="ResetPassword.jsp">password?</a></span>
                    </form>
                </div>
                <!-- /.row -->
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>

    </body>
</html>
