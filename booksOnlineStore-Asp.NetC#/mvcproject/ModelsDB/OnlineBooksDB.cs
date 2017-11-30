using mvcproject.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using mvcproject.Dal;

namespace mvcproject.ModelsDB
{
    public class OnlineBooksDB
    {
        private DBdal dal = new DBdal();
        public List<OnlineBook> getOnlineBooksByCategory(string categoryName)
        {
            List<OnlineBook> onlineBooks = (from book in dal.onlineBooks
                                            where book.onlineBookCategory.Equals(categoryName)
                                            select book).ToList<OnlineBook>();
            return onlineBooks;
        }
    }
}