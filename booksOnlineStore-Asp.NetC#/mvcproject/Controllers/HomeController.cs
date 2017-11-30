using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace mvcproject.Controllers
{
    public class HomeController : Controller
    {
        // GET: Home
        public ActionResult Index()
        {
            return View("HomePage");
        }

        public ActionResult Register()
        {
            return RedirectToAction("Index","Register");
        }
        public ActionResult Login()
        {
            return RedirectToAction("Index", "Login");
        }
    }
}