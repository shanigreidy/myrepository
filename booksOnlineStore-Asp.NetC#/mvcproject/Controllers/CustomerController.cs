using mvcproject.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using mvcproject.Dal;

namespace mvcproject.Controllers
{
    public class CustomerController : Controller
    {
        // GET: Customer
        public ActionResult Index(Customer customer)
        {
            return View("Home", customer);
        }

        public ActionResult PersonalDetails(Customer customer)
        {
            return View(customer);
        }
    }
}