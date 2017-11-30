<%@ page import="il.ac.hit.model.*,java.util.*,java.util.Date.*" language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html lang="en">
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
<div data-role="page" id="expenseslist">
    <div data-role="header">
        <h1>All Expenses</h1>
          <a href="/FinancialManagementApp/Controller/homepage" data-role="button" data-icon="back" id="back" class="ui-btn-left">Back</a>
    	  <a href="/FinancialManagementApp/Controller/logout" data-role="button" data-icon="delete" id="logout" class="ui-btn-right">Logout</a>
    </div>         
     <%
        String fromDateString = "";
	    String toDateString = "";
	    String categoryString = "";	    
     if(request.getAttribute("hasFilterDate").equals("true"))
	 	{
	 		fromDateString = (String)request.getAttribute("fromDate");
	 	    toDateString = (String)request.getAttribute("toDate");
	 	    categoryString = (String)request.getAttribute("category");
	 	}
 	 %>     
     <%  String deviationMessage="";
             if (request.getAttribute("deviationMessage")!=null)
            	 deviationMessage = (String)request.getAttribute("deviationMessage");
     %>       
     <%  String filterMessage="";
             if (request.getAttribute("filterMessage")!=null)
            	 filterMessage = (String)request.getAttribute("filterMessage");
     %>          
    <div class="errormessage" id="deviationMessage"><%=deviationMessage%></div>
    <div class="errormessage" id="filterMessage"><%=filterMessage%></div>        
    <div data-role="content">
        <br/>
        <p class="button-design"><a href="/FinancialManagementApp/Controller/newitempage" data-icon="plus" data-role="button" data-inline="false">Add</a></p>	   		
           <form action="/FinancialManagementApp/Controller/filterdates" method="post">  
         <br/>
                <table class="tabletransparent">
  					<tbody>
   						 <tr>			 
    						<td class="tdtransparent">
       							 Choose Category :
                					<select name="category" id = "categoryvalue">
                 				    	<option><%= categoryString %></option>                				    	                 				    	
                 				    	 <% if (categoryString.equals(""))
                                              {                	  
                                         %>
                                              <option value="Food">Food</option>
                                              <option value="Sport">Sport</option>
                    					      <option value="Clothing">Clothing</option>
                    					      <option value="Entertainment">Entertainment</option>
                    					      <option value="Restaurants">Restaurants</option>
                    					      <option value="Fuel">Fuel</option>
                    					      <option value="Housewares">House Wares</option>
                    					      <option value="Other">Other</option>
                                         <%} %>                				    	                 				    	
                 				    	 <% if (categoryString.equals("Food"))
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
                                          <% if (categoryString.equals("Sport"))
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
                                         <% if (categoryString.equals("Clothing"))
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
                                         <% if (categoryString.equals("Entertainment"))
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
                                         <% if (categoryString.equals("Restaurants"))
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
                                         <% if (categoryString.equals("Fuel"))
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
                                         <% if (categoryString.equals("Housewares"))
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
                                         <% if (categoryString.equals("Other"))
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
    						</td>
    						<td class="tdtransparent">
   							     Start Date: <input type="text" class="datepicker" name = "fromdate"  value = "<%= fromDateString%>" placeholder="dd/MM/yyyy"  data-inline="false">
  						    </td>
  						    <td class="tdtransparent">
       							 End Date: <input type="text" class="datepicker" name = "todate" value = "<%= toDateString%>" placeholder="dd/MM/yyyy"  data-inline="false">
    						</td>
    					</tr>
  					</tbody>
				</table>
				<br/>			
				<br/>  
        <p class="button-design"><input type="submit" data-icon="search" data-role="button" data-inline="false" value="Search"></p>
        </form>        
         <form action="/FinancialManagementApp/Controller/resetfilterdates" method="post"> 
        <p class="button-design"><input type="submit" data-icon="back" data-role="button" data-inline="false" value="Reset Search"></p>
        </form>        
        <br/>
        <br/>
        <div class="ui-responsive" >
        <table data-filter="false" data-mode="reflow" class="table text-center">
            <tr>               
                <th>Description</th>
                <th>Cost</th>
                <th>Date</th>
                <th>Category</th>
                <th></th>
            </tr>
        <%
       		List<CostItem> itemsList = null;
       	 	long userId = LoggedInUser.getInstance().getCurrentUser().getId();            
       	 	if(request.getAttribute("hasFilterDate").equals("true"))
       	 	{
       	 		String fromDate=(String)request.getAttribute("fromDate");
       	 	    String toDate=(String)request.getAttribute("toDate");
       	 	    String category=(String)request.getAttribute("category");
       	 		itemsList = CostItemDAO.getInstance().getCostItemListByDates(userId, fromDate,toDate, category);
       	 	}
       	 	else
       	 	{
        		itemsList = CostItemDAO.getInstance().getCostItemList(userId);
       	 	}
       		 for (CostItem item : itemsList)
       			 {
        	String description = item.getDescription();
        	double cost = item.getCost();
        	Date date = item.getDate(); 
        	String category = item.getCategory().toString();
        %>      	
        	   <tr>
                <td><%= description %> </td>
                <td><%= cost %></td>
                <td><%= date %></td>
                <td><%= category %></td>
                <td data-inline="true">
                    <form action="/FinancialManagementApp/Controller/delete" method="post"> 
                    <input type="submit" name="delete" value="Delete" data-icon="delete" data-role="button"/>
                    <input type="hidden" name="deletevalue" id="deletevalue" value=<%=item.getId()%>></form>               
                    <form action="/FinancialManagementApp/Controller/currentitem" method="post">
                    <input type="submit" name="edit" value="Edit" data-icon="edit" data-role="button"/>
                    <input type="hidden" name="editvalue" id="editvalue" value=<%=item.getId()%>></form>
               </td>
            </tr>
        <% }        
      	%>
        </table>
        </div>        
        <script>
           $(function () {
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
           });
         </script>   
    </div>       
</div>
</body>
</html>