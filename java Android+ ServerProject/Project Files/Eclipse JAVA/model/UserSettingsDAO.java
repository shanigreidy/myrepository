package il.ac.hit.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class UserSettingsDAO implements IUserSettingsDAO
{
	private static UserSettingsDAO instance = null;
    private AnnotationConfiguration AC = new AnnotationConfiguration();
    private SessionFactory factory = AC.configure().buildSessionFactory();

    private UserSettingsDAO()
    {
    }

    /**
     * Gets the single instance of UserSettingsDAO.
     *
     * @return single instance of UserSettingsDAO
     */
    public static UserSettingsDAO getInstance()
    {
        if (instance == null)
        {
            instance = new UserSettingsDAO();
        }
        return instance;
    }
    /**
	 * add new user settings or update user settings(if user settings exists )
	 * @param userSettings
	 * @return user settings ID 
	 * @throws FinancialManagementException  the project exception
	 */
	@Override 
	public long addOrUpdateUserSettings(UserSettings userSettings) throws FinancialManagementException
	{
		Long userSettingsId = null;
        Transaction ta = null;
        Session session = null;
		List<UserSettings> settingsList = null;
		UserSettings settings=null;
		
        try
        {
            session = factory.openSession();
            ta = session.beginTransaction();
            
            settingsList = session.createQuery("FROM UserSettings WHERE userId = " + userSettings.getUserId()).list();
            if(settingsList.size()==0)
            {
            	userSettingsId = (Long) session.save(userSettings);
            	ta.commit();
            }
            else
            {
	            if (settingsList.size() == 1)
	            {
	            	settings = (UserSettings)settingsList.get(0);
		            userSettings.setId(settings.getId());
	            	updateUserSettings(userSettings);
	            	userSettingsId = settings.getId();
	            
	            }
            }
            
        }
        catch (HibernateException ex)
        {
            if (ta != null)
                ta.rollback();
            ex.printStackTrace();
	        throw new FinancialManagementException("Failed to add or update user settings", ex);
        }
        finally
        {
            session.close();
        }
        return userSettingsId;
	}
	
	/**
	 * Delete user settings
	 * 
	 * @param userSettingsId
	 * @return true if deletion has been succeed, false otherwise
	 * @throws FinancialManagementException the project exception
	 */
	@Override 
	public boolean deleteUserSettings (long userSettingsId) throws FinancialManagementException
	{
		 boolean success = false;
	        Transaction ta = null;
	        Session session = null;
	        try
	        {
	            session = factory.openSession();
	            ta = session.beginTransaction();

	            UserSettings tempUserSettings = (UserSettings) session.load(UserSettings.class,userSettingsId);
	            session.delete(tempUserSettings);
	            ta.commit();
	        }
	        catch (HibernateException ex)
	        {
	            if (ta != null)
	                ta.rollback();
	            ex.printStackTrace();
	            throw new FinancialManagementException("Failed deleting user settings", ex);
	        }
	        finally
	        {
	            session.close();
	            success = true;
	        }
	        return success;
	}
	/**
	 * Update user settings
	 * 
	 * @param updatedUserSettings
	 * @return true if updating has been succeed, false otherwise
	 * @throws FinancialManagementException the project exception
	 */
	@Override 
	public boolean updateUserSettings(UserSettings updatedUserSettings) throws FinancialManagementException
	{
		  boolean success = false;
	        Transaction ta = null;
	        Session session = null;
	        try
	        {
	            session = factory.openSession();
	            ta = session.beginTransaction();

	            session.update(updatedUserSettings);
	            ta.commit();
	            success = true;
	        }
	        catch (HibernateException ex)
	        {
	            if (ta != null)
	                ta.rollback();
	            ex.printStackTrace();
	            throw new FinancialManagementException("Failed updating user setting", ex);
	        }
	        finally
	        {
	            session.close();
	        }
	        return success;
	}
	
	/**
	 * Get User settings
	 * 
	 * @param userSettingsId
	 * @return the user settings object
	 * @throws FinancialManagementException the project exception
	 */
	@Override
	public UserSettings getUserSettings(long userId) throws FinancialManagementException
	{
		UserSettings userSettings = null;
        Session session = null;
        List<UserSettings> userSettingsList = null;

        try
        {
            session = factory.openSession();
            session.beginTransaction();
            userSettingsList = session.createQuery("from UserSettings where userId=" + userId).list();
            if (userSettingsList.size() == 1)
            {
            	 userSettings =  userSettingsList.get(0);
            }
        }
        catch (HibernateException ex)
        {
        	 ex.printStackTrace();
	         throw new FinancialManagementException("Failed to get user setting", ex);
        }
        finally
        {
            session.close();
        }
        return userSettings;
	  }
	/**
	 * check if user settings exists
	 * 
	 * @param userId
	 * @return true if user Id Exists,false otherwise
	 * @throws FinancialManagementException the project exception
	 */
	   @Override 
	   public boolean isUserSettingsExists(long userId) throws FinancialManagementException
	    {		   
	        Session session = null;
	        List<UserSettings> userSettingsList = null;
	        boolean isUserExists=true;

	        try
	        {
	            session = factory.openSession();
	            session.beginTransaction();
	            userSettingsList = session.createQuery("FROM UserSettings WHERE userid=" + userId ).list();
	            
	            if (userSettingsList.size() == 0)
	            {
	            	isUserExists = false;
	            }
	        }
	        catch (HibernateException ex)
	        {
	        	 ex.printStackTrace();
		         throw new FinancialManagementException("Failed to check if user setting exists", ex);
	        }
	        finally
	        {
	            session.close();
	        }
	        
	        return isUserExists;
	    }
}
