package il.ac.hit.model;

public class LoggedInUser {
	
	private static LoggedInUser instance = null;
	private User currentUser ; 
	
	  private LoggedInUser()
	    {
	    }
	    /**
	     * Gets the single instance of LoggedInUser.
	     *
	     * @return single instance of LoggedInUser
	     */
	    public static LoggedInUser getInstance()
	    {
	        if (instance == null)
	        {
	            instance = new LoggedInUser();
	        }
	        return instance;
	    }
	    /**
	     * Gets the current logged in user
	     * @return current logged in user
	     */
	    public User getCurrentUser()
	    {
	    	return currentUser;
	    }
	    /**
	     * Set the current logged in user
	     * @param user that currently logged in
	     */
	    public void setCurrentUser(User user)
	    {
	    	currentUser=user;
	    }
	    

}
