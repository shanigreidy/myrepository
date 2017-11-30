package il.ac.hit.model;

public interface IUserSettingsDAO 
{
	/**
	 * add new user settings or update user settings(if user settings exists )
	 * @param userSettings
	 * @return user settings ID 
	 * @throws FinancialManagementException  the project exception
	 */
	public long addOrUpdateUserSettings(UserSettings userSettings) throws FinancialManagementException;
	/**
	 * Delete user settings
	 * 
	 * @param userSettingsId
	 * @return true if deletion has been succeed, false otherwise
	 * @throws FinancialManagementException the project exception
	 */
	public boolean deleteUserSettings (long userSettingsId) throws FinancialManagementException;
	/**
	 * Update user settings
	 * 
	 * @param updatedUserSettings
	 * @return true if updating has been succeed, false otherwise
	 * @throws FinancialManagementException the project exception
	 */
	public boolean updateUserSettings(UserSettings updatedUserSettings) throws FinancialManagementException;
	/**
	 * Get User settings
	 * 
	 * @param userSettingsId
	 * @return the user settings object
	 * @throws FinancialManagementException the project exception
	 */
	public UserSettings getUserSettings(long userSettingsId) throws FinancialManagementException;
	/**
	 * check if user settings exists
	 * 
	 * @param userId
	 * @return true if user Id Exists,false otherwise
	 * @throws FinancialManagementException the project exception
	 */
	 public boolean isUserSettingsExists(long userId) throws FinancialManagementException;

}
