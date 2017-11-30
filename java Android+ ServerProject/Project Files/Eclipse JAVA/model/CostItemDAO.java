package il.ac.hit.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.hibernate.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class CostItemDAO implements ICostItemDAO
	{
		private static CostItemDAO instance = null;
	    private AnnotationConfiguration AC = new AnnotationConfiguration();
	    private SessionFactory factory = AC.configure().buildSessionFactory();

	    private CostItemDAO()
	    {
	    }

	    /**
	     * Gets the single instance of CostItemDAO.
	     *
	     * @return single instance of CostItemDAO
	     */
	    public static CostItemDAO getInstance()
	    {
	        if (instance == null)
	        {
	            instance = new CostItemDAO();
	        }
	        return instance;
	    }

	    /**
	     * Adds a cost item to database.
	     *
	     * @param costItem the cost item
	     * @return the ID of the cost item that was added to the system
	     * @throws FinancialManagementException the project exception
	     */
	    @Override
	    public long addCostItem(CostItem costItem) throws FinancialManagementException
	    {
	        Long costItemId = null;
	        Transaction ta = null;
	        Session session = null;
	        try
	        {
	            session = factory.openSession();
	            ta = session.beginTransaction();
	            costItemId = (Long) session.save(costItem);
	            ta.commit();
	        }
	        catch (HibernateException ex)
	        {
	            if (ta != null)
	                ta.rollback();
	            ex.printStackTrace();
	    		throw new FinancialManagementException("Failed to add cost item",ex);
	        }
	        finally
	        {
	            session.close();
	        }
	        return costItemId;
	    }

	    /**
	     * Deletes a cost item from database by ID.
	     *
	     * @param costItemId the cost item id
	     * @return true if the cost item was deleted successfully, false otherwise
	     * @throws FinancialManagementException the project exception
	     */
	    @Override
	    public boolean deleteCostItem(long costItemId) throws FinancialManagementException
	    {
	        boolean success = false;
	        Transaction ta = null;
	        Session session = null;
	        try
	        {
	            session = factory.openSession();
	            ta = session.beginTransaction();

	            CostItem tempCostItem = (CostItem) session.load(CostItem.class,costItemId);
	            session.delete(tempCostItem);
	            ta.commit();
	        }
	        catch (HibernateException ex)
	        {
	            if (ta != null)
	                ta.rollback();
	            ex.printStackTrace();
	            throw new FinancialManagementException("Failed deleting cost item", ex);
	        }
	        finally
	        {
	            session.close();
	            success = true;
	        }
	        return success;
	    }
	    /**
	     * Update details of exiting cost item.
	     *
	     * @param costItem the updated cost item
	     * @return true if the cost item details were updated successfully, false otherwise
	     * @throws FinancialManagementException the project exception
	     */
	    @Override
	    public boolean updateCostItem(CostItem updatedCostItem) throws FinancialManagementException
	    {
	        boolean success = false;
	        Transaction ta = null;
	        Session session = null;
	        try
	        {
	            session = factory.openSession();
	            ta = session.beginTransaction();

	            session.update(updatedCostItem);
	            ta.commit();
	            success = true;
	        }
	        catch (HibernateException ex)
	        {
	            if (ta != null)
	                ta.rollback();
	            ex.printStackTrace();
	            throw new FinancialManagementException("Failed updating item", ex);
	        }
	        finally
	        {
	            session.close();
	        }
	        return success;
	    }
	    /**
		 * Gets the complete cost items list by user Id.
		 *
		 * @param userId the user's ID
		 * @return a list of cost items
		 * @throws FinancialManagementException the project exception
		 */
	    @Override
	    public List<CostItem> getCostItemList(long userId) throws FinancialManagementException
	    	{
	    		Session session = null;
	    		List<CostItem> itemsList = null;

	    		try
	    		{
	    			session = factory.openSession();
	    			session.beginTransaction();
	    			itemsList = session.createQuery("FROM CostItem WHERE userId = " + userId).list();
	    		}
	    		catch (HibernateException ex)
	    		{
		            ex.printStackTrace();
		            throw new FinancialManagementException("Failed to get cost items list in method getCostItemList", ex);
	    		}
	    		finally
	    		{
	    			session.close();
	    		}
	    		return itemsList;
	    	}
	    
	    /**
		 * Gets a cost items list by user Id, dates range and category.
		 *
		 * @param userId the user's ID,fromDate string that represents the start date ,toDate string that represents the end date and category string that represents the cost item category.
		 * @return a list of cost items
		 * @throws FinancialManagementException the project exception
		 */
	    @Override
	    public List<CostItem> getCostItemListByDates(long userId, String fromDate,String toDate,String category ) throws FinancialManagementException
    	{
	    	  List<CostItem> itemsList =null;
	          CostItemCategory categoryValue = CostItemCategory.valueOf(category); 
	          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			  Date fromDateValue = null;
			  Date toDateValue = null;
				  
		 	 try 
			   {
		 		fromDateValue = formatter.parse(fromDate);
		 		toDateValue = formatter.parse(toDate);
			   } 
					
			 catch (ParseException e) 
			   {							
				e.printStackTrace();
			   }
		 	 
		 	itemsList = getCostItemListByDates(userId,fromDateValue,toDateValue,categoryValue);
		 	
		 	return itemsList;
		 	 
    	}

	    /**
		 * Gets a cost items list by user Id, dates range and category.
		 *
		 * @param userId the user's ID, fromDate the start date ,toDate the end date and category the cost item category.
		 * @return a list of cost items
		 * @throws FinancialManagementException the project exception
		 */
	    @Override
	    public List<CostItem> getCostItemListByDates(long userId, Date fromDate,Date toDate,CostItemCategory category ) throws FinancialManagementException
    	{
    		Session session = null;
    		List<CostItem> itemsList = null;

    		try
    		{
    			session = factory.openSession();
    			session.beginTransaction();
    			
    			if(category == CostItemCategory.NoCategory)
    			{
    				Query query = session.createQuery("FROM CostItem WHERE userId = :userId  AND date >= :fromDate  AND date <= :toDate " );
    				query.setLong("userId", userId);
    				query.setTimestamp("fromDate", fromDate);
    				query.setTimestamp("toDate", toDate);
    				itemsList = query.list();
    			}
    			else
       			{
    				//itemsList = session.createQuery("FROM CostItem WHERE userId = " + userId +" AND date >= "+fromDate +" AND date =< " +toDate + " AND category = "+category ).list();
    				Query query = session.createQuery("FROM CostItem WHERE userId = :userId  AND date >= :fromDate  AND date <= :toDate And category = :category" );
    				query.setLong("userId", userId);
    				query.setTimestamp("fromDate", fromDate);
    				query.setTimestamp("toDate", toDate);
    				query.setParameter("category", category);
    				itemsList = query.list();
    				
    			}

    		}
    		catch (HibernateException ex)
    		{
    			ex.printStackTrace();
	            throw new FinancialManagementException("Failed to get cost items list in method getCostItemListByDates", ex);
    		}
    		finally
    		{
    			session.close();
    		}
    		return itemsList;
    	}
	    
	    /**
	     * Check if user have deviation according to his budget and period of time .
	     *
	     * @param userId the user's ID, fromDate the start date ,toDate the end date and budget the user selected budget.
	     * @return true if user have deviation , false otherwise.
	     * @throws FinancialManagementException the project exception
	     */
	    @Override
	    public boolean checkIfHaveDeviation(long userId, Date fromDate,Date toDate ,double budjet ) throws FinancialManagementException
    	{
	    	List<CostItem> itemsList = getCostItemListByDates(userId, fromDate,toDate,CostItemCategory.NoCategory );
	    	double sumOfCostItems = 0;
	    	
	    	if(itemsList!=null)
	    	{
	    		for(CostItem item : itemsList )
	    		{
	    			sumOfCostItems+=item.getCost();
	    		}
	    		
	    		if(sumOfCostItems > budjet)
	    			return true;
	    	}
	    	
	    	return false;
    	}
	    /**
	     * Gets the cost item.
	     *
	     * @param id the id of the cost item
	     * @return the cost item
	     * @throws FinancialManagementException the cost item exception
	     */
	    @Override
	    public CostItem getCostItem(long id) throws FinancialManagementException
	    {
	        CostItem costItem = null;
	        Session session = null;
	        List<CostItem> costItemsList = null;

	        try
	        {
	            session = factory.openSession();
	            session.beginTransaction();
	            costItemsList = session.createQuery("from CostItem where id=" + id).list();
	            if (costItemsList.size() == 1)
	            {
	                costItem = costItemsList.get(0);
	            }
	        }
	        catch (HibernateException ex)
	        {
	        	 ex.printStackTrace();
		         throw new FinancialManagementException("Failed to get cost item by ID", ex);
	        }
	        finally
	        {
	            session.close();
	        }
	        return costItem;
	    }
	    /**
	     * Check if some string is a number.
	     *
	     * @param String that represents the number to check
	     * @return true if the string represents a number, false otherwise.
	     * @throws FinancialManagementException the project exception
	     */
	    @Override
	     public boolean isNumber (String number)
	    {
	    	try
	    	{
				
	    		if((number).matches("[0-9]+\\.?[0-9]?[0-9]?"))
	    		{
	    		  return true;
	    		}
	    		
	    	 }
	    	catch(NumberFormatException e)
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    	return false; 
	    }
	    /**
	  		 * Gets an array from type double, each cell represents sum of costs of all items from each category .
	  		 *
	  		 * @param userId the user's ID
	  		 * @return array from type double
	  		 * @throws FinancialManagementException the project exception
	  		 */
	    @Override
	     public double [] getSumOfEachCategory(long userId) throws FinancialManagementException
	     {
	    	 double [] sumOfCategories = new double [8];
	    	 for(int i=0 ; i<8 ; i++)
	    	 {
	    		 sumOfCategories[i]=0; 
	    	 }

	    	 List<CostItem> itemsList = null;
	    	 
	    	 itemsList = getCostItemList(userId);
	    	 
	    	 for(CostItem item : itemsList )
	    		{
	    		 switch(item.getCategory())
	    		 {
	    		 case Food:
	    		 {
	    			sumOfCategories[0]+=item.getCost();
	    			break;
	    		 }
	    		 case Sport:
	    		 {
	    			sumOfCategories[1]+=item.getCost();
	    			break;
	    		 }
	    		 case Clothing:
	    		 {
	    			sumOfCategories[2]+=item.getCost();
	    			break;
	    		 }
	    		 case Entertainment:
	    		 {
	    			sumOfCategories[3]+=item.getCost();
	    			break;
	    		 }
	    		 case Restaurants:
	    		 {
	    			sumOfCategories[4]+=item.getCost();
	    			break;
	    		 }
	    		 case Fuel:
	    		 {
	    			sumOfCategories[5]+=item.getCost();
	    			break;
	    		 }
	    		 case Housewares:
	    		 {
	    			sumOfCategories[6]+=item.getCost();
	    			break;
	    		 }
	    		 case Other:
	    		 {
	    			sumOfCategories[7]+=item.getCost();
	    			break;
	    		 }
	    		
	    		 }
	    		}	    	 
	    	 
	    	 return sumOfCategories;
	     }
	    /**
		 * Gets an array from type integer, each cell represents amount of all items from each category .
		 *
		 * @param userId the user's ID
		 * @return array from type integer
		 * @throws FinancialManagementException the project exception
		 */
	    @Override
	     public int [] getAmountOfItemsForEachCategory(long userId) throws FinancialManagementException
	     {
	    	 int [] amountOfItems = new int [8];
	    	 for(int i=0 ; i<8 ; i++)
	    	 {
	    		 amountOfItems[i]=0; 
	    	 }
	    	 
	    	 List<CostItem> itemsList = null;
	    	 
	    	 itemsList = getCostItemList(userId);
	    	 
	    	 for(CostItem item : itemsList )
	    		{
	    		 switch(item.getCategory())
	    		 {
	    		 case Food:
	    		 {
	    			amountOfItems[0]++;
	    			break;
	    		 }
	    		 case Sport:
	    		 {
	    			amountOfItems[1]++;
	    			break;
	    		 }
	    		 case Clothing:
	    		 {
	    			amountOfItems[2]++;
	    			break;
	    		 }
	    		 case Entertainment:
	    		 {
	    			amountOfItems[3]++;
	    			break;
	    		 }
	    		 case Restaurants:
	    		 {
	    			amountOfItems[4]++;
	    			break;
	    		 }
	    		 case Fuel:
	    		 {
	    			amountOfItems[5]++;
	    			break;
	    		 }
	    		 case Housewares:
	    		 {
	    			amountOfItems[6]++;
	    			break;
	    		 }
	    		 case Other:
	    		 {
	    			amountOfItems[7]++;
	    			break;
	    		 }
	    		
	    		 }
	    		}
	    	 
	    	 return amountOfItems;
	     }
	}