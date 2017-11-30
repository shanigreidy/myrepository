using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace mvcproject.Models
{
    public class OnlineBook
    {
        [Key]
        public string onlineBookId { get; set; }
        public string onlineBookName { get; set; }
        public string onlineBookImage { get; set; }
        public string onlineBookCategory { get; set; }

    }
}