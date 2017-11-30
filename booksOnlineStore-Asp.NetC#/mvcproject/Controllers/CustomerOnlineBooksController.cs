using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using mvcproject.Dal;
using mvcproject.ModelView;
using mvcproject.Models;
using mvcproject.ModelsDB;

namespace mvcproject.Controllers
{
    public class CustomerOnlineBooksController : Controller
    {
        private CustomerOnlineBooksDB customerOnlineBooksDB = new CustomerOnlineBooksDB();
        private CustomerOnlineBook customerOnlineBook = new CustomerOnlineBook();
        private OnlineBooksViewModel onlineBooksViewModel = new OnlineBooksViewModel();

        public ActionResult addOnlineBook(string onlineBookId, string categoryName)
        {
            customerOnlineBook.customerId = CurrentCustomer.customerId;
            customerOnlineBook.onlineBookId = onlineBookId;

            customerOnlineBooksDB.AddOnlineBook(customerOnlineBook);
            customerOnlineBook = null;

            return RedirectToAction(categoryName+"OnlineBooks", "Books");
        }

        public ActionResult OnlineBooksList()
        {
            onlineBooksViewModel.onlineBooks = customerOnlineBooksDB.getCustomerOnlineBooksList(); 

            return View(onlineBooksViewModel);
        }
    }
}