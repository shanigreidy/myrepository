<%@ page import="il.ac.hit.model.*,java.util.*,java.util.Date.*,java.text.*" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Graphical Display</title>
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
	<%
		double sumsByCategory[] = null;
		int amountOfItemsByCategory[] = null;
	
		if (request.getAttribute("sumsByCategory") != null) {
			sumsByCategory = (double[]) request.getAttribute("sumsByCategory");
		}
		if (request.getAttribute("amountOfItemsByCategory") != null) {
			amountOfItemsByCategory = (int[]) request.getAttribute("amountOfItemsByCategory");
		}
	%>
</head>
<body>
	<div data-role="page" id="graphicaldisplay">
		<div data-role="header">
			<h1>Graphical Display</h1>
			<a href="/FinancialManagementApp/Controller/homepage" data-role="button" data-icon="back" id="back" class="ui-btn-left">Back</a> <a href="/FinancialManagementApp/Controller/logout" data-role="button" data-icon="delete" id="logout"
				class="ui-btn-right">Logout</a>
		</div>		
		<div id="chart1" style="width: 50%; height:400px;" ></div>

        <div id="chart2" style="width: 50%; height:400px; margin-bottom: 100px;"></div>			

		<script type="text/javascript">
			(function() {
				var chart1 = new CanvasJS.Chart("chart1", {
					width :350,
					height : 400,
					backgroundColor : "transparent",

					title : {
						text : "Amount Of Expenses By Category",
						fontSize: 20,
					},
					animationEnabled : true,
					legend : {
						verticalAlign : "top",
						horizontalAlign : "center"
					},
					data : [
						{
							indexLabelFontSize : 10,
							indexLabelFontFamily : "Monospace",
							indexLabelFontColor : "darkgrey",
							indexLabelLineColor : "darkgrey",
							indexLabelPlacement : "outside",
							type : "pie",
							showInLegend : true,
							toolTipContent : "{y} - <strong>#percent%</strong>",
							dataPoints : [
										{y : <%=sumsByCategory[0]%>, legendText : "Food", indexLabel : "Food"}, 
										{y : <%=sumsByCategory[1]%>, legendText : "Sport", indexLabel : "Sport"}, 
										{y : <%=sumsByCategory[2]%>, legendText : "Clothing", exploded : true, indexLabel : "Clothing"}, 
										{y : <%=sumsByCategory[3]%>, legendText : "Entertainment", indexLabel : "Entertainment"}, 
										{y : <%=sumsByCategory[4]%>, legendText : "Restaurants", indexLabel : "Restaurants"}, 
										{y : <%=sumsByCategory[5]%>, legendText : "Fuel", indexLabel : "Fuel"}, 
										{y : <%=sumsByCategory[6]%>, legendText : "Housewares", indexLabel : "Housewares"}, 
										{y : <%=sumsByCategory[7]%>, legendText : "Other", indexLabel : "Other"} 
										]
						} ]
				});
				chart1.render();
				chart1 = {};
			})();
		</script>
		<script type="text/javascript">
			(function() {
				var chart2 = new CanvasJS.Chart("chart2", {
					width : 300,
					height : 400,
					backgroundColor : "transparent",

					title : 
						{
							text : "Amount Of Items By Category",
							fontSize: 20,
						},
					axisY:
						{
							labelFontSize: 15,
						},
					axisX:
						{
							labelFontSize: 15,
							labelAngle: -30
						},
					indexLabelFontSize : 5,
					animationEnabled : true,
					data : [
						{
							// Change type to "doughnut", "line", "splineArea", etc.
							indexLabelFontSize : 13,
							indexLabelFontFamily : "Monospace",
							indexLabelFontColor : "darkgrey",
							indexLabelLineColor : "darkgrey",
							type : "column",
							dataPoints : 
								[	
									{y : <%=amountOfItemsByCategory[0]%>, label : "Food"},
									{y : <%=amountOfItemsByCategory[1]%>, label : "Sport"},
									{y : <%=amountOfItemsByCategory[2]%>, label : "Clothing"},
									{y : <%=amountOfItemsByCategory[3]%>, label : "Entertainment"},
									{y : <%=amountOfItemsByCategory[4]%>, label : "Restaurants"},
									{y : <%=amountOfItemsByCategory[5]%>, label : "Fuel"},
									{y : <%=amountOfItemsByCategory[6]%>, label : "Housewares"},
									{y : <%=amountOfItemsByCategory[7]%>, label : "Other"}
								]
						} ]
				});
				chart2.render();
				chart2 = {};
			})();
		</script>
	</div>
</body>
</html>