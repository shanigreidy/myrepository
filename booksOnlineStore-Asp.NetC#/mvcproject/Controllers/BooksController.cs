using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using mvcproject.Models;
using mvcproject.Dal;
using mvcproject.ModelView;
using mvcproject.ModelsDB;

namespace mvcproject.Controllers
{
    public class BooksController : Controller
    {
        private OnlineBooksViewModel onlineBooksViewModel = new OnlineBooksViewModel();
        private OnlineBooksDB onlineBooksDB = new OnlineBooksDB();

        public ActionResult OnlineBooksCatalog()
        {
            return View();
        }

        //GET: Books "AddOnlineBook", "CustomerOnlineBooks",new {onlineBookId = @book.onlineBookId}
        public ActionResult HorrorOnlineBooks()
        {
            clearOnlineBooksList();
            onlineBooksViewModel.onlineBooks = onlineBooksDB.getOnlineBooksByCategory("Horror");
            ViewData["Category"] = "Horror";

            return View("OnlineBooksList", onlineBooksViewModel);
        }

        public ActionResult MysteryOnlineBooks()
        {
            clearOnlineBooksList();
            onlineBooksViewModel.onlineBooks = onlineBooksDB.getOnlineBooksByCategory("Mystery");
            ViewData["Category"] = "Mystery";

            return View("OnlineBooksList", onlineBooksViewModel);
        }


        public ActionResult RomanceOnlineBooks()
        {
            clearOnlineBooksList();
            onlineBooksViewModel.onlineBooks = onlineBooksDB.getOnlineBooksByCategory("Romance");
            ViewData["Category"] = "Romance";

            return View("OnlineBooksList", onlineBooksViewModel);
        }

        public ActionResult FictionOnlineBooks()
        {
            clearOnlineBooksList();
            onlineBooksViewModel.onlineBooks = onlineBooksDB.getOnlineBooksByCategory("Fiction");
            ViewData["Category"] = "Fiction";

            return View("OnlineBooksList", onlineBooksViewModel);
        }

        public void clearOnlineBooksList()
        {
            if (onlineBooksViewModel.onlineBooks!= null && onlineBooksViewModel.onlineBooks.Count > 0)
            {
                onlineBooksViewModel.onlineBooks.Clear();
            }
        }
    }
}