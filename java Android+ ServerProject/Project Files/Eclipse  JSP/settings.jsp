<%@ page import="il.ac.hit.model.*" language="java"  contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<!DOCTYPE html>
<html lang = "en">
<head>
	<meta charset="UTF-8">
    <title>Settings</title>
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
<div data-role="page" id="settings">
    <div data-role="header">
        <h1>Settings</h1>
        <a href="/FinancialManagementApp/Controller/homepage" data-role="button" data-icon="back" id="back" class="ui-btn-left">Back</a>
    	<a href="/FinancialManagementApp/Controller/logout" data-role="button" data-icon="delete" id="logout" class="ui-btn-right">Logout</a>
    </div>     
	  <% UserSettings settings = new UserSettings();
	     settings.setPeriod(UserSettingsPeriod.Day);
	     String budgetString = "";        
	    if (UserSettingsDAO.getInstance().isUserSettingsExists(LoggedInUser.getInstance().getCurrentUser().getId()))
	    {
	    	settings = UserSettingsDAO.getInstance().getUserSettings(LoggedInUser.getInstance().getCurrentUser().getId());
			budgetString = Double.toString(settings.getBudget());
	    }
	   %>
       <% String settingsMessage="";
             if (request.getAttribute("settingsMessage")!=null)
            	 settingsMessage = (String)request.getAttribute("settingsMessage");
       %>
    <div class="errormessage" id="settingsMessage"><%=settingsMessage%></div> 
    <div data-role="content" class=button-design>
        <br/>
        <form  action="/FinancialManagementApp/Controller/settings" method="post">
           Choose Period :          
                <select name="settingsPeriod" id = "settingsPeriodvalue">                  
                   <option><%= settings.getPeriod() %></option>
                   <% if (settings.getPeriod().equals(UserSettingsPeriod.Day))
                   {                	  
                   %>
                    <option value="Week">Week</option>
                    <option value="Month">Month</option> 
                  <%} %>
                  <% if (settings.getPeriod().equals(UserSettingsPeriod.Week))
                  {                	  
                  %>
                    <option value="Day">Day</option>
                    <option value="Month">Month</option> 
                  <%} %>
                  
                  <% if (settings.getPeriod().equals(UserSettingsPeriod.Month))
                  {                	  
                  %>
                    <option value="Day">Day</option>
                    <option value="Week">Week</option>
                  <%} %>
               </select>                 
            <p class="textbox-design"> Enter your budget : <input type="text" name="budget" value = "<%=budgetString %>"> </p>                        
           <div class="button-design"><input type="submit" data-icon="check" value="Submit" data-inline="true"></div>
         </form>         
    </div>   
</div>
</body>
</html>