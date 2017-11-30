package il.ac.hit.model;

import java.io.Serializable;
import java.util.Date;

public class CostItem implements Serializable
	{
		private long id;
		private long userId;
	    private String description;
	    private double cost;
	    private CostItemCategory category;
	    private Date date;

	    /**
	     * Instantiates a new cost item using default values.
	     */
	    public CostItem()
	    {
	        super();
	        this.setId(0);
	        this.setUserId(0);
	        this.setDescription("Default");
	        this.setCategory(CostItemCategory.Other);
	        this.setCost(0);
	        this.setDate(null);
	    }

	    /**
	     * Instantiates a new cost item using values given by the user.
	     *
	     * @param description the cost item description
	     * @param category the cost item category
	     * @param cost the cost item cost
	     * @param date the cost item date
	     */
	    public CostItem(long userId,String description,CostItemCategory category ,double cost, Date date)
	    {
	        super();
	        this.setUserId(userId);
	        this.setDescription(description);
	        this.setCategory(category);
	        this.setCost(cost);
	        this.setDate(date);
	    }

	    /**
	     * Gets the id of the cost item.
	     *
	     * @return the id of the cost item
	     */
	    public long getId()
	    {
	        return id;
	    }

	    /**
	     * Sets the id of the cost item.
	     *
	     * @param id the new id of the cost item
	     */
	    public void setId(long id)
	    {
	        this.id = id;
	    }
	    /**
	     * Gets the user id of the cost item.
	     *
	     * @return the  user id of the cost item
	     */
	    public long getUserId() {
			return userId;
		}
	    /**
	     * Sets the user id of the cost item.
	     *
	     * @param id the user id of the cost item
	     */
		public void setUserId(long userId) {
			this.userId = userId;
		}

	    /**
	     * Gets the  cost item description.
	     *
	     * @return the cost item description
	     */
	    public String getDescription()
	    {
	        return description;
	    }

	    /**
	     * Sets the  new description of the cost item.
	     *
	     * @param description the cost item
	     */
	    public void setDescription(String description)
	    {
	        this.description = description;
	    }

	    /**
	     * Gets the cost item's cost.
	     *
	     * @return the cost item's cost
	     */
	    public double getCost()
	    {
	        return cost;
	    }

	    /**
	     * Sets the cost item's cost.
	     *
	     * @param cost the cost item's new cost
	     */
	    public void setCost(double cost)
	    {
	        this.cost = cost;
	    }

	    /**
	     * Gets the cost item's category.
	     *
	     * @return the cost item's category
	     */
	    public CostItemCategory getCategory()
	    {
	        return category;
	    }

	    /**
	     * Sets the cost item's category.
	     *
	     * @param category the cost item's new category
	     */
	    public void setCategory(CostItemCategory category)
	    {
	        this.category = category;
	    }

	    /**
	     * Gets the cost item's date.
	     *
	     * @return the cost item's date
	     */
	    public Date getDate()
	    {
	        return date;
	    }

	    /**
	     * Sets the cost item's date.
	     *
	     * @param date the cost item's new date
	     */
	    public void setDate(Date date)
	    {
	        this.date = date;
	    }

	    /**
	     * Gets the cost item's hash code.
	     *
	     * @return the cost item's hash code
	     */
	    @Override
	    public int hashCode()
	    {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((date == null) ? 0 : date.hashCode());
	        result = prime * result + (int)cost;
	        result = prime * result + (int) (id ^ (id >>> 32));
	        result = prime * result + (int) (userId ^ (userId >>> 32));
	        result = prime * result + ((category == null) ? 0 : category.hashCode());
	        result = prime * result + ((description == null) ? 0 : description.hashCode());
	        return result;
	    }
	    /**
	     * Check if cost item equal to other cost item.
	     *
	     * @param Object obj that represent the other cost item.
	     * @return true if the cost items are equal, false otherwise.
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
	        CostItem other = (CostItem) obj;
	        if (description == null)
	        {
	            if (other.getDescription() != null)
	                return false;
	        }
	        else if (!description.equals(other.getDescription()))
	            return false;
	      if (!(cost != other.getCost()))
	            return false;
	        if (id != other.id)
	            return false;
	        if(userId != other.getUserId())
	        	return false;
	        if (category == null)
	        {
	            if (other.category != null)
	                return false;
	        }
	        else if (!category.equals(other.category))
	            return false;
	        if (date == null)
	        {
	            if (other.date != null)
	                return false;
	        }
	        else if (!date.equals(other.date))
	            return false;
	        return true;
	    }
		/**
	     * Gets the string that represent the cost item
	     *
	     * @return string that represent the cost item.
	     */
	    @Override
	    public String toString() {
	        return "CostItem{" +
	                "id=" + id +
	                ", description='" + description + '\'' +
	                ", cost=" + cost +
	                ", category=" + category +
	                ", date=" + date +
	                '}';
	    }
	}
