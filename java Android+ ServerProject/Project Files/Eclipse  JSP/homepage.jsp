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
<div data-role="page" id="homepage">
	 <div data-role="header">
	    <div class="applicationname" id="applicationname">Financial Management</div>
	    <a href="/FinancialManagementApp/Controller/logout" data-role="button" data-icon="delete" id="logout" class="ui-btn-right">Logout</a>
	 </div>   
	 <div data-role="content" class="ui-responsive">
        <br/>
    	<p class="button-design"><a href="/FinancialManagementApp/Controller/expenseslistpage" data-role="button" data-icon="grid" data-inline="false">Expenses</a></p>
    	<br/>
    	<p class="button-design"><a href="/FinancialManagementApp/Controller/graphicaldisplaypage"  data-role="button" data-icon="bars" data-inline="false">Graphics</a></p> 
    	<br/>
    	<p class="button-design"><a href="/FinancialManagementApp/Controller/settingspage"  data-role="button" data-icon="gear" data-inline="false">Settings</a></p>   
	 </div>  
</div>  
</body>
</html>