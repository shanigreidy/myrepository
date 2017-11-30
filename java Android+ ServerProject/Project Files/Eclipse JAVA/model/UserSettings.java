package il.ac.hit.model;

import java.io.Serializable;
import java.util.Date;

public class UserSettings implements Serializable
{
	private long id;
	private long userId;
	private UserSettingsPeriod period;
	private double budget;
	private Date startDate;
	private Date endDate;
	
	  /**
     * Instantiates a new user settings using default values.
     */
	public UserSettings() 
	{
		super();
		this.setUserId(0);
		this.setPeriod(null);
		this.setBudget(0);
		this.setStartDate(null);
		this.setEndDate(null);
	}
	/**
	 * 
	 * @param userId the user ID
	 * @param period the settings period
	 * @param budget the selected budget
	 * @param startDate the start time of the period
	 * @param endDate the end time of the period
	 */
	public UserSettings( long userId, UserSettingsPeriod period, double budget, Date startDate, Date endDate) {
		super();
		this.setUserId(userId);
		this.setPeriod(period);
		this.setBudget(budget);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}
/**
 * Gets the user settings ID
 * @return the user settings ID
 */
	public long getId() {
		return id;
	}
	/**
	 * Sets the user settings ID
	 * @param id the user settings ID
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Gets the user ID
	 * @return the user ID
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * Sets the user ID
	 * @param id the user ID
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * Gets the user settings period
	 * @return the user settings period
	 */
	public UserSettingsPeriod getPeriod() {
		return period;
	}
	/**
	 * Sets the user settings period
	 * @param period the user settings period
	 */
	public void setPeriod(UserSettingsPeriod period) {
		this.period = period;
	}
	/**
	 * Gets the user settings budget 
	 * @return the user settings budget 
	 */
	public double getBudget() {
		return budget;
	}
	/**
	 * Sets the user settings budget 
	 * @param budget the user settings budget
	 */
	public void setBudget(double budget) {
		this.budget = budget;
	}
	/**
	 * Gets the user settings start date
	 * @return the user settings start date
	 * 
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Sets the user settings start date
	 * @param startDate the user settings start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Gets the user settings end date
	 * @return the user settings end date
	 * 
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * Sets the user settings end date
	 * @param endDate the user settings end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
    /**
     * Gets the user settings's hash code.
     *
     * @return the user settings's hash code
     */
	 @Override
	    public int hashCode()
	    {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + (int) (userId ^ (userId >>> 32));
	        result = prime * result + ((period == null) ? 0 : period.hashCode());
	        result = prime * result + (int) (id ^ (id >>> 32));
	        result = prime * result + (int)budget;
	        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
	        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
	        return result;
	    }
	 /**
	     * Check if user settings equal to other user settings.
	     *
	     * @param Object obj that represent the other user settings.
	     * @return true if the user settings are equal, false otherwise.
	     */
	@Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserSettings other = (UserSettings) obj;
      if (!(budget != other.budget))
            return false;
        if (id != other.id)
            return false;
        if(userId != other.getUserId())
        	return false;
        if (period == null)
        {
            if (other.period  != null)
                return false;
        }
        else if (!period .equals(other.period ))
            return false;
        if (startDate == null)
        {
            if (other.startDate != null)
                return false;
        }
        else if (!startDate.equals(other.startDate))
            return false;
        if (endDate == null)
        {
            if (other.endDate != null)
                return false;
        }
        else if (!endDate.equals(other.endDate))
            return false;
        return true;
    }
	/**
     * Gets the string that represent the user settings
     *
     * @return string that represent the user settings.
     */
	@Override
	public String toString() {
		return "UserSettings [id=" + id + ", userId=" + userId + ", period=" + period + ", budget=" + budget
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	

}
