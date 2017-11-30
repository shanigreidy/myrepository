<%@ page import="il.ac.hit.model.*,java.util.*,java.util.Date.*,java.text.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<!DOCTYPE html>
<html lang = "en">
<head>
    <meta charset="UTF-8">
    <title>Edit Expense</title>
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
<div data-role="page" id="updateitem">
    <div data-role="header">
        <h1>Edit Expense</h1>
        <a href="/FinancialManagementApp/Controller/expenseslistpage" data-role="button" data-icon="back" id="back" class="ui-btn-left">Back</a>
        <a href="/FinancialManagementApp/Controller/logout" data-role="button" data-icon="delete" id="logout" class="ui-btn-right">Logout</a>        
    </div>     
     <%   String currentItemIdString = null;
          String dateString = null;
          String costString = null;
          CostItem currentItem = null;
          long currentItemId = 0;
          DateFormat date = null;

       if(request.getAttribute("updateItemId") != null)
           {
    	   currentItemIdString = (String)request.getAttribute("updateItemId");
    	   currentItemId = Long.parseLong(currentItemIdString);
    	   currentItem = CostItemDAO.getInstance().getCostItem(currentItemId);
    	   costString = Double.toString(currentItem.getCost());
     	   date = new SimpleDateFormat("MM/dd/yyyy");
     	   dateString = date.format(currentItem.getDate()); 
           }
      %>    
      <%  String updateitemMessage="";
           if (request.getAttribute("updateitemMessage")!=null)
            	updateitemMessage = (String)request.getAttribute("updateitemMessage");
      %>    
    <div id="updateitemMessage" style="color:#E21E1E;text-align: center;" ><%=updateitemMessage%></div>   
    <div data-role="content" class=button-design>
            <form  action="/FinancialManagementApp/Controller/updateitem" method="post">         
            <p class="textbox-design"> Description : <input type="text" name="description" id = "descriptionvalue" value="<%= currentItem.getDescription() %>" > </p>
            <p class="textbox-design"> Cost : <input type="text" name="cost" id = "costvalue" value="<%= costString %>"> </p>
            <p>Date: <input data-inline="true" type="text" name = "date" class="datepicker" value=<%= dateString %> placeholder="dd/MM/yyyy"></p>
             Choose Category : 
                  <select name="category" id = "categoryvalue">
                    <option><%= currentItem.getCategory() %></option>                                            				    	                 				    	
                 	<% if (currentItem.getCategory().equals(CostItemCategory.Food))
                     {                	  
                    %>
                      <option value="Sport">Sport</option>
                      <option value="Clothing">Clothing</option>
                      <option value="Entertainment">Entertainment</option>
                      <option value="Restaurants">Restaurants</option>
                      <option value="Fuel">Fuel</option>
                      <option value="Housewares">House Wares</option>
                      <option value="Other">Other</option>
                     <%} %>
                     <% if (currentItem.getCategory().equals(CostItemCategory.Sport))
                      {                	  
                      %>
                       <option value="Food">Food</option>
                       <option value="Clothing">Clothing</option>
                       <option value="Entertainment">Entertainment</option>
                       <option value="Restaurants">Restaurants</option>
                       <option value="Fuel">Fuel</option>
                       <option value="Housewares">House Wares</option>
                       <option value="Other">Other</option>
                      <%} %>
                      <% if (currentItem.getCategory().equals(CostItemCategory.Clothing))
                      {                	  
                      %>
                       <option value="Food">Food</option>
                       <option value="Sport">Sport</option>
                       <option value="Entertainment">Entertainment</option>
                       <option value="Restaurants">Restaurants</option>
                       <option value="Fuel">Fuel</option>
                       <option value="Housewares">House Wares</option>
                       <option value="Other">Other</option>
                     <%} %>
                     <% if (currentItem.getCategory().equals(CostItemCategory.Entertainment))
                       {                	  
                     %>
                        <option value="Food">Food</option>
                        <option value="Sport">Sport</option>
                        <option value="Clothing">Clothing</option>
                        <option value="Restaurants">Restaurants</option>
                        <option value="Fuel">Fuel</option>
                        <option value="Housewares">House Wares</option>
                        <option value="Other">Other</option>
                     <%} %>
                     <% if (currentItem.getCategory().equals(CostItemCategory.Restaurants))
                      {                	  
                      %>
                        <option value="Food">Food</option>
                        <option value="Sport">Sport</option>
                        <option value="Clothing">Clothing</option>
                        <option value="Entertainment">Entertainment</option>
                        <option value="Fuel">Fuel</option>
                        <option value="Housewares">House Wares</option>
                        <option value="Other">Other</option>
                     <%} %>
                     <% if (currentItem.getCategory().equals(CostItemCategory.Fuel))
                      {                	  
                     %>
                        <option value="Food">Food</option>
                        <option value="Sport">Sport</option>
                    	<option value="Clothing">Clothing</option>
                    	<option value="Entertainment">Entertainment</option>
                    	<option value="Restaurants">Restaurants</option>
                    	<option value="Housewares">House Wares</option>
                    	<option value="Other">Other</option>
                     <%} %>
                     <% if (currentItem.getCategory().equals(CostItemCategory.Housewares))
                      {                	  
                     %>
                        <option value="Food">Food</option>
                        <option value="Sport">Sport</option>
                    	<option value="Clothing">Clothing</option>
                    	<option value="Entertainment">Entertainment</option>
                    	<option value="Restaurants">Restaurants</option>
                    	<option value="Fuel">Fuel</option>
                    	<option value="Other">Other</option>
                     <%} %>
                     <% if (currentItem.getCategory().equals(CostItemCategory.Other))
                      {                	  
                     %>
                        <option value="Food">Food</option>
                        <option value="Sport">Sport</option>
                    	<option value="Clothing">Clothing</option>
                    	<option value="Entertainment">Entertainment</option>
                    	<option value="Restaurants">Restaurants</option>
                    	<option value="Fuel">Fuel</option>
                    	<option value="Housewares">House Wares</option>
                    <%} %>                               
                </select>                                         
            <input type="submit" data-icon="plus" value="Edit Expense" data-inline="true">
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