using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using mvcproject.Models;
using mvcproject.ModelsDB;

namespace mvcproject.Controllers
{
    public class RegisterController : Controller
    {
        // GET: Register
        public ActionResult Index()
        {
            return View("Register");
        }

        public ActionResult Registration(Customer customer)
        {
            CustomerModelDB customerModelDB = new CustomerModelDB();
            List<Customer> customers = customerModelDB.getCustomersByMatchId(customer.customerId);

            if (customers.Count > 0)
            {
                //msg: user already exists
                return View("Register",customer); // user exists
            }
            else
            {
                CurrentCustomer.customerId = customer.customerId;
                customerModelDB.addCustomer(customer);
            }

            return RedirectToAction ("Index","Customer",customer);
        }
    }
}