/*package il.ac.hit.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;

public class Program
	{
		public static void main(String[] args)
		{
			
			
		User testUser1 = new User("test","number1", "lala", "test1@gmail.com","1900");
		User testUser2 = new User("test","number2", "lili", "test2@gmail.com", "0000");
		
		try
		{
			Date today = Calendar.getInstance().getTime();
			
			CostItemDAO itemDao = CostItemDAO.getInstance();
			UserDAO userDao = UserDAO.getInstance();			
			long id1 = userDao.addUser(testUser1);
			long id2 = userDao.addUser(testUser2);
			//CostItem costItem1 = new CostItem("testItem1", CostItemCategory.Clothing, 100, today);
			//long itemId1 = itemDao.addCostItem(costItem1);
			
		//	System.out.println("First user: " + userDao.getUser(id1));
		//	System.out.println("Second user: " + userDao.getUser(id2));
			
			//System.out.println("Item: " + itemDao.getCostItem(itemId1));
			//System.out.println("List of items for id " + id1 + " :\n" + itemDao.getCostItemList(itemId1));	
		}
		catch(FinancialManagementException ex)
		{
			System.out.println("EXCEPTION: " + ex.getMessage());
		}
	}
}*/