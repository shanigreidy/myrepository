package il.ac.hit.model;

public interface IUserDAO
	{
		/**
	     * Adds a user to the system.
	     *
	     * @param user the user
	     * @return the ID of the user that was added to the system
	     * @throws FinancialManagementException the project exception
	     */
	    public long addUser(User user) throws FinancialManagementException;

	    /**
	     * Deletes a user from the system by ID.
	     *
	     * @param id the id
	     * @return true if the user was deleted successfully, false otherwise
	     * @throws FinancialManagementException the project exception
	     */
	    public boolean deleteUser(long id) throws FinancialManagementException;

	    /**
	     * Update details of exiting user.
	     *
	     * @param updatedUser the updated user
	     * @return true if the user details were updated successfully, false otherwise
	     * @throws FinancialManagementException the project exception
	     */
	    public boolean updateUser(User updatedUser) throws FinancialManagementException;

	    /**
	     * Gets the user.
	     *
	     * @param id the id
	     * @return the user
	     * @throws FinancialManagementException the project exception
	     */
	    public User getUser(String userName, String password) throws FinancialManagementException;
	    /**
	     * 
	     * @param emailString a string to check
	     * @return true if the string represents a valid email address, false otherwise
	     * @throws FinancialManagementException the project exception
	     */
	    public boolean isValidEmailAddress(String emailString);
	    /**
	     * 
	     * @param userName string to check
	     * @return true if the user name exists, false otherwise
	     */
	    public boolean isUserNameExists(String userName) throws FinancialManagementException;
	}
