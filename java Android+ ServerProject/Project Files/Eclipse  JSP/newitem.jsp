<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<!DOCTYPE html>
<html lang = "en">
<head>
    <meta charset="UTF-8">
    <title>New Expense</title>      
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
<div data-role="page" id="newitem">
    <div data-role="header">
        <h1>New Expense</h1>
        <a href="/FinancialManagementApp/Controller/expenseslistpage" data-role="button" data-icon="back" id="back" class="ui-btn-left">Back</a>
    	<a href="/FinancialManagementApp/Controller/logout" data-role="button" data-icon="delete" id="logout" class="ui-btn-right">Logout</a>
    </div>
        <%  String newitemMessage="";
             if (request.getAttribute("newitemMessage")!=null)
            	 newitemMessage = (String)request.getAttribute("newitemMessage");
        %>        
    <div class="errormessage" id="newitemMessage"><%=newitemMessage%></div>     
    <div data-role="content" class=button-design>
            <br/>
            <form  action="/FinancialManagementApp/Controller/newitem" method="post">     
            <p class="textbox-design"> Description : <input type="text" name="description" id = "descriptionvalue" > </p>
            <p class="textbox-design"> Cost : <input type="text" name="cost" id = "costvalue"> </p>
            <p>Date: <input data-inline="true" type="text" class="datepicker" name = "date" placeholder="dd/MM/yyyy" ></p>    	                
             Choose Category :
                <select name="category" id = "categoryvalue">
                    <option value=""></option>
                    <option value="Food">Food</option>
                    <option value="Sport">Sport</option>
                    <option value="Clothing">Clothing</option>
                    <option value="Entertainment">Entertainment</option>
                    <option value="Restaurants">Restaurants</option>
                    <option value="Fuel">Fuel</option>
                    <option value="Housewares">Housewares</option>
                    <option value="Other">Other</option>
                </select> 
            <p class="button-design"><input type="submit" data-icon="plus" value="Add Expense" data-inline="true"></p>
        </form>
        <script>
           $(function () {
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});   
           });
	     </script>
    </div>
</div>
</body>
</html>