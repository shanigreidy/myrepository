<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>  
  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	<script type="text/javascript" src="/FinancialManagementApp/canvasjs-1.7.0/canvasjs.min.js"></script>	   
    <link rel="stylesheet" type="text/css" href="themes/jquery.mobile.icons.min.css" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="themes/financialmanagement.css" />
    <link rel="stylesheet" type="text/css" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile.structure-1.4.5.min.css" />   
  	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div data-role="page" id="register">
    <div data-role="header">
        <h1>Register</h1>
        <a href="/FinancialManagementApp/Controller/indexpage" data-role="button" data-icon="back" id="back" class="ui-btn-left">Back</a> 
    </div>  
    	<% String registerMessage = "";
		if (request.getAttribute("registerMessage") != null) 
		{
			registerMessage = (String) request.getAttribute("registerMessage");
		}		
    	%>	
    	<div class="errormessage" id="registerMessage"><%=registerMessage%></div>
    <div data-role="content" class="button-design ui-responsive">
     	<form  action="/FinancialManagementApp/Controller/register" method="post">    
	        <p class="textbox-design"> User Name : <input type="text" name="userName" id = "userNamevalue" > </p>
	        <p class="textbox-design"> First Name : <input type="text" name="firstName" id = "firstNamevalue"> </p>
	        <p class="textbox-design"> Last Name : <input type="text" name="lastName" id = "lastNamevalue"> </p>
	        <p class="textbox-design"> E-Mail : <input type="text" name="eMail" id = "eMailvalue"> </p>
	        <p class="textbox-design"> Password : <input type="password" name="password" id = "passwordvalue"> </p>
	        <p class="textbox-design"> Confirm Password : <input type="password" name="confirmPassword" id = "confirmPasswordvalue"> </p>
	        <div class="text-center button-design"><p> <input type="submit" data-icon="check" value="Register" data-inline="true"></p></div>
       </form>
     </div>    
</div> 
</body>
</html>