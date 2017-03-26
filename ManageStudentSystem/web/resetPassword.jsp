<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
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
                var email = document.getElementById("email").value;
                if (!email) {
                    document.getElementById("alert").innerHTML = "Please enter email";
                    return false;
                } else if (!validateEmail(email)) {
                    document.getElementById("alert").innerHTML = "Invalid email format";
                    return false;
                }

                document.getElementById("alert").innerHTML = "";
                document.getElementById("ResetForm").submit();
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

        <div id="page-wrapper">
            <div class="container-fluid">
                <!-- Page Heading -->
                <!-- /.row -->
                <div class="panel-body">
                    <form id="ResetForm" action="ResetPassword" method="post">
                        <div class="imgcontainer">
                            <img src="images/img_avatar2.png" alt="Avatar" class="avatar">
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="text-align: left; margin-top: 15px">Email:*</label>
                            <div class="col-sm-10">
                                <input type="text" placeholder="example@email.com" name="email" id="email" class="form-control" required="true">
                            </div>
                            <label class="col-sm-2 control-label" style="text-align: left; margin-top: 15px;color: red" id="alert"></label><br>
                        </div>

                        <button type="button" onclick="return validateForm();">Submit</button>
                        <a href="Login.jsp">Back</a>
                    </form>
                </div>
                <!-- /.row -->
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>

    </body>
</html>
