<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<!DOCTYPE html>
<html lang = "en">
<head>
    <meta charset="UTF-8">
    <title>Financial Management</title>
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
<div data-role="page" id="index"> 
    <div data-role="header" class="header-title">
         <div class="applicationname" id="applicationname">Financial Management</div>
         <h4>It's free and helpful</h4>
    </div>    
    <div data-role="content">
    <br/>
      <p class="button-design"><a href="/FinancialManagementApp/Controller/registerpage" data-role="button" data-icon="edit" data-inline="false">Register</a></p>
      <br/>
      <p class="button-design"><a href="/FinancialManagementApp/Controller/loginpage" data-role="button" data-icon="user" data-inline="false">Login</a></p>  
    </div> 
</div>
</body>
</html>