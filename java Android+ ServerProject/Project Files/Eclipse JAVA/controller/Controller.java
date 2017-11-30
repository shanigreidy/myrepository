package il.ac.hit.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import il.ac.hit.model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import il.ac.hit.model.*;
/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller/*")
public class Controller extends HttpServlet {
	
	private long editValue = 0;
	private boolean hasSettings = false;
	private boolean hasFilterDate = false;
	private boolean hasDeviation = false;
	private String fromDateFilterString = null;
	private String toDateFilterString = null;
	private String categoryFilterString = null;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
				
		switch (path)
		{
		//Add new cost item to cost items list 
		  case "/newitem":
			  try
			  {
				  String costString = (String) request.getParameter("cost");
				  String descriptionString = (String) request.getParameter("description");
				  String categoryString = (String) request.getParameter("category");			  
				  String dateString = (String) request.getParameter("date");
				  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				  double costValue = 0;
				  					
			      if (descriptionString.equals("") || categoryString.equals("") || dateString.equals("") || costString.equals(""))
					{
					   request.setAttribute("newitemMessage", "Please complete all the Fields.");
					   dispatcher = getServletContext().getRequestDispatcher("/newitem.jsp");
					   dispatcher.forward(request, response);
					}
				  else if (!CostItemDAO.getInstance().isNumber(costString))
				  {
					  request.setAttribute("newitemMessage", "The cost you entered is not valid."
					  		+ " Enter only numbers.");
					  dispatcher = getServletContext().getRequestDispatcher("/newitem.jsp");
					  dispatcher.forward(request, response);					  
				  }
				  else
				  {		  
					  try
					  {
					    costValue = Double.parseDouble(costString);
					  }
					  catch(NumberFormatException ex)
					  {
					    ex.printStackTrace();
					  }
					  			
					  CostItemCategory category = CostItemCategory.valueOf(categoryString); 
					  
					  Date date = null;
						  
				 	 try 
					   {
				 		 date = formatter.parse(dateString);
					   } 
							
					 catch (ParseException e) 
					   {							
						e.printStackTrace();
					   }
							
					   long userId = LoggedInUser.getInstance().getCurrentUser().getId();	
					   CostItem newCostItem=new CostItem(userId,descriptionString, category, costValue, date);
					   CostItemDAO.getInstance().addCostItem(newCostItem);
					   hasDeviation = false;
					   
					   if(hasSettings)
					   {
						   UserSettings settings = UserSettingsDAO.getInstance().getUserSettings(userId);
						   if(settings!=null)
						   {
							   Date fromDate=settings.getStartDate();
							   Date toDate=settings.getEndDate();
							   double budget=settings.getBudget();
							   hasDeviation=CostItemDAO.getInstance().checkIfHaveDeviation(userId, fromDate, toDate, budget);
							   
							   if(hasDeviation)
							   {
								   request.setAttribute("deviationMessage", "You Have Deviation In Your Budget !");  
							   }
						   }
					   }
		              
					   request.setAttribute("hasFilterDate", String.valueOf(hasFilterDate));
					   
						if(hasFilterDate==true)
						 {
						 	 request.setAttribute("fromDate", fromDateFilterString);
						 	 request.setAttribute("toDate", toDateFilterString);
						 	 request.setAttribute("category", categoryFilterString);						 	 
						 }
												
					   dispatcher = getServletContext().getRequestDispatcher("/expenseslist.jsp");
					   dispatcher.forward(request, response);	
					}
				  }
				  			  
			  catch (FinancialManagementException ex)
			  {
				  ex.printStackTrace();
				  dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				  dispatcher.forward(request, response);
			  }
			  
			  break;
			  
		  //Register to the application 	  
		  case "/register":
			  
			  try
			  {
				  String userNameString = (String) request.getParameter("userName");
				  String firstNameString = (String) request.getParameter("firstName");
				  String lastNameString = (String) request.getParameter("lastName");
				  String eMailString = (String) request.getParameter("eMail");
				  String passwordString = (String) request.getParameter("password");
				  String confirmPasswordString = (String) request.getParameter("confirmPassword");
				  
				  if (userNameString.equals("")  || firstNameString.equals("") || lastNameString.equals("") || 
						  eMailString.equals("") || passwordString.equals("") || confirmPasswordString.equals(""))
					{
					   request.setAttribute("registerMessage", "Please complete all the Fields.");
					   dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
					   dispatcher.forward(request, response);
					}
				  else
				  {
				    if(!passwordString.equals(confirmPasswordString))
				    {
					    request.setAttribute("registerMessage", "Password and Confirm Password are not match.");
						dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
						dispatcher.forward(request, response);
				    }
				    else
				      {
				       if (!UserDAO.getInstance().isValidEmailAddress(eMailString))
				        {
					      request.setAttribute("registerMessage", "E-Mail address is not valid.");
						  dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
						  dispatcher.forward(request, response);					
				        }
				       else
				       {
				         if(UserDAO.getInstance().isUserNameExists(userNameString))
				         {
					       request.setAttribute("registerMessage", "User name already exists.Please choose another one.");
						   dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
						   dispatcher.forward(request, response);	
				         }
				         else
				         {
					      User newUser = new User (userNameString, firstNameString, lastNameString, eMailString, passwordString);
					      UserDAO.getInstance().addUser(newUser);
					  
					      User currentUser = UserDAO.getInstance().getUser(userNameString, passwordString);
					  
					      if(currentUser!=null)
					       {
						    LoggedInUser.getInstance().setCurrentUser(currentUser);
					       }
					      
					      dispatcher = getServletContext().getRequestDispatcher("/homepage.jsp");
					      dispatcher.forward(request, response);
				        }
				     }
				   }
				}
			  }
			  catch (FinancialManagementException ex)
			  {
				  ex.printStackTrace();
				  dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				  dispatcher.forward(request, response);
			  }
			  
			  
			  break;
			  
		  //Login to the application	  
		  case "/login":
			  
			  try
			  {
				  String userNameString = (String) request.getParameter("userName");
				  String passwordString = (String) request.getParameter("password");
				  
				  if (userNameString.equals("") || passwordString.equals(""))
					{
					   request.setAttribute("loginMessage", "Please complete all the Fields");
					   dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
					   dispatcher.forward(request, response);
					}
				  else
				  {
				    User currentUser = UserDAO.getInstance().getUser(userNameString, passwordString);
				  
				    if(currentUser!=null)
				    {
				   	    LoggedInUser.getInstance().setCurrentUser(currentUser);
				   	    UserSettings settings = UserSettingsDAO.getInstance().getUserSettings(currentUser.getId());
				   	    
				   	    if (settings!=null)
				   	    {
				   	    	hasSettings = true;

							Date todayDate = new Date();
							Date settingsEndDate = settings.getEndDate();


					        if (todayDate.after(settingsEndDate))
				   	         {
					        	//update settings that have expired dates to updated dates
					        	
					        	UserSettingsPeriod settingsPeriod = settings.getPeriod();
					        					        	
					        	 if(settingsPeriod.equals(UserSettingsPeriod.Day) )
					             {
					        		 settingsEndDate.setTime(todayDate.getTime());
					             }
					            else if(settingsPeriod.equals(UserSettingsPeriod.Week))
					             {
					            	settingsEndDate.setTime(todayDate.getTime() + 7 * 24 * 60 * 60 * 1000);	
					             }
					            else
					             {
					            	settingsEndDate.setTime(todayDate.getTime() + 30 * 24 * 60 * 60 * 1000);	
					             }
					        	 
					        	  UserSettings userSettings = new  UserSettings(currentUser.getId(),settingsPeriod,settings.getBudget(),todayDate,settingsEndDate);					              
					              UserSettingsDAO.getInstance().addOrUpdateUserSettings(userSettings);
				   	         }
				   	    }
					    dispatcher = getServletContext().getRequestDispatcher("/homepage.jsp");
					    dispatcher.forward(request, response);
				    }
				    else
				    {
					    request.setAttribute("loginMessage", "User name or password are incorrect. Please try again.");  
				        dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				        dispatcher.forward(request, response);
				    }
				  }
			}
			   
			  catch (FinancialManagementException ex)
			  {
				  ex.printStackTrace();
				  dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				  dispatcher.forward(request, response);
			  }
				
				break;
				
	//Define user settings (period and budget)			
	case "/settings":
		  
        try
        { 
          double budgetValue=0;
        
          String settingsPeriodString = (String) request.getParameter("settingsPeriod");			  
          UserSettingsPeriod settingsPeriod = UserSettingsPeriod.valueOf(settingsPeriodString); 
        
        
        String budgetString=(String)request.getParameter("budget");

        if (budgetString.equals(""))
        {
        	  request.setAttribute("settingsMessage", "Please complete all the Fields.");        	  
			  dispatcher = getServletContext().getRequestDispatcher("/settings.jsp");
			  dispatcher.forward(request, response);
        }
        else if(!CostItemDAO.getInstance().isNumber(budgetString))
		{
			  request.setAttribute("settingsMessage", "The budget you entered is not valid."
			  		+ " Enter only numbers.");			  
			  dispatcher = getServletContext().getRequestDispatcher("/settings.jsp");
			  dispatcher.forward(request, response);	
		}
	   else
		{
			 budgetValue=Double.parseDouble(budgetString);
		        
             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             String startTimeString = formatter.format(Calendar.getInstance().getTime());
             Date startTime=new Date();
        
            try 
		     {
        	   startTime = formatter.parse(startTimeString);
			 } 
			
			catch (ParseException e) 
			 {							
				e.printStackTrace();
			 }
        
            Date endTime = new Date();
        
           if(settingsPeriod.equals(UserSettingsPeriod.Day) )
            {
        	  endTime.setTime(startTime.getTime());
            }
           else if(settingsPeriod.equals(UserSettingsPeriod.Week))
            {
        	endTime.setTime(startTime.getTime() + 7 * 24 * 60 * 60 * 1000);	
            }
           else
            {
        	endTime.setTime(startTime.getTime() + 30 * 24 * 60 * 60 * 1000);	
           }
      
           long userId = LoggedInUser.getInstance().getCurrentUser().getId();
           UserSettings userSettings = new  UserSettings(userId,settingsPeriod,budgetValue,startTime,endTime);	
           
           
           UserSettingsDAO.getInstance().addOrUpdateUserSettings(userSettings);
           hasSettings = true;  
           
           dispatcher = getServletContext().getRequestDispatcher("/homepage.jsp");
		   dispatcher.forward(request, response);	
          }
        }
        catch(FinancialManagementException ex)
        {
        	 ex.printStackTrace();
			 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
			 dispatcher.forward(request, response);
        }
                   
	  break;
	  
	//Initialize and dispatch attributes to expenses list page				
	case "/expenseslistpage":		
		try
		{
			  request.setAttribute("hasFilterDate", String.valueOf(hasFilterDate));
			  
			  if (hasFilterDate)
			  {
				  request.setAttribute("fromDate", fromDateFilterString);
				  request.setAttribute("toDate", toDateFilterString);
				  request.setAttribute("category", categoryFilterString);		  
			  }
			  
			  dispatcher = getServletContext().getRequestDispatcher("/expenseslist.jsp");
			  dispatcher.forward(request, response);
		}
		catch (ServletException ex) 
		{							
			 ex.printStackTrace();
			 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
			 dispatcher.forward(request, response);
		}
		
		break;
		
	//Load user's data to a draw graph	
	case "/graphicaldisplaypage":
		try
		{
			long userId = LoggedInUser.getInstance().getCurrentUser().getId();
			double sumsByCategory[]=CostItemDAO.getInstance().getSumOfEachCategory(userId);
			request.setAttribute("sumsByCategory", sumsByCategory);
			int amountOfItemsByCategory[]=CostItemDAO.getInstance().getAmountOfItemsForEachCategory(userId);
			request.setAttribute("amountOfItemsByCategory", amountOfItemsByCategory);
			dispatcher = getServletContext().getRequestDispatcher("/graphicaldisplay.jsp");
			dispatcher.forward(request, response);
		}
		catch (ServletException|FinancialManagementException ex) 
		{							
			 ex.printStackTrace();
			 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
			 dispatcher.forward(request, response);
		}
		
		break;
		
       //Dispatch settings attribute to settings page
		case "/settingspage":
			try
			{	
			     String settingsIdString = Long.toString(LoggedInUser.getInstance().getCurrentUser().getId());
			     request.setAttribute("settingsId", settingsIdString);
				 
				 dispatcher = getServletContext().getRequestDispatcher("/settings.jsp");
				 dispatcher.forward(request, response);
			}
			
			catch (ServletException ex) 
			{							
				 ex.printStackTrace();
				 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				 dispatcher.forward(request, response);
			}
			
			break;
			
		//Dispatch to new item page	
		case "/newitempage":
			try
			{
				 dispatcher = getServletContext().getRequestDispatcher("/newitem.jsp");
				 dispatcher.forward(request, response);
			}
			catch (ServletException ex) 
			{							
				 ex.printStackTrace();
				 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				 dispatcher.forward(request, response);
			}
			
			break;
			
	   //Dispatch to register page		
		case "/registerpage":
			try
			{
				 dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
				 dispatcher.forward(request, response);
			}
			catch (ServletException ex) 
			{							
				 ex.printStackTrace();
				 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				 dispatcher.forward(request, response);
			}
			
			break;
			
		//Dispatch to login page		
		case "/loginpage":
			try
			{
				 dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				 dispatcher.forward(request, response);
			}
			catch (ServletException ex) 
			{							
				 ex.printStackTrace();
				 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				 dispatcher.forward(request, response);
			}
			
			break;
		
		//Dispatch to index page			
		case "/indexpage":
			try
			{
 				  dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
				  dispatcher.forward(request, response);
			}
			catch (ServletException ex) 
			{							
				 ex.printStackTrace();
				 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				 dispatcher.forward(request, response);
			}
			break;
		
		//Dispatch to home page	
		case "/homepage":
			try
			{
 				  dispatcher = getServletContext().getRequestDispatcher("/homepage.jsp");
				  dispatcher.forward(request, response);
			}
			catch (ServletException ex) 
			{							
				 ex.printStackTrace();
				 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				 dispatcher.forward(request, response);
			}
			
			break;	
		
		//Delete cost item from cost items list	
		case "/delete":			
			try
			{
				String deleteItemIdString = (String) request.getParameter("deletevalue");
				long deleteItemId = Long.parseLong(deleteItemIdString);
				
				CostItemDAO.getInstance().deleteCostItem(deleteItemId);
				
				request.setAttribute("hasFilterDate", String.valueOf(hasFilterDate));
				
				if(hasFilterDate==true)
				  {
				 	 request.setAttribute("fromDate", fromDateFilterString);
				 	 request.setAttribute("toDate", toDateFilterString);
				 	 request.setAttribute("category", categoryFilterString);				 	 
				  }
							
				dispatcher = getServletContext().getRequestDispatcher("/expenseslist.jsp");
				dispatcher.forward(request, response);
			}
			catch (FinancialManagementException ex) 
			{							
				 ex.printStackTrace();
				 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				 dispatcher.forward(request, response);
			}
			
			
			break;
		
		//Initialize and dispatch selected item	id
		case "/currentitem":
              try
              {
            	  String updateItemIdString = (String) request.getParameter("editvalue");
  				  long updateItemId = Long.parseLong(updateItemIdString);
  				  editValue = updateItemId;
  				  request.setAttribute("updateItemId", updateItemIdString);  				   				  
  				  
            	  dispatcher = getServletContext().getRequestDispatcher("/updateitem.jsp");
				  dispatcher.forward(request, response);	
            	  
    		  }
 			  
    		  catch (ServletException ex)
    		  {
    			 ex.printStackTrace();
 				 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
 				 dispatcher.forward(request, response);
    		  }
    		  
    		  break;
        
    	//Update selected item 
		case "/updateitem":
			try
			{
			  String costString = (String) request.getParameter("cost");
			  String descriptionString = (String) request.getParameter("description");
			  String categoryString = (String) request.getParameter("category");			  
			  String dateString = (String) request.getParameter("date");
			  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			  double costValue = 0;
			  					
		      if (descriptionString.equals("") || categoryString.equals("") || dateString.equals("") || costString.equals(""))
				{
		    	   request.setAttribute("updateitemMessage", "Please complete all the Fields.");
				   request.setAttribute("updateItemId", String.valueOf(editValue));  
				   dispatcher = getServletContext().getRequestDispatcher("/updateitem.jsp");
				   dispatcher.forward(request, response);
				}
			  else if (!CostItemDAO.getInstance().isNumber(costString))
			  {
				  request.setAttribute("updateitemMessage", "The cost you entered is not valid."
				  		+ " Enter only numbers.");
				  request.setAttribute("updateItemId", String.valueOf(editValue));
				  dispatcher = getServletContext().getRequestDispatcher("/updateitem.jsp");
				  dispatcher.forward(request, response);					  
			  }
			  else
			  {		  
				  try
				  {
				    costValue = Double.parseDouble(costString);
				  }
				  catch(NumberFormatException ex)
				  {
				    ex.printStackTrace();
				  }
				  			
				  CostItemCategory category = CostItemCategory.valueOf(categoryString); 
				  
				  Date date = null;
					  
			 	 try 
				   {
			 		 date = formatter.parse(dateString);
				   } 
						
				 catch (ParseException e) 
				   {							
					e.printStackTrace();
				   }
										 
				   long userId = LoggedInUser.getInstance().getCurrentUser().getId();
				   CostItem updatedCostItem=new CostItem(userId,descriptionString, category, costValue, date);
	  			   
	  			   updatedCostItem.setId(editValue);
				   CostItemDAO.getInstance().updateCostItem(updatedCostItem);
				   
				   hasDeviation = false;
				   
				   if(hasSettings)
				   {
					   UserSettings settings = UserSettingsDAO.getInstance().getUserSettings(userId);
					   if(settings!=null)
					   {
						   Date fromDate=settings.getStartDate();
						   Date toDate=settings.getEndDate();
						   double budget=settings.getBudget();
						   hasDeviation=CostItemDAO.getInstance().checkIfHaveDeviation(userId, fromDate, toDate, budget);
						   
						   if(hasDeviation)
						   {
							   request.setAttribute("deviationMessage", "You Have Deviation In Your Budget !");  
						   }
					   }
				   }
	              
				   request.setAttribute("hasFilterDate", String.valueOf(hasFilterDate));
				   
					if(hasFilterDate==true)
					 {
					 	 request.setAttribute("fromDate", fromDateFilterString);
					 	 request.setAttribute("toDate", toDateFilterString);
					 	 request.setAttribute("category", categoryFilterString);						 	 
					 }
											
				   dispatcher = getServletContext().getRequestDispatcher("/expenseslist.jsp");
				   dispatcher.forward(request, response);	
				}
			  }
		 			  
		  catch (FinancialManagementException ex)
		  {
			  ex.printStackTrace();
			  dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
			  dispatcher.forward(request, response);
		  }
		  
		  break;
		
		//Log out from the application  
		case "/logout":
			try
			{
				User logoutCurrentUser = null;
				hasSettings = false;
				hasFilterDate = false;
				
				LoggedInUser.getInstance().setCurrentUser(logoutCurrentUser);
				request.getSession().invalidate();
				dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
			catch (ServletException  ex) 
			{
				ex.printStackTrace();
				dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				dispatcher.forward(request, response);
			}
			
			break;
		
		//Check if dates and category are valid for filter
		case "/filterdates":
			try
			{
				  fromDateFilterString = (String) request.getParameter("fromdate");
				  toDateFilterString = (String) request.getParameter("todate");				  
				  categoryFilterString = (String) request.getParameter("category");
				  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				  Date fromDate = new Date();
				  Date toDate = new Date();
				  
				  if (!fromDateFilterString.equals("") && !toDateFilterString.equals(""))
				  {
				    try
		      	    {
		      	     fromDate = formatter.parse(fromDateFilterString);
		      	     toDate = formatter.parse(toDateFilterString);			      				      	
		      	    }					
				    catch (ParseException e) 
				    {							
				     e.printStackTrace();
				    }
				  }
				  
				  if (categoryFilterString.equals("") || fromDateFilterString.equals("") || toDateFilterString.equals(""))
					{
					   request.setAttribute("filterMessage", "Please Insert All The Fields For Filter.");
					}
	      	
			      else if (fromDate.after(toDate))
			         {
			      		 request.setAttribute("filterMessage", "Start Date should be smaller than End Date."
			      		 		+ "Please try again.");			      	    
			      	 }
			      				      	
			      else
			      	{
				 	 hasFilterDate=true; 
				 	 request.setAttribute("fromDate", fromDateFilterString);
				 	 request.setAttribute("toDate", toDateFilterString);
				 	 request.setAttribute("category", categoryFilterString);
			      	}
				  
				     request.setAttribute("hasFilterDate", String.valueOf(hasFilterDate));
				     
					 dispatcher = getServletContext().getRequestDispatcher("/expenseslist.jsp");
					 dispatcher.forward(request, response);
				 	
				 	 
			}
			catch (ServletException ex) 
			{
				 ex.printStackTrace();
				 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				 dispatcher.forward(request, response);
			}
			
			break;
		
		//Reset filter dates and category	
		case "/resetfilterdates":
			try
			{
				hasFilterDate=false;
				request.setAttribute("hasFilterDate", String.valueOf(hasFilterDate));
				dispatcher = getServletContext().getRequestDispatcher("/expenseslist.jsp");
				dispatcher.forward(request, response);
			}
			catch (ServletException ex) 
			{
				 ex.printStackTrace();
				 dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");
				 dispatcher.forward(request, response);
			}
			
			break;	
			
		default:
		  
		}
			
	}

}
