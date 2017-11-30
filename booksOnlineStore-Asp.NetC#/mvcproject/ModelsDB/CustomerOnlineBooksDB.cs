using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using mvcproject.Dal;
using mvcproject.Models;

namespace mvcproject.ModelsDB
{
    public class CustomerOnlineBooksDB
    {
        private DBdal dal = new DBdal();

        public void AddOnlineBook(CustomerOnlineBook customerOnlineBook)
        {
            if(isOnlineBookExistsInCustomerList(customerOnlineBook) == true)
            {
                Console.WriteLine("Online book is already exists in customer list");
            }
            else
            {
                dal.customerOnlineBooks.Add(customerOnlineBook);
                dal.SaveChanges();
            }
        }

        public List<OnlineBook> getCustomerOnlineBooksList()
        {
            List<OnlineBook> onlineBooksList = new List<OnlineBook>();
            List<CustomerOnlineBook> customersOnlineBooksList = (from customerOnlineBook in dal.customerOnlineBooks
                                                                 where customerOnlineBook.customerId.Equals(CurrentCustomer.customerId)
                                                                 select customerOnlineBook).ToList<CustomerOnlineBook>();

            foreach (CustomerOnlineBook customerOnlineBook in customersOnlineBooksList)
            {
                OnlineBook onlineBook = (from book in dal.onlineBooks
                                         where book.onlineBookId.Equals(customerOnlineBook.onlineBookId)
                                         select book).FirstOrDefault();

                onlineBooksList.Add(onlineBook);
            }

            return onlineBooksList;
        }

        public bool isOnlineBookExistsInCustomerList(CustomerOnlineBook customerOnlineBook)
        {
            List<CustomerOnlineBook> onlineBooks = (from book in dal.customerOnlineBooks
                                                    where book.onlineBookId.Equals(customerOnlineBook.onlineBookId) &&
                                                    book.customerId.Equals(customerOnlineBook.customerId)
                                                    select book).ToList<CustomerOnlineBook>();

            if(onlineBooks.Count > 0)
            {
                return true;
            }

            return false;
        }
    }
}