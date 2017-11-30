package il.ac.hit.model;

import java.io.Serializable;

public class User implements Serializable
	{
		private long id;
	    private String userName;
	    private String firstName;
	    private String lastName;
	    private String eMail;
	    private String password;

	    /**
	     * Instantiates a new user using default values.
	     */
	    public User()
	    {
	        super();
	        this.setUserName("Default");
	        this.setFirstName("FirstName");
	        this.setLastName("LastName");
	        this.seteMail("no@mail-address.com");
	        this.setPassword("admin");
	    }

	    /**
	     * Instantiates a new user using values given by the user.
	     *
	     * @param userName the user name
	     * @param firstName the user's first name
	     * @param lastName the user's last name
	     * @param eMail the user's e-mail address
	     * @param password the user's password
	     */
	    public User(String userName, String firstName, String lastName, String eMail, String password)
	    {
	        super();
	        this.setUserName(userName);
	        this.setFirstName(firstName);
	        this.setLastName(lastName);
	        this.seteMail(eMail);
	        this.setPassword(password);
	    }

	    /**
	     * Gets the id of the user.
	     *
	     * @return the id of the user
	     */
	    public long getId()
	    {
	        return id;
	    }

	    /**
	     * Sets the id of the user.
	     *
	     * @param id the new id of the user
	     */
	    public void setId(long id)
	    {
	        this.id = id;
	    }

	    /**
	     * Gets the user name.
	     *
	     * @return the user name
	     */
	    public String getUserName()
	    {
	        return userName;
	    }

	    /**
	     * Sets the user name.
	     *
	     * @param userName the new user name
	     */
	    public void setUserName(String userName)
	    {
	        this.userName = userName;
	    }

	    /**
	     * Gets the user's first name.
	     *
	     * @return the user's first name
	     */
	    public String getFirstName()
	    {
	        return firstName;
	    }

	    /**
	     * Sets the user's first name.
	     *
	     * @param firstName the user's new first name
	     */
	    public void setFirstName(String firstName)
	    {
	        this.firstName = firstName;
	    }

	    /**
	     * Gets the user's last name.
	     *
	     * @return the user's last name
	     */
	    public String getLastName()
	    {
	        return lastName;
	    }

	    /**
	     * Sets the user's last name.
	     *
	     * @param lastName the user's new last name
	     */
	    public void setLastName(String lastName)
	    {
	        this.lastName = lastName;
	    }

	    /**
	     * Gets the user's password.
	     *
	     * @return the user's password
	     */
	    public String getPassword()
	    {
	        return password;
	    }

	    /**
	     * Sets the user's password.
	     *
	     * @param password the user's new password
	     */
	    public void setPassword(String password)
	    {
	        this.password = password;
	    }

	    /**
	     * Gets the user's e-mail address.
	     *
	     * @return the user's e-mail address
	     */
	    public String geteMail()
	    {
	        return eMail;
	    }

	    /**
	     * Sets the user's e-mail address.
	     *
	     * @param eMail the new user's e-mail address
	     */
	    public void seteMail(String eMail)
	    {
	        this.eMail = eMail;
	    }

	    /**
	     * Gets the user's hash code.
	     *
	     * @return the user's hash code
	     */
	    @Override
	    public int hashCode()
	    {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
	        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	        result = prime * result + (int) (id ^ (id >>> 32));
	        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
	        result = prime * result + ((password == null) ? 0 : password.hashCode());
	        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
	        return result;
	    }
	    /**
	     * Check if user equal to other user.
	     *
	     * @param Object obj that represent the other user.
	     * @return true if the users are equal, false otherwise.
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
	        User other = (User) obj;
	        if (eMail == null)
	        {
	            if (other.eMail != null)
	                return false;
	        }
	        else if (!eMail.equals(other.eMail))
	            return false;
	        if (firstName == null)
	        {
	            if (other.firstName != null)
	                return false;
	        }
	        else if (!firstName.equals(other.firstName))
	            return false;
	        if (id != other.id)
	            return false;
	        if (lastName == null)
	        {
	            if (other.lastName != null)
	                return false;
	        }
	        else if (!lastName.equals(other.lastName))
	            return false;
	        if (password == null)
	        {
	            if (other.password != null)
	                return false;
	        }
	        else if (!password.equals(other.password))
	            return false;
	        if (userName == null)
	        {
	            if (other.userName != null)
	                return false;
	        }
	        else if (!userName.equals(other.userName))
	            return false;
	        return true;
	    }
	    /**
	     * Gets the string that represent the user
	     *
	     * @return string that represent the user.
	     */
	    @Override
	    public String toString()
	    {
	        return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
	                + ", eMail=" + eMail + ", password=" + password + "]";
	    }
	}
