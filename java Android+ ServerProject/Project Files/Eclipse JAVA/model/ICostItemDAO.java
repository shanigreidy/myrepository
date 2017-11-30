package il.ac.hit.model;

import java.util.Date;
import java.util.List;

public interface ICostItemDAO
	{
		/**
	     * Adds a cost item to database.
	     *
	     * @param costItem the cost item
	     * @return the ID of the cost item that was added to the system
	     * @throws FinancialManagementException the project exception
	     */
	    public long addCostItem(CostItem costItem) throws FinancialManagementException;

	    /**
	     * Deletes a cost item from database by ID.
	     *
	     * @param costItemId the cost item id
	     * @return true if the cost item was deleted successfully, false otherwise
	     * @throws FinancialManagementException the project exception
	     */
	    public boolean deleteCostItem(long costItemId) throws FinancialManagementException;

	    /**
	     * Update details of exiting cost item.
	     *
	     * @param costItem the updated cost item
	     * @return true if the cost item details were updated successfully, false otherwise
	     * @throws FinancialManagementException the project exception
	     */
	    public boolean updateCostItem(CostItem costItem) throws FinancialManagementException;

		/**
		 * Gets the complete cost items list by user Id.
		 *
		 * @param userId the user's ID
		 * @return a list of cost items
		 * @throws FinancialManagementException the project exception
		 */
		public List<CostItem> getCostItemList(long userId) throws FinancialManagementException;
		
	    /**
	     * Gets the cost item.
	     *
	     * @param id the id of the cost item
	     * @return the cost item
	     * @throws FinancialManagementException the cost item exception
	     */
	    public CostItem getCostItem(long id) throws FinancialManagementException;
	    /**
	     * Check if some string is a number.
	     *
	     * @param String that represents the number to check
	     * @return true if the string represents a number, false otherwise.
	     * @throws FinancialManagementException the project exception
	     */
	    public boolean isNumber (String number);
	    /**
		 * Gets a cost items list by user Id, dates range and category.
		 *
		 * @param userId the user's ID, fromDate the start date ,toDate the end date and category the cost item category.
		 * @return a list of cost items
		 * @throws FinancialManagementException the project exception
		 */
	    public List<CostItem> getCostItemListByDates(long userId, Date fromDate,Date toDate,CostItemCategory category ) throws FinancialManagementException;
	    /**
		 * Gets a cost items list by user Id, dates range and category.
		 *
		 * @param userId the user's ID,fromDate string that represents the start date ,toDate string that represents the end date and category string that represents the cost item category.
		 * @return a list of cost items
		 * @throws FinancialManagementException the project exception
		 */
	    public List<CostItem> getCostItemListByDates(long userId, String fromDate,String toDate,String category ) throws FinancialManagementException;
	    /**
	     * Check if user have deviation according to his budget and period of time .
	     *
	     * @param userId the user's ID, fromDate the start date ,toDate the end date and budget the user selected budget.
	     * @return true if user have deviation , false otherwise.
	     * @throws FinancialManagementException the project exception
	     */
	    public boolean checkIfHaveDeviation(long userId, Date fromDate,Date toDate,double budget ) throws FinancialManagementException;
	    /**
		 * Gets an array from type double, each cell represents sum of costs of all items from each category .
		 *
		 * @param userId the user's ID
		 * @return array from type double
		 * @throws FinancialManagementException the project exception
		 */
	    public double [] getSumOfEachCategory(long userId) throws FinancialManagementException;
	    /**
		 * Gets an array from type integer, each cell represents amount of all items from each category .
		 *
		 * @param userId the user's ID
		 * @return array from type integer
		 * @throws FinancialManagementException the project exception
		 */
	    public int [] getAmountOfItemsForEachCategory(long userId) throws FinancialManagementException;
	}
