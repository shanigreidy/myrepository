package il.ac.hit.model;

import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class UserDAO implements IUserDAO
	{
		private static UserDAO instance = null;
	    private AnnotationConfiguration AC = new AnnotationConfiguration();
	    private SessionFactory factory = AC.configure().buildSessionFactory();

	    private UserDAO()
	    {
	    }

	    /**
	     * Gets the single instance of UserDAO.
	     *
	     * @return single instance of UserDAO
	     */
	    public static UserDAO getInstance()
	    {
	        if (instance == null)
	        {
	            instance = new UserDAO();
	        }
	        return instance;
	    }

	    /**
	     * Adds a user to the system.
	     *
	     * @param user the user
	     * @return the ID of the user that was added to the system
	     * @throws FinancialManagementException the project exception
	     */
	    @Override
	    public long addUser(User user) throws FinancialManagementException
	    {
	        Long userId = null;
	        Transaction ta = null;
	        Session session = null;
	        try
	        {
	            session = factory.openSession();
	            ta = session.beginTransaction();
	            userId = (Long) session.save(user);
	            ta.commit();
	        }
	        catch (HibernateException ex)
	        {
	            if (ta != null)
	                ta.rollback();
	            ex.printStackTrace();
		        throw new FinancialManagementException("Failed to add new user", ex);
	        }
	        finally
	        {
	            session.close();
	        }
	        return userId;
	    }

	    /**
	     * Deletes a user from the system by ID.
	     *
	     * @param userId the id
	     * @return true if the user was deleted successfully, false otherwise
	     * @throws FinancialManagementException the project exception
	     */
	    @Override
	    public boolean deleteUser(long userId) throws FinancialManagementException
	    {
	        boolean success = false;
	        Transaction ta = null;
	        Session session = null;
	        try
	        {
	            session = factory.openSession();
	            ta = session.beginTransaction();

	            User tempUser = (User) session.load(User.class, userId);
	            session.delete(tempUser);
	            ta.commit();
	        }
	        catch (HibernateException ex)
	        {
	            if (ta != null)
	                ta.rollback();
	            ex.printStackTrace();
	            throw new FinancialManagementException("Failed deleting user", ex);
	        }
	        finally
	        {
	            session.close();
	            success = true;
	        }
	        return success;
	    }

	    /**
	     * Update details of exiting user.
	     *
	     * @param updatedUser the updated user
	     * @return true if the user details were updated successfully, false otherwise
	     * @throws FinancialManagementException the project exception
	     */
	    @Override
	    public boolean updateUser(User updatedUser) throws FinancialManagementException
	    {
	        boolean success = false;
	        Transaction ta = null;
	        Session session = null;
	        try
	        {
	            session = factory.openSession();
	            ta = session.beginTransaction();

	            session.update(updatedUser);
	            ta.commit();
	            success = true;
	        }
	        catch (HibernateException ex)
	        {
	            if (ta != null)
	                ta.rollback();
	            ex.printStackTrace();
	            throw new FinancialManagementException("Failed updating user", ex);
	        }
	        finally
	        {
	            session.close();
	        }
	        return success;
	    }

	    /**
	     * Gets the user.
	     *
	     * @param userId the id
	     * @return the user
	     * @throws FinancialManagementException the project exception
	     */
	    @Override
	    public User getUser(String userName, String password) throws FinancialManagementException
	    {
	        User user = null;
	        Session session = null;
	        List<User> usersList = null;

	        try
	        {
	            session = factory.openSession();
	            session.beginTransaction();
	            usersList = session.createQuery("FROM User WHERE userName=" +" '"+userName +"'" +" AND password="+" '"+password+"'").list();
	            if (usersList.size() == 1)
	            {
	                user = usersList.get(0);
	            }
	        }
	        catch (HibernateException ex)
	        {
	        	ex.printStackTrace();
	            throw new FinancialManagementException("Failed get user", ex);
	        }
	        finally
	        {
	            session.close();
	        }
	        return user;
	    }
	    /**
	     * 
	     * @param emailString a string to check
	     * @return true if the string represents a valid email address, false otherwise
	     * @throws FinancialManagementException the project exception
	     */
	    public boolean isValidEmailAddress(String emailString)
	    {	    	
	    	if (emailString.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	    			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
	    	{
	    		return true;
	    	}
	    	
	    	return false;
	    }
	    /**
	     * 
	     * @param userName string to check
	     * @return true if the user name exists, false otherwise
	     */
	    public boolean isUserNameExists(String userName) throws FinancialManagementException
	    {
	        Session session = null;
	        List<User> usersList = null;
	        boolean isUserExists=true;

	        try
	        {
	            session = factory.openSession();
	            session.beginTransaction();
	            usersList = session.createQuery("FROM User WHERE userName=" +" '" + userName + "'").list();
	            if (usersList.size() == 0)
	            {
	            	isUserExists = false;
	            }
	        }
	        catch (HibernateException ex)
	        {
	        	ex.printStackTrace();
	            throw new FinancialManagementException("Failed get user", ex);
	        }
	        finally
	        {
	            session.close();
	        }
	        
	        return isUserExists;
	    }
	}