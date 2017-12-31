using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using mvcproject.Dal;
using mvcproject.Models;

namespace mvcproject.ModelsDB
{
    public class CustomerDB
    {
        private DBdal dal = new DBdal();
        public List<Customer> getCustomersByMatchId(string customerId)
        {
            List<Customer> customersList = (from cust in dal.customers
                                            where cust.customerId.Equals(customerId)
                                            select cust).ToList<Customer>();

            return customersList;
        }
        public List<Customer> getCustomersByMatchIdAndPassword(LoginCustomer customer)
        {
            List<Customer> customersList = (from cust in dal.customers
                                            where cust.customerId.Equals(customer.customerId) &&
                                            cust.customerPassword.Equals(customer.customerPassword)
                                            select cust).ToList<Customer>();

            return customersList;
        }

        public void addCustomer(Customer customer)
        {
            dal.customers.Add(customer);
            dal.SaveChanges();
        }
    }
}