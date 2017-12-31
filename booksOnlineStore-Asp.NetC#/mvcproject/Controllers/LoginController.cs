using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using mvcproject.Models;
using mvcproject.ModelsDB;
using System.Web.Security;

namespace mvcproject.Controllers
{
    public class LoginController : Controller
    {
        // GET: Login
        public ActionResult Index()
        {
            return View("Login");
        }

        public ActionResult Authenticate(LoginCustomer loginCustomer)
        {
            CustomerDB customerModelDB = new CustomerDB();
            List<Customer> customers = customerModelDB.getCustomersByMatchIdAndPassword(loginCustomer);

            if (customers.Count > 0)
            {
                FormsAuthentication.SetAuthCookie("cookie", false);
                CurrentCustomer.customerId = loginCustomer.customerId;

                return RedirectToAction("Index" ,"Customer", customers[0]);
            }

            //msg: user not exists
            return View("Login",new LoginCustomer());
        }
    }
}