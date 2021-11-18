<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignIn</title>
    <style>
       body{
            background-color: #000000;
        }
        .wrap{
            width: 350px;
            margin: auto;
            background: #ffffff;
            margin-top: 50px;
            padding: 5px;
        }
        form{
            padding: 10px;
            font-family: arial;
            border: 1px dotted white;
            margin: 10px;
        }
        h2{
            text-align: center;
            background: rgb(75, 209, 86);
            color: white;
            padding: 10px;
            border-radius: 10px;
        }
        input{
            padding: 10px;
            margin: 5px;
            border-radius: 5px;
            border:none;
        }
        input[type=text], input[type=email], input[type=tel], input[type=password]{
            width: 90%;
        }
        input[type=submit]{
            width: 30%;
            background: rgb(75, 209, 86);
            margin-left: 39px;
            cursor: pointer;
            font-size: 18px;
            font-weight: bold;
            color: white;
        }
        input[type=submit]:hover{
            background: black;
        }
        select{
            padding: 10px;
            width: 32%;
            border-radius: 5px;
        }

    </style>
</head>
<body>
 <div class="wrap">
        <form action="IndexServlet" method="post">
            <h2>SignIn</h2>
            
            <input type="text" name="username" placeholder="User Name">
            <input type="password" name="password" placeholder="Password">
            <br>
            <br>
            <input type="submit" value="SignUp" onclick="signup()">
            <input type="submit" value="SignIn">
    
        </form>
    </div>
    
    
    <script>
        function signup(){
            window.open("signup.jsp", target="_blank");
        }
    </script>
</body>
</html>